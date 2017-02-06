package com.waiwang1113.myreminder;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.waiwang1113.myreminder.entity.ReminderTask;

public class AddTaskActivity extends BaseActivity {

    private static String TAG = AddTaskActivity.class.getCanonicalName();
    private EditText mTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        setupToolbar();
        mTextName = (EditText)findViewById(R.id.wTextName);
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
