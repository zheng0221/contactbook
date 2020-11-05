package com.example.contact_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.contact_book.RecordFragment;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private String tag = "My";
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    protected void init_contact_list_database(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("phone", "13501483664");
        values.put("name", "吴超亮");
        values.put("gender", "男");
        values.put("relationship", "自己");
        values.put("blacklist_mark", "No");
        values.put("province", "广东省");
        values.put("city", "广州");
        values.put("address", "华南理工大学-大学城校区");
        db.insert("contact_list_database", null, values);
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
