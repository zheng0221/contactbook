package com.example.contact_book;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class miniCardAdapter extends RecyclerView.Adapter<miniCardAdapter.ViewHolder> {
    private List<miniCard> dataList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        //private TextView textView;

        private ViewHolder(View view){
            super(view);
            //textView=view.findViewById(R.id.minicard_name);
        }
    }

    miniCardAdapter(List<miniCard> cardList){
        dataList=cardList;
    }

    @Override
    @NotNull
    public miniCardAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewtype){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.minicard_item,parent,false);
        //View view=View.inflate(context,R.layout.minicard_item,null);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount(){return dataList.size();}

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder,int position){
        //holder.textView.setText(dataList.get(position).name);
    }
}
