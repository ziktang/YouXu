package com.fl.schedule.schedule.presenter;

import com.fl.schedule.R;
import com.fl.schedule.schedule.contact.AddScheContact;
import com.fl.schedule.schedule.model.bean.Schedule;

/**
 * Created by tctctc on 2016/11/18.
 */

public class ScheAddPresenter implements AddScheContact.Presenter {
    private AddScheContact.DataSource mDataSource;
    private AddScheContact.View mView;
    private boolean isOn;

    public ScheAddPresenter(AddScheContact.DataSource dataSource, AddScheContact.View view) {
        mDataSource = dataSource;
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void init(long id) {
        Schedule schedule = mDataSource.init(id);
        if (schedule.getStartTime().length()>10){
            mView.setTimeToggle(false);
            isOn = false;
        }else{
            mView.setTimeToggle(true);
            isOn = true;
        }
        mView.show(schedule);
        mView.OnDoneToggle(schedule.getStatus() == 1?R.drawable.done:R.drawable.un_done);
    }

    @Override
    public void commit(String description, String startTime, String endTime, String warnTime,String place, String note) {
        boolean isOk = mDataSource.commit(description, startTime, endTime, warnTime,place, note);
        if (isOk) {
            mView.commitMessge("保存成功", RESULT_OK);
        } else {
            mView.commitMessge("操作失败", RESULT_FAILURE);
        }
    }

    @Override
    public void allDay(boolean on) {
        isOn = on;
        String[] time = mDataSource.OnAllDay(on);
        mView.allDayShow(time);
    }

    @Override
    public void timeChange(String time, int viewId) {
        int[] s = mDataSource.timeChange(time);
        if (isOn) {
            mView.datePicker(s[0], s[1], s[2], viewId);
        }else {
            mView.dateTimePicker(s[0], s[1], s[2], s[3], s[4], viewId);
        }
    }

    @Override
    public void warnTimeChange(String time) {
        mView.warnTimePicker(mDataSource.warnTimeChange(time));
    }

    @Override
    public void doneToogle(){
        int status = mDataSource.doneToogle();
        mView.OnDoneToggle(status == 1?R.drawable.done:R.drawable.un_done);
    }

    @Override
    public void delete() {
        if (mDataSource.delete()){
            mView.Ondelete("删除成功",RESULT_OK);
        }else{
            mView.Ondelete("删除失败",RESULT_FAILURE);
        }
    }
}
