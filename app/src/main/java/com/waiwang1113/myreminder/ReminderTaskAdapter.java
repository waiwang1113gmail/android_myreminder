package com.waiwang1113.myreminder;

import java.util.ArrayList;

import com.waiwang1113.myreminder.entity.ReminderTask;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;

/**
 * Custom Adapter for handling reminder task items management
 * @author wanwe17
 *
 */

public class ReminderTaskAdapter extends BaseAdapter{
	private static String TAG = ReminderTaskAdapter.class.getCanonicalName();
	private ArrayList<ReminderTask> items;
	
	private Context context;
	 
	public ReminderTaskAdapter(Context context) {
		this.context =context;
		this.items=new ArrayList<ReminderTask>();
		this.items.add(new ReminderTask("remove the gabage."));
		this.items.add(new ReminderTask("clean oven"));
 
		
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		Log.i(TAG,"Creating view for position "+position);
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.tmpl_main_list_item, null);
        }
        ReminderTask item= items.get(position);
        if(item!=null){
			CheckBox mRadioButton = (CheckBox) v.findViewById(R.id.listItemText);
        	mRadioButton.setText(item.getName());
        }
        return v;
	}

	@Override
	public int getCount() {
		return this.items.size();
	}

	@Override
	public Object getItem(int position) { 
		return items.get(position);
	}

	@Override
	public long getItemId(int position) { 
		return items.get(position).hashCode();
	}
}
