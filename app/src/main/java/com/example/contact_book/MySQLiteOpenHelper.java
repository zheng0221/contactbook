package com.example.contact_book;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String CONTACT_LIST_DATABASE_NAME="contact_list_database";

    //上下文
    private final Context context;

    //创建联系人数据库用的SQL语句
    String create_contact_list_database_sql=
            "create table " +CONTACT_LIST_DATABASE_NAME+ " ("
            +"id integer primary key autoincrement"
            +",phone text NOT NULL UNIQUE"
            +",name text"
            +",gender text"
            +",relationship text"
            +",blacklist_mark text"
            +",province text"
            +",city text"
            +",address text"
            +")";

    MySQLiteOpenHelper(Context context_in){
        super(context_in,CONTACT_LIST_DATABASE_NAME,null,DATABASE_VERSION);
        context=context_in;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //创建联系人数据库
        db.execSQL(create_contact_list_database_sql);

        //调试时使用
        Toast.makeText(context,"Create",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        db.execSQL("drop table if exists "+CONTACT_LIST_DATABASE_NAME);
        onCreate(db);
    }
}
