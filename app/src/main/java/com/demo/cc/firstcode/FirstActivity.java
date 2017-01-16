package com.demo.cc.firstcode;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.demo.cc.appclick.R;

/**
 * Created by ChenXingLing on 2017/1/6.
 */

public class FirstActivity extends BaseActivity {

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("FirstActivity", "Task id is:" +getTaskId());
        setContentView(R.layout.first_layout);
        Button button = (Button) findViewById(R.id.button_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Second2Activity.actionStart(FirstActivity.this,"data1","data2");
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("FirstActivity", "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("FirstActivity", "启动");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("FirstActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("FirstActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("FirstActivity", "停止");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出程序时 关闭SQLiteDatabase
        if (database != null && database.isOpen()) {
            database.close();
        }
        Log.i("FirstActivity", "销毁");
    }
}
