package com.example.ibb.lianxi;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ibb.R;

/**
 * Created by 张凯雅 on 2018/3/15.
 */

public class Item_one extends RecyclerView.ViewHolder {

    TextView my_texta;
    TextView my_textaa;

    public Item_one(View itemView) {
        super(itemView);

        my_texta =(TextView) itemView.findViewById(R.id.my_texta);
        my_textaa =(TextView) itemView.findViewById(R.id.my_textaa);
    }






}
