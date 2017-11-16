package com.binghui.binghuiliu.dreamy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.binghui.binghuiliu.dreamy.bean.Shot;
import com.binghui.binghuiliu.dreamy.bean.ShotList;
import com.binghui.binghuiliu.dreamy.network.ApiManager;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSubscription = ApiManager.getShotsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Shot>>() {
                    @Override
                    public void call(List<Shot> shotList) {
                        Log.v("Dreamy_Success_2", shotList.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.v("Dreamy_Failure", throwable.getLocalizedMessage());
                    }
                });
    }
}
