 package com.example.contact_book;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class contactList extends Fragment {
    private View view;
    private List<miniCard> dataList=new ArrayList<>();

    public contactList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_contact_list,container,false);
        initDataList();
        initRecyclerView();
        return view;
    }

    private void initDataList() {
        dataList.clear();
        MySQLiteOpenHelper helper=new MySQLiteOpenHelper(view.getContext());
        SQLiteDatabase db=helper.getWritableDatabase();

        Cursor cursor=db.query("contact_list_database",null,null,null,null,null,null);
        cursor.moveToFirst();
        if(cursor.getCount()==0)
            return;
        do{
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String phone=cursor.getString(cursor.getColumnIndex("phone"));
            String relationship=cursor.getString(cursor.getColumnIndex("relationship"));
            miniCard card=new miniCard(name,phone,relationship);
            dataList.add(card);
        }while(cursor.moveToNext());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //联系人fragment中按钮监听事件
        if(getActivity()!=null){
            final FloatingActionButton add_contact_btn=(FloatingActionButton)getActivity().findViewById(R.id.add_contact);
            add_contact_btn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(),new_contact_msg_input.class));
                }
            });
        }


    }

    private void initRecyclerView(){
        //布局管理器
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        //RecyclerView分割线
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(view.getContext(),DividerItemDecoration.VERTICAL);

        //实例化RecyclerView，声明所在布局
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.contact_list);

        //实例化名片的Adapter
        miniCardAdapter adapter=new miniCardAdapter(dataList,getContext());

        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
