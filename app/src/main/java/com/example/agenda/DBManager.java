package com.example.agenda;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    public Cursor getContactsByBirthday(String date) {
        return getReadableDatabase().rawQuery("SELECT * FROM " + ContactContract.ContactEntry.TABLE_NAME + " WHERE birthday LIKE '"+ date +"'", new String[0]);

    }
}
