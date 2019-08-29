package com.example.ibb.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ibb.R;
import com.example.ibb.app.MyApp;
import com.example.ibb.custom_view.CustomDialog;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.login_register.StartRegisterActivity;
import com.example.ibb.okhttp.EntityListRequest;
import com.example.ibb.okhttp.EntityRequest;
import com.example.ibb.utils.AppConfig;
import com.example.ibb.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.umeng.commonsdk.UMConfigure;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.StringRequest;

/**
 * Created by liping on 2018/5/11.
 */

public class BaseNetActivity extends AppCompatActivity implements OnResponseListener {
    private RequestQueue mRequestQueue;
    private CustomDialog dialog;
    private Object sign = new Object();
    protected String TAG = this.getClass().getSimpleName();
    protected RecyclerView mRecyclerView;
    protected SmartRefreshLayout mRefreshLayout;
    protected PermissionCallback requestPermissionAndBack;

    public interface PermissionCallback {
        void requestPermissionAndBack(boolean isOk);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApp.activity = this;
        //UMConfigure.setLogEnabled(true);
        //UMConfigure.setEncryptEnabled(true);
    }

    protected <T> Request buildRequest(String url, RequestMethod method, boolean isList, Class<T> clazz) {
        Request request = null;
        if (clazz == null) {
            request = new StringRequest(url, method);
        } else {
            if (isList) {
                request = new EntityListRequest<>(url, method, clazz);
            } else {
                request = new EntityRequest<>(url, method, clazz);
            }

        }
        //request.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
        request.addHeader("token", AppConfig.getInstance().getString("token", null));
        return request;
    }

    protected <T> Request buildRequest(String url, RequestMethod method, Class<T> clazz) {
        return buildRequest(url, method, false, clazz);
    }

    protected <T> Request buildRequest(String url, Class<T> clazz) {
        return buildRequest(url, RequestMethod.POST, false, clazz);
    }

    protected <T> Request buildPostListRequest(String url, Class<T> clazz) {
        return buildRequest(url, RequestMethod.POST, true, clazz);
    }

    protected void doRequest(int what, boolean needWait, Request request, OnResponseListener listener) {
        if (mRequestQueue == null)
            mRequestQueue = NoHttp.newRequestQueue();
        mRequestQueue.add(what, request, listener);
        if (needWait) showDialog();
    }

    protected void doRequest(int what, boolean needWait, Request request) {
        doRequest(what, needWait, request, this);
    }

    protected void doRequest(int what, Request request) {
        doRequest(what, false, request);
    }

    @Override
    public void onStart(int what) {
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onSucceed(int what, Response response) {
        Log.d(TAG, "onSucceed: " + response.get());
        Object o = response.get();
        if (response.isSucceed()) {
            if (o instanceof HttpBaseEntity) {
                if (((HttpBaseEntity) o).getCode() == 401) {
                    handle401(what);
                    return;
                } else if (((HttpBaseEntity) o).getCode() == 404) {
                    handleFailed(what, "something went wrong, please retry later");
                    return;
                } else if (((HttpBaseEntity) o).getCode() == 400) {
                    handleFailed(what, "something went wrong, please retry later");
                    return;
                } else if (((HttpBaseEntity) o).getCode() == 500) {
                    handleFailed(what, "something went wrong, please retry later");
                    return;
                }
                if (((HttpBaseEntity) o).getMeta().isSuccess())
                    handle200True(what, (HttpBaseEntity) o);
                else handle200False(what, (HttpBaseEntity) o);
            }

        } else {
            handleFailed(what, "something went wrong, please retry later");
        }

    }

    @Override
    public void onFailed(int what, Response response) {
        Log.d(TAG, "onFailed: " + response.getException());
        handleException(what, response.getException());
    }

    @Override
    public void onFinish(int what) {
        Log.d(TAG, "onFinish: ");
        hideDialog();
        stopRefreshAndLoadMore();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideDialog();
        if (mRequestQueue != null) {
            mRequestQueue.cancelBySign(sign);
            mRequestQueue.stop();
        }
    }


    //显示等待
    public void showDialog() {
        if (isFinishing()) return;
        if (dialog != null && dialog.isShowing()) hideDialog();
        dialog = new CustomDialog(this, R.style.CustomDialog);
        dialog.setCancelable(true);
        dialog.show();
    }

    //隐藏等待
    public void hideDialog() {
        if (isFinishing()) return;
        if (dialog != null && dialog.isShowing()) dialog.dismiss();
    }

    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {

    }

    protected <T> void handle200False(int what, HttpBaseEntity<T> data) {
        ToastUtils.show(data.getMeta().getMessage());
    }

    protected void handleFailed(int what, String error) {
        ToastUtils.show(error);
    }

    protected void handleException(int what, Exception e) {

    }

    protected void handle401(int what) {
        new AlertDialog.Builder(this)
                .setTitle("提醒")
                .setMessage("您需要登陆才能继续")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("登陆", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(BaseNetActivity.this, StartRegisterActivity.class);
                        startActivity(intent);
                    }
                }).create().show();
    }

    protected void initRefreshLayout() {
        mRefreshLayout = (SmartRefreshLayout) findViewById(R.id.refresh_layout);
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        mRefreshLayout.setEnableLoadmoreWhenContentNotFull(true);
        mRefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }


    protected void stopRefreshAndLoadMore() {
        if (mRefreshLayout != null && mRefreshLayout.isRefreshing()) {
            mRefreshLayout.finishRefresh();
            mRefreshLayout.setLoadmoreFinished(false);
        }
        if (mRefreshLayout != null && mRefreshLayout.isLoading()) {
            mRefreshLayout.finishLoadmore();
        }
    }

    /**
     * 没有更多了
     */
    protected void loadMoreOver() {
        if (mRefreshLayout != null) {
            mRefreshLayout.finishLoadmore();
            mRefreshLayout.setLoadmoreFinished(true);
        }
    }

    /**
     * 申请权限
     */
    public void checkAndRequestAllPermission(String[] permissions, PermissionCallback requestPermissionAndBack) {
        this.requestPermissionAndBack = requestPermissionAndBack;
        if (permissions == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (!checkAllPermissions(permissions)) {//没有所有的权限
                ActivityCompat.requestPermissions(this, permissions, 1);
            } else {
                if (requestPermissionAndBack != null) {
                    requestPermissionAndBack.requestPermissionAndBack(true);//有权限
                }
            }
        } else {
            if (requestPermissionAndBack != null) {
                requestPermissionAndBack.requestPermissionAndBack(true);//有权限
            }
        }
    }

    private boolean checkAllPermissions(@NonNull String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] per,
                                           @NonNull int[] grantResults) {
        if (requestCode == 1) {
            Log.d(TAG, "onRequestPermissionsResult: activity");
            boolean isAll = true;
            for (int i = 0; i < per.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    isAll = false;
                    break;
                }
            }
            if (!isAll) {
                ToastUtils.show("部分功能可能无法使用，因为你拒绝了一些权限");
            }
            if (requestPermissionAndBack != null) {
                requestPermissionAndBack.requestPermissionAndBack(isAll);//isAll 用户是否拥有所有权限
            }
        }
        super.onRequestPermissionsResult(requestCode, per, grantResults);
    }

}
