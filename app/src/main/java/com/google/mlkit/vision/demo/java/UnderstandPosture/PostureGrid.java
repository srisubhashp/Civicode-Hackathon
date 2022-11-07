package com.google.mlkit.vision.demo.java.UnderstandPosture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Transaction;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.mlkit.vision.demo.java.chatpapp.LoginActivity;

import java.util.ArrayList;

public class PostureGrid extends BaseAdapter {
    private Context mContext;
    StorageReference storageRef;
    ArrayList<Bitmap> imageArray = new ArrayList<>();;
//    public int[] imageArray = {
//
//    };

    public PostureGrid(Context context, ArrayList<Bitmap> imageArray) {
        this.mContext = context;
        this.imageArray = imageArray;
    }

    @Override
    public int getCount() {
        return imageArray.size();
    }

    @Override
    public Object getItem(int i) {
        return imageArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(mContext);
        //imageView.setMaxWidth(20);
        //imageView.setMaxHeight(90);
        imageView.setImageBitmap(imageArray.get(i));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(200, 280));
        return imageView;
    }
}
