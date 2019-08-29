package com.example.ibb.mvp.presenter;

import android.content.pm.PackageManager;
import android.view.View;

import com.example.ibb.BuildConfig;
import com.example.ibb.entity.Version;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.mvp.model.DownModel;
import com.example.ibb.mvp.view.DownloadView;
import com.yanzhenjie.nohttp.Headers;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by ASUS on 2018/5/31.
 */

public class DownPresenter {
    private DownModel model;
    private DownloadView view;

    public DownPresenter(DownModel model, DownloadView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * 通过接口检测是否需要升级
     */
    public void requestNeedUpdate() {
        if (BuildConfig.DEBUG) return;
        model.getVersion(new OnResponseListener<HttpBaseEntity<Version>>() {
            @Override
            public void onStart(int what) {
            }
            @Override
            public void onSucceed(int what, Response<HttpBaseEntity<Version>> response) {
                Version version = response.get().getData();
                String currentVersion = "1.0.0";
                try {
                    currentVersion = view.getContext().getPackageManager().getPackageInfo(view.getContext().getPackageName(), 0).versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    currentVersion = "1.0.0";
                    e.printStackTrace();
                }
                if (!currentVersion.equals(version.getVersion())) {
                    view.showUpdateAlert(version.getUrl());
                }
            }
            @Override
            public void onFailed(int what, Response<HttpBaseEntity<Version>> response) {
            }
            @Override
            public void onFinish(int what) {
            }
        });

    }
    /**
     * 请求permission
     */
    public void requestPermission(String url){
        view.checkAnsShowPermissionDialog(url);
    }

    /**
     * 用户点击了更新按钮
     */
    public void startDown(String url,String filePath) {
        model.downloadApp(url,new DownloadListener() {
            @Override
            public void onDownloadError(int what, Exception exception) {
                view.downFailed();
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {
                view.startDown();
            }

            @Override
            public void onProgress(int what, int progress, long fileCount, long speed) {
                view.downProgress(progress);
            }

            @Override
            public void onFinish(int what, String filePath) {
                view.downOver(filePath);
            }

            @Override
            public void onCancel(int what) {
                view.downCancel();
            }
        },filePath);
    }
}
