package com.demo.cc.appclick;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by cc on 2016/11/3.
 */

public class AutoTextTest extends AppCompatActivity implements View.OnClickListener {

    String[] books = new String[]{
            "测试1", "sd", "asd", "哈哈", "可是", "bds", "dfgf", "jyyy"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //設置容器，引用布局，相当于引用某个界面
        setContentView(R.layout.autotext);

        //创建一个ArrayAdapter，封装数组
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,//下拉框显示的样式
                books);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        textView.setAdapter(arrayAdapter);

        Button returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(this);

        Button showDialog = (Button) findViewById(R.id.showDialog);
        showDialog.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.returnButton:
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                break;
            case R.id.showDialog:
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setIcon(R.mipmap.icon);
                builder.setTitle("自定义普通对话框");
                builder.setMessage("简单提示框");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) findViewById(R.id.editText);
                        editText.setText("单击了确定按钮");
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText) findViewById(R.id.editText);
                        editText.setText("单击了取消按钮");
                    }
                });
                builder.create().show();
                break;
        }

    }
}
