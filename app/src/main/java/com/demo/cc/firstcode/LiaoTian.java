package com.demo.cc.firstcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.demo.cc.appclick.R;
import com.demo.cc.model.Msg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenXingLing on 2017/1/6.
 */

public class LiaoTian extends AppCompatActivity {

    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private MsgAdapter adapter;
    private List<Msg> msgList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置容器，引用布局，相当于引用某个界面
        setContentView(R.layout.liaotiao);
        initMsgs();
        adapter = new MsgAdapter(LiaoTian.this,R.layout.msg_item,msgList);
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();//当有消息是刷新listView中的显示
                    msgListView.setSelection(msgList.size());//将listView定位到最后一行
                    inputText.setText("");//清空输入框
                }
            }
        });
    }

    private void initMsgs(){
        Msg msg1 = new Msg("你好",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("你是谁",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("你猜",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
