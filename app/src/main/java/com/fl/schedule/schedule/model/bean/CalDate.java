package com.fl.schedule.schedule.model.bean;

import com.fl.schedule.utils.ChinaDate;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by tctctc on 2016/11/8.
 */

public class CalDate implements Serializable {
    Date mDate;
    boolean isDate = true;
    private String lunarStr;
    private boolean isSelectday;
    boolean isToday;

    public String toDateString(){
        return mDate.getYear()+"-"+(mDate.getMonth()+1)+"-"+mDate.getDate();
    }

    public Date getDate() {
        return mDate;
    }

    public void setDay(int day) {
        mDate.setDate(day);
    }

    public boolean isToday() {
        return isToday;
    }

    public void setToday(boolean today) {
        isToday = today;
    }

    public CalDate(boolean isDate) {
        this.isDate = isDate;
    }


    public boolean isDate() {
        return isDate;
    }

    public int getYear() {
        return mDate.getYear();
    }

    public int getMonth() {
        return mDate.getMonth() + 1;
    }

    public int getDay() {
        return mDate.getDate();
    }

    public boolean isSelectday() {
        return isSelectday;
    }

    public void setSelectday(boolean today) {
        isSelectday = today;
    }

    public String getLunarStr() {
        return lunarStr;
    }

    public CalDate(int year, int month, int day) {
        mDate = new Date();
        mDate.setYear(year);
        mDate.setMonth(month - 1);
        mDate.setDate(day);
        try {
            lunarStr = ChinaDate.oneDay(year, month, day);
            lunarStr = lunarStr.substring(lunarStr.length() - 2, lunarStr.length());
        } catch (Exception e) {
            lunarStr = "";
        }
    }

    @Override
    public String toString() {
        return "今天是" + mDate.getYear() + "年" + (mDate.getMonth() + 1) + "月" + mDate.getDate() + "日  " + lunarStr;
    }
}
