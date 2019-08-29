package com.example.ibb.mvp.model;

import android.os.Environment;

import com.example.ibb.apimanager.URLApi;
import com.example.ibb.entity.Version;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.okhttp.EntityRequest;
import com.example.ibb.utils.FileUtils;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.download.DownloadListener;
import com.yanzhenjie.nohttp.download.DownloadRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;

/**
 * Created by ASUS on 2018/5/31.
 */

public class DownModel {
    public void getVersion(OnResponseListener<HttpBaseEntity<Version>> listener) {
        final EntityRequest<Version> request = new EntityRequest<>(URLApi.updateApp, RequestMethod.POST, Version.class);
        NoHttp.newRequestQueue().add(0, request, listener);
    }

    public void downloadApp(String url, DownloadListener listener, String filePath) {
        DownloadRequest request = new DownloadRequest(url, RequestMethod.GET,
                filePath, "app_release_ibb.apk", true, true);
        NoHttp.newDownloadQueue().add(1, request, listener);
    }
}
