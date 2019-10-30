package com.example.agenda;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateContactActivity extends AppCompatActivity {

    EditText name;
    EditText surname;
    EditText phoneNumber;
    EditText birthday;
    String birthdayToSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        birthdayToSave=null;
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


    //Agregar contacto a la base de datos
    public void addContact(View v) {
        String nameSurname=name.getText().toString()+" "+surname.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);

        Contact c = new Contact(name.getText().toString(),surname.getText().toString(),phoneNumber.getText().toString(), birthdayToSave);

        DBManager manager = new DBManager(this);

        if(manager.saveContact(c)!=-1){
            Toast.makeText(this, "Succeed saving "+nameSurname,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Error saving contact",Toast.LENGTH_SHORT).show();
        }

        startActivity(intent);

    }
}
