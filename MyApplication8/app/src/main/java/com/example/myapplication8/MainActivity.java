package com.example.myapplication8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private int[] imgids= com.example.myapplication8.images.imgids;
    private ImageView imageView;
    public Button btn;
    int imgstart=0;
    private ScheduledExecutorService scheduledExecutorService;
    private Handler handler =new Handler()
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what)
            { case 0x123:
                imageView.setImageResource(imgids[imgstart++%7]);
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
                scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0x123);
                    }
                }, 0, 100, TimeUnit.MILLISECONDS);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (scheduledExecutorService != null)
        {
            scheduledExecutorService.shutdown();
        }
    }
}