package com.wsine.west.exp7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by West on 2015/11/25.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Contacts.db";
    private static final String TABLE_NAME = "Contacts";
    private static final int DB_VERSION = 1;

    public MyDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table " + TABLE_NAME
                + " (_id integer primary key autoincrement, "
                + "_no text not null, "
                + "_name text not null, "
                + "_sex text,"
                + "_pnumber text);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF exists " + TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public long insert(Contact entity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_no", entity.getNo());
        values.put("_name", entity.getName());
        values.put("_sex", entity.getSex());
        values.put("_pnumber", entity.getPhoneNumber());
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public int update(Contact entity,String oldNo) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "_no = ?";
        String[] whereArgs = { oldNo};

        ContentValues values = new ContentValues();
        values.put("_no", entity.getNo());
        values.put("_name", entity.getName());
        values.put("_sex", entity.getSex());
        values.put("_pnumber", entity.getPhoneNumber());
        int rows = db.update(TABLE_NAME, values, whereClause, whereArgs);
        db.close();
        return rows;
    }

    public int delete(Contact entity) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "_no = ?";
        String[] whereArgs = { entity.getNo() };

        int rows = db.delete(TABLE_NAME, whereClause, whereArgs);
        db.close();
        return rows;
    }

    public Cursor query() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

}
