package com.binghui.binghuiliu.dreamy.app;

import android.app.Application;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.preference.PreferenceManager;
import com.binghui.binghuiliu.dreamy.BuildConfig;
import com.binghui.binghuiliu.dreamy.util.Constants;
import com.binghui.binghuiliu.dreamy.util.notification.NotificationJobService;
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

        scheduleNotifications();
    }

    private void scheduleNotifications() {
        // TODO: it's not working
        PreferenceManager.getDefaultSharedPreferences(this).edit().putLong(Constants.LAST_LAUNCH, System.currentTimeMillis()).apply();

        final int JOB_ID = 1001;
        try {
            JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
            JobInfo jobInfo = new JobInfo.Builder(JOB_ID, new ComponentName(this, NotificationJobService.class))
                    .setRequiresCharging(false)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) // do the job in any kind of network connection
                    .setPersisted(true) //keep the job after reboot
                    .setPeriodic(Constants.NOTIFICATION_PERIOD) //schedule the job to launch in the given time. note on Nougat it can't be less than 15 mins. https://stackoverflow.com/questions/38344220/job-scheduler-not-running-on-android-n
                    //.setMinimumLatency(15000)
                    .build();
            jobScheduler.schedule(jobInfo);
        } catch (Exception ex) {
            Timber.e("scheduleNotifications failure");
        }
    }
}
