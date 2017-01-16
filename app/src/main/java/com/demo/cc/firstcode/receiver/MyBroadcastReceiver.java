package com.demo.cc.firstcode.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by ChenXingLing on 2017/1/10.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"接收广播",Toast.LENGTH_SHORT).show();
        abortBroadcast();//截断
    }
}
