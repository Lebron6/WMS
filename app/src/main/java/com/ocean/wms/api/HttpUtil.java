package com.ocean.wms.api;

import android.content.Context;

import com.ocean.wms.tools.HttpLogger;
import com.ocean.wms.tools.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by James on 2020/1/6.
 */
public class HttpUtil {


    public static OkHttpClient mOkHttpClient;
    public static Retrofit retrofit;

    public static WMSApi createRequest(Context context) {
        retrofit = new Retrofit.Builder()
                .baseUrl(WMSUrl.BaseUrl)
                .client(okhttpclient(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WMSApi vamApi = retrofit.create(WMSApi.class);
        return vamApi;
    }

    /**
     * 初始化okhttpclient.
     * @return okhttpClient
     */
    public static OkHttpClient okhttpclient(Context context) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger(context.getClass().getSimpleName()));
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor())
                .build();
        return mOkHttpClient;
    }
}
