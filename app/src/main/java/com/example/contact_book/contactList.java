 package com.example.contact_book;


import android.content.Intent;
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

import java.util.Vector;

public class contactList extends Fragment {
    private View view;
    private Vector<miniCard> data=new Vector<miniCard>();

    public contactList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initData(data);
        view=inflater.inflate(R.layout.fragment_contact_list,container,false);
        initRecyclerView();
        return view;
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
        miniCardAdapter adapter=new miniCardAdapter(data);

        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    //用于测试
    private void initData(Vector<miniCard> inList){
        for(int i=0;i!=3;++i){
            inList.add(new miniCard("Alan"));
            inList.add(new miniCard("Anne"));
            inList.add(new miniCard("Bob"));
            inList.add(new miniCard("Bluse"));
            inList.add(new miniCard("Carly"));
            inList.add(new miniCard("Cindy"));
            inList.add(new miniCard("Dante"));
            inList.add(new miniCard("Dasic"));
            inList.add(new miniCard("Effa"));
            inList.add(new miniCard("Emily"));
        }
    }

}
