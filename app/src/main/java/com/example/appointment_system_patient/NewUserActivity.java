package com.example.appointment_system_patient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import Pojos.PatientPojo;

public class NewUserActivity extends AppCompatActivity {


    EditText t_num,t_pass,t_confirm;
    Button signup,already_user;
    String item;
    ProgressDialog mDialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("patientData");

        getSupportActionBar().hide();

        t_num= findViewById(R.id.new_pat_num);
        t_pass = findViewById(R.id.new_pat_pass);
        t_confirm = findViewById(R.id.con_pass);
        signup = findViewById(R.id.create);
        already_user = findViewById(R.id.prev_user);



        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = t_num.getText().toString();
                String pass = t_pass.getText().toString();
                String confirm = t_confirm.getText().toString();
                String id = databaseReference.push().getKey();

                mDialog = new ProgressDialog(NewUserActivity.this);
                mDialog.setMessage("Adding User..." );
                mDialog.setTitle("Loading");
                mDialog.show();

                if(pass.equals(confirm)){

                    PatientPojo patientPojo = new PatientPojo();
                    patientPojo.setNumber(num);
                    patientPojo.setPassword(pass);
                    databaseReference.child(id).setValue(patientPojo);
                    Bundle basket= new Bundle();
                    basket.putString("id", id);
                    mDialog.dismiss();
                    Toast.makeText(NewUserActivity.this, "Patient Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(NewUserActivity.this,Patient_details_starting.class);
                    intent1.putExtras(basket);
                    startActivity(intent1);
                    finish();

                }
                else {
                    Toast.makeText(NewUserActivity.this, "Password Mismatched", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NewUserActivity.this,Login_screen.class);
        startActivity(intent);
        finish();
    }

}
