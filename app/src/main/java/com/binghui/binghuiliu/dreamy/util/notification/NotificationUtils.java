package com.binghui.binghuiliu.dreamy.util.notification;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.preference.PreferenceManager;

import com.binghui.binghuiliu.dreamy.util.Constants;

import timber.log.Timber;

/**
 * Created by binghuiliu on 11/12/2017.
 */

public class NotificationUtils {

    // command to force fire job: adb shell cmd jobscheduler run -f com.binghui.binghuiliu.dreamy 1001
    public static void scheduleNotifications(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(Constants.LAST_LAUNCH, System.currentTimeMillis()).apply();

        final int JOB_ID = 1001;
        try {
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            JobInfo jobInfo = new JobInfo.Builder(JOB_ID, new ComponentName(context, NotificationJobService.class))
                    .setRequiresCharging(false)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) // do the job in any kind of network connection
                    .setPersisted(true) //keep the job after reboot
                    .setPeriodic(Constants.NOTIFICATION_PERIOD) //schedule the job to launch in the given time. note on Nougat it can't be less than 15 mins. https://stackoverflow.com/questions/38344220/job-scheduler-not-running-on-android-n
                    //.setMinimumLatency(15000)
                    .build();
            jobScheduler.cancelAll(); // cancel all previously scheduled jobs
            jobScheduler.schedule(jobInfo);
        } catch (Exception ex) {
            Timber.e("scheduleNotifications failure");
        }
    }
}
