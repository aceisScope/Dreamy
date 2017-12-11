package com.binghui.binghuiliu.dreamy.app;

import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.preference.PreferenceManager;
import com.binghui.binghuiliu.dreamy.BuildConfig;
import com.binghui.binghuiliu.dreamy.util.Constants;
import com.binghui.binghuiliu.dreamy.util.notification.NotificationHelper;
import com.binghui.binghuiliu.dreamy.util.notification.NotificationJobService;
import com.binghui.binghuiliu.dreamy.util.notification.NotificationUtils;
import com.facebook.stetho.Stetho;

import timber.log.Timber;

/**
 * Created by binghuiliu on 16/11/2017.
 */

public class DreamyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.uprootAll();
            Timber.plant(new Timber.DebugTree());

            Stetho.initializeWithDefaults(this);
        }

        NotificationUtils.scheduleNotifications(this);
    }
}
