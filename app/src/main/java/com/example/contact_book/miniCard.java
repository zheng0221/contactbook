package com.example.contact_book;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class miniCard {
    public String name;
    public String nickname;
    public String phone;
    public String phoneType;
    public String company;
    public String email;
    public String remark;
    public String address;
    public String note;
    public int star=0;
    public String relationship;
    public Bitmap avatar;

    miniCard(String _name, String _phone, String _relationship, Bitmap _avatar){
        name=_name;
        phone=_phone;
        relationship=_relationship;
        avatar=_avatar;
    }

}
