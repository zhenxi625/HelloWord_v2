package com.demo.cc.firstcode.sms;

import android.os.Bundle;
import android.widget.TextView;

import com.demo.cc.appclick.R;
import com.demo.cc.firstcode.BaseActivity;

/**
 * Created by ChenXingLing on 2017/2/7.
 */

public class SMSActivity extends BaseActivity {

    private TextView sender;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_layout);

        sender = (TextView) findViewById(R.id.sender);
        content = (TextView) findViewById(R.id.content);
    }
}
