package com.example.agenda;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private ImageButton mOpenBirthdaySearchButton;



    ArrayList<String> testArray = new ArrayList<>();
    ArrayList<String> testArrayNumbers = new ArrayList<>();;

    DBManager manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);


        recyclerView.setLayoutManager(layoutManager);

        manager = new DBManager(this);
        SQLiteDatabase db = manager.getReadableDatabase();


        // specify an adapter (see also next example)
        mAdapter = new RecyclerAdapter(manager.getAllContacts());
        recyclerView.setAdapter(mAdapter);

    }
    public void setBirthdayDate(){

    }
    public void addContactIntent(View view){
        Intent intent = new Intent(this, CreateContactActivity.class);

        startActivity(intent);

    }
    public void showBirthdaySearchDialog(View view){
        BirthdaySearchDialog dialog = new BirthdaySearchDialog();
        dialog.show(getSupportFragmentManager(), "BirthdayDialog");
    }


}


