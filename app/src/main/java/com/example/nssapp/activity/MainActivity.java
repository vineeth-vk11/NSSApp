package com.example.nssapp.activity;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nssapp.R;
import com.example.nssapp.activity.HomeActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openhomeactivity();
            }
        });


    }

    public void openhomeactivity(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
