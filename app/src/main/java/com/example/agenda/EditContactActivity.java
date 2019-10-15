package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditContactActivity extends AppCompatActivity {

    private TextView name;
    private TextView surname;
    private TextView phoneNumber;
    private TextView birthday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);


        name = (EditText)findViewById(R.id.etName);
        surname = (EditText)findViewById(R.id.etSurname);
        phoneNumber = (EditText) findViewById(R.id.etPhonenumber);
        birthday = (EditText) findViewById(R.id.etBirthday);

    }

    public void editContact(View view){

    }
    public void deleteContact(View view){

    }
}
