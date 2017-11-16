package com.binghui.binghuiliu.dreamy;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.binghui.binghuiliu.dreamy.model.Shot;
import com.binghui.binghuiliu.dreamy.network.ShotService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Inject
    Call<List<Shot>> shotsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((DreamyApplication) getApplication()).getNetworkComponent().inject(this);

        shotsList.enqueue(new Callback<List<Shot>>() {
            @Override public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                Log.v("Dreamy_", response.body().toString());
            }

            @Override public void onFailure(Call<List<Shot>> call, Throwable t) {
                Log.v("Dreamy_", t.getLocalizedMessage());
            }
        });
    }
}
