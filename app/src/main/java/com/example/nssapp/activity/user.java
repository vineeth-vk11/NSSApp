package com.example.nssapp.activity;

public class user {

    public String name, email, phone ,id, room,visits,date,syllabus,details;

    public user(){

    }

    public user(String name, String email, String phone, String id, String room, String visits,String date,String syllabus, String details) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.room = room;
        this.visits=visits;
        this.date=date;
        this.syllabus=syllabus;
        this.details=details;
    }
}

