package com.demo.cc.appclick;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by cc on 2016/11/9.
 */

public class Alarm extends AppCompatActivity {

    MediaPlayer alarmMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载指定音乐,并为之创建mediaPalyer对象
        alarmMusic = MediaPlayer.create(this, R.raw.alarm);
        alarmMusic.setLooping(true);
        //播放音乐
        alarmMusic.start();
        //创建一个对话框
        new AlertDialog.Builder(Alarm.this).setTitle("闹钟").setMessage("闹钟响了。。。")
                .setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //停止音乐
                                alarmMusic.stop();
                                //结束该Activity
                                Alarm.this.finish();
                            }
                        }
                ).show();

    }
}
