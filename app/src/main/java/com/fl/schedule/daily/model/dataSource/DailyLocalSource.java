package com.fl.schedule.daily.model.dataSource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fl.schedule.app.MyApplication;
import com.fl.schedule.base.model.MyDataBaseHelper;
import com.fl.schedule.daily.model.bean.Daily;
import com.fl.schedule.daily.model.bean.DailyDao;
import com.fl.schedule.daily.model.bean.DaoMaster;
import com.fl.schedule.daily.model.bean.DaoSession;

/**
 * Created by tctctc on 2016/11/20.
 */

public class DailyLocalSource {
    private Context mContext;
    private static final String dbName = "Daily";
    private static DailyLocalSource sLocalSource;
    private DailyDao mDailyDao;

    private DailyLocalSource(){
        MyDataBaseHelper helper = new MyDataBaseHelper(MyApplication.getContext(),dbName);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        DaoSession session = daoMaster.newSession();
        mDailyDao = session.getDailyDao();
    }
}
