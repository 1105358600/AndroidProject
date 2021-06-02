package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            ListView lv = new ListView(this);
            lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
            setContentView(lv);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    setTitle("您点击的是：" + arg0.getItemAtPosition(arg2).toString());
                }
            });
            lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                    setTitle("您选择的是：" + arg0.getItemAtPosition(arg2).toString());
                }
                public void onNothingSelected(AdapterView<?> arg0) {

                }
            });
        }
        List<String> getData(){
            List<String> l = new ArrayList<String>();
            l.add("北京");
            l.add("上海");
            l.add("成都");
            l.add("香港");
            l.add("南京");
            return l;
        }
    }