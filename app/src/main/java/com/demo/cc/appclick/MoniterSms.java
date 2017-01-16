package com.demo.cc.appclick;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by cc on 2016/11/9.
 */

public class MoniterSms extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monitorsms);

        //为content：//sms的数据改注册监听器
        getContentResolver().registerContentObserver(Uri.parse("content://sms")
        ,true,new SmsObserver(new Handler()));
    }

    private final class SmsObserver extends ContentObserver{
        SmsObserver(Handler handler){
            super(handler);
        }

        public void onChange(boolean selfChange){
            //查询发送箱中的短信（处于正在发送状态的短信放在发送箱）
            Cursor cursor = getContentResolver().query(Uri.parse("content://sms/outbox"),null,null ,null ,null );
            //遍历查询得到的结果集
            while (cursor.moveToNext()){
                StringBuilder sb = new StringBuilder();
                //获取短信的发送地址
                sb.append("address=").append(cursor.getString(cursor.getColumnIndex("address")));
                //获取短信标题
                sb.append(";subject=").append(cursor.getString(cursor.getColumnIndex("subject")));
                //获取短信内容
                sb.append(";body=").append(cursor.getString(cursor.getColumnIndex("body")));
                //获取短信发送时间
                sb.append(";time=").append(cursor.getLong(cursor.getColumnIndex("date")));
                System.out.println(sb.toString());
            }
        }
    }
}
