package com.example.nssapp.activity.Interface;

import com.example.nssapp.activity.Model.User;
import com.example.nssapp.activity.Model.User1;

import java.util.List;

public interface IFirebaseLoadDone {
    void onFirebaseLoadSuccess(List<User> userList);
    void onFirebaseLoadFailed(String message);


}
