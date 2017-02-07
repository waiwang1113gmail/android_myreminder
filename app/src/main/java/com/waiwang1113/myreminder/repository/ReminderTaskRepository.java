package com.waiwang1113.myreminder.repository;

import android.util.Log;

import com.waiwang1113.myreminder.entity.ReminderTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface
 * Created by wanwe17 on 2/2/2017.
 */

public class ReminderTaskRepository {

    private static String TAG = ReminderTaskRepository.class.getCanonicalName();
    private List<ReminderTask> list;
    public ReminderTaskRepository(){
        Log.d(TAG,"Constructing new ReminderTaskRepository");
        list=new ArrayList<>();
        list.add(new ReminderTask("Pay parking tickets"));
        list.add(new ReminderTask("buy oranges and card boards"));
    }
    public void addTask(ReminderTask task){
        Log.d(TAG,"Adding new task: " + task);
        list.add(task);
    }
    public List<ReminderTask> getAllTasks(){
        Log.d(TAG,"returning " + this.list.toString());
        return this.list;
    }
    public void removeTask(ReminderTask task){
        list.remove(task);
    }
}
