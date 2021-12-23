package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int seconds;
    boolean f;
    boolean f1;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.text);
        if(savedInstanceState!=null){
        seconds=savedInstanceState.getInt("s");
        f=savedInstanceState.getBoolean("flag");}
        count();
    }

    @Override
    protected void onStop() {
        super.onStop();
        f1=f;
        f=false;

    }

    @Override
    protected void onStart() {
        super.onStart();
        f=f1;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("s",seconds);
        outState.putBoolean("flag",f);

    }

    public void start(View view) {
        f=true;
    }

    public void pause(View view) {
        f=false;
    }

    public void restart(View view) {
        f=false;
        seconds=0;
    }
     public void count(){
         Handler handler=new Handler();
         handler.post(new Runnable() {
             @Override
             public void run() {
                 int hour=seconds/3600;
                 int min=(seconds%3600)/60;
                 int sec=seconds%60;
                 String time=String.format(Locale.getDefault(),"%d:%02d:%02d",hour,min,sec);
                 textView.setText(time);
                 if(f){
                     seconds++;
                 }
                 handler.postDelayed(this,1000);

             }
         });



     }
}