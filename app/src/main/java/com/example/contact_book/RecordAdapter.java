package com.example.contact_book;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Record适配器
public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    private List<Record> recordList;
    interface setOnClickListener    //首先在适配器中定义接口及其点击方法抽象函数
    {
        void Onclick(String s);
    }
    private setOnClickListener msetOnClickListemer;
    public void setItemClickListener(setOnClickListener s1)
    {
        msetOnClickListemer=s1;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        public final String TAG = "MAIN";                       //log使用的tag
        TextView recordName;
        TextView recordNumber;
        TextView recordDate;
        TextView recordTime;
        TextView recordType;
        TextView recordPlace;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View.
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, recordName.getText() + "");
                }
            });
            recordName = (TextView) view.findViewById(R.id.RecordName);
            recordNumber = (TextView) view.findViewById(R.id.RecordNumber);
            recordDate = (TextView) view.findViewById(R.id.RecordDate);
            recordTime = (TextView) view.findViewById(R.id.RecordTime);
            recordType = (TextView) view.findViewById(R.id.RecordType);
            recordPlace = (TextView) view.findViewById(R.id.RecordPlace);
        }
    }

    //初始化
    public RecordAdapter(List<Record> recList) {
        recordList = recList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Record record = recordList.get(position);
        holder.recordName.setText(record.getName());
        holder.recordNumber.setText(record.getNumber());
        holder.recordDate.setText(record.getDate());
        holder.recordTime.setText(record.getTime());
        holder.recordType.setText(record.getType());
        holder.recordPlace.setText(record.getPlace());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里我们将其他地方传进来的接口参数传入了子项的控件的文字，便于在其他地方来对子项实例进行各种操作
                if( msetOnClickListemer!=null)
                    msetOnClickListemer.Onclick((String) holder.recordNumber.getText());
            }
        });
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }

}
