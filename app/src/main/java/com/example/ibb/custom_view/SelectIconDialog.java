package com.example.ibb.custom_view;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.ibb.BuildConfig;
import com.example.ibb.R;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.base.BaseNetFragment;
import com.example.ibb.utils.FinalUtils;
import com.example.ibb.utils.ScreenDpiUtils;

import java.io.File;

import static android.view.Gravity.BOTTOM;

/**
 * Created by ASUS on 2018/5/18.
 */

public class SelectIconDialog extends Dialog implements View.OnClickListener {
    private File file;
    private Context context;
    private Fragment fragment;
    private int cameraRequestCode = FinalUtils.REQUEST_CAMERA_ICON;
    private int albumRequestCode = FinalUtils.REQUEST_ALBUM_ICON;

    public SelectIconDialog(@NonNull Context context, File file, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
        this.file = file;
        initPop();
    }

    public SelectIconDialog(@NonNull Fragment fragment, File file, @StyleRes int themeResId) {
        super(fragment.getActivity(), themeResId);
        this.context = fragment.getActivity();
        this.fragment = fragment;
        this.file = file;
        initPop();
    }

    private void initPop() {
        setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(context).inflate(R.layout.select_icon_pop, null);
        LinearLayout.LayoutParams sLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        sLp.width = (int) (context.getResources().getDisplayMetrics().widthPixels - ScreenDpiUtils.dp2px(context, 40));//margin值由宽度来控制
        setContentView(view, sLp);
        View camera = findViewById(R.id.head_icon_select_camera);
        View album = findViewById(R.id.head_icon_select_album);
        View cancel = findViewById(R.id.head_icon_select_cancel);
        camera.setOnClickListener(this);//相机拍照
        album.setOnClickListener(this);//相册选择
        cancel.setOnClickListener(this);//取消选择

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = BOTTOM;
        window.setWindowAnimations(R.style.AnimationBottomSlide);
        window.setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.head_icon_select_camera:
                dismiss();
                if (fragment != null) {
                    ((BaseNetFragment) fragment).checkAndRequestAllPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA}, new BaseNetFragment.PermissionCallback() {
                        @Override
                        public void requestPermissionAndBack(boolean isOk) {
                            if (isOk) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                if (Build.VERSION.SDK_INT > 23) {//处理7.0的情况
                                    Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                    fragment.startActivityForResult(intent, cameraRequestCode);
                                } else {
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                                    fragment.startActivityForResult(intent, cameraRequestCode);
                                }
                            }
                        }
                    });
                    break;
                }
                if (context != null) {
                    ((BaseNetActivity) context).checkAndRequestAllPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA}, new BaseNetActivity.PermissionCallback() {
                        @Override
                        public void requestPermissionAndBack(boolean isOk) {
                            if (isOk) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                if (Build.VERSION.SDK_INT > 23) {//处理7.0的情况
                                    Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                                    ((Activity) context).startActivityForResult(intent, cameraRequestCode);
                                } else {
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                                    ((Activity) context).startActivityForResult(intent, cameraRequestCode);
                                }
                            }
                        }
                    });
                    break;
                }

            case R.id.head_icon_select_album:
                dismiss();
                Intent intent2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                ((Activity) context).startActivityForResult(intent2, albumRequestCode);
                break;
            case R.id.head_icon_select_cancel:
                dismiss();
                break;
        }
    }

    public void setCameraRequestCode(int cameraRequestCode) {
        this.cameraRequestCode = cameraRequestCode;
    }

    public void setAlbumRequestCode(int albumRequestCode) {
        this.albumRequestCode = albumRequestCode;
    }
}
