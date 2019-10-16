package com.example.agenda;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BirthdaySearchActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday_search);


        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);

        Intent intent = getIntent();
        String birthday = intent.getStringExtra("birthday");
        DBManager manager = new DBManager(this);


        setUpRecyclerView(manager.getContactsByBirthday(birthday));

    }

    public void setUpRecyclerView(Cursor cursor){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerAdapter(cursor);
        recyclerView.setAdapter(mAdapter);

    }
}
