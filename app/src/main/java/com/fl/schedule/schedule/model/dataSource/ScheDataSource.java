package com.fl.schedule.schedule.model.dataSource;

import android.content.Context;

import com.fl.schedule.app.MyApplication;
import com.fl.schedule.schedule.model.localDataSource.ScheLocalSuorce;
import com.fl.schedule.schedule.model.bean.Schedule;

import java.util.ArrayList;

/**
 * Created by tctctc on 2016/11/18.
 */

public class ScheDataSource {

    private ScheLocalSuorce mScheLocalSuorce;
    private static Context mContext;
    private static ScheDataSource sDataSuorce;

    private ScheDataSource() {
        mScheLocalSuorce = ScheLocalSuorce.getInstance();
    }

    public static ScheDataSource getInstance() {
        mContext = MyApplication.getContext();
        if (sDataSuorce == null) {
            sDataSuorce = new ScheDataSource();
        }
        return sDataSuorce;
    }

    public Schedule get(long id) {
       return mScheLocalSuorce.get(id);
    }

    public ArrayList<Schedule> getListByDate(String startDate) {
        return mScheLocalSuorce.getListByDate(startDate);
    }

    public boolean delete(long id) {
        mScheLocalSuorce.delete(id);
        return true;
    }

    public boolean add(Schedule schedule) {
        mScheLocalSuorce.add(schedule);
        return true;
    }

    public boolean update(Schedule schedule) {
        mScheLocalSuorce.update(schedule);
        return true;
    }
}
