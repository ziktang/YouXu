package com.fl.schedule.schedule.presenter;

import com.fl.schedule.schedule.contact.CalContact;
import com.fl.schedule.schedule.model.bean.CalDate;

import java.util.List;

/**
 * Created by tctctc on 2016/11/17.
 */

public class CalPresenter implements CalContact.Presenter{
    private CalContact.OnScheMainPresenter mMainPresenter;
    private CalContact.DataSource mDataSource;
    private CalContact.View mView;

    public CalPresenter(CalContact.OnScheMainPresenter mainPresenter, CalContact.DataSource dataSource, CalContact.View view) {
        mMainPresenter = mainPresenter;
        mDataSource = dataSource;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void OnItemSelected(CalDate calDate){
        mDataSource.setCurrentDate(calDate);
        mMainPresenter.onSelected(mDataSource.getCurrentDate());
    }

    @Override
    public void initData() {
        List<CalDate> calDates = mDataSource.getInitData(mMainPresenter.getLastDay());
        mView.setCalDates(calDates);
    }

    @Override
    public void refresh() {
        mView.pageRefresh(mDataSource.refresh(mMainPresenter.getLastDay()));
    }

    @Override
    public void OnPageSelect() {
        OnItemSelected(mDataSource.getCurrentDate());
    }
}
