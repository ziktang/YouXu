package com.fl.schedule.schedule.model.remoteDataSource;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by tctctc on 2016/11/18.
 */

public class ScheRemoteSource {
    private static ScheduleClient.ScheduleService mScheduleService;

    public Observable<ResponseBody> upload(String scheduleListJson) {
        return getScheduleService().postSchedules(scheduleListJson)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ResponseBody> modify(String scheduleListJson) {
        return getScheduleService().postSchedules(scheduleListJson)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ResponseBody> delete(long ScheId) {
        return getScheduleService().deleteSchedules(ScheId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ResponseBody> complete(long ScheId) {
        return getScheduleService().completeSchedules(ScheId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread());
    }

    private ScheduleClient.ScheduleService getScheduleService() {
        if (mScheduleService == null) {
            mScheduleService = ScheduleClient.getRetrofit().create(ScheduleClient.ScheduleService.class);
        }
        return mScheduleService;
    }
}
