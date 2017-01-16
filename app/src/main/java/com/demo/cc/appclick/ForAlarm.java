package com.demo.cc.appclick;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.Callable;

/**
 * Created by cc on 2016/11/9.
 */

public class ForAlarm extends AppCompatActivity {

    Button setTime;
    AlarmManager alarmManager;
    Calendar currentTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);
        setTime = (Button) findViewById(R.id.setTime);
        alarmManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(ForAlarm.this, 0, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        //指定启动AlarmActivity组件
                        Intent intent = new Intent(ForAlarm.this,Alarm.class);
                        //创建PendingIntent对象,延迟传输
                        PendingIntent pendingIntent = PendingIntent.getActivity(
                                ForAlarm.this,0,intent,0
                        );
                        Calendar c = Calendar.getInstance();
                        c.setTimeInMillis(System.currentTimeMillis());
                        //根据用户选择时间来设置Calendar对象
                        c.set(Calendar.HOUR,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        //设置AlarmManager将在Calendar对应的时间启动组件
                        alarmManager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
                        //显示闹钟设置成功提示信息
                        Toast.makeText(ForAlarm.this,"闹钟设置成功",Toast.LENGTH_SHORT).show();
                    }
                },
                   currentTime.get(Calendar.HOUR_OF_DAY)
                   ,currentTime.get(Calendar.MINUTE),false)
                   .show();
            }
        });

    }
}
