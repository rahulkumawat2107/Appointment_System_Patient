package com.example.appointment_system_patient;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MyAccount extends AppCompatActivity {

    Button basic_info,change_pass,settings,legal_info,send_feed,logout;
    ImageButton prev_page;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        getSupportActionBar().hide();

        prev_page = findViewById(R.id.back);
        basic_info = findViewById(R.id.information);
        change_pass = findViewById(R.id.change_pass);
        settings = findViewById(R.id.settings);
        legal_info = findViewById(R.id.legal_info);
        send_feed = findViewById(R.id.send_feedback);
        logout = findViewById(R.id.logout);



        prev_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccount.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        basic_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAccount.this,Profile.class);
                startActivity(intent);
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_dialog_logout);


                Button cancel_logout = (Button) dialog.findViewById(R.id.cancel);
                Button confirm_logout = (Button) dialog.findViewById(R.id.yes);

                confirm_logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(MyAccount.this, Login_screen.class);
                        startActivity(intent);
                        finish();
                    }
                });

                cancel_logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

        });

    }

    public void onBackPressed() {
        Intent intent = new Intent(MyAccount.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
