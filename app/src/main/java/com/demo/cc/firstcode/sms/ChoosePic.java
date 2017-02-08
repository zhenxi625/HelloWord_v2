package com.demo.cc.firstcode.sms;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.demo.cc.appclick.BuildConfig;
import com.demo.cc.appclick.R;
import com.demo.cc.firstcode.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by ChenXingLing on 2017/2/7.
 */

public class ChoosePic extends BaseActivity implements View.OnClickListener {

    final int TAKE_PHOTO_REQUEST_CODE = 111;

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;

    private ImageView picture;
    private Uri imageUri;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosepic);

        Button takePhoto = (Button) findViewById(R.id.take_photo);
        takePhoto.setOnClickListener(this);

        Button chooseFromAlbum = (Button) findViewById(R.id.choose_from_album);
        chooseFromAlbum.setOnClickListener(this);
        picture = (ImageView) findViewById(R.id.picture);
    }

    private void takePhotoAction() {
        //检查SDK版本以及是否已授予该权限。
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO_REQUEST_CODE);
            //此后，您等待回调onRequestPermissionsResult（int，String []，int []）覆盖方法
        } else {
            //Android版本低于6.0或已授予权限。
            takePhoto();
        }
    }

    //用户选择允许或需要后，会回调onRequestPermissionsResult方法, 该方法类似于onActivityResult
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == TAKE_PHOTO_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 已授予权限
                takePhoto();
            } else {
                Toast.makeText(this, "未授权", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_photo:
                takePhotoAction();
                break;
            case R.id.choose_from_album:
                chooseFromAlbum();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    //判断是否是AndroidN以及更高的版本
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        Uri contentUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".fileProvider", file);
                        intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
                    } else {
                        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                    startActivityForResult(intent,CROP_PHOTO);

                    /*Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image");
                    intent.putExtra("scale", true);
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(intent, CROP_PHOTO);//启动剪裁程序
                    } else {
                        ContentValues contentValues = new ContentValues(1);
                        contentValues.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
                        Uri uri = getBaseContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        startActivityForResult(intent, CROP_PHOTO);
                    }*/
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
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

    private void takePhoto() {
        //创建file对象，用于拍照后的图片。Environment.getExternalStorageDirectory()获取根目录
        file = new File(Environment.getExternalStorageDirectory(), "test.jpg");
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        /*获取当前系统的android版本号*/
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        Log.e("ChoosePic", "currentApiVersion:" + currentApiVersion);
        imageUri = Uri.fromFile(file);
        if (currentApiVersion < 24) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, TAKE_PHOTO);//启动相机程序
        } else {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
            Uri uri = getBaseContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, TAKE_PHOTO);
        }

    }

    private void chooseFromAlbum() {
        //创建file对象，用于拍照后的图片。Environment.getExternalStorageDirectory()获取根目录
        File outputImage = new File(Environment.getExternalStorageDirectory(), "test2.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageUri = Uri.fromFile(outputImage);
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.putExtra("crop",true);
        intent.putExtra("scale",true);
        /*获取当前系统的android版本号*/
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, CROP_PHOTO);
        Log.i("ChoosePic","执行完成");
    }

}
