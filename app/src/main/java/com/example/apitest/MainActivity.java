package com.example.apitest;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random rand;
    private static final int MAX_DEVICES = 2;
    private Spinner mDeviceSelection;
    private EditText mDurationField;
    private Button mCommandButton;

    private EditText mTestDevice;
    private EditText mTestDuration;
    private Button mSpecificTestButton;
    private Button mRandomTestButton;

    private EditText mCommandOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        rand = new Random();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDeviceSelection = findViewById(R.id.deviceSelection);
        mDurationField = findViewById(R.id.durationField);
        mCommandButton = findViewById(R.id.commandButton);


        mTestDevice = findViewById(R.id.testDevice);
        mTestDuration = findViewById(R.id.testDuration);
        mSpecificTestButton = findViewById(R.id.specificTestButton);
        mRandomTestButton = findViewById(R.id.randomTestButton);



        mCommandButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mDeviceSelection.getSelectedItem().equals("Blue"))
                    sendCommand(0, Integer.parseInt(mDurationField.getText().toString()));
                else
                    sendCommand(1, Integer.parseInt(mDurationField.getText().toString()));


            }
        });

        mSpecificTestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendCommand(Integer.parseInt(mTestDevice.getText().toString()), Integer.parseInt(mTestDuration.getText().toString()));
            }
        });

        mRandomTestButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mTestDevice.setText(Integer.toString(rand.nextInt(MAX_DEVICES)));
                mTestDuration.setText(Integer.toString(rand.nextInt(10) + 1));
                sendCommand(Integer.parseInt(mTestDevice.getText().toString()), Integer.parseInt(mTestDuration.getText().toString()));
            }
        });

        startActivity(new Intent(this,  CalendarPractice.class));
        finish();
    }

    private void sendCommand(int device, int duration) {
        mCommandOutput = findViewById(R.id.commandOutput);
        if (device >= MAX_DEVICES || device < 0)
            mCommandOutput.setText("Device ID should be between 0 and " + (MAX_DEVICES - 1));
        else {
            mCommandOutput.setText("{\n\"topic\": \"Quick_Run\",\n\"device\": " + device + ",\n\"duration\": " + duration + "\n}");
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            APICall server = new APICall();
            try {
                JSONObject response = server.runDevice(device, duration);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}