package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private int[] imgids= {//图片资源列表
            R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,
            R.drawable.p5,R.drawable.p6,R.drawable.p7
    };;
    private ImageView imageView;
    public Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageView1);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Task task=new Task();
            task.execute();
            }
        });
    }
    class Task extends AsyncTask<Void,Integer,Boolean>
   {    int imgstart;

       @Override
       protected void onPreExecute() {
           super.onPreExecute();
       }

       @Override
       protected Boolean doInBackground(Void... params){

           try {
               while(true)
               {
                   Thread.sleep(100);
                   imgstart++;
                   imgstart=imgstart%7;
                   publishProgress(imgstart);
                   if(imgstart==8)
                   {
                        break;
                   }
               }

           } catch (InterruptedException e) {
               e.printStackTrace();
           }
            return true;

       }

       @Override
       protected void onProgressUpdate(Integer... values) {
           super.onProgressUpdate(values);
           imageView.setImageResource(imgids[values[0]]);
       }

       @Override
       protected void onPostExecute(Boolean bollean) {
           super.onPostExecute(bollean);
       }
   }
}