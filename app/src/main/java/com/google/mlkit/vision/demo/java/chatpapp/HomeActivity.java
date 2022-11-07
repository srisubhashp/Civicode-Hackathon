package com.google.mlkit.vision.demo.java.chatpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.mlkit.vision.demo.R;
import com.google.mlkit.vision.demo.java.LivePreviewActivity;
import com.google.mlkit.vision.demo.java.UnderstandPosture.understandBadPosture;
import com.google.mlkit.vision.demo.java.UserStatistics.UserStats;

public class HomeActivity extends AppCompatActivity {

    CardView stat;
    CardView live_act;
    CardView prof;
    TextView user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        stat = (CardView) findViewById(R.id.user_stat);
        live_act = (CardView) findViewById(R.id.live_preview);
        prof = (CardView) findViewById(R.id.profile);
        user_name = findViewById(R.id.userName);
        String id = getIntent().getStringExtra("UserID");
        user_name.setText(LoginActivity.user);
        Log.i("User ID: ", id);
        Log.e("HomeAct","Entered the OnCreate Function");
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("HA","Entered LP");
                //we are diverting to the Register page.
                Intent i3;
                i3 = new Intent(HomeActivity.this, UserStats.class);
                i3.putExtra("UserID", id);
                startActivity(i3);
            };
        });

        live_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("HA","Entered UP");
                //we are diverting to the Register page.
                Intent i4;
                i4 = new Intent(HomeActivity.this, LivePreviewActivity.class);
                i4.putExtra("UserID", id);
                startActivity(i4);
            };
        });
        prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("HA","Entered US");
                //we are diverting to the Register page.
                Intent i2;
                i2 = new Intent(HomeActivity.this, understandBadPosture.class);
                i2.putExtra("UserID", id);
                startActivity(i2);
            };
        });
    }
}