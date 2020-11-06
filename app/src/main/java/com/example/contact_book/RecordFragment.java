package com.example.contact_book;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;


import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//通话记录所使用的Fragment
public class RecordFragment extends Fragment {
    public final String TAG = "MAIN";                       //log使用的tag
    private Context context;
    private View view;
    private SQLiteDatabase db;
    private List<Record> recordList = new ArrayList<>();    //通话记录列表
    private List<String> nameList = new ArrayList<>();      //保存已有的联系人
    private List<String> unPermissionList = new ArrayList<String>(); //申请未得到授权的权限列表
    private String[] permissionList = new String[]{         //申请的权限列表
            Manifest.permission.READ_CALL_LOG,
            Manifest.permission.WRITE_CALL_LOG
    };
    private String[] columns = {CallLog.Calls.CACHED_NAME// 通话记录的联系人
            , CallLog.Calls.NUMBER          // 通话记录的电话号码
            , CallLog.Calls.DATE            // 通话记录的日期
            , CallLog.Calls.DURATION        // 通话时长
            , CallLog.Calls.TYPE            // 通话类型
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.record_list, container, false);
        initRecord(); // 初始化数据
        //创建View
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.record_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //设置recyclerView每个子项的分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);
        RecordAdapter adapter = new RecordAdapter(recordList);  //装载数据
        recyclerView.setAdapter(adapter);
        Log.d(TAG, "record_fragment加载完成");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(context);
        db = mySQLiteOpenHelper.getWritableDatabase();      //连接数据库
    }

    //初始化数据，从CallLog.Calls.CONTENT_URI拿
    private void initRecord() {
        //-----------------------------连接数据库
        Log.d(TAG, "record_list初始化数据");
        checkPermission();      //检查权限是否运行
        //TODO 申请权限时程序退出申请完成后不会闪退
        checkContentProvider();
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                null, null, CallLog.Calls.DEFAULT_SORT_ORDER);  //逆序排序
        //依次读取cursor =====注意!虚拟机没有通话记录，要自己先打几个
        if (cursor == null)
            Toast.makeText(context, "暂无通话记录。", Toast.LENGTH_LONG).show();
        else
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));   //姓名
                String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));      //号码
                long dateLong = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));          //获取通话日期
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(dateLong));
                int duration_int = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION));    //获取通话时长，值为多少秒
                int type_int = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));            //获取通话类型：1.呼入2.呼出3.未接

                String time = duration_int + "S";
                String type;
                switch (type_int) {     //给type赋值对应的类型
                    case 1:
                        type = "呼入";
                        break;
                    case 2:
                        type = "呼出";
                        break;
                    case 3:
                        type = "未接";
                        break;
                    default:
                        type = "";
                        break;
                }
                if (name == null || name.equals("")) //没有姓名的联系人用电话号码代替
                    name = number;

                //--------------------------------------放进数据库
                ContentValues values = new ContentValues();
                values.put("name", name);
                values.put("number", number);
                values.put("date", date);
                values.put("time", time);
                values.put("type", type);
                values.put("place", "广州");
                db.insert("record_list_database", null, values);
                //--------------------------------------完成
                Record record = new Record(name, number, date, time, type);
                recordList.add(record);
                Log.d(TAG, "Call log: " + "\n" + "name: " + name + "\n"
                        + "phone number: " + number + "\n");
            }
    }


    private void checkContentProvider() {
        //为contentProvider测试一下是否有数据
        Cursor c = db.query("record_list_database", null, null, null, null, null, null);
        if (c == null)
            Toast.makeText(getContext(), "暂无通话记录。", Toast.LENGTH_LONG).show();
        else
            while (c.moveToNext()) {
                String name = c.getString(c.getColumnIndex("number"));
                Toast.makeText(getContext(), name, Toast.LENGTH_LONG).show();
                break;
            }
    }

    private void checkContentResolver() {
        Uri uri = Uri.parse("content://com.example.contact_book.provider/record_list_database");
        Cursor c = context.getContentResolver().query(uri, null,
                null, null, null);
        if (c == null)
            Toast.makeText(context, "暂无通话记录。", Toast.LENGTH_LONG).show();
        else
            while (c.moveToNext()) {
                String name = c.getString(c.getColumnIndex("name"));
                Toast.makeText(context, "读取的第一个名字:" + name, Toast.LENGTH_LONG).show();
                break;

            }
    }

    //权限判断和申请
    private void checkPermission() {
        unPermissionList.clear();//清空申请的没有通过的权限
        //逐个判断是否还有未通过的权限
        for (int i = 0; i < permissionList.length; i++) {
            if (ContextCompat.checkSelfPermission(getActivity(), permissionList[i]) !=
                    PackageManager.PERMISSION_GRANTED) {
                unPermissionList.add(permissionList[i]);//添加还未授予的权限到unPermissionList中
            }
        }

        //有权限没有通过，需要申请
        if (unPermissionList.size() > 0) {
            ActivityCompat.requestPermissions(getActivity(), permissionList, 100);
            Log.d(TAG, "check 有权限未通过");
        } else {
            //权限已经都通过了，可以将程序继续打开了
            Log.d(TAG, "check 权限都已经申请通过");
        }
    }

}
