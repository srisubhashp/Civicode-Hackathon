package com.google.mlkit.vision.demo.java.PostureSparkAdapter;

import com.robinhood.spark.SparkAdapter;

import java.util.ArrayList;

public class PostureSparkAdapter extends SparkAdapter {

    ArrayList<Long> bad_pos_count;
    ArrayList<String> x_vals;
    public PostureSparkAdapter(ArrayList<Long> bad_pos_count, ArrayList<String> x_vals) {
        this.bad_pos_count = bad_pos_count;
        this.x_vals = x_vals;
    }

    @Override
    public int getCount() {
        return bad_pos_count.size();
    }

//    @Override
//    public Object getItem(int index) {
//        return bad_pos_count[index];
//    }

    @Override
    public float getY(int index) {
        return (float) (bad_pos_count.get(index));
    }
    @Override
    public Object getItem(int index) {
        return "" + x_vals.get(index) + ": " + bad_pos_count.get(index);
    }
}
