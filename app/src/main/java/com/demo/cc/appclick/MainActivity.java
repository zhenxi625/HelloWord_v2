package com.demo.cc.appclick;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.cc.firstcode.BaseActivity;
import com.demo.cc.firstcode.LiaoTian;
import com.demo.cc.firstcode.Second2Activity;
import com.demo.cc.firstcode.UISizeLearn;
import com.demo.cc.firstcode.contact.ContactsLearn;
import com.demo.cc.firstcode.fragment.MyFragment;
import com.demo.cc.firstcode.layout.ListViewLearn;
import com.demo.cc.firstcode.layout.UIWidgetLearn;
import com.demo.cc.firstcode.newsdemo.NewsDemo;
import com.demo.cc.firstcode.notify.NotificationActivity;
import com.demo.cc.firstcode.receiver.LoginActivity;
import com.demo.cc.firstcode.savedata.FileType;
import com.demo.cc.firstcode.savedata.MyDatabaseHelper;
import com.demo.cc.firstcode.savedata.SqliteLearn;
import com.demo.cc.learn.ContactActivity;
import com.demo.cc.learn.TableLearn;
import com.demo.cc.learn.WebActivity;
import com.demo.cc.learn.WebViewLearn;
import com.demo.cc.model.Person;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private NetworkChangeReceiver networkChangeReceiver;
    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;
    private EditText editText;
    private MyDatabaseHelper dbHelper;

    //定义字体大小菜单项的标识
    final int FONT_10 = 14;
    final int FONT_12 = 0x112;
    final int FONT_14 = 0x113;
    final int FONT_16 = 0x114;

    final int FONT_18 = 0x115;

    //定义普通菜单项标识
    final int PLAIN_ITEM = 0x11b;
    //定义字体颜色菜单项标识
    final int FONT_RED = 0xFF6EB4;
    final int FONT_BLUE = 0x8968CD;

    final int FONT_GREEN = 0x76EE00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置容器，引用布局，相当于引用某个界面
        setContentView(R.layout.activity_main);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,2);

        //监听网络变化
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);

        Button forceOffline = (Button) findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(this);

        TextView notify = (TextView) findViewById(R.id.notify);
        notify.setOnClickListener(this);

        TextView contacts_learn = (TextView) findViewById(R.id.contacts_learn);
        contacts_learn.setOnClickListener(this);

        TextView tran = (TextView) findViewById(R.id.tran);
        tran.setOnClickListener(this);

        TextView fileTpye = (TextView) findViewById(R.id.fileSave);
        fileTpye.setOnClickListener(this);

        TextView sqLite = (TextView) findViewById(R.id.sqLite);
        sqLite.setOnClickListener(this);

        TextView insertData = (TextView) findViewById(R.id.insertData);
        insertData.setOnClickListener(this);

        TextView webView = (TextView) findViewById(R.id.webView);
        webView.setOnClickListener(this);

        TextView webViewDeo = (TextView) findViewById(R.id.webViewDeo);
        webViewDeo.setOnClickListener(this);

        TextView contactWebView = (TextView) findViewById(R.id.contact_webView_learn);
        contactWebView.setOnClickListener(this);

        TextView button = (TextView) findViewById(R.id.button);
        button.setOnClickListener(this);

        TextView antoButton = (TextView) findViewById(R.id.antoButton);
        antoButton.setOnClickListener(this);

        TextView chooseDate = (TextView) findViewById(R.id.chooseDate);
        chooseDate.setOnClickListener(this);

        TextView showBig = (TextView) findViewById(R.id.showBig);
        showBig.setOnClickListener(this);

        TextView goTabHost = (TextView) findViewById(R.id.goTabHost);
        goTabHost.setOnClickListener(this);

        TextView alarm = (TextView) findViewById(R.id.alarm);
        alarm.setOnClickListener(this);

        TextView drawApi = (TextView) findViewById(R.id.drawApi);
        drawApi.setOnClickListener(this);

        TextView createUser = (TextView) findViewById(R.id.createUser);
        createUser.setOnClickListener(this);

        TextView firstCode = (TextView) findViewById(R.id.firstCode);
        firstCode.setOnClickListener(this);

        TextView table_row_button = (TextView) findViewById(R.id.table_row_button);
        table_row_button.setOnClickListener(this);

        TextView uiWidget = (TextView) findViewById(R.id.uiWidget);
        uiWidget.setOnClickListener(this);

        TextView uiLayout = (TextView) findViewById(R.id.uiLayout);
        uiLayout.setOnClickListener(this);

        TextView frame_layout = (TextView) findViewById(R.id.frame_layout);
        frame_layout.setOnClickListener(this);

        TextView table_layout = (TextView) findViewById(R.id.table_layout);
        table_layout.setOnClickListener(this);

        TextView title2 = (TextView) findViewById(R.id.title2);
        title2.setOnClickListener(this);

        TextView list_view = (TextView) findViewById(R.id.list_view);
        list_view.setOnClickListener(this);

        TextView uisize = (TextView) findViewById(R.id.ui_size);
        uisize.setOnClickListener(this);

        TextView liaoTian = (TextView) findViewById(R.id.liaoTian);
        liaoTian.setOnClickListener(this);

        TextView fragment = (TextView) findViewById(R.id.fragment);
        fragment.setOnClickListener(this);

        TextView newsDemo = (TextView) findViewById(R.id.news_demo);
        newsDemo.setOnClickListener(this);

        TextView sendReceiver = (TextView) findViewById(R.id.send_receiver);
        sendReceiver.setOnClickListener(this);

        TextView liReceiver = (TextView) findViewById(R.id.li_receiver);
        liReceiver.setOnClickListener(this);

        TextView contact = (TextView) findViewById(R.id.contact);
        contact.setOnClickListener(this);

        editText = (EditText) findViewById(R.id.editText2);
        Log.i("cxl", "创建");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("cxl", "启动");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("cxl", "重新开始");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("cxl", "暂停");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("cxl", "停止");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //取消动态注册的广播接收器
        unregisterReceiver(networkChangeReceiver);

        localBroadcastManager.unregisterReceiver(localReceiver);
        Log.i("cxl", "销毁");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 添加OptionsMenu菜单项
        /* menu.add(groupId, itemId, order, title)
         * groupId:菜单项所在的组
         * itemId:菜单项编号
         * order:排序
         * title：标题
         * setIcon()方法为菜单设置图标，这里使用的是系统自带的图标，同学们留意一下,
         * 以 android.R开头的资源是系统提供的，我们自己提供的资源是以R开头的  */
        menu.add(0, 1, Menu.NONE, "蓝牙发送").setIcon(android.R.drawable.ic_menu_send);
        //添加子菜单
        SubMenu subMenu = menu.addSubMenu(0, 2, Menu.NONE, "重要程度>>").setIcon(android.R.drawable.ic_menu_share);
        //添加子菜单项
        subMenu.add(2, 201, 1, "☆☆☆☆☆");
        subMenu.add(2, 202, 2, "☆☆☆");
        subMenu.add(2, 203, 3, "☆");

        menu.add(0, 3, Menu.NONE, "重命名").setIcon(android.R.drawable.ic_menu_edit);
        menu.add(0, 4, Menu.NONE, "删除").setIcon(android.R.drawable.ic_menu_close_clear_cancel);


        //向menu中添加子菜单
        SubMenu fontMenu = menu.addSubMenu("字体大小");
        //设置菜单的图标
        fontMenu.setIcon(R.mipmap.cc2);

        //设置菜单头的图标
        fontMenu.setHeaderIcon(R.mipmap.icon);

        //设置菜单头的标题
        fontMenu.setHeaderTitle("选择字体大小");
        fontMenu.add(0, FONT_10, 0, "10号字体大小");
        fontMenu.add(0, FONT_12, 0, "12号字体大小");
        fontMenu.add(0, FONT_14, 0, "14号字体大小");
        fontMenu.add(0, FONT_16, 0, "16号字体大小");
        fontMenu.add(0, FONT_18, 0, "18号字体大小");
        //向menu中添加普通菜单项
        fontMenu.add(0, PLAIN_ITEM, 0, "普通菜单项");

        //向menu中添加子菜单
        SubMenu colorMenu = menu.addSubMenu("字体颜色");
        //设置菜单头的图标
        colorMenu.setIcon(R.mipmap.cc1);
        //设置菜单头的标题
        colorMenu.setHeaderTitle("选择字体颜色");
        colorMenu.add(21, FONT_RED, 23, "红色");
        colorMenu.add(0, FONT_GREEN, 0, "绿色");
        colorMenu.add(0, FONT_BLUE, 0, "蓝色");

        return super.onCreateOptionsMenu(menu);
    }

    public void simpleNotice(View view) {
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //此Builder为android.support.v4.app.NotificationCompat.Builder中的，下同。
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        //系统收到通知时，通知栏上面显示的文字。
        mBuilder.setTicker("天津，晴，2～15度，微风");
        //显示在通知栏上的小图标
        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        //通知标题
        mBuilder.setContentTitle("天气预报");
        //通知内容
        mBuilder.setContentText("天津，晴，2～15度，微风");

        //设置大图标，即通知条上左侧的图片（如果只设置了小图标，则此处会显示小图标）
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        //显示在小图标左侧的数字
        mBuilder.setNumber(6);

        //设置为不可清除模式
        mBuilder.setOngoing(true);

        //显示通知，id必须不重复，否则新的通知会覆盖旧的通知（利用这一特性，可以对通知进行更新）
        nm.notify(1, mBuilder.build());
    }

    //点击通知进入一个Activity，点击返回时进入指定页面。
    public void resultActivityBackApp(View view) {
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setTicker("通知标题2");
        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setContentTitle("通知标题2");
        mBuilder.setContentText("点击通知进入一个Activity，点击返回时进入指定页面。");

        //设置点击一次后消失（如果没有点击事件，则该方法无效。）
        mBuilder.setAutoCancel(true);

        //点击通知之后需要跳转的页面
        Intent resultIntent = new Intent(this, NotificationActivity.class);

        //使用TaskStackBuilder为“通知页面”设置返回关系
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        //为点击通知后打开的页面设定 返回 页面。（在manifest中指定）
        stackBuilder.addParentStack(NotificationActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent pIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pIntent);
        // mId allows you to update the notification later on.
        nm.notify(2, mBuilder.build());
    }

    //点击通知进入一个Activity，点击返回时回到桌面
    public void resultActivityBackHome(View view) {
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setTicker("通知标题3");
        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setContentTitle("通知标题3");
        mBuilder.setContentText("点击通知进入一个Activity，点击返回时回到桌面");

        //设置点击一次后消失（如果没有点击事件，则该方法无效。）
        mBuilder.setAutoCancel(true);

        Intent notifyIntent = new Intent(this, MainActivity.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pIntent);

        nm.notify(3, mBuilder.build());
    }

    //带进度条的通知
    public void progressNotice(View view) {
        final NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setTicker("通知标题4");
        mBuilder.setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.drawable.ic_launcher);

        // 在后台线程中启动长时间操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                int progress;
                for (progress = 0; progress <= 100; progress++) {
                    //将进度指示器设置为最大值，当前完成百分比，和确定状态
                    mBuilder.setProgress(100, progress, false);

                    //不明确进度的进度条
//                    mBuilder.setProgress(0, 0, true);

                    nm.notify(4, mBuilder.build());
                    // 模拟延时
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 当循环完成后，更新通知
                mBuilder.setContentText("Download complete");
                // 删除进度条
                mBuilder.setProgress(0, 0, false);
                nm.notify(4, mBuilder.build());
            }
        }
        ).start();
    }

    //扩展布局的通知。按住通知条下滑，可以查看更详细的内容
    public void expandLayoutNotice(View view) {
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setTicker("通知标题5");
        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setContentTitle("通知标题5");
        mBuilder.setContentText("按住通知下拉可显示扩展布局");

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = new String[]{"Beijing", "Tianjin", "Shanghai", "Chongqing"};
        // 设置扩展布局的标题
        inboxStyle.setBigContentTitle("Event tracker details:");

        for (String s : events) {
            inboxStyle.addLine(s);
        }
        mBuilder.setStyle(inboxStyle);

        nm.notify(5, mBuilder.build());
    }

    /*//自定义布局的通知
    public void customLayoutNotice(View view) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setTicker("通知标题6");
        mBuilder.setTicker("通知标题6");
        mBuilder.setSmallIcon(R.drawable.ic_launcher);

        //R.layout.custom_layout_notice
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.custom_layout_notice );
        mBuilder.setContent(remoteViews);
        //为RemoteViews上的按钮设置文字
        remoteViews.setCharSequence(R.id.custom_layout_button1, setText, Button1);
        remoteViews.setCharSequence(R.id.custom_layout_button2, setText, Button2);

        //为RemoteViews上的按钮设置点击事件
        Intent intent1 = new Intent(this, CustomLayoutResultActivity.class);
        intent1.putExtra(content, From button1 click!);
        PendingIntent pIntentButton1 = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.custom_layout_button1, pIntentButton1);

        Intent intent2 = new Intent(this, CustomLayoutResultActivity.class);
        intent2.putExtra(content, From button2 click!);
        PendingIntent pIntentButton2 = PendingIntent.getActivity(this, 1, intent2, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.custom_layout_button2, pIntentButton2);

        nm.notify(6, mBuilder.build());
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notify:
                simpleNotice(v);
                resultActivityBackApp(v);
                resultActivityBackHome(v);
                progressNotice(v);
                expandLayoutNotice(v);
                break;
            case R.id.contacts_learn:
                Intent contactsLearn = new Intent(this, ContactsLearn.class);
                this.startActivity(contactsLearn);
                break;
            case R.id.tran:
                SQLiteDatabase database = dbHelper.getWritableDatabase();
                database.beginTransaction();
                try {
                    database.delete("book",null,null);
                    /*if (true){
                        //手动抛出异常，让事务失败
                        throw new NullPointerException();
                    }*/
                    ContentValues values = new ContentValues();
                    values.put("name","ttt");
                    values.put("author","aaa");
                    values.put("pages",12);
                    values.put("price",11.22);
                    database.insert("book",null,values);
                    database.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    database.endTransaction();
                }
                break;
            case R.id.sqLite:
                dbHelper.getWritableDatabase();
                break;
            case R.id.insertData:
                Intent sqliteLearn = new Intent(this, SqliteLearn.class);
                this.startActivity(sqliteLearn);
                break;
            case R.id.fileSave:
                Intent fileSave = new Intent(this, FileType.class);
                this.startActivity(fileSave);
                break;
            case R.id.webView:
                Intent webView = new Intent(this, WebViewLearn.class);
                this.startActivity(webView);
                break;
            case R.id.webViewDeo:
                Intent webViewDeo = new Intent(this, WebActivity.class);
                this.startActivity(webViewDeo);
                break;
            case R.id.contact_webView_learn:
                Intent contactWebViewLearn = new Intent(this, ContactActivity.class);
                this.startActivity(contactWebViewLearn);
                break;
            case R.id.button:
                Log.i("cxl", "启动第二个Activity...");
                Intent intent = new Intent(this, SecondActivity.class);
                this.startActivity(intent);
                break;
            case R.id.antoButton:
                Log.i("cxl", "启动输入自动提示Activity...");
                Intent aotu = new Intent(this, AutoTextTest.class);
                this.startActivity(aotu);
                break;
            case R.id.chooseDate:
                Log.i("cxl", "启动日期选择Activity...");
                Intent chooseDate = new Intent(this, ChooseDate.class);
                this.startActivity(chooseDate);
                break;
            case R.id.showBig:
                Log.i("cxl", "启动展示大图Activity...");
                Intent showBig = new Intent(this, GridViewTest.class);
                this.startActivity(showBig);
                break;
            case R.id.goTabHost:
                Log.i("cxl", "启动goTabHost...");
                Intent goTabHost = new Intent(this, TabHostTest.class);
                this.startActivity(goTabHost);
                break;
            case R.id.alarm:
                Intent alarm = new Intent(this, Alarm.class);
                this.startActivity(alarm);
                break;
            case R.id.drawApi:
                setContentView(R.layout.canvastest);
                break;
            case R.id.createUser:
                setContentView(R.layout.bundletest);
                findViewById(R.id.buttonRegistered).setOnClickListener(this);
                break;
            case R.id.buttonRegistered:
                addUserOnClick();
                break;
            case R.id.contact:
                Intent contentProviderTest = new Intent(this, ContentProviderTest.class);
                this.startActivity(contentProviderTest);
                break;
            case R.id.firstCode:
                Intent firstCodeIntent = new Intent(this, Second2Activity.class);
                startActivityForResult(firstCodeIntent, 1);
                break;
            case R.id.table_row_button:
                Intent table_row_button = new Intent(this, TableLearn.class);
                this.startActivity(table_row_button);
                break;
            case R.id.uiWidget:
                Intent uiWidget = new Intent(this, UIWidgetLearn.class);
                this.startActivity(uiWidget);
                break;
            case R.id.uiLayout:
                setContentView(R.layout.uilayoutlearn);
                break;
            case R.id.frame_layout:
                setContentView(R.layout.framelayoutlearn);
                break;
            case R.id.table_layout:
                Intent login = new Intent(this, LoginActivity.class);
                this.startActivity(login);
                break;
            case R.id.title2:
                setContentView(R.layout.title);
                break;
            case R.id.list_view:
                Intent listView = new Intent(this, ListViewLearn.class);
                this.startActivity(listView);
                break;
            case R.id.ui_size:
                Intent uiSize = new Intent(this, UISizeLearn.class);
                this.startActivity(uiSize);
                break;
            case R.id.liaoTian:
                Log.i("cxl", "聊天");
                Intent liaoTian = new Intent(this, LiaoTian.class);
                this.startActivity(liaoTian);
                break;
            case R.id.fragment:
                Intent myFragment = new Intent(this, MyFragment.class);
                this.startActivity(myFragment);
                break;
            case R.id.news_demo:
                Log.i("cxl", "新闻");
                Intent news_demo = new Intent(this, NewsDemo.class);
                this.startActivity(news_demo);
                break;
            case R.id.send_receiver:
                Log.i("cxl", "接收广播");
                Intent sendReceiver = new Intent("com.demo.cc.firstcode.receiver.MY_BROADCAST");
                sendOrderedBroadcast(sendReceiver, null);//有序广播
