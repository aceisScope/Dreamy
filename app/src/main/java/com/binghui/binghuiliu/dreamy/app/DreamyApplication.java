package com.binghui.binghuiliu.dreamy.app;

import android.app.Application;
import android.content.Context;

import com.binghui.binghuiliu.dreamy.BuildConfig;

import timber.log.Timber;

/**
 * Created by binghuiliu on 16/11/2017.
 */

public class DreamyApplication extends Application {
    

    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();

        if (BuildConfig.DEBUG) {
            Timber.uprootAll();
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void setupGraph() {

    }

}
