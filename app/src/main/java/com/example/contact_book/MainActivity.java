package com.example.contact_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private String tag = "My";
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tickle_button = (Button) findViewById(R.id.tickle);
        tickle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,CareText.class);
                startActivity(intent);
            }
        });




        //陈宇驰所写:给通话记录按钮添加监听
        //设置按钮监听-通话记录列表
        Button record_button = (Button) this.findViewById(R.id.record);
        record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击后切换fragment的内容
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                RecordFragment record_fragment = new RecordFragment();
                transaction.replace(R.id.contact_list_frag, record_fragment);
                transaction.commit();
            }
        });


        //数据库连接
        mySQLiteOpenHelper = new MySQLiteOpenHelper(MainActivity.this);
        db = mySQLiteOpenHelper.getWritableDatabase();

        //init_contact_list_database(db);

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        new EditText_focus_processor().StartProcess(getBaseContext(), getCurrentFocus(), ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //关闭数据库连接
        db.close();
        mySQLiteOpenHelper.close();
    }
}
