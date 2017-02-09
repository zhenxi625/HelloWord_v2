package com.demo.cc.firstcode.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ChenXingLing on 2017/2/9.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context,LongRunningService.class);
        context.startService(intent1);
    }
}
