package com.example.ibb;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.AddEventBusBean;
import com.example.ibb.entity.Version;
import com.example.ibb.mvp.model.DownModel;
import com.example.ibb.mvp.presenter.DownPresenter;
import com.example.ibb.mvp.view.DownloadView;
import com.example.ibb.ui.ui.add.ApplyQActivity;
import com.example.ibb.ui.ui.answered.OneFragment;
import com.example.ibb.ui.ui.find.FindFragment;
import com.example.ibb.ui.ui.mine.MineFragment;
import com.example.ibb.ui.ui.msg.MessageFragment;
import com.example.ibb.utils.FileUtils;
import com.example.ibb.utils.NetwokeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

public class MainActivity extends BaseNetActivity implements View.OnClickListener, DownloadView {
    private ProgressDialog progressDialog;
    private DownPresenter presenter;
    private RadioButton answered_radiobt;
    private RadioButton find_radiobt;
    private RadioButton message_radiobt;
    private RadioButton mine_radiobt;
    private MineFragment mineFragment;//我的
    private FindFragment findFragment;//发现
    private OneFragment oneFragment;//问答
    private MessageFragment messageFragment;//消息
    private RadioButton add_radiobt;
    private FragmentTransaction transaction;
    private NetwokeUtils netwoke;
    //退出时的时间
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter=new DownPresenter(new DownModel(),this);
        presenter.requestNeedUpdate();
        initview();
        initdata();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onBackPressed() {
        //返回手机的主屏幕
        Intent intent=new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    /**
     * 找控件id
     */
    protected void initview() {
        EventBus.getDefault().register(this);
        answered_radiobt =(RadioButton) findViewById(R.id.answered_radiobt);
        find_radiobt =(RadioButton)findViewById(R.id.find_radiobt);
        add_radiobt =(RadioButton)findViewById(R.id.add_radiobt);
        message_radiobt =(RadioButton)findViewById(R.id.message_radiobt);
        mine_radiobt =(RadioButton)findViewById(R.id.mine_radiobt);
        findViewById(R.id.im_add).setOnClickListener(this);

        answered_radiobt.setOnClickListener(this);
        find_radiobt.setOnClickListener(this);
        add_radiobt.setOnClickListener(this);
        message_radiobt.setOnClickListener(this);
        mine_radiobt.setOnClickListener(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(AddEventBusBean addEventBusBean) {
        Log.d(TAG, "Event: ");
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        initHide(transaction);
        if (oneFragment == null){
            oneFragment = new OneFragment();
            transaction.add(R.id.show_fm, oneFragment);
        }else {
            transaction.show(oneFragment);
        }
        transaction.commit();
    }
    protected void initdata() {
        //getnetwoke();
        oneFragment = new OneFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.show_fm, oneFragment);
        transaction.show(oneFragment);
        transaction.commit();
    }

    //判断网络状态
    private void getnetwoke() {
        if (netwoke == null) {
            netwoke = new NetwokeUtils();
        }
        String getnetwoke = netwoke.getnetwoke(MainActivity.this);
        Toast.makeText(MainActivity.this, getnetwoke, Toast.LENGTH_SHORT).show();
        if (getnetwoke.equals("网络无连接")) {
            setNetwork();
        }
    }

    private void setNetwork() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("无法连接网络");
        builder.setMessage("网络不可用，如果继续，请先设置网络！");
        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {
                Intent intent = null;
                if (Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName(
                            "com.android.settings", "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                startActivity(intent);
            }

        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }

        });
        builder.create();
        builder.show();
    }


    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        initHide(transaction);
        switch (v.getId()){
            case R.id.answered_radiobt:
                if (oneFragment == null){
                    oneFragment = new OneFragment();
                    transaction.add(R.id.show_fm, oneFragment);
                }else {
                    transaction.show(oneFragment);
                }
                transaction.commit();
                break;
            case R.id.find_radiobt:
                if (findFragment == null){
                    findFragment = new FindFragment();
                    transaction.add(R.id.show_fm, findFragment);
                }else {
                    transaction.show(findFragment);
                }
                transaction.commit();
                break;
            case R.id.im_add:
               Intent intent=new Intent(this, ApplyQActivity.class);
               startActivity(intent);
                break;
            case R.id.message_radiobt:
                if (messageFragment == null){
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.show_fm, messageFragment);
                }else {
                    transaction.show(messageFragment);
                }
                transaction.commit();
                break;
            case R.id.mine_radiobt:
                if (mineFragment == null){
                    mineFragment = new MineFragment();
                    transaction.add(R.id.show_fm,mineFragment);
                }else {
                    transaction.show(mineFragment);
                }
                transaction.commit();
                break;
        }

    }

    private void initHide(FragmentTransaction transaction) {
        if (oneFragment != null){
            transaction.hide(oneFragment);
        }
        if (findFragment != null){
            transaction.hide(findFragment);
        }
        if (messageFragment != null){
            transaction.hide(messageFragment);
        }
        if (mineFragment != null){
            transaction.hide(mineFragment);
        }
    }


    @Override
    public void showUpdateAlert(final String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
            progressDialog.setCancelable(false);
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
                    presenter.startDown(url, FileUtils.getAppFile(MainActivity.this, Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
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
