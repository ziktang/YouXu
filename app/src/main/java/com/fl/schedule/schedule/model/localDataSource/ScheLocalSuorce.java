package com.fl.schedule.schedule.model.localDataSource;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.fl.schedule.app.MyApplication;
import com.fl.schedule.daily.model.bean.DaoMaster;
import com.fl.schedule.daily.model.bean.DaoSession;
import com.fl.schedule.home.model.bean.UserConfig;
import com.fl.schedule.schedule.model.bean.Schedule;
import com.fl.schedule.schedule.model.bean.ScheduleDao;

import org.greenrobot.greendao.query.QueryBuilder;
import java.util.ArrayList;

/**
 * Created by tctctc on 2016/11/17.
 */

public class ScheLocalSuorce {
    private static ScheLocalSuorce sDataSuorce;
    private final static String dbname = "Schedule";
    private Context mContext;
    private ScheduleDao mScheduleDao;

    private ScheLocalSuorce() {
        mContext = MyApplication.getContext();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, dbname, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        mScheduleDao = daoSession.getScheduleDao();
    }

    public static ScheLocalSuorce getInstance() {
        if (sDataSuorce == null) {
            sDataSuorce = new ScheLocalSuorce();
        }
        return sDataSuorce;
    }

    public Schedule get(long id) {
        QueryBuilder qb = mScheduleDao.queryBuilder();
        return (Schedule) qb.where(ScheduleDao.Properties.Id.eq(id))
                .build().unique();
    }

    public ArrayList<Schedule> getListByDate(String startDate) {
        QueryBuilder qb = mScheduleDao.queryBuilder();
        return (ArrayList<Schedule>) qb.where(ScheduleDao.Properties.StartDate.eq(startDate), ScheduleDao.Properties.UserId.eq(UserConfig.getInstance().getUserInfo().getId())).orderAsc(ScheduleDao.Properties.StartTime).list();
    }

    public boolean delete(long id) {
        mScheduleDao.deleteByKey(id);
        return true;
    }

    public boolean add(Schedule schedule) {
        mScheduleDao.insert(schedule);
        return true;
    }

    public boolean update(Schedule schedule) {
        mScheduleDao.update(schedule);
        return true;
    }
}
