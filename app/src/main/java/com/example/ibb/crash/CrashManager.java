package com.example.ibb.crash;

import android.util.Log;

/**
 * Created by ASUS on 2018/6/1.
 */

public class CrashManager implements Thread.UncaughtExceptionHandler {
    private String TAG="CrashManager";
    private static CrashManager crashManager;

    private CrashManager(){
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    public static CrashManager getInstance(){
        if (crashManager==null){
            synchronized (CrashManager.class){
                if (crashManager==null){
                    crashManager=new CrashManager();
                }
            }
        }
        return crashManager;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.e(TAG, e.toString());
        System.exit(0);
    }
}
