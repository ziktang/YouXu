package com.fl.schedule.utils;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

import static android.R.attr.data;

/**
 * Created by tctctc on 2016/11/8.
 */

public class DateUtils {
    static String[] weeks = {"日", "一", "二", "三", "四", "五", "六"};
    static int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static boolean isToday(Date date) {
        Date date1 = new Date();
//        Log.i("www","data-year:"+date.getYear());
//        Log.i("www","data-month:"+date.getMonth());
//        Log.i("www","data-day:"+date.getDate());
//        Log.i("www","data1-year:"+date1.getYear());
//        Log.i("www","data1-month:"+date1.getMonth());
//        Log.i("www","data1-day:"+date1.getDate());
        if (date1.getYear()+1900 != date.getYear()) {
            return false;
        }
        if (date1.getMonth() != date.getMonth()) {
            return false;
        }
        if (date1.getDate() != date.getDate()) {
            return false;
        }
        return true;
    }

    public static String getChineseWeek(Date date) {
        int i = getWeekOfMonthDay(date.getYear(), date.getMonth()+1, date.getDate());
        return weeks[i-1];
    }

    public static int getMonthDays(int year, int month) {
        if (month > 12) {
            month = 1;
            year += 1;
        } else if (month < 1) {
            month = 12;
            year -= 1;
        }
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            monthDays[1] = 28;
        }
        return monthDays[month - 1];
    }

    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    //        public static int getWeekOfMonthFirstDay(){
//            //获取当前月第一天：
//            Calendar calendar = Calendar.getInstance();
//            calendar.add(Calendar.MONTH, 0);
//            calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
//            return calendar.get(Calendar.DAY_OF_WEEK);
//        }
//        public static int getWeekOfMonthEndDay(){
//            //获取当前月最后一天：
//            Calendar calendar = Calendar.getInstance();
//            calendar.add(Calendar.MONTH, 0);
//            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//            return calendar.get(Calendar.DAY_OF_WEEK);
//        }
    //获取指定日期是星期几：
    public static int getWeekOfMonthDay(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getCurrentMonthDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getWeekDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR);
    }

    public static int getMinute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }
}
