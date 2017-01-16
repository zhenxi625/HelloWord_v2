package com.demo.cc.appclick;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by cc on 2016/11/10.
 */

public class BatteryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        //获取当前电量
        int current = bundle.getInt("level");
        //获取总电量
        int total = bundle.getInt("scale");
        //如果当前电量小于总电量的15%
        if (current * 1.0 /total < 0.15){
            Toast.makeText(context,"电池电量过低！",Toast.LENGTH_SHORT).show();
        }
    }
}
