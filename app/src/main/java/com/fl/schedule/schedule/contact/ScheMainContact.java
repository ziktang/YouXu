package com.fl.schedule.schedule.contact;

import com.fl.schedule.base.contact.BaseModel;
import com.fl.schedule.base.contact.BasePresenter;
import com.fl.schedule.base.contact.BaseView;
import com.fl.schedule.home.model.bean.WeatherInfo;
import com.fl.schedule.schedule.model.bean.CalDate;
import com.fl.schedule.schedule.model.bean.Schedule;
import java.util.List;

/**
 * Created by tctctc on 2016/11/17.
 */

public interface ScheMainContact {
    interface View extends BaseView {
        void setHeadWeather(WeatherInfo weather);
        void setScheList(List<Schedule> scheList);
        void changeDate(CalDate calDate);
    }

    interface Presenter extends BasePresenter {
        void detailSchedule(Schedule schedule);
        CalDate getNewDate(int position);
        CalDate getTodayDate();
        void refreshScheList();
    }

    interface DataSource extends BaseModel {
        List<Schedule> getScheList();
        int getLastDay();
        CalDate getNewDate(int position);
        CalDate getTodayDate();
        void setCurCalDate(CalDate calDate);
    }
}
