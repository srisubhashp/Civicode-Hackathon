package com.google.mlkit.vision.demo.java.UserStatistics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.mlkit.vision.demo.R;
import com.google.mlkit.vision.demo.java.PostureSparkAdapter.PostureSparkAdapter;
import com.google.mlkit.vision.demo.java.chatpapp.UserProfile;
import com.robinhood.spark.SparkView;

import org.checkerframework.checker.units.qual.A;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class UserStats extends AppCompatActivity {
    SparkView sparkView;
    TextView s1;
    TextView date1;

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stats);
        sparkView = findViewById(R.id.spark1);
        s1 = findViewById(R.id.s1);
        date1 = findViewById(R.id.date1);
        String id = getIntent().getStringExtra("UserID");

        reference = FirebaseDatabase.getInstance().getReference("user");
        reference.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                ArrayList<Long> bad_pos_count = new ArrayList<Long>();
                ArrayList<String> bad_pos_count_key2 = new ArrayList<String>();
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        DataSnapshot dataSnapshot2 = dataSnapshot.child("bad_pos_count");
                        for (DataSnapshot snapshot : dataSnapshot2.getChildren()){
                            bad_pos_count.add((Long) snapshot.getValue());
                            bad_pos_count_key2.add(snapshot.getKey());
                            String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                            Log.i("Timestamp1: ", snapshot.getKey()+" " + currentDate);
                            // Log.i("Timestamp1: ", currentDate);
                        }
                        Log.i("Count: ", bad_pos_count_key2.toString());
                        String[] x_vals = {"Analysis 1", "Analysis 2", "Analysis 3", "Analysis 4", "Analysis 5", "Analysis 6", "Analysis 7"};
                        updateDisplayWithData(bad_pos_count, bad_pos_count_key2);
                    }
                } else {
                    Toast.makeText(UserStats.this, "Failed to read", Toast.LENGTH_SHORT).show();
                }
            }

        });
        sparkView.setScrubListener(new SparkView.OnScrubListener() {
            @Override
            public void onScrubbed(Object value) {
                // s1.setText(getString(R.string.scrub_format, value));
//                Log.i("Is this x?", value);
                if(value == null) {
                    s1.setText("");
                } else {
                    String[] res = value.toString().split(":");
                    s1.setText(res[1]);
                    date1.setText(res[0 ]);
                }

            }
        });
//        reference = FirebaseDatabase.getInstance().getReference("user");
//        reference.child()



    }

    private void updateDisplayWithData(ArrayList<Long> bad_pos_count, ArrayList<String> x_vals) {
        PostureSparkAdapter adapter = new PostureSparkAdapter(bad_pos_count, x_vals);
        sparkView.setAdapter(adapter);
    }
//canges maddde

}