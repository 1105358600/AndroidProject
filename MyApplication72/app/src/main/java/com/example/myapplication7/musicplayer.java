package com.example.myapplication7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class musicplayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musicplayer);
        final Intent intent =new Intent(musicplayer.this,MusicService.class);
        ImageButton btn_play=(ImageButton) findViewById(R.id.btn_play);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( MusicService.isplay==false)
                {startService(intent);
                    Toast.makeText(getApplicationContext(), "正在播放", Toast.LENGTH_LONG).show();
                }
                else
                {stopService(intent);
                    Toast.makeText(getApplicationContext(), "暂停播放", Toast.LENGTH_LONG).show();}
        }});

        ImageButton btn_return=(ImageButton) findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( MusicService.isplay==true)
                {
                    Intent intent2 = new Intent( musicplayer.this,MainActivity.class);
                    startActivity(intent2);
                    Toast.makeText(getApplicationContext(), "正在播放", Toast.LENGTH_LONG).show();
                }
            }});

        ImageButton btn_stop=(ImageButton) findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( MusicService.isplay==true)
                {stopService(intent);
                Intent intent1 = new Intent( musicplayer.this,MainActivity.class);
                    startActivity(intent1);
                    Toast.makeText(getApplicationContext(), "停止播放", Toast.LENGTH_LONG).show();
                }
            }});
    }
}