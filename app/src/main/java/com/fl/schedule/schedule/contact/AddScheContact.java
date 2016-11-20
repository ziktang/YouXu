package com.fl.schedule.schedule.contact;

import com.fl.schedule.base.contact.BaseModel;
import com.fl.schedule.base.contact.BasePresenter;
import com.fl.schedule.base.contact.BaseView;
import com.fl.schedule.schedule.model.bean.Schedule;

import org.greenrobot.greendao.annotation.NotNull;

import static android.R.attr.description;
import static com.fl.schedule.R.id.place;

/**
 * Created by tctctc on 2016/11/18.
 */

public class AddScheContact {
    public interface View extends BaseView {
        void show(Schedule schedule);
        void allDayShow(String[] time);
        void commitMessge(String message,int code);
        void datePicker(int year,int month,int day,int viewId);
        void dateTimePicker(int year,int month,int day,int hour,int minute,int viewId);
        void warnTimePicker(String[] warnTimes);
        void setTimeToggle(boolean on);
        void OnDoneToggle(int res);
        void Ondelete(String message,int code);
    }

    public interface Presenter extends BasePresenter {
        public static final int RESULT_OK = 0;
        public static final int RESULT_FAILURE = 1;
        void init(long id);
        void commit(String description,String startTime,String endTime,
                       String warnTime,String place, String note);
        void allDay(boolean on);
        void timeChange(String time,int viewId);
        void warnTimeChange(String time);
        void doneToogle();
        void delete();
    }

    public interface DataSource extends BaseModel {
        String[] OnAllDay(boolean on);
        int[] timeChange(String time);
        String[] warnTimeChange(String time);
        Schedule init(long id);
        boolean commit(String description,String startTime,String endTime,
                       String warnTime,String place, String note);
        int doneToogle();
        boolean delete();
    }
}
