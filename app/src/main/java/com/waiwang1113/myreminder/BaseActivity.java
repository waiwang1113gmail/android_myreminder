package com.waiwang1113.myreminder;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.waiwang1113.myreminder.injection.AppComponent;
import com.waiwang1113.myreminder.injection.AppModule;
import com.waiwang1113.myreminder.injection.DaggerAppComponent;

/**
 * This class contains all helper methods that can be used by the sub classes
 * @author wanwe17
 *
 */
public class BaseActivity extends Activity {
    private static String TAG = BaseActivity.class.getCanonicalName();
    private static AppComponent APP_COMPONENT;
    protected Toolbar mToolbar;

    protected AppComponent getAppComponent(){
        if(APP_COMPONENT == null){
            APP_COMPONENT = DaggerAppComponent.builder().appModule(new AppModule()).build();
        }
        return APP_COMPONENT;
    }
    protected void setupToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(mToolbar);
        getActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
}
