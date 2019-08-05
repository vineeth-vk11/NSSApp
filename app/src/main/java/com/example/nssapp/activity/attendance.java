package com.example.nssapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.TextView;

import com.example.nssapp.R;
import com.example.nssapp.activity.Interface.IFirebaseLoadDone;
import com.example.nssapp.activity.Model.User;
import com.example.nssapp.activity.Model.User1;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

public class attendance extends AppCompatActivity implements IFirebaseLoadDone {

    SearchableSpinner searchableSpinner;
    Task<Void> userRef;
    DatabaseReference userRef1,userRef2;

    IFirebaseLoadDone iFirebaseLoadDone;
    List<User>users;

    TextView id,email,phone,room,visits,name;
    boolean isFirstTimeClick = true;

    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        id = findViewById(R.id.id_no);
        email=findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        room = findViewById(R.id.room);
        visits = findViewById(R.id.visits);
        save = findViewById(R.id.save);


        searchableSpinner = (SearchableSpinner)findViewById(R.id.seachable_spinner);
        searchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!isFirstTimeClick){
                    User user = users.get(i);
                    id.setText(user.getId());
                    email.setText(user.getEmail());
                    phone.setText(user.getPhone());
                    room.setText(user.getRoom());
                    visits.setText(user.getVisits());
                }

                else
                    isFirstTimeClick=false;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        iFirebaseLoadDone = this;


        userRef1 = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Attendance");
        userRef1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<User>users = new ArrayList<>();
                for(DataSnapshot userSnapShot:dataSnapshot.getChildren()){
                    users.add(userSnapShot.getValue(User.class));

                }

                iFirebaseLoadDone.onFirebaseLoadSuccess(users);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                iFirebaseLoadDone.onFirebaseLoadFailed(databaseError.getMessage());
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_new = email.getText().toString().trim();
                String id_new = id.getText().toString().trim();
                String phone_new = phone.getText().toString().trim();
                String room_new = room.getText().toString().trim();
                String visits_new = visits.getText().toString().trim();
                String name = searchableSpinner.getSelectedItem().toString().trim();

                try {
                    userRef =  FirebaseDatabase.getInstance().getReference("Attendance")
                            .child(phone_new).child("visits").setValue(visits_new);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

    }

    @Override
    public void onFirebaseLoadSuccess(List<User> userList) {
        users = userList;
        List<String>name_list = new ArrayList<>();
        for(User user:userList){
            name_list.add(user.getName());

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,name_list);
        searchableSpinner.setAdapter(adapter);

    }


    @Override
    public void onFirebaseLoadFailed(String message) {

    }

    public void increaseInteger(View view) {
        visits = findViewById(R.id.visits);
        String visit_text = visits.getText().toString();
        int minteger = (int) Integer.parseInt(visit_text);
        minteger = minteger + 1;
        display(minteger);

    }public void decreaseInteger(View view) {
        visits = findViewById(R.id.visits);
        String visit_text = visits.getText().toString();
        int minteger = (int) Integer.parseInt(visit_text);
        minteger = minteger - 1;
        display(minteger);
    }

    private void display(int number) {
        visits = (TextView) findViewById(
                R.id.visits);
        visits.setText("" + number);
    }

    public static String EncodeString(String string) {
        return string.replace(".", ",");
    }

    public static String DecodeString(String string) {
        return string.replace(",", ".");
    }


}

