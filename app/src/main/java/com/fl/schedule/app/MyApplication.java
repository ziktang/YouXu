package com.fl.schedule.app;

import android.app.Application;
import android.content.Context;
import com.fl.schedule.utils.ImageLoadUtils;

import cn.smssdk.SMSSDK;
/**
 * Created by tctctc on 2016/11/6.
 */

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        SMSSDK.initSDK(this, "18f0edd6bc6ff", "b55925c99bfea6053ab5bcffc3483e25");
        ImageLoadUtils.init(context);
    }

    public static Context getContext() {
        return context;
    }
}
