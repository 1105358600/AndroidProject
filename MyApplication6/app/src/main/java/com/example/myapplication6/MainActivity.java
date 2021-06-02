package com.example.myapplication6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ZoomControls;

public class MainActivity extends AppCompatActivity {

        TextView tv1;
        static float size1 = 20;
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            tv1 = (TextView)findViewById(R.id.textView);//定义一个图片视图对象
            ZoomControls zc = (ZoomControls)findViewById(R.id.myZoomController);
            zc.setOnZoomInClickListener(new View.OnClickListener(){//设置ZoomControls的放大监听器
                public void onClick(View v) {
                    size1 += 10;
                    tv1.setText("ZoomControlsLarger");
                    tv1.setTextSize(size1);
                }
            });
            zc.setOnZoomOutClickListener(new View.OnClickListener(){//设置ZoomControls的缩小监听器
                public void onClick(View v) {
                    size1 -= 10;
                    tv1.setText("ZoomControlsSmaller");
                    tv1.setTextSize(size1);
                }
            });
        }
    }