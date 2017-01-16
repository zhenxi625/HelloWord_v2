package com.demo.cc.firstcode.savedata;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.demo.cc.appclick.R;
import com.demo.cc.firstcode.BaseActivity;

/**
 * Created by ChenXingLing on 2017/1/12.
 */

public class SqliteLearn extends BaseActivity implements View.OnClickListener {

    SQLiteDatabase database;
    Button btn = null;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlitelearn);

        //创建或打开数据库
        database = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString() + "/test.db", null);
        listView = (ListView) findViewById(R.id.show);

        //rawQuery 查询
        database.execSQL("create table news_inf(_id integer primary key autoincrement," +
                "news_title varchar(50)," +
                "news_content varchar(255))");
        Cursor cursor = database.rawQuery("select * from news_inf", null);
        inflateList(cursor);

        btn = (Button) findViewById(R.id.ok);
        btn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出程序时 关闭SQLiteDatabase
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
                //获取用户输入
                String title = ((EditText) findViewById(R.id.title)).getText().toString();
                String content = ((EditText) findViewById(R.id.content)).getText().toString();

                try {
                    insertData(database, title, content);
                    Cursor cursor = database.rawQuery("select * from news_inf", null);
                    inflateList(cursor);
                } catch (Exception e) {
                    //执行DDL创建表
                    database.execSQL("create table news_inf(_id integer primary key autoincrement," +
                            "news_title varchar(50)," +
                            "news_content varchar(255))");
                    //执行insert插入语句
                    insertData(database, title, content);
                    //执行查询
                    Cursor cursor = database.rawQuery("select * from news_inf", null);
                    inflateList(cursor);
                }
                break;
            default:
                break;
        }
    }

    private void insertData(SQLiteDatabase database, String title, String content) {
        //执行插入语句
        database.execSQL("insert into news_inf values(null,?,?)", new String[]{title, content});
    }

    private void inflateList(Cursor cursor) {
        /*
        SimpleCursorAdapter(Context context,int layout,Cursor c,String[] from,int[] to,int flags).
        最后一个参数flags是一个标识，标识当数据改变调用onContentChanged()的时候，
        是否通知ContentProvider数据的改变，如果无需监听ContentProvider的改变，则可以传0。
        对于SimpleCursorAdapter适配器的Cursor的改变，可以使用SimpleCursorAdapter.swapCursor(Cursor)方法，
        它会与旧的Cursor互换，并且返回旧的Cursor。
        */
        //填充SimpleCursorAdapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.line, cursor,
                new String[]{"news_title", "news_content"},
                new int[]{R.id.my_title, R.id.my_content}, 0);
        //显示数据
        listView.setAdapter(adapter);
    }
}
