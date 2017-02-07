package com.waiwang1113.myreminder.injection;

import com.waiwang1113.myreminder.repository.ReminderTaskRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provider for dependency injection
 * Created by wanwe17 on 2/2/2017.
 */
@Module
public class AppModule {
    @Provides@Singleton
    ReminderTaskRepository provideReminderTaskRepository(){
        return new ReminderTaskRepository();
    }

}
