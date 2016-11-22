package com.fl.schedule.daily.presenter;

import com.fl.schedule.daily.contact.DailyMainContact;

/**
 * Created by tctctc on 2016/11/21.
 */

public class DailyMainPresenter implements DailyMainContact.Presenter {
    private DailyMainContact.View mView;
    private DailyMainContact.DataSource mDataSource;

    public DailyMainPresenter(DailyMainContact.View view, DailyMainContact.DataSource dataSource) {
        mView = view;
        mDataSource = dataSource;
        mView.setPresenter(this);
    }

    @Override
    public void init() {
        mView.initView();
        mView.showList(mDataSource.getDailyList());
        mView.initData(mDataSource.getDate());
    }

    @Override
    public void dateChange(String date) {

    }
}
