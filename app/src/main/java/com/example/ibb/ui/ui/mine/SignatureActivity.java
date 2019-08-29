package com.example.ibb.ui.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.ibb.R;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.utils.AppConfig;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SignatureActivity extends BaseActivity implements View.OnClickListener {

    private ImageView signature_back_iv;
    private EditText edit_underwrite;

    String signa = "";

    @Override
    protected void initview() {

        Intent it= getIntent();

        signa = it.getStringExtra("signa");

        //返回键
        signature_back_iv = (ImageView)findViewById(R.id.signature_back_iv);
        signature_back_iv.setOnClickListener(this);
        edit_underwrite = (EditText) findViewById(R.id.edit_underwrite);

        edit_underwrite.setText(null!=signa ? signa:"");
        edit_underwrite.setSelection(null!=signa ?signa.length():0);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_signature;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回键
            case R.id.signature_back_iv:
                SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
                final String token = user.getString("token", null);
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("token", token)
                                .build();
                        return chain.proceed(request);
                    }
                }).build();
                final String nickname = edit_underwrite.getText().toString();
                FormBody body1 = new FormBody.Builder()
                        .add("signature", nickname)
                        .build();
                Request request1 = new Request.Builder().url(URLApi.setSignature).post(body1).build();
                client.newCall(request1).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.i("TGA", "signature------" + response.body().string());

                    }
                });

                Intent it = getIntent();
                it.putExtra("signa",nickname);

                setResult(10003,it);
                AppConfig.getInstance().putString("signature",nickname);
                finish();
                break;
        }
    }
}
