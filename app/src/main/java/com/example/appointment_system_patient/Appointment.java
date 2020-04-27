package com.example.appointment_system_patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.Calendar;

import Adapters.MyTimeSlotAdapter;
import Adapters.PageAdapter;
import Pojos.BookingPojo;
import Pojos.Common;
import butterknife.OnClick;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class Appointment extends AppCompatActivity {

    TextView apt_time,apt_date,apt_amount;
    Button confirm;
    public String selectedDateStr,final_date;
    Calendar selected_date;
    AlertDialog dialog;
    LocalBroadcastManager localBroadcastManager;

    //This is our tablayout
    private TabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

//    private BroadcastReceiver displayTime = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            //int temp = Integer.parseInt(intent.getStringExtra("time"));
//            Log.d("@abc", "onReceive: " + intent.getStringExtra(Common.KEY_STEP));
//            //String time = Common.convertTimeSlotToString(temp);
//            //apt_time.setText(time);
//        }
//    };
//
//    private void setData() {
//        Log.d("@abc", "setData: "+Common.convertTimeSlotToString(Common.currentTimeSlot));
//        apt_time.setText(new StringBuilder(Common.convertTimeSlotToString(Common.currentTimeSlot)));
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        getSupportActionBar().hide();

        apt_date = findViewById(R.id.appt_date);
        apt_time = findViewById(R.id.appt_time);
        apt_amount = findViewById(R.id.appt_price);
        confirm = findViewById(R.id.confirm_appt);

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(buttonNextReceiver,new IntentFilter(Common.KEY_ENABLE_BUTTON_NEXT));

        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);

        //Calendar Code

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, 0);

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE, 2);
        final Calendar defaultSelectedDate = Calendar.getInstance();

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate,endDate)
                .datesNumberOnScreen(5)
                .mode(HorizontalCalendar.Mode.DAYS)
                .configure()
                .showTopText(true)
                .showBottomText(true)
                .textColor(Color.LTGRAY,Color.BLACK)
                .selectorColor(Color.parseColor("#0999ED"))
                .colorTextMiddle(Color.LTGRAY, Color.parseColor("#0999ED"))
                .end()
                .defaultSelectedDate(defaultSelectedDate)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {

                //if(selected_date.getTimeInMillis() != date.getTimeInMillis()){
                    selected_date = date;
                    selectedDateStr = selected_date.toString();
                    selectedDateStr = DateFormat.format("dd_MM_yyyy", date).toString();
                    apt_date.setText(DateFormat.format("d MMM yyyy", date).toString());
                    final_date = DateFormat.format("d MMM yyyy", date).toString();
                    Intent intent = new Intent("my-event");
                    // You can also include some extra data.
                    intent.putExtra("data", selectedDateStr);
                    LocalBroadcastManager.getInstance(Appointment.this).sendBroadcast(intent);
             //   }
/*
                selectedDateStr = DateFormat.format("EEE, d MMM yyyy", date).toString();
                apt_date.setText("Date : "+selectedDateStr);

                Log.d("abc", "selecteddatre " + selectedDateStr);
                Intent intent = new Intent("my-event");
                // You can also include some extra data.
                intent.putExtra("data", selectedDateStr);
                LocalBroadcastManager.getInstance(Appointment.this).sendBroadcast(intent);

 */
            }
            @Override
            public void onCalendarScroll(HorizontalCalendarView calendarView,
                                         int dx, int dy) {

            }

            @Override
            public boolean onDateLongClicked(Calendar date, int position) {
                return true;
            }
        });




        //Fragment code
        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Morning"));
        tabLayout.addTab(tabLayout.newTab().setText("Evening"));

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.setScrollPosition(position, positionOffset, true);

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Common.currentTimeSlot != -1){
                    Log.d("@abc", "confirmbooking()");
                    confirmBooking();
                }

                Log.d("@abc", "onReceive0: "+Common.currentTimeSlot);
                //adding to database
                BookingPojo bookingPojo = new BookingPojo();
                bookingPojo.setName(sharedPreferences.getString("name",null));
                bookingPojo.setPhone(sharedPreferences.getString("number",null));
                bookingPojo.setSlot(Long.valueOf(Common.currentTimeSlot));
                bookingPojo.setTime(Common.convertTimeSlotToString(Common.currentTimeSlot));

                DocumentReference bookingdate = FirebaseFirestore.getInstance()
                        .collection("Appointment")
                        .document("Date")
                        .collection(selectedDateStr)
                        .document(String.valueOf(Common.currentTimeSlot));

                bookingdate.set(bookingPojo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(Appointment.this,Confirmation.class);
                        Bundle basket= new Bundle();
                        basket.putString("finaldate", final_date);
                        intent.putExtras(basket);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), ""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }


    //Booking of appointment
    private void confirmBooking() {
        Intent intent = new Intent(Common.KEY_CONFIRM_BOOKING);
        Log.d("@abc", "KEY _ confirmbooking()" + "yo");
        localBroadcastManager.sendBroadcast(intent);
    }

    private BroadcastReceiver buttonNextReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Common.currentTimeSlot = intent.getIntExtra(Common.KEY_TIME_SLOT,-1);
            apt_time.setText(", " + new StringBuilder(Common.convertTimeSlotToString(Common.currentTimeSlot)));
            Log.d("@abc", "onReceive: "+Common.currentTimeSlot);
        }
    };

    @Override
    protected void onDestroy() {
        localBroadcastManager.unregisterReceiver(buttonNextReceiver);
        super.onDestroy();
    }

    public void onBackPressed() {
        Intent intent = new Intent(Appointment.this,MainActivity.class);
        startActivity(intent);
        finish();
    }


}
