package com.demo.cc.firstcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.demo.cc.appclick.R;

/**
 * Created by ChenXingLing on 2017/1/6.
 */

public class UISizeLearn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置容器，引用布局，相当于引用某个界面
        setContentView(R.layout.ui_size_learn);

        float xdpi = getResources().getDisplayMetrics().xdpi;
        float ydpi = getResources().getDisplayMetrics().ydpi;
        Log.d("UISizeLearn","xdpi is :"+xdpi +"-"+ "ydpi is :" + ydpi);
    }
}
