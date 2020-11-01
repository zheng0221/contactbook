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
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            View currentFocus=getCurrentFocus();
            if(IsShouldHideKeyboard(currentFocus,ev)){
                HideKeyboard(currentFocus);
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    //根据EditText控件在屏幕的区域判断是否该失去焦点
    private boolean IsShouldHideKeyboard(View view,MotionEvent motionEvent){
        if(view instanceof EditText){
            //控件坐标
            int[] coord={0,0};
            view.getLocationInWindow(coord);
            int left=coord[0];
            int top=coord[1];
            int right=left+view.getWidth();
            int buttom=top+view.getHeight();
            int evX=(int)motionEvent.getRawX();
            int evY=(int)motionEvent.getRawY();
            return !((left<=evX && evX<=right) && (top<=evY && evY<=buttom));
        }
        else return false;
    }

    public void HideKeyboard(View view){
        //InputMethodManager管理输入法与应用程序的交互
        InputMethodManager inputMethodManager=((InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE));
        if(inputMethodManager!=null){
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
        view.clearFocus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭数据库连接
        mySQLiteOpenHelper.close();
        db.close();
    }
}