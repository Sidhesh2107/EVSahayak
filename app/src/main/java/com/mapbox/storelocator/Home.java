package com.mapbox.storelocator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.mapbox.storelocator.activity.MapActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Home extends AppCompatActivity {
Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Home.this, Register.class);
                startActivity(intent);
                finish();

            }
        }, 3000);
    }
}