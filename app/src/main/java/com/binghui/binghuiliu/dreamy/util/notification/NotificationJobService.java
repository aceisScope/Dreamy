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
        long lastLaunchTime = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getLong(Constants.LAST_LAUNCH, -1);
        if(lastLaunchTime > 0) {
            long intervalSinceLastLaunch = System.currentTimeMillis() - lastLaunchTime;
            if(intervalSinceLastLaunch > Constants.NOTIFICATION_PERIOD) {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this)
                                .setAutoCancel(true)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Dreamy")
                                .setContentText("Miss you!");
                // When the app launches by a notification, starts the intended activity
                Intent resultIntent = new Intent(NotificationJobService.this, MainActivity.class);
                // TaskStackBuilder here only acts as an example. The purpose is to make the app go back from
                // the activity opened by clicking on the notification to the main activity when the back button is pressed.
                // TaskStackBuilder describes the activity chain.
                // In real usage case, a relationship like below should be registered in the manifest.
                // <activity android:name=".MessageActivity" android:parentActivityName=".MainActivity"/>
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(NotificationJobService.this);
                stackBuilder.addParentStack(MainActivity.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // channel is required for Oreo
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(Constants.NOTIFICATION_CHANNEL_ID,
                            Constants.NOTIFICATION_CHANNEL_NAME,
                            NotificationManager.IMPORTANCE_DEFAULT);
                    mNotificationManager.createNotificationChannel(channel);
                }
                mNotificationManager.notify(1, mBuilder.build());
            }
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }


}
