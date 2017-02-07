package com.waiwang1113.myreminder;

import com.waiwang1113.myreminder.entity.ReminderTask;
import com.waiwang1113.myreminder.repository.ReminderTaskRepository;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Custom Adapter for handling reminder task items management
 * @author wanwe17
 *
 */

class ReminderTaskAdapter extends BaseAdapter{
	private static String TAG = ReminderTaskAdapter.class.getCanonicalName();
	
	private Context context;

	private ReminderTaskRepository repository;

	ReminderTaskAdapter(Context context, ReminderTaskRepository repository) {
		this.context =context;
		this.repository=repository;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		Log.i(TAG,"Creating view for position "+position);
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.tmpl_main_list_item,null);
        }
        ReminderTask item= repository.getAllTasks().get(position);
        if(item!=null){
			CheckBox mRadioButton = (CheckBox) v.findViewById(R.id.checkBox);
			TextView content = (TextView) v.findViewById(R.id.task_name);
			content.setText(item.getName());
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
