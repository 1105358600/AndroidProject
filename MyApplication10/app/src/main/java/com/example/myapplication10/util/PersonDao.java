package com.example.myapplication10.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class PersonDao {
    private SQLiteDatabase db;
    public PersonDao(Context context)
    {
        MyDatabaseHelper myOpenHelper =new MyDatabaseHelper(context);
        db = myOpenHelper.getReadableDatabase();
    }
    public void insert(String id, String name, String sex, String phone){
        String sql="insert into Person(name,sex,phone) values(?,?,?)";
        db.execSQL(sql,new Object[]{name,sex,phone});
    }
}
