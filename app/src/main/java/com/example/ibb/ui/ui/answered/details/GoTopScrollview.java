package com.example.ibb.ui.ui.answered.details;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ibb.R;

/**
 * Created by Administrator on 2018/2/9.
 */

public class GoTopScrollview extends ScrollView implements View.OnClickListener {
    private int height=50;
    private TextView lt_main_title_yc;
    private TextView lt_main_title;
    public GoTopScrollview(Context context) {
        super(context);
    }

    public GoTopScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GoTopScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void setHeight(int height){
        this.height=height;
    }
    public void setOnClickListener(TextView lt_main_title, TextView lt_main_title_yc) {
//        this.lt_main_title = lt_main_title;
//        this.lt_main_title_yc = lt_main_title_yc;
//        this.lt_main_title_yc.setOnClickListener(this);
//        this.lt_main_title.setOnClickListener(this);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (height!=0){
            if (t<height){
//                iv.setBackgroundResource(R.color.colorPrimaryDark);
//                iv.setBackgroundColor(Color.BLUE);
//                lt_main_title_yc.setVisibility(GONE);
//                lt_main_title.setVisibility(VISIBLE);
            }else{
//                lt_main_title_yc.setBackgroundResource(R.drawable.details_button);
//                tv1.setBackgroundColor(Color.BLACK);
//                lt_main_title_yc.setVisibility(VISIBLE);
//                lt_main_title.setVisibility(GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        this.scrollTo(0,0);
    }


}
