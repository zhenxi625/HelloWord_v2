package com.demo.cc.firstcode.contact;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.demo.cc.appclick.R;
import com.demo.cc.firstcode.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenXingLing on 2017/1/12.
 */

public class ContactsLearn extends BaseActivity {

    ListView contactsView;
    List<String> contactsList = new ArrayList<>();

    //请求READ_CONTACTS权限结果变量。 它可以是任何大于0的数字
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactslearn);
        this.contactsView = (ListView) findViewById(R.id.contacts_view);
        //读取并显示联系人
        showContacts();
    }

    private void showContacts() {
        //检查SDK版本以及是否已授予该权限。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //此后，您等待回调onRequestPermissionsResult（int，String []，int []）覆盖方法
        } else {
            //Android版本低于6.0或已授予权限。
            List<String> contacts = getContactNames();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
            contactsView.setAdapter(adapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 已授予权限
                showContacts();
            } else {
                Toast.makeText(this, "未授予程序读取联系人权限，无法显示联系人", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private List<String> getContactNames() {
        List<String> contacts = new ArrayList<>();
        // 获取ContentResolver
        ContentResolver cr = getContentResolver();
        // 获取所有联系人的游标
//        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        Cursor cursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        // 将游标移动到第一个。检查游标是否为空。
        if (cursor.moveToFirst()) {
            // 通过游标迭代
            do {
                // 获取联系人姓名
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contacts.add(name + "\n(" + phone + ")");
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contacts;
    }

}
