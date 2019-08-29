package com.example.ibb.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.ibb.R;
import com.yanzhenjie.nohttp.tools.NetUtils;

public class WebViewBaseActivity extends AppCompatActivity {
    private String url;
    private WebView webView;
    private String name;

    public static void start(Context context, String name, String url) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(url)) {
            Log.e("WebViewBaseActivity", "start: name为空或者url为空");
            return;
        }
        Intent intent = new Intent(context, WebViewBaseActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_base);
        getMsgFromBefore();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void getMsgFromBefore() {
        Intent intent = getIntent();
        if (intent != null) {
            boolean netAva = NetUtils.isNetworkAvailable();
            if (!netAva) {
                return;
            }
            url = intent.getStringExtra("url");
            name = intent.getStringExtra("name");
            webView = ((WebView) findViewById(R.id.webView));
            webView.getSettings().setJavaScriptEnabled(true);

            findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            ((TextView) findViewById(R.id.tv_title)).setText(name);
            if (url != null) {
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                    }

                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);

                    }
                });
                webView.loadUrl(url);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (webView.canGoBack()) {
            webView.goBack();  //返回上一页
        }
    }

    /**
     * 拦截实体键(MENU  BACK  POWER  VOLUME  HOME)的点击事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {  //按下实体的返回键
            if (webView.canGoBack()) {  //说明WebView有上一页
                webView.goBack();  //WebView返回上一页
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
