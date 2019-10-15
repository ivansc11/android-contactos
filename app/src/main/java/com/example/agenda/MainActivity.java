package com.example.agenda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    ArrayList<String> testArray = new ArrayList<>();
    ArrayList<String> testArrayNumbers = new ArrayList<>();;

    DBManager manager;

    Context thisContext = this;

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

        // TODO: add checking if an user is actually sended from the save contact activity
        saveContactToDatabase();

        // specify an adapter (see also next example)
        mAdapter = new RecyclerAdapter(manager.getAllContacts());
        recyclerView.setAdapter(mAdapter);




    }


    public void saveContactToDatabase(){
        Contact c = (Contact)getIntent().getSerializableExtra("newContact");
        if(c != null){
            manager.saveContact(c);
            Log.d("asd",c.getName());
        }

    }


    public void addContactIntent(View view){
        Intent intent = new Intent(this, CreateContactActivity.class);

        startActivity(intent);

    }

    public void editContactIntent(View view){
        Intent intent = new Intent(this, EditContactActivity.class);

        startActivity(intent);
    }
}


