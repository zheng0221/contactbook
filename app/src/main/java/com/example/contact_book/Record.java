package com.example.contact_book;
//记录类
public class Record {
    private String Name;
    private String Time;
    private String Type;
    private String Place;

    public Record(String Name, String Time, String Type, String Place) {
        this.Name = Name;
        this.Time = Time;
        this.Type = Type;
        this.Place = Place;
    }

    public String getName() {
        return Name;
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
