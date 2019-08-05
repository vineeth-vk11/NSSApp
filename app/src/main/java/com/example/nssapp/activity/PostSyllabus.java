package com.example.nssapp.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.example.nssapp.R;

import org.w3c.dom.Text;

public class PostSyllabus extends AppCompatActivity {
    TextView mclass6,mclass7,mclass8,mclass9,mclass10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_syllabus);

        mclass6 = findViewById(R.id.class6);
        mclass7 = findViewById(R.id.class7);
        mclass8 = findViewById(R.id.class8);
        mclass9 = findViewById(R.id.class9);
        mclass10 = findViewById(R.id.class10);

        mclass6.setMovementMethod(new ScrollingMovementMethod());
        mclass7.setMovementMethod(new ScrollingMovementMethod());
        mclass8.setMovementMethod(new ScrollingMovementMethod());
        mclass9.setMovementMethod(new ScrollingMovementMethod());
        mclass10.setMovementMethod(new ScrollingMovementMethod());


        String class6 = getIntent().getStringExtra("class6");
        String class7 = getIntent().getStringExtra("class7");
        String class8 = getIntent().getStringExtra("class8");
        String class9 = getIntent().getStringExtra("class9");
        String class10 = getIntent().getStringExtra("class10");

        mclass6.setText(class6);
        mclass7.setText(class7);
        mclass8.setText(class8);
        mclass9.setText(class9);
        mclass10.setText(class10);






    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
