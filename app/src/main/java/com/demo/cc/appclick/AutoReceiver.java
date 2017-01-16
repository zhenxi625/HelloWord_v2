package com.demo.cc.appclick;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by cc on 2016/11/9.
 */

public class AutoReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context,AutoService.class);
        //启动指定service
        context.startService(intent1);
    }
}
