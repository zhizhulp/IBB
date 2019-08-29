package com.example.ibb.lianxi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ibb.R;

/**
 * Created by 张凯雅 on 2018/3/15.
 */

public class Item_thread extends RecyclerView.ViewHolder  {

    TextView my_textc;

    Item_thread(View itemView) {
        super(itemView);
        my_textc =(TextView) itemView.findViewById(R.id.my_textc);

    }
}