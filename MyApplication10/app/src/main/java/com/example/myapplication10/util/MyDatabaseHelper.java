package com.example.myapplication10.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public MyDatabaseHelper(@Nullable Context context ) {
        super(context, "Message.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String CREATE_TABLE="create table Person("+
        "id intger primary key autoincrement,"+
                "name varchar(10),"+
        "sex varchar(2),"+"phone varchar(15))";
            db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
