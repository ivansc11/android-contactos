package com.example.agenda;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditContactActivity extends AppCompatActivity {

    Contact contact;

    private TextView name;
    private TextView surname;
    private TextView phoneNumber;
    private TextView birthday;
    private String birthdayToSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);


        name = (EditText)findViewById(R.id.et_name);
        surname = (EditText)findViewById(R.id.et_surname);
        phoneNumber = (EditText) findViewById(R.id.et_phone);
        birthday = (EditText) findViewById(R.id.et_birthday);
        birthday.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.et_birthday:
                        showDatePickerDialog();
                        break;
                }
            }
        });
        contact = (Contact)getIntent().getSerializableExtra("editContact");
        name.setText(contact.getName());
        surname.setText(contact.getSurname());
        phoneNumber.setText(contact.getPhoneNumber());
        birthday.setText(ToolsDate.stringToDate(contact.getBirthday()));
        birthdayToSave=contact.getBirthday();

    }

    public void editContact(View view){
        Intent intent = new Intent(this, MainActivity.class);
        DBManager manager = new DBManager(this);

        contact.setName(name.getText().toString());
        contact.setSurname(surname.getText().toString());
        contact.setBirthday(birthdayToSave);
        contact.setPhoneNumber(phoneNumber.getText().toString());
        //Toast.makeText(this, birthday.getText().toString(),Toast.LENGTH_SHORT).show();

        if( manager.updateContact(contact)!=-1){
            Toast.makeText(this, "Succeed editing contact",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error editing contact",Toast.LENGTH_SHORT).show();
        }

        startActivity(intent);


    }

    public void deleteContact(View view){
        Intent intent = new Intent(this, MainActivity.class);

        DBManager manager = new DBManager(this);
        manager.deleteContact(contact);

        startActivity(intent);

    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = ToolsDate.pickerToString(day,month,year);
                birthday.setText(selectedDate);
                birthdayToSave=ToolsDate.getBirthdaySave(year,month,day);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}
