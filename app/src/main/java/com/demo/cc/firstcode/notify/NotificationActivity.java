package com.demo.cc.firstcode.notify;

import android.app.NotificationManager;
import android.os.Bundle;

import com.demo.cc.appclick.R;
import com.demo.cc.firstcode.BaseActivity;

/**
 * Created by ChenXingLing on 2017/1/16.
 */

public class NotificationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifycation);
        //任务栏通知消失
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(2);
    }
}
