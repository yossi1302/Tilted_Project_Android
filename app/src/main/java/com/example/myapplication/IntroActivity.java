package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

public class IntroActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        new CountDownTimer(2500, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                changeActivity();
            }
        }.start();
    }

    public void changeActivity(){
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }
}