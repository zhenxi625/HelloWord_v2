package com.demo.cc.firstcode.sms;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.cc.appclick.R;
import com.demo.cc.firstcode.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by ChenXingLing on 2017/2/7.
 */

public class ChoosePic extends BaseActivity implements View.OnClickListener{

    final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1234;

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;

    private Button takePhoto;
    private ImageView picture;
    private Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosepic);

        takePhoto = (Button) findViewById(R.id.take_photo);
        takePhoto.setOnClickListener(this);
        picture = (ImageView) findViewById(R.id.picture);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.take_photo:
                //创建file对象，用于拍照后的图片
                File file = new File(Environment.getExternalStorageDirectory(),"test.jpg");
                try {
                    if (file.exists()){
                        file.delete();
                    }
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = Uri.fromFile(file);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);//启动相机程序
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if (requestCode == RESULT_OK){
                    showCameraAction();
                }
                break;
            case CROP_PHOTO:
                if (requestCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(
                                getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);//将剪裁后的照片显示出来
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    //用户选择允许或需要后，会回调onRequestPermissionsResult方法, 该方法类似于
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode,grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(imageUri,"image/*");
                intent.putExtra("scale",true);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,CROP_PHOTO);//启动剪裁程序

            } else {
                // TODO Permission Denied 没有权限
            }
        }
    }

    private void showCameraAction() {
        //检查并申请权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        }
    }
}
