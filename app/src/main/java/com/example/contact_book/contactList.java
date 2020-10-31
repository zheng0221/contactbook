package com.example.contact_book;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 */
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
