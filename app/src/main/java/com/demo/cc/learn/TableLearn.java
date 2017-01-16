package com.demo.cc.learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.demo.cc.appclick.MainActivity;
import com.demo.cc.appclick.R;

/**
 * Created by ChenXingLing on 2017/1/6.
 */

public class TableLearn extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tablelearn);
        Button button = (Button) findViewById(R.id.btn12);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn12:
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
