package com.example.nssapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.nssapp.R;

public class curriculum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curriculum);

        RecyclerView list = (RecyclerView)findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));

        String[] titles = {"CLASS 6","CLASS 7","CLASS 8","CLASS 9 ","CLASS 10"};
        list.setAdapter(new curriculum_adapter(titles, this));
    }
}
