package com.google.mlkit.vision.demo.java.UnderstandPosture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.mlkit.vision.demo.R;
import com.google.mlkit.vision.demo.java.chatpapp.LoginActivity;

import org.checkerframework.checker.units.qual.A;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class understandBadPosture extends AppCompatActivity {
    GridView gridView;
    ArrayList<Bitmap> imageArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_understand_bad_posture);
        gridView = findViewById(R.id.imgGrid1);
        //ImageView img_test = findViewById(R.id.img_test);
        imageArray = new ArrayList<Bitmap>();
        //Drawable img1 = getResources().getDrawable(R.drawable.aasan);
        Bitmap aasan_img = BitmapFactory.decodeResource(this.getResources(), R.drawable.aasan);
        imageArray.add(aasan_img);
        PostureGrid gridAdapter = new PostureGrid(this, imageArray);
        gridView.setAdapter(gridAdapter);
        //mage_test.setImageBitmap(PostureGrid.imageArray.get(0));
        FirebaseStorage storageRef = FirebaseStorage.getInstance();
        StorageReference storageReference=storageRef.getReference().child(LoginActivity.user);
        final int[] flag = {0};
        storageReference.listAll().addOnCompleteListener(new OnCompleteListener<ListResult>() {
            @Override
            public void onComplete(@NonNull Task<ListResult> task) {
                Log.i("Results", task.getResult().toString());
                for(StorageReference refs: task.getResult().getItems()) {
                    Log.i("Results2", refs.toString());
                    long file_size = 1024 * 1024 * 10;
                    refs.getBytes(file_size).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            imageArray.add(bitmap);
                            if(flag[0] ==0) {
                                imageArray.remove(0);
                                flag[0] =1;
                            }
                            Log.i("Results3", String.valueOf(imageArray.size()));
                            gridAdapter.notifyDataSetChanged();
//                            PostureGrid gridAdapter = new PostureGrid(this,);
//                            gridView.setAdapter(gridAdapter);
//                            gridAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                imageArray.get(i).compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Intent in1 = new Intent(understandBadPosture.this, FullScreenPos.class);
                in1.putExtra("image", byteArray);
                startActivity(in1);
            }
        });
    }
}