package com.demo.cc.appclick;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cc on 2016/11/3.
 */

public class TabHostTest extends TabActivity implements View.OnClickListener {

    private int currentColor = 0;

    final int[] color = new int[]{
            R.color.color7,
            R.color.color6,
            R.color.color5,
            R.color.color4,
            R.color.color3,
            R.color.color2,
            R.color.color1,
    };

    final int[] names = new int[]{
            R.id.color_textView1,
            R.id.color_textView2,
            R.id.color_textView3,
            R.id.color_textView4,
            R.id.color_textView5,
            R.id.color_textView6,
            R.id.color_textView7,
    };

    TextView[] textView = new TextView[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost tabHost = getTabHost();

        LayoutInflater.from(this).inflate(R.layout.tabhost, tabHost.getTabContentView(), true);

        tabHost.addTab(tabHost.newTabSpec("tab01")
                .setIndicator("测试1").setContent(R.id.tab01));
        tabHost.addTab(tabHost.newTabSpec("tab02")
                .setIndicator("测试2").setContent(R.id.tab02));
        tabHost.addTab(tabHost.newTabSpec("tab03")
                .setIndicator("测试3").setContent(R.id.tab03));

        /**
         * 返回第一页
         */
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        /**
         * 颜色
         */
        for (int i=0;i<7;i++){
            textView[i] = (TextView) findViewById(names[i]);
        }
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message message){
                //表明消息来自本程序所发送
                if (message.what == 0x1122){

                    //依次改变7个textView的颜色
                    for (int i = 0; i < 7 - currentColor; i++) {
                        textView[i].setBackgroundResource(color[i+currentColor]);
                    }
                    for (int i = 7-currentColor,j=0; i < 7; i++,j++) {
                        textView[i].setBackgroundResource(color[j]);
                    }
                }
                super.handleMessage(message);
            }
        };

        //定义一个线程 周期性的改变currentColor变量值
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                currentColor++;
                if (currentColor >= 6){
                    currentColor = 0;
                }

                //发送一条消息通知系统改变7个textView组件的背景色
                Message message = new Message();
                //给该消息定义一个标识
                message.what = 0x1122;
                handler.sendMessage(message);
            }
        },0,100);

        Button simpleBtn = (Button) findViewById(R.id.simple);
        simpleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(TabHostTest.this,
                        "简单的提示信息",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        Button asImageBtn = (Button) findViewById(R.id.asImage);
        asImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个Toast提示信息
                Toast toast = Toast.makeText(TabHostTest.this,
                        "带图片的提示信息",
                        Toast.LENGTH_LONG);
                //设置改Toast提示信息的持续时间
                toast.setGravity(Gravity.CENTER,0,0);
                //获取toast中原有的view
                View toastView = toast.getView();
                //创建一个imageView
                ImageView imageView = new ImageView(TabHostTest.this);
                imageView.setImageResource(R.mipmap.icon);
                //创建一个linearLayout容器
                LinearLayout linearLayout = new LinearLayout(TabHostTest.this);
                linearLayout.addView(imageView);
                linearLayout.addView(toastView);

                toast.setView(linearLayout);
                toast.show();

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent mainActivity = new Intent(this, MainActivity.class);
        this.startActivity(mainActivity);
    }
}
