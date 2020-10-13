package com.example.contact_book;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
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
        Log.d("My","A");
        initData(data);
        Log.d("My","B");
        view=inflater.inflate(R.layout.fragment_contact_list,container,false);
        Log.d("My","C");
        initRecyclerView();
        Log.d("My","D");
        return view;
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        Log.d("My","E");
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        Log.d("My","F");
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.contact_list);
        Log.d("My","G");
        miniCardAdapter adapter=new miniCardAdapter(data);
        Log.d("My","H");
        recyclerView.setAdapter(adapter);
        Log.d("My","I");
        recyclerView.setLayoutManager(linearLayoutManager);
    }

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
