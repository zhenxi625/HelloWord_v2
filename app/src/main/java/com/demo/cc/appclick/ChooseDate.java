package com.demo.cc.appclick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by cc on 2016/11/3.
 */

public class ChooseDate extends AppCompatActivity implements View.OnClickListener  {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1.配置文件方式

        //設置容器，引用布局，相当于引用某个界面
        setContentView(R.layout.choose_date);

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ChooseDate.this.year = year;
                ChooseDate.this.month = monthOfYear;
                ChooseDate.this.day = dayOfMonth;

                showDate(year, month, day,hour,minute);
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                ChooseDate.this.hour = hourOfDay;
                ChooseDate.this.minute = minute;
                showDate(year, month, day,hour,minute);
            }
        });

        Button returnFirst = (Button) findViewById(R.id.returnFirst);
        returnFirst.setOnClickListener(this);
    }

    private void showDate(int year, int month, int day,int hour,int minute){
        EditText editText = (EditText) findViewById(R.id.editText);
        editText.setText("日期为："+year+"年"+month+"月"+day+"日"+hour+"时"+minute+"分");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        this.startActivity(intent);
    }
}
