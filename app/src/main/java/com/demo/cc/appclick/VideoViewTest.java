package com.demo.cc.appclick;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

/**
 * Created by cc on 2016/11/10.
 */

public class VideoViewTest extends AppCompatActivity {

    VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.videoviewtest);
        videoView = (VideoView) findViewById(R.id.video);
        mediaController = new MediaController(this);
        File video = new File("/raw/alarm.mp3");
        if (video.exists()){
            videoView.setVideoPath(video.getAbsolutePath());
            //设置VideoView与MediaController建立联系
            videoView.setMediaController(mediaController);
            //设置MediaController与VideoView建立联系
            mediaController.setMediaPlayer(videoView);
            //让VideoView获取焦点
            videoView.requestFocus();
            videoView.start();
        }

    }
}
