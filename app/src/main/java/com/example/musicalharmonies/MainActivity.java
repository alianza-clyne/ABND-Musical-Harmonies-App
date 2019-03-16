package com.example.musicalharmonies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Find the View that shows the gospel category
        TextView gospel = (TextView) findViewById(R.id.gospel);

        // Set a click listener on that View
        gospel.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the gospel category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link GospelActivity}
                Intent gospelIntent = new Intent(MainActivity.this, GospelActivity.class);

                // Start the new activity
                startActivity(gospelIntent);
            }
        });

        // Find the View that shows the pop category
        TextView pop = (TextView) findViewById(R.id.pop);

        // Set a click listener on that View
        pop.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the pop category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link PopActivity}
                Intent popIntent = new Intent(MainActivity.this, PopActivity.class);

                // Start the new activity
                startActivity(popIntent);
            }
        });

        // Find the View that shows the rnb category
        TextView RnB = (TextView) findViewById(R.id.rnb);

        // Set a click listener on that View
        RnB.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the rnb category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link RnBActivity}
                Intent RnBIntent = new Intent(MainActivity.this, RnBActivity.class);

                // Start the new activity
                startActivity(RnBIntent);
            }
        });

        // Find the View that shows the instrumental category
        TextView instrumental = (TextView) findViewById(R.id.instrumental);

        // Set a click listener on that View
        instrumental.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the instrumental category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link InstrumentalActivity}
                Intent instrumentalIntent = new Intent(MainActivity.this, InstrumentalActivity.class);

                // Start the new activity
                startActivity(instrumentalIntent);
            }
        });
    }
    }

