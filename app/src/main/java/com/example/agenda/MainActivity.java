package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.agenda.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    ArrayList<String> testArray = new ArrayList<>();
    ArrayList<String> testArrayNumbers = new ArrayList<>();;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Log.d("Creation","Initialize app");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DBManager manager = new DBManager(this);
        SQLiteDatabase db = manager.getReadableDatabase();

        // specify an adapter (see also next example)
        mAdapter = new RecyclerAdapter(manager.getAllContacts());
        recyclerView.setAdapter(mAdapter);


    }
}


