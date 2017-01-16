package com.demo.cc.firstcode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.demo.cc.appclick.R;

/**
 * Created by ChenXingLing on 2017/1/5.
 */

public class Second2Activity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Second2Activity", "Task id is:" +getTaskId());
        setContentView(R.layout.seond_layout);
        Button button = (Button) findViewById(R.id.button_2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test2();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Second2Activity", "销毁");
    }

    //重写Back键事件
    @Override
    public void onBackPressed(){
        test2();
    }

    private void test2(){
        Intent intent = new Intent(Second2Activity.this,ThirdActivity.class);
        startActivity(intent);
    }

    private void test(){
        Intent intent = new Intent();
        intent.putExtra("data_return","Hello MainActivity");
        setResult(RESULT_OK,intent);//向上一个活动返回数据
        finish();
    }

    public static void actionStart(Context context,String data1,String data2){
        Intent intent = new Intent(context,Second2Activity.class);
        intent.putExtra("param1",data1);
        intent.putExtra("param2",data2);
        context.startActivity(intent);
    }
}
