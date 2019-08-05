package com.example.nssapp.activity.Model;

public class User {
    private String name,email,id,phone,room,visits;

    public User(){

    }

    public User(String name, String email, String id, String phone, String room, String visits) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.phone = phone;
        this.room = room;
        this.name = name;
        this.visits = visits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisits() {
        return visits;
    }


    public void setVisits(String visits) {
        this.visits = visits;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
