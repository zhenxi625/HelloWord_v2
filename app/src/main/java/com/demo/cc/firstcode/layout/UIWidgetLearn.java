package com.demo.cc.firstcode.layout;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.demo.cc.appclick.MainActivity;
import com.demo.cc.appclick.R;
import com.demo.cc.firstcode.BaseActivity;

public class UIWidgetLearn extends BaseActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private EditText editText;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uiwidgetlearn);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);
        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.edit_text);
        imageView = (ImageView) findViewById(R.id.image_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                //提示框
                String inputText = editText.getText().toString();
                Toast.makeText(UIWidgetLearn.this, inputText, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                //改变图片
                imageView.setImageResource(R.drawable.a222);
                break;
            case R.id.button3:
                //显示\取消进度条
                if (progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
                break;
            case R.id.button4:
                //进度条改为水平，点击一下进度条增加10进度
                int progress = progressBar.getProgress();
                progress = progress + 10;
                progressBar.setProgress(progress);
                break;
            case R.id.button5:
                //确定、取消对话框
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(UIWidgetLearn.this);
                alertDialog.setTitle("this is dialog");
                alertDialog.setMessage("something");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
                break;
            case R.id.button6:
                //不可取消对话框 取消方法:dialog.dismiss();
                ProgressDialog dialog = new ProgressDialog(UIWidgetLearn.this);
                dialog.setTitle("this is dialog");
                dialog.setMessage("something");
                dialog.setCancelable(true);
                dialog.show();
                break;
            case R.id.button7:
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                break;
            default:
                break;
        }
    }
}
