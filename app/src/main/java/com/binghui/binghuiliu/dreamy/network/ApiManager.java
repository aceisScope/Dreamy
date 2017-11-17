package com.binghui.binghuiliu.dreamy.network;

import com.binghui.binghuiliu.dreamy.R;
import com.binghui.binghuiliu.dreamy.bean.Shot;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by binghuiliu on 16/11/2017.
 */

public class ApiManager {

    public static final String baseURL = "https://api.dribbble.com/v1/";
    public static final String team = "kit8";

    private static final Retrofit sRetrofit = new Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .baseUrl(baseURL)
            .build();

    private static final ApiService apiService = sRetrofit.create(ApiService.class);

    public static Observable<List<Shot>> getShotsList() {
        return apiService.getShotsList(team, ""); // Should get access token from resource file
    }
}
