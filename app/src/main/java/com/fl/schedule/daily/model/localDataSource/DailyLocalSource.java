package com.fl.schedule.daily.model.localDataSource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fl.schedule.app.MyApplication;
import com.fl.schedule.base.model.MyDataBaseHelper;
import com.fl.schedule.daily.model.bean.Daily;
import com.fl.schedule.daily.model.bean.DailyDao;
import com.fl.schedule.daily.model.bean.DaoMaster;
import com.fl.schedule.daily.model.bean.DaoSession;
import com.fl.schedule.home.model.bean.UserConfig;
import com.fl.schedule.schedule.model.bean.ScheduleDao;

import java.util.List;

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

    public static DailyLocalSource getInstance(){
        if (sLocalSource == null){
            sLocalSource = new DailyLocalSource();
        }
        return sLocalSource;
    }

    public Daily get(long id){
        return mDailyDao.queryBuilder().where(DailyDao.Properties.Id.eq(id)
                , DailyDao.Properties.UserId.eq(UserConfig.getInstance().getUserInfo().getId())).build().unique();

    }

    public List<Daily> getListByDate(String date){
        return mDailyDao.queryBuilder().where(DailyDao.Properties.Date.eq(date)
                , DailyDao.Properties.UserId.eq(UserConfig.getInstance().getUserInfo().getId())).orderDesc(DailyDao.Properties.Time).build().list();
    }

    public boolean add(Daily daily){
        mDailyDao.insert(daily);
        return true;
    }

    public boolean delete(long id){
        mDailyDao.deleteByKey(id);
        return true;
    }

    public boolean update(Daily daily){
        mDailyDao.update(daily);
        return true;
    }

}
