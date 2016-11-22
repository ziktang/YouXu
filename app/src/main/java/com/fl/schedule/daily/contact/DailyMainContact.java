package com.fl.schedule.daily.contact;

import com.fl.schedule.base.contact.BaseModel;
import com.fl.schedule.base.contact.BasePresenter;
import com.fl.schedule.base.contact.BaseView;
import com.fl.schedule.daily.model.bean.Daily;

import java.util.List;

/**
 * Created by tctctc on 2016/11/21.
 */

public class DailyMainContact {
    public interface View extends BaseView{
        void initView();
        void showList(List<Daily> dailyList);
        void initData(String date);

    }

    public interface Presenter extends BasePresenter{
        void init();
        void dateChange(String date);
    }

    public interface DataSource extends BaseModel{
        List<Daily> getDailyList();
        void setDate(String date);
        String getDate();

    }


}
