package com.example.nssapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nssapp.R;

public class clp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clp);
        RecyclerView list = (RecyclerView)findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));

        String[] titles = {"About CLP","Curriculum","Syllabus","My Status","Attendance"};
        list.setAdapter(new clp_adapter(titles, this));
    }

}
