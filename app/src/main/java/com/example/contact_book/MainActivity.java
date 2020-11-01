package com.example.contact_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private String tag="My";
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //数据库连接
        mySQLiteOpenHelper=new MySQLiteOpenHelper(MainActivity.this);
        db=mySQLiteOpenHelper.getWritableDatabase();

        //init_contact_list_database(db);

    }

    protected void init_contact_list_database(SQLiteDatabase db){
        ContentValues values=new ContentValues();
        values.put("phone","13501483664");
        values.put("name","吴超亮");
        values.put("gender","男");
        values.put("relationship","自己");
        values.put("blacklist_mark","No");
        values.put("province","广东省");
        values.put("city","广州");
        values.put("address","华南理工大学-大学城校区");
        db.insert("contact_list_database",null,values);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //关闭数据库连接
        db.close();
        mySQLiteOpenHelper.close();
    }
}
