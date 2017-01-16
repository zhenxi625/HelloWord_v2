package com.demo.cc.appclick;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.demo.cc.model.Person;

/**
 * Created by cc on 2016/11/4.
 * 用户注册
 */

public class BundleResult extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bundleresult);
        //获取只能被本应用程序读写的SharedPreferences对象
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //读取字符串
        String time = preferences.getString("time",null);
        //读取int类型的数据
        int randNum = preferences.getInt("random",0);
        //使用Toast提示信息

        TextView textName = (TextView) findViewById(R.id.name);
        TextView textPwd = (TextView) findViewById(R.id.password);
        TextView textGender = (TextView) findViewById(R.id.gender);
        //获取Result的Intent
        Intent intent = getIntent();
        //获取该Intent所携带的数据
        Bundle data = intent.getExtras();
        //从Bundle数据包中取出数据
        Person person = (Person) data.getSerializable("person");
        if (null != person){
            textName.setText("您的用户名："+person.getName());
            textPwd.setText("您的密码："+person.getPass());
            textGender.setText("你的性别："+person.getGender());
            textGender.setText(time ==null?"没有数据":"写入时间为："+time+"\n"+"上次写入的随机送为："+randNum);
        }else {
            textName.setText("没获取到数据");
        }

        Log.i("cxl","创建");

        Button button = (Button) findViewById(R.id.gotFirstBtn);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        this.startActivity(intent);
    }
}
