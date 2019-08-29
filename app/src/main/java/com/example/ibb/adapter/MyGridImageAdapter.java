package com.example.ibb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibb.R;

/**
 * Created by 张凯雅 on 2018/4/3.
 * 九宫格图片
 */

public class MyGridImageAdapter extends BaseAdapter {
    private Context context;
    private int[] images;;

    public MyGridImageAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.gridimage_item, null);
        ImageView iv = (ImageView) view.findViewById(R.id.griditem_image);
        iv.setImageResource(images[position]);
        return view;
    }
}
