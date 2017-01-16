package com.demo.cc.firstcode.savedata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ChenXingLing on 2017/1/12.
 * adb android自带查看工具目录：sdk\platform-tools
 * 1.进入到设备控制台C:\Users\ChenXingLing>adb shell
 * 2.# cd /data/data/com.demo.cc.appclick
 * 3.vbox86p:/data/data/com.demo.cc.appclick # ls
 * 4.cd databases
 * 5.# sqlite3 BookStore.db（sqlite3 +数据库名，管理数据库）
 * 6.用.table命令查看表；.schema命令查看建表的sql语句 .exit或.quit退出数据库编辑
 *
 *
 * SQLiteDatabase.query()方法，7个参数的详解:
 * 1.表名
 * 2.查询的列，不指定默认查询所有
 * 3. 4.查询某一行或某几行的数据，不指定默认查询所有
 * 5.指定要group by的列，不指定不过滤
 * 6.对group by的参数进一步过滤，不指定不过滤
 * 7.对查询结果指定排序方式，不指定为默认的排序方式
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK
            = "create table book ("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "name text,"
            + "category_id integer )";

    public static final String CREATE_CATEGORY
            = "create table category ("
            + "id integer primary key autoincrement,"
            + "category_name text,"
            + "category_code integer)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
//        Toast.makeText(mContext,"book、category表创建成功",Toast.LENGTH_SHORT).show();
    }

    //该方法用于数据库升级
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                db.execSQL(CREATE_CATEGORY);
            case 2:
                db.execSQL("alter table book add column category_id integer");
            default:
        }
    }
}
