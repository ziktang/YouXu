package com.fl.schedule.Schedule.model;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaDataSource;

import com.fl.schedule.Schedule.bean.Schedule;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tctctc on 2016/11/17.
 */

public class ScheduleLocalDataSuorce {
    private static ScheduleLocalDataSuorce sDataSuorce;
    private final static String dbname = "Schedule";
    private ScheduleLocalDataSuorce() {
    }

    public ScheduleLocalDataSuorce getInstance() {
        if (sDataSuorce == null) {
            sDataSuorce = new ScheduleLocalDataSuorce();
        }
        return sDataSuorce;
    }

    public Schedule get(int id) {

        return null;
    }

    public ArrayList<Schedule> getListByDate(String startTime) {

        return null;
    }

    public boolean delete(int id) {

        return true;
    }

    public boolean add(Schedule schedule) {

        return true;
    }

    public boolean update(Schedule schedule) {

        return true;
    }

}
