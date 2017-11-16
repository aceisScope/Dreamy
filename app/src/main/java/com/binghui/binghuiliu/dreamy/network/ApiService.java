package com.binghui.binghuiliu.dreamy.network;

import com.binghui.binghuiliu.dreamy.model.Shot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by binghuiliu on 15/11/2017.
 */

public interface ApiService {
    @GET("teams/{team}/shots?page=2&access_token=8432422eabc3c6a951b5424701181653b6a18096219a88c7dd52c815ef25bf24")
    Call<List<Shot>> shotsList(@Path("team") String team);
}
