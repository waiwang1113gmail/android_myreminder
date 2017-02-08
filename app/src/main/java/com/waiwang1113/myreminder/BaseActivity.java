package com.waiwang1113.myreminder;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

import com.waiwang1113.myreminder.injection.AppComponent;
import com.waiwang1113.myreminder.injection.AppModule;
import com.waiwang1113.myreminder.injection.DaggerAppComponent;

/**
 * This class contains all helper methods that can be used by the sub classes
 * @author wanwe17
 *
 */
public class BaseActivity extends AppCompatActivity {
    private static String TAG = BaseActivity.class.getCanonicalName();
    private static AppComponent APP_COMPONENT;


    protected AppComponent getAppComponent(){
        if(APP_COMPONENT == null){
            APP_COMPONENT = DaggerAppComponent.builder().appModule(new AppModule()).build();
        }
        return APP_COMPONENT;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
}
