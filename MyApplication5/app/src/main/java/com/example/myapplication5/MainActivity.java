package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

        ProgressBar pb1,pb2,pb3,pb4,pb5;
        View.OnClickListener ocl1 = null;
        View.OnClickListener ocl2 = null;
        Button bt1;
        Button bt2;
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);//设置应用程序窗体显示状态
            setContentView(R.layout.activity_main);
            setProgressBarIndeterminateVisibility(true);//设置窗体显示状态为真，表示一个程序正在运行
            pb5 = (ProgressBar)findViewById(R.id.progressBar5);
            bt1 = (Button)findViewById(R.id.button);
            bt2 = (Button)findViewById(R.id.button2);
            ocl1 = new View.OnClickListener(){
                public void onClick(View v){
                    pb5.setProgress((int)(pb5.getProgress()*1.2));
                    //进度条显示为当前长度的1.2倍
                }
            };
            ocl2 = new View.OnClickListener(){
                public void onClick(View v){
                    pb5.setProgress((int)(pb5.getProgress()*0.8));
                    //进度条显示为当前长度的0.8倍
                }
            };
            bt1.setOnClickListener(ocl1);//按钮绑定响应操作
            bt2.setOnClickListener(ocl2);
        }
    }