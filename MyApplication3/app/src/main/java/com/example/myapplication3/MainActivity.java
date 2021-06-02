package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        TextView tv1;
        TextView tv2;
        Spinner sp;
        View.OnClickListener ocl = null;
        ArrayAdapter<CharSequence> adapter;
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            //class
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            tv1 = (TextView)findViewById(R.id.textView);
            tv2 = (TextView)findViewById(R.id.textView2);
            sp = (Spinner)findViewById(R.id.spinner);
            tv1.setText("请选择");
            sp.setPrompt("选择项");//为列表项设置标题
            spinner_set();//调用下拉列表赋值、响应函数
        }
        private void spinner_set(){
            //将下拉列表的值先传给适配器
            adapter = ArrayAdapter.createFromResource(this, R.array.cities, android.R.layout.simple_spinner_item);
            //适配器获得值
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //下拉列表从适配器中读取值
            sp.setAdapter(adapter);
            //下拉列表选定值后响应
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                    // TODO Auto-generated method stub
                    if(arg2 != 0){
                        tv2.setText("您选择的是：" + adapter.getItem(arg2));
                    }
                    else{
                        tv2.setText("您还没有选择!");
                    }
                }
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                    tv2.setText("您还没有选择!");
                }
            });
        }
    }