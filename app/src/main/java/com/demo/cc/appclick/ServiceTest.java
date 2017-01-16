package com.demo.cc.appclick;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cc on 2016/11/9.
 */

public class ServiceTest extends Service {

    //必须实现的方法
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //service被创建时回调该方法
    @Override
    public void onCreate() {
        super.onCreate();
    }

    //service被启动时回调该方法
    @Override
    public int onStartCommand(Intent intent,int falgs,int startId){
        System.out.println("onStartCommand");
        return START_STICKY;
    }

    //service被关闭之前回调
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
    }

}
