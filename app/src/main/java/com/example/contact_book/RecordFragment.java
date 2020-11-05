package com.example.contact_book;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//通话记录所使用的Fragment
public class RecordFragment extends Fragment {
    private View view;
    private List<Record> recordList = new ArrayList<>();    //测试通话记录

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.record_list, container, false);
        //测试通话记录是否能用
        initRecord(); // 初始化数据
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.record_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        RecordAdapter adapter = new RecordAdapter(recordList);
        recyclerView.setAdapter(adapter);
        Log.d("Main", "record_fragment加载完成");
        return view;
    }

    private void initRecord() {  //测试通话记录是否能用
        Log.d("Main", "初始化数据");
        for (int i = 0; i < 20; i++) {
            String s=String.valueOf(i);
            Record record1 = new Record(s,s,s,s);
            recordList.add(record1);
        }
    }
}
