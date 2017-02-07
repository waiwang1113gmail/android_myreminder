package com.waiwang1113.myreminder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toolbar;

public class MainActivity extends BaseActivity {

    private static String TAG = BaseActivity.class.getCanonicalName();

    private ReminderTaskAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate method");
		setContentView(R.layout.activity_main);
        setupToolbar();
        ListView reminderList = (ListView) this.findViewById(R.id.taskList);
        adapter=new ReminderTaskAdapter(this,this.getAppComponent().provideReminderTaskRepository());
		reminderList.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume method");
        adapter.notifyDataSetChanged();
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) { 
		int id = item.getItemId();
		if (id == R.id.action_add) {
            Log.d(TAG,"add action menu item selected");
			Intent newIntent = new Intent(this,AddTaskActivity.class);
			startActivity(newIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
