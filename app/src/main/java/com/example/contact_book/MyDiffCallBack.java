package com.example.contact_book;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class MyDiffCallBack extends DiffUtil.Callback {

    private List<miniCard> oldList;
    private List<miniCard> newList;

    MyDiffCallBack(List<miniCard> oldList, List<miniCard> newList){
        this.oldList=oldList;
        this.newList=newList;
    }
    @Override
    public int getOldListSize() {
        return oldList!=null?oldList.size():0;
    }

    @Override
    public int getNewListSize() {
        return newList!=null?newList.size():0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        miniCard oldItem=oldList.get(oldItemPosition);
        miniCard newItem=newList.get(newItemPosition);
        return oldItem.phone.equals(newItem.phone);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        miniCard oldItem=oldList.get(oldItemPosition);
        miniCard newItem=newList.get(newItemPosition);
        return oldItem.name.equals(newItem.name);
    }
}
