package com.fl.schedule.loginRegister.model.bean;
import com.fl.schedule.app.ApiConfig;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.POST;
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
                    .baseUrl(ApiConfig.baseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    public interface LoReService{
        @POST("login")
        Observable<ResponseBody> getLoginInfo(@Field("userName") String userName,@Field("password") String password);

        @POST("register")
        Observable<ResponseBody> getRegisterInfo(@Field("number") String number,@Field("userName") String userName,@Field("password") String password);
    }
}
