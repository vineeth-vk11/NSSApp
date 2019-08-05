package com.example.nssapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nssapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class syllabus extends AppCompatActivity {

    LinearLayoutManager mLayoutManager;
    SharedPreferences mSharedPref;

    DatabaseReference ref;
    ArrayList<syllabus_format>list;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSharedPref = getSharedPreferences("SortSettings",MODE_PRIVATE);
        String mSorting = mSharedPref.getString("Sort","newest");


        if(mSorting.equals("newest")){
            mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
        }
        else if(mSorting.equals("oldest")){
            mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.setReverseLayout(false);
            mLayoutManager.setStackFromEnd(false);
        }


        FloatingActionButton button1 = findViewById(R.id.floating);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openentsyllabusactivity();
            }
        });

        ref = FirebaseDatabase.getInstance().getReference("syllabus");
        ref.keepSynced(true);
        recyclerView = findViewById(R.id.syllabus_recycler);
        recyclerView.setHasFixedSize(true);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

    }

    private void firebaseSearch(String searchText){
        Query firebaseSearchQuery = ref.orderByChild("date").startAt(searchText).endAt(searchText+"\uf8ff");

        FirebaseRecyclerAdapter<syllabus_format,ViewHolder>firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<syllabus_format, ViewHolder>(
                        syllabus_format.class,
                        R.layout.syllabus_item,
                        ViewHolder.class,
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, syllabus_format syllabus_format, int i) {
                        viewHolder.setDetails(getApplicationContext(),syllabus_format.getDate());

                    }
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder = super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListner(new ViewHolder.ClickListner() {
                            @Override
                            public void onItemClick(View view, int position) {


                                String sclass6 = getItem(position).getSyllabus6();
                                String sclass7 = getItem(position).getSyllabus7();
                                String sclass8 = getItem(position).getSyllabus8();
                                String sclass9 = getItem(position).getSyllabus9();
                                String sclass10 = getItem(position).getSyllabus10();

                                Intent intent = new Intent(view.getContext(),PostSyllabus.class);
                                intent.putExtra("class6",sclass6);
                                intent.putExtra("class7",sclass7);
                                intent.putExtra("class8",sclass8);
                                intent.putExtra("class9",sclass9);
                                intent.putExtra("class10",sclass10);

                                startActivity(intent);


                            }

                            @Override
                            public void onItemlongClick(View view, int position) {

                            }
                        });
                        return viewHolder;
                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<syllabus_format,ViewHolder>firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<syllabus_format, ViewHolder>(
                        syllabus_format.class,
                        R.layout.syllabus_item,
                        ViewHolder.class,
                        ref
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, syllabus_format syllabus_format, int i) {
                        viewHolder.setDetails(getApplicationContext(),syllabus_format.getDate());

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder = super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListner(new ViewHolder.ClickListner() {
                            @Override
                            public void onItemClick(View view, int position) {
                                String sclass6 = getItem(position).getSyllabus6();
                                String sclass7 = getItem(position).getSyllabus7();
                                String sclass8 = getItem(position).getSyllabus8();
                                String sclass9 = getItem(position).getSyllabus9();
                                String sclass10 = getItem(position).getSyllabus10();

                                Intent intent = new Intent(view.getContext(),PostSyllabus.class);
                                intent.putExtra("class6",sclass6);
                                intent.putExtra("class7",sclass7);
                                intent.putExtra("class8",sclass8);
                                intent.putExtra("class9",sclass9);
                                intent.putExtra("class10",sclass10);

                                startActivity(intent);


                            }

                            @Override
                            public void onItemlongClick(View view, int position) {

                            }
                        });
                        return viewHolder;
                    }
                };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                firebaseSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                firebaseSearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_sort){
            showSortDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSortDialog(){
        String[] sortOptions = {"Newest","Oldest"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort by")
                .setIcon(R.drawable.ic_sort_black_24dp)
                .setItems(sortOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==0){
                            SharedPreferences.Editor editor = mSharedPref.edit();
                            editor.putString("Sort","newest");
                            editor.apply();
                            recreate();
                        }
                        else if(i==1){
                            SharedPreferences.Editor editor = mSharedPref.edit();
                            editor.putString("Sort","oldest");
                            editor.apply();
                            recreate();


                        }
                    }
                });
        builder.show();

    }

    public void openentsyllabusactivity(){
        Intent intent = new Intent(this, activity_syllabus_dialog.class);
        startActivity(intent);
    }


}
