package com.vstar.sacredsun_android_phone.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.vstar.sacredsun_android_phone.R;

/**
 * Created by tanghuailong on 2017/2/14.
 */
@Deprecated
public class HintService extends Service{
    private static final String LOG_TAG = "HintService";
    Notification notification = null;
    Runnable runnable = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG,"onCreate");
        int requestID = (int) System.currentTimeMillis();
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,requestID,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("任务未处理")
                .setContentText("三条任务未处理")
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_SOUND)
                .setOngoing(true)
                .build();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        startForeground(1,notification);
        Handler handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG,"execute");
                notification = new NotificationCompat.Builder(HintService.this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("任务已经处理")
                        .setContentText(System.currentTimeMillis()+"时间")
                        .setContentIntent(pendingIntent)
                        .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_SOUND)
                        .setOngoing(true)
                        .build();
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(1,notification);
                handler.postDelayed(runnable,10000);
            }
        };
        handler.post(runnable);

    }


    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        Log.d(LOG_TAG,"onstartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG,"onDestory");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
