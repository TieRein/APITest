package com.example.apitest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalendarPractice extends AppCompatActivity {


    private Button mbuttonBasic;
    private Button mbuttonAsynchronous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_practice);

        mbuttonBasic = findViewById(R.id.buttonBasic);
        mbuttonBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarPractice.this, BasicActivity.class);
                startActivity(intent);
            }
        });

        mbuttonAsynchronous = findViewById(R.id.buttonAsynchronous);
        mbuttonAsynchronous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalendarPractice.this, AsynchronousActivity.class);
                startActivity(intent);
            }
        });
    }
}
