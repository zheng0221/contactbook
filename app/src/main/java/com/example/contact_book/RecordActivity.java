package com.example.contact_book;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {     //单个号码的详细通话记录列表
    public final String TAG = "MAIN";                       //log使用的tag
    private SQLiteDatabase db;
    private List<Record> recordList = new ArrayList<>();    //通话记录列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        db = mySQLiteOpenHelper.getWritableDatabase();      //连接数据库
        initRecord(); // 初始化数据
        //创建View
        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.record_person_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());        //设置recyclerView每个子项的分割线
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        RecordAdapter adapter = new RecordAdapter(recordList);  //装载数据
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "record_person_fragment加载完成");
    }

    private void initRecord() {
        Log.d(TAG, "record_person_list初始化数据");
        Intent intent = this.getIntent();
        String number_get = intent.getStringExtra("number");
        Cursor cursor = db.rawQuery("select * from record_list_database where number = ?", new String[]{number_get});
        if (cursor == null)
            Toast.makeText(this, "数据库无通话记录。", Toast.LENGTH_LONG).show();
        else
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String time = cursor.getString(cursor.getColumnIndex("time"));
                String type = cursor.getString(cursor.getColumnIndex("type"));
                Cursor cursor1 = db.rawQuery("select * from number_place_database where number = ?", new String[]{number});
                String place = "";
                if (cursor1.getCount() != 0) {
                    while (cursor1.moveToNext()) {
                        place = cursor1.getString(cursor1.getColumnIndex("place"));
                    }
                }
                Record record = new Record(name, number, date, time, type, place);     //初始化一条记录
                recordList.add(record);
            }
    }
}