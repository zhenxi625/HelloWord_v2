package com.demo.cc.firstcode.layout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.demo.cc.appclick.R;
import com.demo.cc.model.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenXingLing on 2017/1/6.
 */

public class ListViewLearn extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置容器，引用布局，相当于引用某个界面
        setContentView(R.layout.listview);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(ListViewLearn.this,R.layout.fruit_item,fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(ListViewLearn.this,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits(){
        Fruit fruit1 = new Fruit("苹果",R.mipmap.cc1);
        fruitList.add(fruit1);
        Fruit fruit2 = new Fruit("梨子",R.mipmap.cc2);
        fruitList.add(fruit2);
        Fruit fruit3 = new Fruit("香蕉",R.mipmap.cc3);
        fruitList.add(fruit3);
        Fruit fruit4 = new Fruit("桃子",R.mipmap.cc4);
        fruitList.add(fruit4);
        Fruit fruit5 = new Fruit("橘子",R.mipmap.cc5);
        fruitList.add(fruit5);
        Fruit fruit6 = new Fruit("葡萄",R.mipmap.cc1);
        fruitList.add(fruit6);
        Fruit fruit7 = new Fruit("柿子",R.mipmap.cc3);
        fruitList.add(fruit7);
        Fruit fruit8 = new Fruit("火龙果",R.mipmap.cc4);
        fruitList.add(fruit8);
        Fruit fruit9 = new Fruit("西瓜",R.mipmap.cc2);
        fruitList.add(fruit9);
        Fruit fruit10 = new Fruit("哈密瓜",R.mipmap.cc1);
        fruitList.add(fruit10);
    }

}
