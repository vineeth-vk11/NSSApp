package com.example.nssapp.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.nssapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity implements View.OnClickListener{

    private EditText txtEmailAddress;
    private EditText txtPassword;
    private EditText txtroom;
    private EditText txtid;
    private EditText txtphone;
    private EditText txtusername;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtEmailAddress = findViewById(R.id.editText9);
        txtPassword = findViewById(R.id.editText2);
        txtusername = findViewById(R.id.editText);
        txtphone = findViewById(R.id.editText8);
        txtid = findViewById(R.id.editText10);
        txtroom = findViewById(R.id.editText11);
        Button button1 = findViewById(R.id.textView2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openloginactivity();
            }
        });

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.register).setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login User
        }
    }

    public void openloginactivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void registerUser() {
        final String name = txtusername.getText().toString().trim();
        final String email = txtEmailAddress.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        final String phone = txtphone.getText().toString().trim();
        final String id = txtid.getText().toString().trim();
        final String room = txtroom.getText().toString().trim();
        final String visits = "0";
        final String date = "0";
        final String syllabus="0";
        final String details="0";



        if (name.isEmpty()) {
            txtusername.setError(getString(R.string.input_error_name));
            txtusername.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            txtEmailAddress.setError(getString(R.string.input_error_email));
            txtEmailAddress.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmailAddress.setError(getString(R.string.input_error_email_invalid));
            txtEmailAddress.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            txtPassword.setError(getString(R.string.input_error_password));
            txtPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            txtPassword.setError(getString(R.string.input_error_password_length));
            txtPassword.requestFocus();
            return;
        }

        if (phone.isEmpty()) {
            txtphone.setError(getString(R.string.input_error_phone));
            txtphone.requestFocus();
            return;
        }

        if (phone.length() != 10) {
            txtphone.setError(getString(R.string.input_error_phone_invalid));
            txtphone.requestFocus();
            return;
        }

        if (id.isEmpty()) {
            txtid.setError(getString(R.string.input_error_id));
            txtid.requestFocus();
            return;
        }

        if (room.isEmpty()) {
            txtroom.setError(getString(R.string.input_error_room));
            txtroom.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            user userr = new user(
                                    name,
                                    email,
                                    phone,
                                    id,
                                    room,
                                    visits,
                                    date,
                                    syllabus,
                                    details
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(userr).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if(task.isSuccessful()){
                                        Toast.makeText(register.this,getString(R.string.registration_success),Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(register.this,MainActivity.class);
                                        startActivity(i);
                                    }else{
                                        Toast.makeText(register.this,getString(R.string.registration_fail),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });



                            FirebaseDatabase.getInstance().getReference("status")
                                    .child(name)
                                    .setValue(userr).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    progressBar.setVisibility(View.GONE);
                                    if(task.isSuccessful()){
                                        Toast.makeText(register.this,getString(R.string.registration_success),Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(register.this,MainActivity.class);
                                        startActivity(i);
                                    }else{
                                        Toast.makeText(register.this,getString(R.string.registration_fail),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                        }else{
                            Toast.makeText(register.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }






                    }
                });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                registerUser();
                break;
        }
    }

}
