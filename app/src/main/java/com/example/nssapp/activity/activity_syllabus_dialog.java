package com.example.nssapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.nssapp.R;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class activity_syllabus_dialog extends AppCompatActivity implements View.OnClickListener {
    DatePickerDialog picker;
    EditText eText;

    EditText txtsyllabus6,txtsyllabus7,txtsyllabus8,txtsyllabus9,txtsyllabus10,txtdate;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    DatabaseReference df;
    long maxid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus_dialog);
        eText=(EditText) findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        txtsyllabus6 = findViewById(R.id.edtInput5);
        txtsyllabus7 = findViewById(R.id.edtInput);
        txtsyllabus8 = findViewById(R.id.edtInput2);
        txtsyllabus9 = findViewById(R.id.edtInput3);
        txtsyllabus10 = findViewById(R.id.edtInput4);

        txtdate = findViewById(R.id.editText1);
        findViewById(R.id.button).setOnClickListener(this);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        df = FirebaseDatabase.getInstance().getReference("syllabus");

        df.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    maxid = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(activity_syllabus_dialog.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    private void enterSyllabus(){
        final String syllabus6 = txtsyllabus6.getText().toString().trim();
        final String syllabus7 = txtsyllabus7.getText().toString().trim();
        final String syllabus8 = txtsyllabus8.getText().toString().trim();
        final String syllabus9 = txtsyllabus9.getText().toString().trim();
        final String syllabus10 = txtsyllabus10.getText().toString().trim();

        final String date1 = txtdate.getText().toString().trim();

        if (syllabus6.isEmpty()) {
            txtsyllabus6.setError(getString(R.string.input_error_name));
            txtsyllabus6.requestFocus();
            return;
        }

        if (date1.isEmpty()) {
            txtdate.setError(getString(R.string.input_error_email));
            txtdate.requestFocus();
            return;
        }

        syllabus_format syllabus_write_1 = new syllabus_format(
                syllabus6,
                syllabus7,
                syllabus8,
                syllabus9,
                syllabus10,
                date1
        );

        FirebaseDatabase.getInstance().getReference("syllabus")
                .child(String.valueOf(maxid+1))
                .setValue(syllabus_write_1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(activity_syllabus_dialog.this,getString(R.string.registration_success),Toast.LENGTH_LONG).show();
                    Intent i = new Intent(activity_syllabus_dialog.this,syllabus.class);
                    startActivity(i);
                }else{
                    Toast.makeText(activity_syllabus_dialog.this,getString(R.string.registration_fail),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                enterSyllabus();
                break;
        }
    }
}
