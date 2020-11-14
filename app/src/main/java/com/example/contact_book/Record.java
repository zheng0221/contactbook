package com.example.contact_book;

//记录类
public class Record {
    private String Name;
    private String Number;
    private String Date;
    private String Time;
    private String Type;
    private String Place;

    public Record(String name, String number, String date, String time, String type,String place) {
        this.Name = name;
        this.Number = number;
        this.Date = date;
        this.Time = time;
        this.Type = type;
        this.Place = place;
    }

    public String getName() {
        return Name;
    }

    public String getDate() {
        return Date;
    }

    public String getNumber() {
        return Number;
    }

    public String getPlace() {
        return Place;
    }

    public String getTime() {
        return Time;
    }

    public String getType() {
        return Type;
    }
}
