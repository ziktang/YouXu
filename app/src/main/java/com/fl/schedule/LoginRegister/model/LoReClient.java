package com.fl.schedule.LoginRegister.model;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tctctc on 2016/11/7.
 */

public class LoReClient {
    private static Retrofit mRetrofit;

    public static Retrofit getRetrofit(){
        if (mRetrofit == null){
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Config.baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public interface TestService{
        @GET("test")
        Observable<ResponseBody> getQueryTest(@Query("name") String name);

        @POST("test")
        Observable<ResponseBody> postBodyTest(@Body User user);

        @FormUrlEncoded
        @POST("test")
        Observable<ResponseBody> postFieldTest(@Field("user") String user);

        @FormUrlEncoded
        @Multipart
        @POST("test")
        Observable<ResponseBody> postTextImg(@Field("user") String user, @PartMap Map<Integer, MultipartBody.Part> map);

        @POST("test")
        Observable<ResponseBody> postFieldMapTest(@FieldMap Map map);

        @Multipart
        @POST("test/upload")
        Call<ResponseBody> upload(@Part MultipartBody.Part file);
    }
}
