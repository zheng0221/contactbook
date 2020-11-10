package com.example.contact_book;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class miniCardAdapter extends RecyclerView.Adapter<miniCardAdapter.ViewHolder> {
    private List<miniCard> contactList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView;
        TextView phoneTextView;
        TextView relationshipTextView;

        private ViewHolder(View view){
            super(view);
            nameTextView=(TextView)view.findViewById(R.id.name_TextView);
            phoneTextView=(TextView)view.findViewById(R.id.phone_TextView);
            relationshipTextView=(TextView)view.findViewById(R.id.relationship_TextView);
        }
    }

    miniCardAdapter(List<miniCard> cardList, Context context){
        contactList=cardList;
        mContext=context;
    }

    @Override
    @NotNull
    public miniCardAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewtype){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.minicard_item,parent,false);
        //View view=View.inflate(context,R.layout.minicard_item,null);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount(){return contactList.size();}

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder,int position){
        holder.nameTextView.setText(contactList.get(position).name);
        holder.phoneTextView.setText(contactList.get(position).phone);
        holder.relationshipTextView.setText(contactList.get(position).relationship);
    }
}
