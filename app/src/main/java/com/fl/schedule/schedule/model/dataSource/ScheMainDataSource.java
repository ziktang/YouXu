package com.fl.schedule.schedule.model.dataSource;

import com.fl.schedule.schedule.contact.ScheMainContact;
import com.fl.schedule.schedule.model.bean.CalDate;
import com.fl.schedule.schedule.model.bean.Schedule;
import com.fl.schedule.schedule.model.dataSource.ScheDataSource;

import java.util.Calendar;
import java.util.List;

/**
 * Created by tctctc on 2016/11/17.
 */

public class ScheMainDataSource implements ScheMainContact.DataSource{


    private CalDate mTodayDate;
    private static final int POSITION = 60;
    private CalDate mCurCalDate;

    public ScheMainDataSource() {
        initDate();
    }

    @Override
    public List<Schedule> getScheList() {
        return ScheDataSource.getInstance().getListByDate(mCurCalDate.toDateString());
    }

    @Override
    public int getLastDay() {
        return mCurCalDate.getDay();
    }

    public void setCurCalDate(CalDate curCalDate) {
        mCurCalDate = curCalDate;
    }

    @Override
    public CalDate getNewDate(int position) {
        CalDate calDate;
        if (position == POSITION) {
            calDate = getNewDate(mTodayDate.getMonth() + position - POSITION, true);
        } else {
            calDate = getNewDate(mTodayDate.getMonth() + position - POSITION, false);
        }
        return calDate;
    }

    private void initDate() {
        Calendar calendar = Calendar.getInstance();
        mTodayDate = new CalDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DATE));
        mCurCalDate = mTodayDate;
    }

    public CalDate getNewDate(int month, boolean isToday) {
        CalDate calDate = new CalDate(mTodayDate.getYear(), month, 1);
        if (isToday)
            calDate.setToday(true);
        return calDate;
    }

    public CalDate getTodayDate(){
        return mTodayDate;
    }
}
