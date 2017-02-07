package com.waiwang1113.myreminder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.waiwang1113.myreminder.entity.ReminderTask;
import com.waiwang1113.myreminder.repository.ReminderTaskRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * List All current tasks that have not been done.
 */
public class MainActivity extends BaseActivity {

    private static String TAG = BaseActivity.class.getCanonicalName();

    private ReminderTaskAdapter mAdapter;

    private List<ReminderTask> mSelectedTask;
    public MainActivity(){
        mSelectedTask = new ArrayList<>();
    }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate method");
		setContentView(R.layout.activity_main);
        setupToolbar();
        ListView reminderList = (ListView) this.findViewById(R.id.taskList);
        mAdapter =new ReminderTaskAdapter(this,this.getAppComponent().provideReminderTaskRepository());
		reminderList.setAdapter(mAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        if(this.mSelectedTask.size()==0)
		    getMenuInflater().inflate(R.menu.main, menu);
        else
            getMenuInflater().inflate(R.menu.main_delete, menu);
		return true;
	}
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume method");
        mAdapter.notifyDataSetChanged();
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) { 
		int id = item.getItemId();
		if (id == R.id.action_add) {
            Log.d(TAG,"add action menu item selected");
			Intent newIntent = new Intent(this,AddTaskActivity.class);
			startActivity(newIntent);
			return true;
		} else if(id == R.id.action_delete){
            Log.d(TAG,"delete action menu item selected");
            for(ReminderTask task:this.mSelectedTask){
                getAppComponent().provideReminderTaskRepository().removeTask(task);
            }
            //Reset and update action menu item
            this.mSelectedTask.removeAll(mSelectedTask);
            invalidateOptionsMenu();
            mAdapter.notifyDataSetChanged();
        }
		return super.onOptionsItemSelected(item);
	}

    /**
     * Custom Adapter for handling reminder task items management
     * @author wanwe17
     *
     */

    class ReminderTaskAdapter extends BaseAdapter {
        private String TAG = ReminderTaskAdapter.class.getCanonicalName();

        private Context context;

        private ReminderTaskRepository repository;

        ReminderTaskAdapter(Context context, ReminderTaskRepository repository) {
            this.context =context;
            this.repository=repository;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            View v = vi.inflate(R.layout.tmpl_main_list_item,null);
            ReminderTask item= repository.getAllTasks().get(position);
            Log.i(TAG,"Creating list item: "+item);
            if(item!=null){
                CheckBox mRadioButton = (CheckBox) v.findViewById(R.id.checkBox);
                TextView content = (TextView) v.findViewById(R.id.task_name);
                content.setText(item.getName());

                mRadioButton.setOnCheckedChangeListener(new CustomOnCheckedChangeListener(item));
            }
            return v;
        }

        @Override
        public int getCount() {
            return repository.getAllTasks().size();
        }

        @Override
        public Object getItem(int position) {
            return repository.getAllTasks().get(position);
        }

        @Override
        public long getItemId(int position) {
            return repository.getAllTasks().get(position).hashCode();
        }
    }

    class CustomOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener{
        private ReminderTask task;

        public CustomOnCheckedChangeListener( ReminderTask task){
            this.task = task;
        }
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            //Keep tracking number of tasks have been selected
            //If number of selected tasks greater than zero, action menu should change
            //to remove menu item, otherwise it should shows add menu item.
            Log.d(TAG,"task has been selected: "+compoundButton +" "+b);
            if(b){
                mSelectedTask.add(task);
            }else{
                mSelectedTask.remove(task);

            }
            invalidateOptionsMenu();
        }
    }

}
