package com.demo.cc.firstcode;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.demo.cc.appclick.R;

/**
 * Created by ChenXingLing on 2017/1/6.
 */

public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("ThirdActivity", "Task id is:" +getTaskId());
        setContentView(R.layout.third_layout);
        Button button = (Button) findViewById(R.id.button_3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityController.finishAll();
            }
        });
    }
}
