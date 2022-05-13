package com.example.mobilemarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLData;

public class LocalDatabase extends SQLiteOpenHelper {
    public static final String LOGIN = "LOGIN";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";

    public LocalDatabase(@Nullable Context context) {
        super(context, "LoginDetails.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + LOGIN + " (" + EMAIL + " VARCHAR , " + PASSWORD + " VARCHAR)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addItem(Loginclass l){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv =  new ContentValues();
        cv.put(NAME,l.getEmail());
        cv.put(PASSWORD, l.getPassword());

        long x = db.insert(LOGIN,null,cv);

        if(x==-1){
            return false;
        }
        else{
            return true;
        }
    }
}
