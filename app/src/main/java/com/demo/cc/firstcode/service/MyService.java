package com.demo.cc.firstcode.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.demo.cc.appclick.MainActivity;
import com.demo.cc.appclick.R;

/**
 * Created by ChenXingLing on 2017/2/8.
 */

public class MyService extends Service {

    private DownloadBinder binder = new DownloadBinder();

    public class DownloadBinder extends Binder{

        public void startDownload(){
            Log.i("MyService","执行startDownload");
        }

        public int getProgress(){
            Log.i("MyService","执行getProgress");
            return 0;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyService","Service onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO: 处理的具体逻辑
                stopSelf();
            }
        }).start();
        Log.i("MyService","Service onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyService","Service onDestroy");
    }
}
