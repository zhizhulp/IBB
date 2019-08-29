package com.example.ibb.ui.ui.mine;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ibb.BuildConfig;
import com.example.ibb.MainActivity;
import com.example.ibb.R;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.mvp.model.DownModel;
import com.example.ibb.mvp.presenter.DownPresenter;
import com.example.ibb.mvp.view.DownloadView;
import com.example.ibb.utils.AppConfig;
import com.example.ibb.utils.DataCleanUtils;
import com.example.ibb.utils.FileUtils;
import com.example.ibb.utils.ToastUtils;

import java.io.File;

public class MineSettingActivity extends BaseNetActivity implements View.OnClickListener, DownloadView {

    private ProgressDialog progressDialog;
    private RelativeLayout minesetting_bindphone_relative;
    private ImageView minesetting_back_iv;
    private RelativeLayout minesetting_mimalogin_relative;
    private RelativeLayout minesetting_update_relative;
    private ImageView setting_delete_iv;
    private RelativeLayout minesetting_about_relative;
    private RelativeLayout minesetting_userxieyi_relative;
    private Button signout_button;
    private TextView tvDataCount;
    private View imNew;
    private DownPresenter presenter;

    protected void initview() {
        minesetting_back_iv = (ImageView) findViewById(R.id.minesetting_back_iv);
        minesetting_back_iv.setOnClickListener(this);

        minesetting_mimalogin_relative = (RelativeLayout) findViewById(R.id.minesetting_mimalogin_relative);
        minesetting_mimalogin_relative.setOnClickListener(this);

        minesetting_bindphone_relative = (RelativeLayout) findViewById(R.id.minesetting_bindphone_relative);
        minesetting_bindphone_relative.setOnClickListener(this);

        minesetting_update_relative = (RelativeLayout) findViewById(R.id.minesetting_update_relative);
        minesetting_update_relative.setOnClickListener(this);

        minesetting_about_relative = (RelativeLayout) findViewById(R.id.minesetting_about_relative);
        minesetting_about_relative.setOnClickListener(this);
        //用户协议
        minesetting_userxieyi_relative = (RelativeLayout) findViewById(R.id.minesetting_userxieyi_relative);
        minesetting_userxieyi_relative.setOnClickListener(this);
        //退出登录
        signout_button = (Button) findViewById(R.id.signout_button);
        signout_button.setOnClickListener(this);
        if (AppConfig.getInstance().getString("token", null) == null) signout_button.setVisibility(View.GONE);
        else signout_button.setVisibility(View.VISIBLE);

        findViewById(R.id.lat_clear_data).setOnClickListener(this);
        tvDataCount = findViewById(R.id.tv_data_count);
        tvDataCount.setText(DataCleanUtils.getApplicationDataSize(this));

        imNew = findViewById(R.id.im_new);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_setting);
        initview();
        presenter=new DownPresenter(new DownModel(),this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.minesetting_back_iv:
                finish();
                break;

            case R.id.minesetting_mimalogin_relative:
                startActivity(new Intent(MyApp.activity, SettingPassWordLoginActivity.class));
                break;

            case R.id.minesetting_bindphone_relative:
                startActivity(new Intent(MyApp.activity, SettingBindPhoneActivity.class));
                break;

            case R.id.minesetting_about_relative:
                startActivity(new Intent(MyApp.activity, SettingAboutActivity.class));
                break;

            case R.id.minesetting_update_relative:
                presenter.requestNeedUpdate();
                break;
            //用户协议
            case R.id.minesetting_userxieyi_relative:
                startActivity(new Intent(MyApp.activity, UserAgreementActivity.class));
                break;
            //退出登录
            case R.id.signout_button:

                new AlertDialog.Builder(MineSettingActivity.this)
                        .setTitle("提醒")
                        .setMessage("您确定要退出吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AppConfig.getInstance().clearXml();
                        finish();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
                break;
            case R.id.lat_clear_data:
                if (tvDataCount.getText().equals("0K")) {
                    ToastUtils.show("无缓存");
                    return;
                }
                ProgressDialog progressDialog = ProgressDialog.show(this, null, "正在清理缓存...", true, true);
                DataCleanUtils.cleanApplicationData(this);
                tvDataCount.setText(DataCleanUtils.getApplicationDataSize(this));
                progressDialog.dismiss();
                ToastUtils.show("缓存已清除");
                break;
        }
    }

    @Override
    public void showUpdateAlert(final String url) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("更新通知");
        builder.setMessage("您有新的更新，点击确定下载" );
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                presenter.requestPermission(url);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void startDown() {
        if (progressDialog==null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(true);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        }
        progressDialog.show();
    }

    @Override
    public void downProgress(int progress) {
        if (progressDialog != null) {
            progressDialog.setProgress(progress);
        }
    }

    @Override
    public void downOver(String filePath) {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        installFile(filePath);

    }

    @Override
    public void downCancel() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void downFailed() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void checkAnsShowPermissionDialog(final String url) {
        checkAndRequestAllPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionCallback() {
            @Override
            public void requestPermissionAndBack(boolean isOk) {
                if (isOk){
                    presenter.startDown(url, FileUtils.getAppFile(MineSettingActivity.this, Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
                }
            }
        });
    }

    @Override
    public Context getContext() {
        return this;
    }

    private void installFile(String filePath) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT > 23) {
            Uri uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", new File(filePath));
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setDataAndType(uri, "application/vnd.android.package-archive");
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(i);
        } else {
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
            startActivity(i);
        }
    }
}
