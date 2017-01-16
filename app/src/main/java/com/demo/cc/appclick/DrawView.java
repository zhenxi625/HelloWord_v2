package com.demo.cc.appclick;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by cc on 2016/11/4.
 */

public class DrawView extends View{

    public float currentX = 40;
    public float currentY = 50;


    public DrawView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
    }

    //绘图
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);


        //创建画笔
        Paint paint = new Paint();

        //设置画笔的颜色
        paint.setColor(Color.RED);

        //绘制一个小圆
        canvas.drawCircle(currentX,currentY,15,paint);
    }

    //触摸事件
    public boolean onTouchEvent(MotionEvent motionEvent){
        //当前组件的两个属性
        this.currentX = motionEvent.getX();
        this.currentY = motionEvent.getY();

        //通知改组件重绘
        this.invalidate();

        //返回true表明处理方法已处理该事件
        return true;
    }


}
