package com.example.appointment_system_patient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import Pojos.PatientPojo;

public class Patient_details_starting extends AppCompatActivity {

    EditText name,email,age,b_grp,address;
    Button skip,submit;
    ProgressDialog mDialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details_starting);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("patientData");

        skip = findViewById(R.id.skip);
        submit = findViewById(R.id.submit_details);
        name = findViewById(R.id.p_name);
        email = findViewById(R.id.p_email);
        age = findViewById(R.id.age);
        b_grp = findViewById(R.id.blood_grp);
        address = findViewById(R.id.address);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nme = name.getText().toString();
                String mail = email.getText().toString();
                String p_age = age.getText().toString();
                String p_blood = b_grp.getText().toString();
                String addr = address.getText().toString();
                String id;

                mDialog = new ProgressDialog(Patient_details_starting.this);
                mDialog.setMessage("Adding Your Details..." );
                mDialog.setTitle("Loading");
                mDialog.show();
                Bundle bundle1 = getIntent().getExtras();
                id = bundle1.getString("id");

                Log.d("12345", "shared peferne "+id);



/*
                HashMap<String, Object> map = new HashMap<>();
                map.put("name", nme);
                map.put("number", "+91"+num);
                databaseReference.child(id).updateChildren(map);*/

                mDialog.dismiss();
                Toast.makeText(Patient_details_starting.this, "Patient Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Patient_details_starting.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Patient_details_starting.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Patient_details_starting.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
