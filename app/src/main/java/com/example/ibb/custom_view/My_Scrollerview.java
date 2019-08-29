package com.example.ibb.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2018/2/28.
 */

public class My_Scrollerview extends ScrollView {
    public My_Scrollerview(Context context) {
        super(context);
    }

    public My_Scrollerview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public My_Scrollerview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

