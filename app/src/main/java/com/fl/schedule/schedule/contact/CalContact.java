package com.fl.schedule.schedule.contact;

import com.fl.schedule.base.contact.BaseModel;
import com.fl.schedule.base.contact.BasePresenter;
import com.fl.schedule.base.contact.BaseView;
import com.fl.schedule.schedule.model.bean.CalDate;

import java.util.List;

/**
 * Created by tctctc on 2016/11/17.
 */

public interface CalContact {
    interface View extends BaseView{
        void setCalDates(List<CalDate> calDates);
        void pageRefresh(int[] update);
    }

    interface Presenter extends BasePresenter{
        void OnItemSelected(CalDate calDate);
        void initData();
        void refresh();
        void OnPageSelect();

    }

    interface DataSource extends BaseModel{
        List<CalDate> getInitData(int lastDay);
        CalDate getCurrentDate();
        void setCurrentDate(CalDate calDate);
        int[] refresh(int lastDay);
    }
    interface OnScheMainPresenter{
        int getLastDay();
        void onSelected(CalDate calDate);
    }
}
