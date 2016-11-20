package com.fl.schedule.schedule.model.remoteDataSource;

import com.fl.schedule.app.ApiConfig;
import com.fl.schedule.schedule.model.bean.Schedule;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tctctc on 2016/11/20.
 */

public class ScheduleClient {
    private static Retrofit sRetrofit;

    public static Retrofit getRetrofit(){
        if (sRetrofit == null){
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build();
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiConfig.baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }

        return sRetrofit;
    }

    public interface ScheduleService{
        //同步上传日程
        @POST("schedules")
        Observable<ResponseBody> postSchedules(@Field("scheduleJson") String scheduleString);

        //删除日程
        @DELETE("schedules/{id}")
        Observable<ResponseBody> deleteSchedules(@Path("id") long id);

        @PUT("schedules/{id}")
        Observable<ResponseBody> modifySchedules(@Path("id") long id,@Field("scheduleJson") String scheduleString);


        @PATCH("schedules/{id}")
        Observable<ResponseBody> completeSchedules(@Path("id") long id);
    }
}
