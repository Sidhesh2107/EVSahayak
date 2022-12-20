package com.mapbox.evsahayak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mapbox.evsahayak.activity.MapActivity;
import com.mapbox.evsahayak.activity.MapAther;

public class Charging extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging);
        Button revolt = findViewById(R.id.revolt);
        Button ather = findViewById(R.id.ather);
<<<<<<< HEAD
        View back = findViewById(R.id.back_charging);
=======
>>>>>>> origin/master
        revolt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Charging.this, MapActivity.class);

                startActivity(intent);
                finish();

            }
        });

        ather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Charging.this, MapAther.class);

                startActivity(intent);
                finish();

            }
        });

<<<<<<< HEAD
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Charging.this, MainActivity.class);

                startActivity(intent);
                finish();

            }
        });

=======
>>>>>>> origin/master


    }
}