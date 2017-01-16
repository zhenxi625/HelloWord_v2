package com.demo.cc.appclick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by cc on 2016/11/4.
 */

public class ForDrawView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawview);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        Toast toast = Toast.makeText(this,motionEvent.getX()+"-"+motionEvent.getY(),Toast.LENGTH_SHORT);
        toast.show();
        Log.i("cxl",motionEvent.getX()+"-"+motionEvent.getY());
        return true;
    }

}
