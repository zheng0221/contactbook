package com.example.contact_book;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RelationshipPicker extends WheelView<String> {
    private List<String> mRelationshipList=new ArrayList<>();
    private String mSelectedRelationship;
    private int mSelectedRelationshipIndex;
    private OnRelationshipSelectedListener mOnRelationshipSelectedListener;

    public RelationshipPicker(Context context) {
        this(context, null);
    }

    public RelationshipPicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RelationshipPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initRelationship();
        initAttrs(context, attrs);
        setItemMaximumWidthText("0000");
        setSelectedRelationship(mSelectedRelationshipIndex, false);
        setOnWheelChangeListener(new OnWheelChangeListener<String>() {
            @Override
            public void onWheelSelected(String item, int position) {
                mSelectedRelationship=item;
                mSelectedRelationshipIndex=position;
                if(mOnRelationshipSelectedListener!=null){
                    Log.d("My","onWheelSelected_1");
                    mOnRelationshipSelectedListener.onRelationshipSelected(item);
                    Log.d("My","onWheelSelected_2");
                }
            }
        });

        Log.d("My","RelationshipPicker");
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs == null) {
            return;
        }
        mSelectedRelationshipIndex=mRelationshipList.size()/2+1;
        mSelectedRelationship=mRelationshipList.get(mRelationshipList.size()/2+1);

        Log.d("My","initAttrs");
    }

    private void initRelationship() {
        mRelationshipList.add("亲人");
        mRelationshipList.add("亲戚");
        mRelationshipList.add("朋友");
        mRelationshipList.add("同事");
        mRelationshipList.add("同学");
        mRelationshipList.add("上司");
        mRelationshipList.add("下属");
        mRelationshipList.add("老师");
        mRelationshipList.add("其他");


        setDataList(mRelationshipList);

        Log.d("My","initRelationship");
    }



    public void setSelectedRelationship(int selectedRelationshipIndex) {
        setSelectedRelationship(selectedRelationshipIndex, true);
        Log.d("My","setSelectedRelationship");
    }

    public void setSelectedRelationship(int selectedRelationshipIndex, boolean smoothScroll) {
        setCurrentPosition(selectedRelationshipIndex, smoothScroll);
        Log.d("My","setSelectedRelationship");
    }

    public String getSelectedRelationship() {
        return mSelectedRelationship;
    }

    public void setOnRelationshipSelectedListener(OnRelationshipSelectedListener onRelationshipSelectedListener) {
        mOnRelationshipSelectedListener = onRelationshipSelectedListener;
        Log.d("My","setOnRelationshipSelectedListener");
    }

    public interface OnRelationshipSelectedListener {
        void onRelationshipSelected(String Relationship);
    }
}
