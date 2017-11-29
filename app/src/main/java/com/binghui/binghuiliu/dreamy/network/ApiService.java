package com.binghui.binghuiliu.dreamy.network;

import com.binghui.binghuiliu.dreamy.R;
import com.binghui.binghuiliu.dreamy.bean.Shot;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by binghuiliu on 15/11/2017.
 */

public interface ApiService {
    @GET("teams/{team}/shots?page=2")
    Observable<List<Shot>> getShotsList(@Path("team") String team, @Query("access_token") String accessToken);
}
