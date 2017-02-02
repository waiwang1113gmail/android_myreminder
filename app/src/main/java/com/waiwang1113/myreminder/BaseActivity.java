package com.waiwang1113.myreminder;

import android.app.Activity;

import com.waiwang1113.myreminder.injection.AppComponent;
import com.waiwang1113.myreminder.injection.AppModule;
import com.waiwang1113.myreminder.injection.DaggerAppComponent;

/**
 * This class contains all helper methods that can be used by the sub classes
 * @author wanwe17
 *
 */
public class BaseActivity extends Activity {
    protected AppComponent getAppComponent(){
        return DaggerAppComponent.builder().appModule(new AppModule()).build();
    }
}
