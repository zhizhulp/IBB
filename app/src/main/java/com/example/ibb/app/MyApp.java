package com.example.ibb.app;

import android.app.Activity;
import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;

/**
 * Created by 张凯雅 on 2017/12/12.
 */

public class MyApp extends Application {

    public static Activity activity;
    private static MyApp myApp;
    public static MyApp getMyApp(){
        return myApp;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "1fe6a20054bcef865eeb0991ee84525b");
        UMConfigure.setLogEnabled(true);
        PlatformConfig.setWeixin("wx711eeb82a1bdcc0c", "4a797dde1250c8e417637cac92ee065c");
        PlatformConfig.setSinaWeibo("839730803", "935121d04569cdb61c38eb12a1a9a1ac","http://app-api.ibbtech.cn");
        PlatformConfig.setQQZone("1106726265", "bTPOniCCcmGjLNEM");
        NoHttp.initialize(this);
        Logger.setDebug(true);
        Logger.setTag("NoHttpLog");
    }
}
