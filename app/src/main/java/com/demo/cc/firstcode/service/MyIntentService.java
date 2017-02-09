package com.demo.cc.firstcode.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by ChenXingLing on 2017/2/9.
 */

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");//调用父类的有参构造函数
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //当前线程id
        Log.d("MyIntentService","当前线程id:"+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService","执行onDestroy");
    }
}
