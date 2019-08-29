package com.example.ibb.utils;

import android.content.Context;
import android.util.TypedValue;

import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static android.util.TypedValue.COMPLEX_UNIT_PX;

/**
 * Created by ASUS on 2018/5/17.
 */

public class ScreenDpiUtils {
    public static float px2dp(Context context,float pxValue){
        return TypedValue.applyDimension(COMPLEX_UNIT_PX,pxValue,context.getResources().getDisplayMetrics());
    }

    public static float dp2px(Context context,float dpValue){
        return TypedValue.applyDimension(COMPLEX_UNIT_DIP,dpValue,context.getResources().getDisplayMetrics());
    }

    public static int getScreenWidth(Context context){

        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
