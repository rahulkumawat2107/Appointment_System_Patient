package com.example.appointment_system_patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import Pojos.Common;

public class Confirmation extends AppCompatActivity {

    TextView token,time;
    ImageButton back;
    Button done;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Bundle bundle1 = getIntent().getExtras();
        date = bundle1.getString("finaldate");

        getSupportActionBar().hide();

        token = findViewById(R.id.token_no);
        time = findViewById(R.id.date_time);
        back = findViewById(R.id.back_appt);
        done = findViewById(R.id.done);

        time.setText(new StringBuilder(Common.convertTimeSlotToString(Common.currentTimeSlot)) + ", " + date);
        token.setText(Integer.toString(Common.currentTimeSlot+1));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Confirmation.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Confirmation.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void onBackPressed() {
        Intent intent = new Intent(Confirmation.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
