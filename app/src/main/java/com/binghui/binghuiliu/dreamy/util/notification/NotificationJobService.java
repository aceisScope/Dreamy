package com.binghui.binghuiliu.dreamy.util.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import com.binghui.binghuiliu.dreamy.R;
import com.binghui.binghuiliu.dreamy.main.MainActivity;
import com.binghui.binghuiliu.dreamy.util.Constants;

import timber.log.Timber;

/**
 * Created by binghuiliu on 07/12/2017.
 */

public class NotificationJobService extends JobService {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.d("NotificationJobService created");
    }

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Timber.d("NotificationJobService started");
        long lastLaunchTime = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getLong(Constants.LAST_LAUNCH, -1);
        if(lastLaunchTime > 0) {
            long intervalSinceLastLaunch = System.currentTimeMillis() - lastLaunchTime;
            if(intervalSinceLastLaunch > Constants.NOTIFICATION_PERIOD) {
                postNotification();
            }
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }

    private void postNotification() {
        NotificationHelper notificationHelper = new NotificationHelper(this);
        notificationHelper.notify(101, "Dreamy", "Miss you!");
    }
}
