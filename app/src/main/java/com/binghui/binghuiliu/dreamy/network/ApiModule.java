package com.binghui.binghuiliu.dreamy.network;

import com.binghui.binghuiliu.dreamy.model.Shot;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by binghuiliu on 15/11/2017.
 */

@Module
public class ApiModule {
    public static final String baseURL = "https://api.dribbble.com/v1/";
    public static final String team = "kit8";

    @Provides
    @Singleton
    Retrofit provideRetrofit() {

        return new Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseURL)
                .build();
    }

    @Provides
    Call<List<Shot>> prodiveShotsList(Retrofit retrofit) {
        return retrofit
                .create(ApiService.class)
                .shotsList(team);
    }
}
