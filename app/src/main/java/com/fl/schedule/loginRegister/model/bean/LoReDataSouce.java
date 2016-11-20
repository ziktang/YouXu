package com.fl.schedule.loginRegister.model.bean;

import okhttp3.ResponseBody;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tctctc on 2016/11/11.
 */

public class LoReDataSouce {

    private LoReClient.LoReService mLoReService;

    public Observable<ResponseBody> postLogin(String name,String password) {
        Observable<ResponseBody> observable = getDataService().getLoginInfo(name,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    public Observable<ResponseBody> postRegister(String number,String name,String password) {
        Observable<ResponseBody> observable = getDataService().getRegisterInfo(number,name,password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    private LoReClient.LoReService getDataService() {
        if (mLoReService == null) {
            mLoReService = LoReClient.getRetrofit().create(LoReClient.LoReService.class);
        }
        return mLoReService;
    }
}
