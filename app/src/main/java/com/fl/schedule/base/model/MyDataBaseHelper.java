package com.fl.schedule.base.model;

import android.content.Context;

import com.fl.schedule.daily.model.bean.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Created by tctctc on 2016/11/20.
 */

public class MyDataBaseHelper extends DaoMaster.DevOpenHelper {
    public MyDataBaseHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

    }
}
