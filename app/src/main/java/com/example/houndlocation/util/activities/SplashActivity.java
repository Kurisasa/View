package com.example.houndlocation.util.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.houndlocation.R;
import com.example.houndlocation.util.model.Main;

public class SplashActivity extends AppCompatActivity {

    private int SPLASH_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(SPLASH_TIME);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
//                    Intent intent = new Intent(this, MainActivity.class);
//                    startActivity(intent);
                }
            }
        };
        timer.start();
    }
}
