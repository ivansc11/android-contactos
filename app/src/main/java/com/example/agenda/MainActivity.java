package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        testArray.add("Ivan");
        testArray.add("Ivan");
        testArray.add("Ivan");
        testArrayNumbers.add("123456789");
        testArrayNumbers.add("123456789");
        testArrayNumbers.add("123456789");
        testArray.add("Ivan");
        testArray.add("Ivan");
        testArray.add("Ivan");
        testArrayNumbers.add("123456789");
        testArrayNumbers.add("123456789");
        testArrayNumbers.add("123456789");

        testArray.add("Ivan");
        testArray.add("Ivan");
        testArray.add("Ivan");
        testArrayNumbers.add("123456789");
        testArrayNumbers.add("123456789");
        testArrayNumbers.add("123456789");

        testArray.add("Ivan");
        testArray.add("Ivan");
        testArray.add("Ivan");
        testArrayNumbers.add("123456789");
        testArrayNumbers.add("123456789");
        testArrayNumbers.add("123456789");


        testArray.add("Ivan");
        testArray.add("Ivan");
        testArray.add("Ivan");
        testArrayNumbers.add("123456789");
        testArrayNumbers.add("123456789");
        testArrayNumbers.add("123456789");






        // specify an adapter (see also next example)
        mAdapter = new RecyclerAdapter(testArray,testArrayNumbers, this);
        recyclerView.setAdapter(mAdapter);
    }
}


