package com.demo.cc.firstcode.contact;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.demo.cc.firstcode.savedata.MyDatabaseHelper;

/**
 * Created by ChenXingLing on 2017/1/13.
 */

public class DatabaseProvider extends ContentProvider {

    public static final int BOOK_DIR = 0;
    public static final int BOOK_ITEM = 1;
    public static final int CATEGORY_DIR = 2;
    public static final int CATEGORY_ITEM = 3;

    public static final String AUTHORITY = "com.demo.cc.firstcode.contact.provider";

    private static UriMatcher uriMatcher;

    private MyDatabaseHelper dbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
        uriMatcher.addURI(AUTHORITY,"category",CATEGORY_DIR);
        uriMatcher.addURI(AUTHORITY,"category/#",CATEGORY_ITEM);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext(),"BookStore.db",null,2);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                cursor = database.query("book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                cursor = database.query("book",projection,"id=?",new String[]{bookId},null,null,sortOrder);
                break;
            case CATEGORY_DIR:
                cursor = database.query("category",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                cursor = database.query("category",projection,"id=?",new String[]{categoryId},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        /*
        * 1.必须以vnd开头
        * 2.如果内容URI以路径结尾，则后接android.cursor.dir/，如果以id结尾，则后接android.cursor.item/
        * 3.最后接上vnd.<authority>.<path>
        *   所以，对于content://com.demo.cc.firstcode.contact.provider/book这个内容URI，它所对应的MIME类型就可以写成：
        *   vnd.android.cursor.dir/vnd.com.demo.cc.firstcode.contact.provider.book
        *
        *   对于content://com.demo.cc.firstcode.contact.provider/book/1这个内容URI，它所对应的MIME类型就可以写成：
        *   vnd.android.cursor.item/vnd.com.demo.cc.firstcode.contact.provider.book
        * */
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.demo.cc.firstcode.contact.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.demo.cc.firstcode.contact.provider.book";
            case CATEGORY_DIR:
                return "vnd.android.cursor.dir/vnd.com.demo.cc.firstcode.contact.provider.category";
            case CATEGORY_ITEM:
                return "vnd.android.cursor.item/vnd.com.demo.cc.firstcode.contact.provider.category";
            default:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Uri uriReturn = null;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
            case BOOK_ITEM:
                long newBookId = database.insert("book",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/book/"+newBookId);
                break;
            case CATEGORY_DIR:
            case CATEGORY_ITEM:
                long newCategoryId = database.insert("category",null,values);
                uriReturn = Uri.parse("content://"+AUTHORITY+"/category/"+newCategoryId);
                break;
            default:
                break;
        }
        return uriReturn;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int deleteRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                deleteRows = database.delete("book",selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                deleteRows = database.delete("book","id=?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                deleteRows = database.delete("category",selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                deleteRows = database.delete("category","id=?",new String[]{categoryId});
                break;
            default:
                break;
        }
        return deleteRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int updateRows = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                updateRows = database.update("book",values,selection,selectionArgs);
                break;
            case BOOK_ITEM:
                String bookId = uri.getPathSegments().get(1);
                updateRows = database.update("book",values,"id=?",new String[]{bookId});
                break;
            case CATEGORY_DIR:
                updateRows = database.update("category",values,selection,selectionArgs);
                break;
            case CATEGORY_ITEM:
                String categoryId = uri.getPathSegments().get(1);
                updateRows = database.update("category",values,"id=?",new String[]{categoryId});
                break;
            default:
                break;
        }
        return updateRows;
    }
}
