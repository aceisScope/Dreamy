package com.binghui.binghuiliu.dreamy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.binghui.binghuiliu.dreamy.R;
import com.binghui.binghuiliu.dreamy.app.ApplicationModule;
import com.binghui.binghuiliu.dreamy.ui.ShotsPresenter;

import javax.inject.Inject;

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
        DaggerShotsPresenterComponent
                .builder()
                .applicationModule(new ApplicationModule(getApplication()))
                .shotsPresenterModule(new ShotsPresenterModule())
                .build()
                .inject(this);
    }
}
