package com.example.contact_book;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class new_contact_msg_input extends AppCompatActivity {
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private SQLiteDatabase db;
    ContentValues values=new ContentValues();

    EditText nameInput_EditText;
    EditText nicknameInput_EditText;
    EditText phoneInput_EditText;
    EditText companyInput_EditText;
    EditText emailInput_EditText;
    EditText emailRemarkInput_EditText;
    EditText addressInput_EditText;
    EditText noteInput_EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact_msg_input);

        //连接数据库
        mySQLiteOpenHelper=new MySQLiteOpenHelper(this);
        db=mySQLiteOpenHelper.getWritableDatabase();

        initComponent();
        Button saveEvent_btn=(Button)findViewById(R.id.saveEvent_btn);
        final StringPicker relationshipPicker=(StringPicker) findViewById(R.id.relationshipPicker);
        final StringPicker phoneTypePicker=(StringPicker) findViewById(R.id.phoneTypeInput_StringPicker);
        final Button nameExpand_btn=(Button)findViewById(R.id.nameExpand_btn);
        final Button phoneExpand_btn=(Button)findViewById(R.id.phoneExpand_btn);
        final Button emailExpand_btn=(Button)findViewById(R.id.emailExpand_btn);


        /* 头像选择功能稍后再做
        final int fromAlbum=2;
        ImageView avatarImage=(ImageView)findViewById(R.id.avatarImage);
        avatarImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent,fromAlbum);
            }
        });
         */


        //Name_展开按钮
        final LinearLayout nameExpand_LinearLayout=(LinearLayout) findViewById(R.id.nameExpand_LinearLayout);
        nameExpand_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameExpand_LinearLayout.getVisibility()==View.GONE){
                    nameExpand_LinearLayout.setVisibility(View.VISIBLE);
                    nameExpand_btn.setBackground(ContextCompat.getDrawable(getBaseContext(),R.mipmap.arrow_up));
                } else {
                    nameExpand_LinearLayout.setVisibility(View.GONE);
                    nameExpand_btn.setBackground(ContextCompat.getDrawable(getBaseContext(),R.mipmap.arrow_down));
                }
            }
        });

        //Phone_展开按钮
        final LinearLayout phoneExpand_LinearLayout=(LinearLayout)findViewById(R.id.phoneExpand_LinearLayout);
        phoneExpand_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phoneExpand_LinearLayout.getVisibility()==View.GONE){
                    phoneExpand_LinearLayout.setVisibility(View.VISIBLE);
                    phoneExpand_btn.setBackground(ContextCompat.getDrawable(getBaseContext(),R.mipmap.arrow_up));
                } else {
                    phoneExpand_LinearLayout.setVisibility(View.GONE);
                    phoneExpand_btn.setBackground(ContextCompat.getDrawable(getBaseContext(),R.mipmap.arrow_down));
                }
            }
        });

        //Email 展开按钮
        final LinearLayout emailExpand_LinearLayout=(LinearLayout)findViewById(R.id.emailExpand_LinearLayout);
        emailExpand_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(emailExpand_LinearLayout.getVisibility()==View.GONE){
                    emailExpand_LinearLayout.setVisibility(View.VISIBLE);
                    emailExpand_btn.setBackground(ContextCompat.getDrawable(getBaseContext(),R.mipmap.arrow_up));
                } else {
                    emailExpand_LinearLayout.setVisibility(View.GONE);
                    emailExpand_btn.setBackground(ContextCompat.getDrawable(getBaseContext(),R.mipmap.arrow_down));
                }
            }
        });

        //关系选择器数值设置
        List<String> relationshipList=new ArrayList<>();
        initRelationshipList(relationshipList);
        relationshipPicker.setDataList(relationshipList);
        relationshipPicker.setHalfVisibleItemCount(2);
        relationshipPicker.setSelectedStringIndex(0);
        //关系选择器监听器
        relationshipPicker.setOnStringSelectedListener(new StringPicker.OnStringSelectedListener() {
            @Override
            public void onStringSelected(String Relationship) {
                values.put("relationship",relationshipPicker.getSelectedString());
            }
        });

        //号码类型选择器数值设置
        List<String> phoneTypeList=new ArrayList<>();
        initPhoneTypeList(phoneTypeList);
        phoneTypePicker.setDataList(phoneTypeList);
        phoneTypePicker.setHalfVisibleItemCount(1);
        phoneTypePicker.setSelectedStringIndex(0);
        phoneTypePicker.setOnStringSelectedListener(new StringPicker.OnStringSelectedListener() {
            @Override
            public void onStringSelected(String String) {
                values.put("phoneType",phoneTypePicker.getSelectedString());
            }
        });

        //保存按钮
        saveEvent_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(nameInput_EditText.getText().toString().isEmpty() || phoneInput_EditText.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(),"Name & Phone 不得为空",Toast.LENGTH_SHORT).show();
                } else {
                    saveInDatabase();
                    finish();
                }
            }
        });
    }

    private void initComponent(){
        nameInput_EditText=(EditText)findViewById(R.id.nameInput_EditText);
        nicknameInput_EditText=(EditText)findViewById(R.id.nicknameInput_EditText);
        phoneInput_EditText=(EditText)findViewById(R.id.phoneInput_EditText);
        companyInput_EditText=(EditText)findViewById(R.id.companyInput_EditText);
        emailInput_EditText=(EditText)findViewById(R.id.emailInput_EditText);
        emailRemarkInput_EditText=(EditText)findViewById(R.id.emailRemarkInput_EditText);
        addressInput_EditText=(EditText)findViewById(R.id.addressInput_EditText);
        noteInput_EditText=(EditText)findViewById(R.id.noteInput_EditText);
    }

    private void initRelationshipList(List<String> mRelationshipList){
        mRelationshipList.add("");
        mRelationshipList.add("助理");
        mRelationshipList.add("兄弟");
        mRelationshipList.add("子女");
        mRelationshipList.add("同居伴侣");
        mRelationshipList.add("父亲");
        mRelationshipList.add("介绍人");
        mRelationshipList.add("经理");
        mRelationshipList.add("母亲");
        mRelationshipList.add("父母");
        mRelationshipList.add("合作伙伴");
        mRelationshipList.add("朋友");
        mRelationshipList.add("亲属");
        mRelationshipList.add("姐妹");
        mRelationshipList.add("配偶");
        mRelationshipList.add("同学");
        mRelationshipList.add("同事");
        mRelationshipList.add("老师");
        mRelationshipList.add("上级");
        mRelationshipList.add("下属");
        mRelationshipList.add("其他");
    }

    private void initPhoneTypeList(List<String> list){
        list.add("");
        list.add("单位");
        list.add("住宅");
        list.add("车载电话");
        list.add("手机");
        list.add("单位手机");
        list.add("单位传真");
    }

    private void saveInDatabase(){
        values.put("name",nameInput_EditText.getText().toString());
        values.put("nickname",nicknameInput_EditText.getText().toString());
        values.put("phone",phoneInput_EditText.getText().toString());
        //values.put("phoneType",phoneTypePicker.getSelectedString());
        values.put("company",companyInput_EditText.getText().toString());
        values.put("email",emailInput_EditText.getText().toString());
        values.put("remark",emailRemarkInput_EditText.getText().toString());
        values.put("address",addressInput_EditText.getText().toString());
        values.put("note",noteInput_EditText.getText().toString());
        //values.put("relationship",relationshipPicker.getSelectedString());
        if(db.insert("contact_list_database",null,values)==-1){
            Toast.makeText(new_contact_msg_input.this,"该号码已存在",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(new_contact_msg_input.this,nameInput_EditText.getText().toString()+" 成功添加到联系人",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 2:
                if(resultCode== Activity.RESULT_OK && data!=null){

                }
        }
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            AlertDialog.Builder builder=new AlertDialog.Builder(new_contact_msg_input.this);
            AlertDialog alert=builder.setIcon(R.mipmap.setting)
                    .setTitle("提示信息")
                    .setMessage("新添加的联系人尚未保存")
                    .setNegativeButton("不保存", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    }).create();
            alert.show();
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //关闭数据库连接
        mySQLiteOpenHelper.close();
        db.close();
    }
}