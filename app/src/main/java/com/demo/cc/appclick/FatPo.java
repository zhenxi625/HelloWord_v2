package com.demo.cc.appclick;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cc on 2016/11/10.
 */

public class FatPo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fatpo);

        Button play = (Button) findViewById(R.id.play);
        Button stop = (Button) findViewById(R.id.stop);
        ImageView imageView = (ImageView) findViewById(R.id.anim);
        final ImageView imageView2 = (ImageView) findViewById(R.id.image);
        //获取AnimatedVectorDrawable动画对象
        final AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();

        //加载第一份动画资源
        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim);
        //设置动画结束后保留动画状态
        animation.setFillAfter(true);
        //加载第二份动画资源
        final Animation reverse = AnimationUtils.loadAnimation(this,R.anim.reverse);
        //设置动画结束后保留动画状态
        reverse.setFillAfter(true);
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                if (message.what == 0x123){
                    imageView2.startAnimation(reverse);
                }
            }
        };

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim.start();
                imageView2.startAnimation(animation);
                //设置3秒后启动第二个动画
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0x123);
                    }
                },3500);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim.stop();
            }
        });

    }

}
