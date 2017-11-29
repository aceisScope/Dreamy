package com.binghui.binghuiliu.dreamy.network;

import android.app.Application;

import com.binghui.binghuiliu.dreamy.R;
import com.binghui.binghuiliu.dreamy.bean.Shot;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by binghuiliu on 16/11/2017.
 */

public class ApiManager {

    public static final String baseURL = "https://api.dribbble.com/v1/";
    public static final String team = "kit8";

    private static final Retrofit sRetrofit = new Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create()).create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 使用RxJava2作为回调适配器
            .baseUrl(baseURL)
            .build();

    private static final ApiService apiService = sRetrofit.create(ApiService.class);

    public static Observable<List<Shot>> getShotsList(Application application) {
        return apiService.getShotsList(team, application.getString(R.string.access_token));
    }
}
