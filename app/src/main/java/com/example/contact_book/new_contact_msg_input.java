package com.example.contact_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class new_contact_msg_input extends AppCompatActivity {
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact_msg_input);

        //连接数据库
        mySQLiteOpenHelper=new MySQLiteOpenHelper(this);
        db=mySQLiteOpenHelper.getWritableDatabase();

        //文本编辑栏
        final EditText test_edit=(EditText)findViewById(R.id.test_edit);

        //按钮事件
        Button test_btn=(Button)findViewById(R.id.test_btn);
        test_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ContentValues values=new ContentValues();
                values.put("phone",test_edit.getText().toString());
                db.insert("contact_list_database",null,values);
            }
        });
    }

    /*
    EditText的焦点处理代码来自
    https://blog.csdn.net/weixin_43615488/article/details/103927055
    dispatchTouchEvent()、IsShouldHideKeyboard()、HideKeyboard()
    由于在blog中作者直接将其写在Activity中
        为方便使用
            将方法包装为方法类EditText_focus_processor
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        new EditText_focus_processor().StartProcess(getBaseContext(),getCurrentFocus(),ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭数据库连接
        mySQLiteOpenHelper.close();
        db.close();
    }
}