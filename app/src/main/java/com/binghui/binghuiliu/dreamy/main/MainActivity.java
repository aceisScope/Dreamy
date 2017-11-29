package com.binghui.binghuiliu.dreamy.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.binghui.binghuiliu.dreamy.R;
import com.binghui.binghuiliu.dreamy.app.ApplicationModule;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ShotsPresenter shotsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupComponent();

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
        shotsPresenter.attachView(mainFragment);
        shotsPresenter.getShotList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shotsPresenter.detachView();
    }

    protected void setupComponent() {
        DaggerShotsPresenterComponent
                .builder()
                .applicationModule(new ApplicationModule(getApplication()))
                .shotsPresenterModule(new ShotsPresenterModule())
                .build()
                .inject(this);
    }
}
