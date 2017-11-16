package com.binghui.binghuiliu.dreamy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.binghui.binghuiliu.dreamy.bean.Shot;
import com.binghui.binghuiliu.dreamy.bean.ShotList;
import com.binghui.binghuiliu.dreamy.network.ApiManager;
import com.binghui.binghuiliu.dreamy.ui.DaggerShotsPresenterComponent;
import com.binghui.binghuiliu.dreamy.ui.ShotsPresenter;

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

    @Inject
    ShotsPresenter shotsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupComponent();
        shotsPresenter.getShotList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shotsPresenter.detachView();
    }

    protected void setupComponent() {
        DaggerShotsPresenterComponent.builder().build().inject(this);
    }
}
