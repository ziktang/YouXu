package com.fl.schedule.schedule.presenter;

import com.fl.schedule.schedule.contact.CalContact;
import com.fl.schedule.schedule.contact.ScheMainContact;
import com.fl.schedule.schedule.model.bean.CalDate;
import com.fl.schedule.schedule.model.bean.Schedule;

/**
 * Created by tctctc on 2016/11/17.
 */

public class ScheMainPresenter implements CalContact.OnScheMainPresenter,ScheMainContact.Presenter {

    private ScheMainContact.DataSource mDataSource;
    private ScheMainContact.View mView;


    public ScheMainPresenter(ScheMainContact.DataSource dataSource, ScheMainContact.View view) {
        mDataSource = dataSource;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public int getLastDay() {
        return mDataSource.getLastDay();
    }

    @Override
    public void onSelected(CalDate calDate) {
        mDataSource.setCurCalDate(calDate);
        mView.changeDate(calDate);
        mView.setScheList(mDataSource.getScheList());
    }

    @Override
    public void detailSchedule(Schedule schedule) {

    }

    @Override
    public CalDate getNewDate(int position) {
        return mDataSource.getNewDate(position);
    }

    @Override
    public CalDate getTodayDate() {
        return mDataSource.getTodayDate();
    }

    @Override
    public void refreshScheList() {
        mView.setScheList(mDataSource.getScheList());
    }
}
