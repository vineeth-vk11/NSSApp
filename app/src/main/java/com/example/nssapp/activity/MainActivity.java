package com.example.nssapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.nssapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmailAddress;
    private EditText txtPassword;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmailAddress = findViewById(R.id.login_email);
        txtPassword = findViewById(R.id.login_password);
        mAuth = FirebaseAuth.getInstance();

        Button button1 = findViewById(R.id.textView2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openregisteractivity();
            }
        });


    }



    public void openregisteractivity(){
        Intent intent = new Intent(this, register.class);
        startActivity(intent);
    }

    public void Login(View v){
        final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this,"Please wait...","Processing...",true);
        final String email = txtEmailAddress.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,getString(R.string.login_success),Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(i);
                        }else {
                            Toast.makeText(MainActivity.this,getString(R.string.login_fail),Toast.LENGTH_LONG).show();

                        }

                    }
                });



    }
}
