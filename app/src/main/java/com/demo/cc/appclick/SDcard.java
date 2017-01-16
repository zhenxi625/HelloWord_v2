package com.demo.cc.appclick;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 * Created by cc on 2016/11/7.
 */

public class SDcard extends AppCompatActivity {

    final String FILE_NAME = "/test.bin";
    final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdcard);

        Button write = (Button) findViewById(R.id.write);
        Button read = (Button) findViewById(R.id.read);
        final EditText editWrite = (EditText) findViewById(R.id.editWrite);
        final EditText editRead = (EditText) findViewById(R.id.editRead);

        //为write按钮绑定事件监听器
        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //将editWrite的内容写入文件中
                write(editWrite.getText().toString());
                editWrite.setText("");
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //读取指定文件中的内容，并显示出来
                editRead.setText(read());
            }
        });
    }

    //用户选择允许或需要后，会回调onRequestPermissionsResult方法, 该方法类似于
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode,grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        try {
            if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted 许可授予
                    //获取sd卡的目录
                    File sdCardDir = Environment.getExternalStorageDirectory();
                    File targetFile = new File(sdCardDir.getCanonicalPath()+FILE_NAME);

                    //以指定文件创建RandomAccessFile对象
                    RandomAccessFile raf = new RandomAccessFile(targetFile,"rw");
                    //将文件记录指针移动到最后
                    raf.seek(targetFile.length());
                    //输出文件内容
                    raf.write("222222222".getBytes());
                    raf.close();

                } else {
                    // TODO Permission Denied 没有权限
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String read(){
        try {
            //如果手机插入了SD卡，而且应用程序具有访问sd卡的权限
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                //获取sd卡对应的存储目录
                File sdCardDir = Environment.getExternalStorageDirectory();
                //获取指定文件对应的输入流
                FileInputStream inputStream = new FileInputStream(sdCardDir.getCanonicalPath()+FILE_NAME);
                //将指定输入流包装成BufferedReader
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder sb = new StringBuilder("");
                String line;
                while ((line=reader.readLine()) != null){
                    sb.append(line);
                }
                return sb.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void write(String content){
        //检查并申请权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }

    private void write1(String content){
        try {
            //如果手机插入了SD卡，而且应用程序具有访问sd卡的权限
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                //获取sd卡的目录
                File sdCardDir = Environment.getExternalStorageDirectory();
                File targetFile = new File(sdCardDir.getCanonicalPath()+FILE_NAME);

                //以指定文件创建RandomAccessFile对象
                RandomAccessFile raf = new RandomAccessFile(targetFile,"rw");
                //将文件记录指针移动到最后
                raf.seek(targetFile.length());
                //输出文件内容
                raf.write(content.getBytes());
                raf.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
