package com.example.ibb.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

/**
 * Created by 张凯雅 on 2017/12/12.
 */

public class MyScrollview extends ScrollView {

    private int mTouchSlop;
    private int downX;
    private int downY;
    private int moveY;

    public MyScrollview(Context context) {
        super(context);

        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);

        mTouchSlop=ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mTouchSlop=ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getRawX();
                downY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveY = (int) ev.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop){
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
