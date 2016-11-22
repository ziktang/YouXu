package com.fl.schedule.daily.model.dataSource;

import com.fl.schedule.daily.contact.DailyMainContact;
import com.fl.schedule.daily.model.bean.Daily;
import com.fl.schedule.daily.model.localDataSource.DailyLocalSource;

import java.util.Calendar;
import java.util.List;

/**
 * Created by tctctc on 2016/11/21.
 */

public class DailyMainSource implements DailyMainContact.DataSource {
    private DailyLocalSource mLocalSource;
    private String mdate;

    public DailyMainSource() {
        mLocalSource = DailyLocalSource.getInstance();
        mdate = Calendar.getInstance().get(Calendar.YEAR)+"-"+ (Calendar.getInstance().get(Calendar.MONTH)+1);
    }

    public Daily get(long id) {
        return mLocalSource.get(id);
    }


    @Override
    public List<Daily> getDailyList() {
        return mLocalSource.getListByDate(mdate);
    }

    @Override
    public void setDate(String date) {
        mdate = date;
    }

    @Override
    public String getDate() {
        return mdate;
    }
}
