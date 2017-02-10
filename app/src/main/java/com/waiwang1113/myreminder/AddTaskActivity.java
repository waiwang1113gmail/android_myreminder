package com.waiwang1113.myreminder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.waiwang1113.myreminder.entity.ReminderTask;
import com.waiwang1113.myreminder.utilities.AnimationUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener ,TimePickerDialog.OnTimeSetListener {

    private static String TAG = AddTaskActivity.class.getCanonicalName();
    private EditText mTextName;
    private EditText mFieldDate;
    private EditText mFieldTime;
    private CheckBox mNeedNotification;
    private LinearLayout mDateTimePanel;

    private Calendar mNotficationTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        mTextName = (EditText)findViewById(R.id.wTextName);
        mFieldDate = (EditText)findViewById(R.id.wTextDate);
        mNotficationTime = Calendar.getInstance();
        setupDate(mNotficationTime.getTime());
        mFieldDate.setOnClickListener(v-> {
            FragmentManager fm = getSupportFragmentManager();
            DatePickerFragment editNameDialogFragment = DatePickerFragment.newInstance(AddTaskActivity.this);
            editNameDialogFragment.show(fm, "fragment_edit_name");
        });
        mFieldTime = (EditText)findViewById(R.id.wTextTime);
        setupTime(mNotficationTime.getTime());
        mFieldTime.setOnClickListener(v -> {
            FragmentManager fm = getSupportFragmentManager();
            TimePickerFragment editNameDialogFragment = TimePickerFragment.newInstance(AddTaskActivity.this);
            editNameDialogFragment.show(fm, "fragment_edit_name");
        });
        mNeedNotification = (CheckBox) findViewById(R.id.wNeedNotification);
        mDateTimePanel= (LinearLayout) findViewById(R.id.wDateTimePanel);
        mNeedNotification.setOnCheckedChangeListener((v,b) ->{
            if(b){
                AnimationUtil.expand(mDateTimePanel);
            }else{
                AnimationUtil.collapse(mDateTimePanel);
            }


        });
    }

    private void setupTime(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        mFieldTime.setText(dateFormat.format(date));
    }
    private void setupDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("EEE, MMM dd yyyy");
        mFieldDate.setText(dateFormat.format(date));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.done_button_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_done) {
            addTask();
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        mNotficationTime.set(year,month,day);
        mNotficationTime.set(year,month,day);
        setupDate(mNotficationTime.getTime());
    }
    private void addTask(){
        String name = mTextName.getText().toString();
        ReminderTask task = new ReminderTask(name);
        getAppComponent().provideReminderTaskRepository().addTask(task);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        mNotficationTime.set(Calendar.HOUR,i);
        mNotficationTime.set(Calendar.MINUTE,i1);
        setupTime(mNotficationTime.getTime());
        Log.i(TAG,String.format("Time select: %d:%d",i,i1));
    }

    public static class DatePickerFragment extends DialogFragment{
        private DatePickerDialog.OnDateSetListener listener;
        public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener){
            DatePickerFragment newInstance = new DatePickerFragment();
            newInstance.listener = listener;
            return newInstance;
        }
        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), listener, year, month, day);
        }

    }
    public static class TimePickerFragment extends DialogFragment {
        private TimePickerDialog.OnTimeSetListener listener;
        public static TimePickerFragment newInstance(TimePickerDialog.OnTimeSetListener listener){
            TimePickerFragment instance = new TimePickerFragment();
            instance.listener=listener;
            return instance;
        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), listener, hour, minute,
                    true);
        }


    }
}
