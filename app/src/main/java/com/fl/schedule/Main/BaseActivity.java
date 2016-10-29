package com.fl.schedule.Main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by tctctc on 2016/10/26.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
