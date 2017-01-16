package com.demo.cc.appclick;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

/**
 * Created by cc on 2016/11/4.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("http://www.baidu.com");
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);
                return true;
            }
        });

        //获取控件
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        //设置进度条事件
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            //当拖动条的滑块位置发生改变时
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                System.out.println(progress);
                //动态改变图片的透明度
                imageView.setAlpha(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.second,menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        this.startActivity(intent);
    }

    /*@Override
    public boolean onTouchEvent(MotionEvent event){

        Log.v("cxl","触屏事件"+event.getAction());
        //启动隐式Intent   ACTION_DIAL拨号盘  ACTION_CALL直接拨号
//        Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:5566"));
////        call.setFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
//        this.startActivity(call);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.baidu.com"));//打开网址
//        intent.setData(Uri.parse("content://contacts/people/"));//打开联系人
        this.startActivity(intent);
        return super.onTouchEvent(event);
    }*/
}
