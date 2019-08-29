package com.example.ibb.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by 张凯雅 on 2018/4/17.
 * 权限工具类
 */

public class PermissionUtils {

    public static final int CAMERA_REQUEST_CODE = 0x01;

    public static void getPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermission(activity);
        }
    }

    private static void requestPermission(final Activity activity) {
//        ContextCompat.checkSelfPermission()

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            Log.d("requestPermission", "需要获取权限" + ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA));
            // 第一次请求权限时，用户如果拒绝，下一次请求shouldShowRequestPermissionRationale()返回true
// 向用户解释为什么需要这个权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
//                Log.d("requestPermission", "打开dialog获取权限");
                new AlertDialog.Builder(activity).setMessage("申请相机权限").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //申请相机权限
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                    }
                }).show();
            } else {
                //申请相机权限
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            }
        } else {
//            cameraTv.setText("相机权限已申请");
        }
//        這個是申請SD卡權限的
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            Log.d("requestPermission", "需要获取权限" + ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA));
            // 第一次请求权限时，用户如果拒绝，下一次请求shouldShowRequestPermissionRationale()返回true
// 向用户解释为什么需要这个权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                Log.d("requestPermission", "打开dialog获取权限");
                new AlertDialog.Builder(activity).setMessage("申请SD卡操作权限").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //申请相机权限
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    }
                }).show();
            } else {
                //申请相机权限
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        } else {
//            cameraTv.setText("相机权限已申请");
        }
    }
}
