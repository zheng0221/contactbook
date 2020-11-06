package com.example.contact_book;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import org.jetbrains.annotations.NotNull;


public class MyProvider extends ContentProvider {
    public static final int DIR = 0;
    public static final int ITEM = 1;
    private static UriMatcher uriMatcher;
    public static final String AUTHORITY = "com.example.contact_book.provider";

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "record_list_database", DIR);
        uriMatcher.addURI(AUTHORITY, "record_list_database/#", ITEM);
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(@NotNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = new MySQLiteOpenHelper(getContext()).getWritableDatabase(); //连接数据库
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case DIR: // 查询表中的所有数据
                cursor = db.query("record_list_database", projection, selection, selectionArgs, null, null,null);
                break;
            case ITEM: // 查询表中的单条数据
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public Uri insert(@NotNull Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int update(@NotNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(@NotNull Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public String getType(@NotNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case DIR:
                return "vnd.android.cursor.dir/vnd.com.example.contact_book.record_list_database";
            case ITEM:
                return "vnd.android.cursor.item/vnd.com.example.contact_book.record_list_database";
            default:
                break;
        }
        return null;
    }
}