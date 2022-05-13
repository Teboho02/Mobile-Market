package com.example.mobilemarket;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class CreateAccoutdatabase extends SQLiteOpenHelper {
    public static final String CREATE_ACCOUNT = "CREATE_ACCOUNT";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";

    public CreateAccoutdatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context,"CreateAccount", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String statemet = "CREATE TABLE " + CREATE_ACCOUNT + "(" + NAME + " VARCHAR, " + EMAIL + " VARCHAR, " + PASSWORD + " VARCHAR)";
        db.execSQL(statemet);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addItem(CreaateAcc l){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv =  new ContentValues();
        cv.put(NAME,l.getName());
        cv.put(EMAIL, l.getEmail());
        cv.put(PASSWORD, l.getPassword());

        long x = db.insert(CREATE_ACCOUNT,null,cv);

        if(x==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public ArrayList<String> getinfo(){
        List<CreaateAcc> returnlist = new ArrayList<>();

        String query = "SELECT * FROM " + CREATE_ACCOUNT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<String> things = new ArrayList<String>();

        if(cursor.moveToFirst()){
            do{
                String name = cursor.getString(0);
                String email = cursor.getString(1);
                String pass = cursor.getString(2);

                things.add(name);
                things.add(email);
                things.add(pass);

                CreaateAcc creaateAcc  = new CreaateAcc(name, email, pass);
                returnlist.add(creaateAcc);
            }
            while(cursor.moveToNext());

        }
        else{
            //we do mot have to do anything
        }
        db.close();

        return things;
    }
}
