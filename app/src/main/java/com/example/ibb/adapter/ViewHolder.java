package com.example.ibb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ibb.R;

/**
 * Created by 张凯雅 on 2018/3/13.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView item_title_iv;
    public ViewHolder(View itemView) {
        super(itemView);
        item_title_iv =(TextView) itemView.findViewById(R.id.item_title_iv);
    }
}
