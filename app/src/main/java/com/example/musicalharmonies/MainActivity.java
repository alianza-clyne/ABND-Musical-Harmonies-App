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
        TextView numbers = (TextView) findViewById(R.id.gospel);

        // Set a click listener on that View
        numbers.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the gospel category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link GospelActivity}
                Intent numbersIntent = new Intent(MainActivity.this, GospelActivity.class);

                // Start the new activity
                startActivity(numbersIntent);
            }
        });

        // Find the View that shows the pop category
        TextView family = (TextView) findViewById(R.id.pop);

        // Set a click listener on that View
        family.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the pop category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link PopActivity}
                Intent familyIntent = new Intent(MainActivity.this, PopActivity.class);

                // Start the new activity
                startActivity(familyIntent);
            }
        });

        // Find the View that shows the rnb category
        TextView colors = (TextView) findViewById(R.id.rnb);

        // Set a click listener on that View
        colors.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the rnb category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link RnBActivity}
                Intent colorsIntent = new Intent(MainActivity.this, RnBActivity.class);

                // Start the new activity
                startActivity(colorsIntent);
            }
        });

        // Find the View that shows the instrumental category
        TextView phrases = (TextView) findViewById(R.id.instrumental);

        // Set a click listener on that View
        phrases.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the instrumental category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link InstrumentalActivity}
                Intent phrasesIntent = new Intent(MainActivity.this, InstrumentalActivity.class);

                // Start the new activity
                startActivity(phrasesIntent);
            }
        });
    }
    }

