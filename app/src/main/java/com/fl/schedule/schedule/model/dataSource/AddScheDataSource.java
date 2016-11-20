package com.fl.schedule.schedule.model.dataSource;

import android.util.Log;

import com.fl.schedule.home.model.bean.UserConfig;
import com.fl.schedule.schedule.contact.AddScheContact;
import com.fl.schedule.schedule.model.bean.Schedule;
import com.fl.schedule.utils.CustomUUID;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tctctc on 2016/11/18.
 */

public class AddScheDataSource implements AddScheContact.DataSource {

    private int mType = 1;
    private Schedule mSchedule;
    private boolean isAllDayOn = true;
    private String initDate;

    @Override
    public String[] OnAllDay(boolean on) {
        if (on) {
            return new String[]{initDate, initDate};
        } else {
            if (mType == 0) {
                return new String[]{mSchedule.getStartTime() + " 00:00", mSchedule.getEndTime() + " 00:00"};
            }
            return new String[]{mSchedule.getStartTime(), mSchedule.getEndTime()};
        }
    }

    public AddScheDataSource(String initDate) {
        this.initDate = initDate;
    }

    @Override
    public int[] timeChange(String time) {
        int[] s = new int[5];
        s[0] = Integer.parseInt(time.substring(0, 4));
        s[1] = Integer.parseInt(time.substring(5, 7));
        s[2] = Integer.parseInt(time.substring(8, 10));
        if (!isAllDayOn) {
            s[3] = Integer.parseInt(time.substring(11, 13));
            s[4] = Integer.parseInt(time.substring(14, 16));
        }
        return s;
    }

    @Override
    public String[] warnTimeChange(String time) {
        return new String[]{"不提醒", "5分钟前", "10分钟前", "15分钟前", "30分钟前", "1小时前", "3小时前", "一天前"};
    }


    @Override
    public Schedule init(long id) {
        Log.i("qqq", "id:" + id);
        if (id == 0) {
            mType = 0;
        } else {
            mType = 1;
        }
        return dateTimeInit(id);
    }

    @Override
    public boolean commit(String description, String startTime, String endTime,
                          String warnTime, String place, String note) {
        mSchedule.setDescription(description);
        mSchedule.setStartDate(startTime.substring(0, 10));
        mSchedule.setStartTime(startTime);
        mSchedule.setEndTime(endTime);
        mSchedule.setWarnTime(warnTime);
        mSchedule.setPlace(place);
        mSchedule.setNote(note);


        if (mType == 0) {
            ScheDataSource.getInstance().add(mSchedule);
        } else {
            ScheDataSource.getInstance().update(mSchedule);
        }
        return true;
    }

    @Override
    public int doneToogle() {
        if (mSchedule.getStatus() == 1) {
            mSchedule.setStatus(0);
        } else if (mSchedule.getStatus() == 0) {
            mSchedule.setStatus(1);
        }
        return mSchedule.getStatus();
    }

    @Override
    public boolean delete() {
        return ScheDataSource.getInstance().delete(mSchedule.getId());
    }

    private Schedule dateTimeInit(long id) {
        Log.i("qqq", "mType:" + mType);
        if (mType == 1) {
            mSchedule = ScheDataSource.getInstance().get(id);
        } else {
            mSchedule = new Schedule(CustomUUID.getInstance().generate());
            mSchedule.setUserId(UserConfig.getInstance().getUserInfo().getId());
            mSchedule.setStartTime(initDate);
            mSchedule.setEndTime(initDate);
        }
        return mSchedule;
    }
}
