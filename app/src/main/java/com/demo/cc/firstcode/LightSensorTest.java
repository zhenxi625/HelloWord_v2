package com.demo.cc.firstcode;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.demo.cc.appclick.R;

/**
 * Created by ChenXingLing on 2017/2/13.
 */

public class LightSensorTest extends BaseActivity implements View.OnClickListener{

    private SensorManager sensorManager;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lightsensor);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(listener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        textView = (TextView) findViewById(R.id.light_level);
        textView.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null){
            sensorManager.unregisterListener(listener);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.light_level:
                break;
            default:
                break;
        }
    }

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float value = event.values[0];
            textView.setText("当前光照等级："+value + " lx");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
