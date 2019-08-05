package com.example.nssapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nssapp.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class mystatus extends AppCompatActivity {

    private TextView mdate,msyllabus,mteam,mclass;
    DatabaseReference ref;
    Task<Void> ref1;
    private EditText mcomment;
    private Button menter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystatus);

        mdate = findViewById(R.id.textView15);
        mclass = findViewById(R.id.textView17);
        mcomment = findViewById(R.id.textView22);
        menter = findViewById(R.id.button2);

        msyllabus = findViewById(R.id.textView18);
        mteam = findViewById(R.id.textView20);

        msyllabus.setMovementMethod(new ScrollingMovementMethod());
        mteam.setMovementMethod(new ScrollingMovementMethod());


        ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child("date").getValue().toString();
                String value1 = dataSnapshot.child("syllabus").getValue().toString();
                String value2 = dataSnapshot.child("details").getValue().toString();
                String value3 = dataSnapshot.child("class").getValue().toString();
                mdate.setText(value);
                msyllabus.setText(value1);
                mteam.setText(value2);
                mclass.setText(value3);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        menter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = mcomment.getText().toString().trim();


                try {
                    ref1 =  FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("comment").setValue(comment);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });


    }
}
