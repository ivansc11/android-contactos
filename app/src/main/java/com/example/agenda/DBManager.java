package com.example.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.Serializable;


public class DBManager extends SQLiteOpenHelper implements Serializable {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contacts.db";
    SQLiteDatabase db = getWritableDatabase();

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("Creation","Initialize db");


        //Creates the table upon instatiation of this class.
        db.execSQL(" CREATE TABLE " + ContactContract.ContactEntry.TABLE_NAME + " ("
        + ContactContract.ContactEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
        + ContactContract.ContactEntry.NAME + " TEXT NOT NULL,"
        + ContactContract.ContactEntry.SURNAME + " TEXT NOT NULL,"
        + ContactContract.ContactEntry.PHONE_NUMBER + " TEXT NOT NULL,"
        + ContactContract.ContactEntry.BIRTHDAY + " TEXT NOT NULL)");

        mockData(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getAllContacts(){
        return getReadableDatabase().query(ContactContract.ContactEntry.TABLE_NAME, null, null, null, null, null, null);
    }



    public void deleteContact(Contact c){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + ContactContract.ContactEntry.TABLE_NAME+ " WHERE "+ "_ID" +"='"+c.getID()+"'");

    }




    public long saveContact(Contact c){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                ContactContract.ContactEntry.TABLE_NAME,
                null,
                c.toContentValues());

    }



    private void mockData(SQLiteDatabase sqLiteDatabase){
        //Populates the table with data
        mockContact(sqLiteDatabase, new Contact("Ivan","Srebernic","2995279176","26/12/1997"));
        mockContact(sqLiteDatabase, new Contact("Ivan","Srebernic","2995279176","26/12/1997"));
        mockContact(sqLiteDatabase, new Contact("Ivan","Srebernic","2995279176","26/12/1997"));
        mockContact(sqLiteDatabase, new Contact("Ivan","Srebernic","2995279176","26/12/1997"));
        mockContact(sqLiteDatabase, new Contact("Ivan","Srebernic","2995279176","26/12/1997"));
        mockContact(sqLiteDatabase, new Contact("Ivan","Srebernic","2995279176","26/12/1997"));
        mockContact(sqLiteDatabase, new Contact("Ivan","Srebernic","2995279176","26/12/1997"));
        mockContact(sqLiteDatabase, new Contact("Ivan","Srebernic","2995279176","26/12/1997"));

        Log.d("Creation","asd");
    }

    public long mockContact(SQLiteDatabase db, Contact c){
        return db.insert(
                ContactContract.ContactEntry.TABLE_NAME,
                null,
                c.toContentValues());
    }
}
