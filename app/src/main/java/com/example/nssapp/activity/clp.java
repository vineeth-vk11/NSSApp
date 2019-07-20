package com.example.nssapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
