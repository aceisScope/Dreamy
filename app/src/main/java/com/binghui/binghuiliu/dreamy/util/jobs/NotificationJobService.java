package com.binghui.binghuiliu.dreamy.util.jobs;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;

import com.binghui.binghuiliu.dreamy.R;
import com.binghui.binghuiliu.dreamy.main.MainActivity;
import com.binghui.binghuiliu.dreamy.util.Constants;

/**
 * Created by binghuiliu on 07/12/2017.
 */

public class NotificationJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        long lastLaunchTime = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getLong(Constants.LAST_LAUNCH, -1);
        if(lastLaunchTime > 0) {
            long intervalSinceLastLaunch = System.currentTimeMillis() - lastLaunchTime;
            if(intervalSinceLastLaunch > Constants.LAUNCH_PERIOD) {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(NotificationJobService.this, Constants.NOTIFICATION_CHANNEL_ID)
                                .setAutoCancel(true)
                                .setContentTitle("Dreamy")
                                .setContentText("又有新的内容上线了，快来我们app看看吧!");
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
