package com.demo.cc.appclick;

import android.Manifest;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.cc.firstcode.BaseActivity;

import java.util.ArrayList;

/**
 * Created by cc on 2016/11/9.
 */

public class ContentProviderTest extends BaseActivity implements View.OnClickListener {

    //请求READ_CONTACTS权限结果变量。 它可以是任何大于0的数字
    private static final int PERMISSIONS_REQUEST_WRITE_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentprovidertest);

        Button search = (Button) findViewById(R.id.search);
        Button add = (Button) findViewById(R.id.add);

        add.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    private void writeContacts() {
        //检查SDK版本以及是否已授予该权限。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS}, PERMISSIONS_REQUEST_WRITE_CONTACTS);
            //此后，您等待回调onRequestPermissionsResult（int，String []，int []）覆盖方法
        } else {
            //Android版本低于6.0或已授予权限。
            add();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_WRITE_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 已授予权限
                writeContacts();
            } else {
                Toast.makeText(this, "未授予程序读取联系人权限，无法显示联系人", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void add() {
        String name = ((EditText) findViewById(R.id.name)).getText().toString();
        String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        ContentValues values = new ContentValues();
        //向ContactsContract.RawContacts.CONTENT_URI执行一个空值插入，
        //目的是获取系统返回的rawContactId
        Uri rawContentUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContentUri);
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        //设置内容类型
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        //设置联系人名字
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
        //向联系人uri添加联系人名字
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
        //设置联系人号码
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);
        //设置号码类型
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
        //向联系人uri添加号码
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        values.clear();
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
        //设置联系人email地址
        values.put(ContactsContract.CommonDataKinds.Email.DATA, email);
        //设置该邮件类型
        values.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
        //向联系人Email URI添加email数据
        getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        Toast.makeText(ContentProviderTest.this, "联系人添加成功", Toast.LENGTH_SHORT).show();
    }

    public void search() {
        //定义两个List来封装系统的联系人信息、指定联系人的电话号码
        final ArrayList<String> names = new ArrayList<>();
        final ArrayList<ArrayList<String>> details = new ArrayList<>();
        //使用contentResolver查找联系人数据
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        //遍历查询结果，获取系统中所有联系人
        while (cursor.moveToNext()) {
            //获取联系人ID
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            //获取联系人名字
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            names.add(name);

            //使用ContentResolver查找联系人号码
            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId, null, null);
            ArrayList<String> detail = new ArrayList<>();
            //遍历查询结果，获取该联系人的多个号码
            while (phones.moveToNext()) {
                //获取查询结果中电话号码列的数据
                String phoneNumber = phones.getString(phones.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                ));
                detail.add("电话号码" + phoneNumber);
            }
            phones.close();
            //使用ContentResolver查找联系人Email
            Cursor emails = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + contactId, null, null
            );
            //遍历查询结果，获取该联系人多个Email地址
            while (emails.moveToNext()) {
                //获取查询结果中Email列的数据
                String emailAddress = emails.getString(emails.getColumnIndex(
                        ContactsContract.CommonDataKinds.Email.DATA
                ));
                detail.add("邮件地址" + emailAddress);
            }
            emails.close();
            details.add(detail);
        }
        cursor.close();
        //加载result.xml界面布局代表的视图
        View resultDialog = getLayoutInflater().inflate(R.layout.contentproviderresult, null);
        //获取ExpandableListView
        ExpandableListView list = (ExpandableListView) resultDialog.findViewById(R.id.list);
        //创建ExpandableListAdapter对象
        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return names.size();
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return details.get(groupPosition).size();
            }

            //获取指定组位置处的数据
            @Override
            public Object getGroup(int groupPosition) {
                return names.get(groupPosition);
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return details.get(groupPosition).get(childPosition);
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            //改选项决定每个组的外观
            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                return textView;
            }

            private TextView getTextView() {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 64
                );
                TextView textView = new TextView(ContentProviderTest.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setPadding(36, 0, 0, 0);
                textView.setTextSize(20);
                return textView;
            }

            //该方法决定每个子选项的外观
            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView textView = getTextView();
                textView.setText(getChild(groupPosition, childPosition).toString());
                return textView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };
        //为ExpandableListView设置Adapter对象
        list.setAdapter(adapter);
        //对话框显示结果
        new AlertDialog.Builder(ContentProviderTest.this)
                .setView(resultDialog)
                .setPositiveButton("确定", null).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                writeContacts();
                break;
            case R.id.search:
                search();
                break;
            default:
                break;
        }
    }
}
