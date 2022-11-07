package com.google.mlkit.vision.demo.java.chatpapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.mlkit.vision.demo.R;

public class UserProfile extends AppCompatActivity {
    DatabaseReference reference;
    TextView tv1, tv2, tv3, tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        String id = getIntent().getStringExtra("UserID");
        tv1 = findViewById(R.id.textView2);
        tv2 = findViewById(R.id.textView3);
        tv3 = findViewById(R.id.textView4);

        reference = FirebaseDatabase.getInstance().getReference("user");
        reference.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()) {
                    if(task.getResult().exists()) {
                        DataSnapshot dataSnapshot = task.getResult();
                        String username = String.valueOf(dataSnapshot.child("emailID").getValue());
                        String name = String.valueOf(dataSnapshot.child("name").getValue());
                        String status = String.valueOf(dataSnapshot.child("age").getValue());
                        tv1.setText(username);
                        tv2.setText(name);
                        tv3.setText(status);
                    }
                } else {
                    Toast.makeText(UserProfile.this, "Failed to read", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}