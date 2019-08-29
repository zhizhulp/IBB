package com.example.ibb.mvp.view;
import android.content.Context;

/**
 * Created by ASUS on 2018/5/31.
 */

public interface DownloadView {
    void showUpdateAlert(String url);
    void startDown();
    void downProgress(int progress);
    void downOver(String filePath);
    void downCancel();
    void downFailed();
    void checkAnsShowPermissionDialog(String url);
    Context getContext();
}
