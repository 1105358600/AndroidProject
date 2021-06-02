package com.example.myapplication10.util;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.myapplication10.R;
import com.example.myapplication10.util.PersonDao;

public class SqliteActivity extends Activity {
    private PersonDao personDao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        personDao =new PersonDao(this);
    }
}
