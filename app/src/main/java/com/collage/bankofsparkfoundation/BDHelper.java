package com.collage.bankofsparkfoundation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDHelper extends SQLiteOpenHelper {
    public BDHelper(@Nullable Context context) {
        super(context, "Userdata.bd", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase BD) {
        BD.execSQL("create Table Userdetails(name TEXT primary key, accountnumber TEXT, amount TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase BD, int i, int i1) {
        BD.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertuserdata(String name, String accountnumber, String amount) {
        SQLiteDatabase BD = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("accountnumber", accountnumber);
        contentValues.put("amount", amount);
        long result = BD.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
        public Cursor getdata ()
        {
            SQLiteDatabase BD = this.getWritableDatabase();
            Cursor cursor = BD.rawQuery("Select * from Userdetails", null);
            return cursor;

        }
    }
