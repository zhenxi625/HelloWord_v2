package com.demo.cc.appclick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by cc on 2016/11/9.
 */

public class ForServiceActivity extends AppCompatActivity{

    Button start,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicetest);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        //创建启动service的Intent
        final Intent intent = new Intent();
        //为Intent设置action属性
        intent.setAction("ziDingYiActionName");
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //启动指定service
                startService(intent);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止指定service
                stopService(intent);
            }
        });
    }
}
