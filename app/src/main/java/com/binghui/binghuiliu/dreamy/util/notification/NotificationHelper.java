package com.binghui.binghuiliu.dreamy.util.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.binghui.binghuiliu.dreamy.R;
import com.binghui.binghuiliu.dreamy.main.MainActivity;

/**
 * Created by binghuiliu on 11/12/2017.
 */

public class NotificationHelper extends ContextWrapper {

    private NotificationManager notificationManager;
    public static final String CHANNEL_ID = "com.binghui.binghuiliu.dreamy.ONE";
    public static final String CHANNEL_NAME = "Channel One";

    public NotificationHelper(Context base) {
        super(base);
        createChannels();
    }

    private void createChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, notificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            getManager().createNotificationChannel(notificationChannel);
        }
    }

    private NotificationManager getManager() {
        if (notificationManager == null) {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    private Notification getNotification(String title, String body) {
        /*
            TaskStackBuilder here only acts as an example. The purpose is to make the app go back from
            the activity opened by clicking on the notification to the main activity when the back button is pressed.
            TaskStackBuilder describes the activity chain.
            In real usage case, a relationship like below should be registered in the manifest.
            <activity android:name=".MessageActivity" android:parentActivityName=".MainActivity"/>
        */
        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return new Notification.Builder(getApplicationContext(), CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent)
                    .build();
        } else {
            return new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setSmallIcon(getSmallIcon())
                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent)
                    .build();
        }
    }

    private int getSmallIcon() {
        return android.R.drawable.stat_notify_chat;
    }

    public void notify(int notificationId, String title, String body) {
        getManager().notify(notificationId, getNotification(title, body));
    }
}
