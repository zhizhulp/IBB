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
 * Created by 张凯雅 on 2018/2/26.
 */

public class MyChooseGridViewAdapter extends BaseAdapter{
    private Context context;
    private int[] images;
    private String[] text;

    public MyChooseGridViewAdapter(Context context, int[] images, String[] text) {
        this.context = context;
        this.images = images;
        this.text = text;
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
        View view = LayoutInflater.from(context).inflate(R.layout.chooseitem_gridview, null);
        ImageView iv = (ImageView) view.findViewById(R.id.choose_gridView_iv);
        TextView tv = (TextView) view.findViewById(R.id.choose_gridView_tv);
        iv.setImageResource(images[position]);
        tv.setText(text[position]);
        return view;
    }
}
