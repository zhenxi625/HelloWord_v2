package com.demo.cc.appclick;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cc on 2016/11/3.
 */

public class GridViewTest extends AppCompatActivity implements View.OnClickListener{

    int[] imageIds = new int[]{
            R.mipmap.cc1,
            R.mipmap.cc2,
            R.mipmap.cc3,
            R.mipmap.cc4,
            R.mipmap.cc5,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);

        Button returnButton = (Button) findViewById(R.id.returnButton);
        returnButton.setOnClickListener(this);

        List<Map<String, Object>> listItems = new ArrayList<>();
        for (int imageId : imageIds) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("image", imageId);
            listItems.add(listItem);
        }

        final ImageSwitcher imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this,android.R.anim.fade_out));
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(GridViewTest.this);
                imageView.setBackgroundColor(0xff0000);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT
                ));
                return imageView;
            }
        });

        //创建一个SimpleAdapter  使用layout/cell作为界面布局
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.cell, new String[]{"image"}, new int[]{R.id.imageView2});
        GridView gridView = (GridView) findViewById(R.id.gridView);
        //为gridView设置Adapter
        gridView.setAdapter(simpleAdapter);

        //添加列表项被选中的监听器
        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //显示当前被选中的图片
                imageSwitcher.setImageResource(imageIds[position % imageIds.length]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //添加列表项被单击的监听器
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //显示被单击的图片的图片
                imageSwitcher.setImageResource(imageIds[position % imageIds.length]);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,MainActivity.class);
        this.startActivity(intent);
    }
}
