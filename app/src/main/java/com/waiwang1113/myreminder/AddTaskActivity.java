package com.waiwang1113.myreminder;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.waiwang1113.myreminder.entity.ReminderTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends BaseActivity {

    private static String TAG = AddTaskActivity.class.getCanonicalName();
    private EditText mTextName;
    private EditText mFieldDate;
    private EditText mFieldTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        mTextName = (EditText)findViewById(R.id.wTextName);
        mFieldDate = (EditText)findViewById(R.id.wTextDate);
        setupDate(new Date());
        mFieldTime = (EditText)findViewById(R.id.wTextTime);
        setupTime(new Date());
    }

    private void setupTime(Date date) {
        int hour = 0;
        int minute = 0;
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

    private void addTask(){
        String name = mTextName.getText().toString();
        ReminderTask task = new ReminderTask(name);
        getAppComponent().provideReminderTaskRepository().addTask(task);
    }

}
