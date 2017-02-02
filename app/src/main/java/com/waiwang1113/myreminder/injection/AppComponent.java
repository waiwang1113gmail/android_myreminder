package com.waiwang1113.myreminder.injection;

import com.waiwang1113.myreminder.repository.ReminderTaskRepository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wanwe17 on 2/2/2017.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    ReminderTaskRepository provideReminderTaskRepository();
}
