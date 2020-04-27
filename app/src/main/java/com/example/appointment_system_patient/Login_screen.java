package com.example.appointment_system_patient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Pojos.PatientPojo;

public class Login_screen extends AppCompatActivity {

    Bundle basket;
    EditText patient_num,pass;
    Button btn_signin,checkbox,btn_signup;
    String pat_num,password;
    ProgressDialog mDialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        getSupportActionBar().hide();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("patientData");

        patient_num=findViewById(R.id.pat_num);
        pass=findViewById(R.id.pat_pass);
        btn_signin=findViewById(R.id.sign_in);
        btn_signup = findViewById(R.id.sign_up);
        checkbox = findViewById(R.id.LoginCheckBox);

        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        Boolean status=sharedPreferences.getBoolean("loginstatus",false);

        if (status==true){
            Intent intent1=new Intent(Login_screen.this,MainActivity.class);
            startActivity(intent1);
            finish();
        }

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pat_num = patient_num.getText().toString();
                password = pass.getText().toString();
                mDialog = new ProgressDialog(Login_screen.this);
                mDialog.setMessage("Please Wait..." + pat_num);
                mDialog.setTitle("Loading");
                mDialog.show();

                final String[] no = new String[1];

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot dataSnapshot) {
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                            PatientPojo patientPojo = dataSnapshot1.getValue(PatientPojo.class);
                            String s_num = patientPojo.getNumber();

                            String spassword=patientPojo.getPassword();

                            if((pat_num.equals(s_num))&&(password.equals(spassword))){
                                SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();

                                editor.putString("number",patientPojo.getNumber());
                                editor.putString("name",patientPojo.getName());
                                editor.putString("email",patientPojo.getEmail());
                                editor.putString("password",patientPojo.getPassword());
                                editor.putString("imageUrl",patientPojo.getImageURL());

                                no[0] = patientPojo.getNumber();

                                CheckBox ch = (CheckBox) findViewById(R.id.LoginCheckBox);
                                if(ch.isChecked())
                                    editor.putBoolean("loginstatus",true);
                                else
                                    editor.putBoolean("loginstatus",false);
                                editor.commit();
                                flag=1;
                                break;
                            }
                        }
                        if(flag==1){
                            Bundle basket= new Bundle();
                            basket.putString("number", no[0]);

                            mDialog.dismiss();
                            Intent intent1=new Intent(Login_screen.this,MainActivity.class);
                            intent1.putExtras(basket);
                            startActivity(intent1);
                            finish();
                        }
                        else{
                            Toast.makeText(Login_screen.this,"Invalid Login Details",Toast.LENGTH_LONG).show();
                            mDialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(Login_screen.this,"Connection Error",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_screen.this,NewUserActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
