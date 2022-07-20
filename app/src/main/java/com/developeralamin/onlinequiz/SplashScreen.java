package com.developeralamin.onlinequiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {

    public static ArrayList<ModelClass> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        list = new ArrayList<>();

        list.add(new ModelClass("What is your name?", "sakib", "alamin","akash", "nasim","alamin"));
        list.add(new ModelClass("What is your name?", "sakib", "alamin","akash", "nasim","alamin"));
        list.add(new ModelClass("What is your name?", "sakib", "alamin","akash", "nasim","alamin"));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent(SplashScreen.this, MainActivity.class));
               finish();
            }
        }, 3000);
    }
}