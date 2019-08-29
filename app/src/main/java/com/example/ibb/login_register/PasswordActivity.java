package com.example.ibb.login_register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ibb.MainActivity;
import com.example.ibb.R;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.entity.PasswordBean;
import com.example.ibb.gyroutils.ParallelViewHelper;
import com.example.ibb.utils.IStringUtils;
import com.example.ibb.utils.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 设置密码页
 */
public class PasswordActivity extends BaseActivity implements View.OnClickListener {

    private ImageView password_imageView;
    private ParallelViewHelper parallelViewHelper;
    private ImageView password_back_iv;
    private EditText password_setting_et;
    private Button password_enter_rt;
    private String password;
    private String phone_number;

    @Override
    protected void initview() {

        password_back_iv =(ImageView)findViewById(R.id.password_back_iv);
        password_setting_et =(EditText)findViewById(R.id.password_setting_et);
        password_enter_rt =(Button)findViewById(R.id.password_enter_rt);

        password_back_iv.setOnClickListener(this);
        password_setting_et.setOnClickListener(this);
        password_enter_rt.setOnClickListener(this);
    }
    @Override
    protected void initdata() {

        Intent intent = getIntent();
        phone_number = intent.getStringExtra("phone_number");

        password = password_setting_et.getText().toString().trim();

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_password;
    }

    @Override
    public void onClick(View v) {
        password = password_setting_et.getText().toString().trim();

        switch (v.getId()){
            case R.id.password_back_iv:
                finish();
                break;

            case R.id.password_setting_et:

                break;

            case R.id.password_enter_rt:

                if (!password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,14}$")){
                    Toast toast = Toast.makeText(MyApp.activity,"密码格式不正确，密码须有6-14位数字、字母组成，请重新输入！",Toast.LENGTH_LONG);
                    //设置toast居中显示
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else {
                    //设置密码
                    setPassword();
                }
                break;
        }
    }

    /**
     * 设置密码
     */
    private void setPassword(){

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("token", getIntent().getStringExtra("token"))
                        .build();
                return chain.proceed(request);
            }
        }).build();
        FormBody body = new FormBody.Builder()
                .add("phoneNumber",phone_number)
                .add("password",password)
                .build();
        Request request = new Request.Builder().url(URLApi.mimastring).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PasswordActivity.this,"请求失败"+e.getMessage() ,Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();
                Log.i("TAG",">__________"+string);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Gson gson = new Gson();
                        PasswordBean passwordBean = gson.fromJson(string, PasswordBean.class);

                        if (passwordBean.getMeta().isSuccess()){
                            //SP存储
                            SharedPreferences sp = getSharedPreferences("newsp_ibb", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("sp_PhoneNumer",phone_number);
                            editor.apply();
                            //Intent跳转
                            Intent intent = new Intent(MyApp.activity, MainActivity.class);
                            startActivity(intent);
                            ToastUtils.show("密码设置成功");
                        }else {
                            Toast.makeText(PasswordActivity.this,passwordBean.getMeta().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
