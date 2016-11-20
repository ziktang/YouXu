package com.fl.schedule.schedule.model.dataSource;

import com.fl.schedule.schedule.contact.CalContact;
import com.fl.schedule.schedule.model.bean.CalDate;
import com.fl.schedule.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.fl.schedule.R.id.day;

/**
 * Created by tctctc on 2016/11/17.
 */

public class CalDataSource implements CalContact.DataSource {

    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private CalDate mCurCarDate;
    private int mCurDaysOfMonth;
    private int mCurWeekFirstDay;
    private ArrayList<CalDate> mCalDateList;

    public CalDataSource(CalDate calDate) {
        mCurCarDate = calDate;
        mCalDateList = new ArrayList<>();
    }


    private List<CalDate> initData(int lastDay) {
        mCalDateList.clear();
        mCurDaysOfMonth = DateUtils.getMonthDays(mCurCarDate.getYear(), mCurCarDate.getMonth());
        if (mCurCarDate.isToday()) {
            mCurCarDate.setDay(Calendar.getInstance().get(Calendar.DATE));
        } else {
            lastDay = lastDay > mCurDaysOfMonth ? mCurDaysOfMonth : lastDay;
            mCurCarDate.setDay(lastDay);
        }

        mCurWeekFirstDay = DateUtils.getWeekOfMonthDay(mCurCarDate.getYear(), mCurCarDate.getMonth(), 1);
        for (int i = 1; i <= ROWS * COLUMNS; i++) {
            //上个月尾巴日期
            if (i < mCurWeekFirstDay) {
                mCalDateList.add(new CalDate(false));
            }
            //这个月日期
            else if (i >= mCurWeekFirstDay && i < mCurWeekFirstDay + mCurDaysOfMonth) {
                CalDate calDate = new CalDate(mCurCarDate.getYear(), mCurCarDate.getMonth(), i - mCurWeekFirstDay + 1);
                int nowDate = i - mCurWeekFirstDay + 1;
                if (nowDate == mCurCarDate.getDay()) {
                    calDate.setToday(mCurCarDate.isToday());
                    calDate.setSelectday(true);
                }
                mCalDateList.add(calDate);
            }
            //下个月头部日期
            else {
                mCalDateList.add(new CalDate(false));
            }
        }

        return mCalDateList;
    }

    @Override
    public List<CalDate> getInitData(int lastDay) {
        return initData(lastDay);
    }

    @Override
    public CalDate getCurrentDate() {
        return mCurCarDate;
    }

    @Override
    public void setCurrentDate(CalDate calDate) {
        mCurCarDate = calDate;
    }


    @Override
    public int[] refresh(int lastDay) {
        int delete = mCurCarDate.getDay() + mCurWeekFirstDay - 2;
        if (mCurCarDate.isToday()) {
            mCurCarDate.setDay(Calendar.getInstance().get(Calendar.DATE));
        } else {
            int day = lastDay;
            day = day > mCurDaysOfMonth ? mCurDaysOfMonth : day;
            mCurCarDate.setDay(day);
        }
        int add = mCurCarDate.getDay() + mCurWeekFirstDay - 2;
        if (mCalDateList.size() > 0) {
            mCalDateList.get(add).setSelectday(true);
            if (delete != add)
                mCalDateList.get(delete).setSelectday(false);
        }
        int[] update = {add, delete};
        return update;
    }
}
