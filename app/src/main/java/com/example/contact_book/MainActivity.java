package com.example.contact_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String tag = "My";
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;
    private List<String> unPermissionList = new ArrayList<String>(); //申请未得到授权的权限列表
    private String[] permissionList = new String[]{         //申请的权限列表
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_SMS,
            Manifest.permission.SEND_SMS,
            Manifest.permission.READ_CONTACTS,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();  //授予权限
        Button tickle_button = (Button) findViewById(R.id.tickle);
        tickle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,CareText.class);
                startActivity(intent);
            }
        });

        //陈宇驰所写:给通话记录按钮添加监听   设置按钮监听-通话记录列表
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

    //权限判断和申请
    private void checkPermission() {
        unPermissionList.clear();//清空申请的没有通过的权限
        //逐个判断是否还有未通过的权限
        for (int i = 0; i < permissionList.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissionList[i]) !=
                    PackageManager.PERMISSION_GRANTED) {
                unPermissionList.add(permissionList[i]);//添加还未授予的权限到unPermissionList中
            }
        }
        //有权限没有通过，需要申请
        if (unPermissionList.size() > 0) {
            ActivityCompat.requestPermissions(this,permissionList, 100);
            Log.d(tag, "check 有权限未通过");
        } else {
            //权限已经都通过了，可以将程序继续打开了
            Log.d(tag, "check 权限都已经申请通过");
        }
    }
}