//                sendBroadcast(sendReceiver);
                break;

            case R.id.li_receiver:
                Log.i("cxl", "本地广播（无法通过静态注册方式接收）");
                Intent liReceiver = new Intent("com.demo.cc.appclick.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(liReceiver);//发送本地广播
                IntentFilter intentFilter2 = new IntentFilter();
                intentFilter2.addAction("com.demo.cc.appclick.LOCAL_BROADCAST");
                localReceiver = new LocalReceiver();
                localBroadcastManager.registerReceiver(localReceiver, intentFilter2);
                break;
            case R.id.force_offline:
                Log.i("cxl", "强制下线");
                Intent forceOffline = new Intent("com.demo.cc.firstcode.receiver.FORCE_OFFLINE");
                sendBroadcast(forceOffline);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (requestCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d("MainActivity", returnedData);
                }
                break;
            default:
        }
    }

    //点击注册，显示注册结果
    public void addUserOnClick() {
        //获取只能被本应用程序读写的SharedPreferences对象
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //存入当前时间
        editor.putString("time", sdf.format(new Date()));
        //存入一个随机数
        editor.putInt("random", (int) (Math.random() * 100));
        //提交存入的数据
        editor.apply();

        EditText editName = (EditText) findViewById(R.id.editName);
        EditText editPwd = (EditText) findViewById(R.id.editPwd);
        RadioButton male = (RadioButton) findViewById(R.id.male);
        String gender = male.isChecked() ? "男" : "女";
        Person person = new Person(editName.getText().toString(), editPwd.getText().toString(), gender);

        //创建一个Bundle对象
        Bundle data = new Bundle();
        data.putSerializable("person", person);

        //创建一个Intent
        Intent intent = new Intent(this, BundleResult.class);
        intent.putExtras(data);

        //启动Intent对应的Activity
        startActivity(intent);
    }

    //菜单被单击后的回调方法
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        //判断单击的是哪个菜单项，并针对性的作出响应
        switch (menuItem.getItemId()) {
            case 1:
                Toast.makeText(getApplicationContext(), "蓝牙发送……", Toast.LENGTH_SHORT).show();
                return true;
            case 201:
                Toast.makeText(getApplicationContext(), "非常重要：☆☆☆☆☆", Toast.LENGTH_SHORT).show();
                return true;
            case 202:
                Toast.makeText(getApplicationContext(), "重要：☆☆☆", Toast.LENGTH_SHORT).show();
                return true;
            case 203:
                Toast.makeText(getApplicationContext(), "普通：☆", Toast.LENGTH_SHORT).show();
                return true;
            case 3:
                Toast.makeText(getApplicationContext(), "重命名……", Toast.LENGTH_SHORT).show();
                return true;
            case 4:
                Toast.makeText(getApplicationContext(), "删除……", Toast.LENGTH_SHORT).show();
                return true;
            case FONT_10:
                editText.setTextSize(10 * 2);
                break;
            case FONT_12:
                editText.setTextSize(12 * 2);
                break;
            case FONT_14:
                editText.setTextSize(14 * 2);
                break;
            case FONT_16:
                editText.setTextSize(16 * 2);
                break;
            case FONT_18:
                editText.setTextSize(18 * 2);
                break;
            case FONT_RED:
                editText.setTextColor(Color.RED);
                break;
            case FONT_GREEN:
                editText.setTextColor(Color.GREEN);
                break;
            case FONT_BLUE:
                editText.setTextColor(Color.BLUE);
                break;
            case PLAIN_ITEM:
                Toast.makeText(this, "单击了普通菜单", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "网络已连接", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "网络连接不可用", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "本地广播", Toast.LENGTH_SHORT).show();
        }
    }
}
