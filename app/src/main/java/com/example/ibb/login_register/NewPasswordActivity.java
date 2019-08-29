package com.example.ibb.login_register;

import android.content.Intent;
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
import com.example.ibb.entity.NewPasswordBean;
import com.example.ibb.gyroutils.ParallelViewHelper;
import com.example.ibb.utils.IStringUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewPasswordActivity extends BaseActivity implements View.OnClickListener {

    private ImageView newpassword_back_iv;
    private EditText newpassword_setting_et;
    private Button newpassword_enter_rt;
    private String newpassword;
    private String newphone_number;

    @Override
    protected void initview() {

        newpassword_back_iv =(ImageView)findViewById(R.id.newpassword_back_iv);
        newpassword_setting_et =(EditText)findViewById(R.id.newpassword_setting_et);
        newpassword_enter_rt =(Button)findViewById(R.id.newpassword_enter_rt);

        newpassword_back_iv.setOnClickListener(this);
        newpassword_setting_et.setOnClickListener(this);
        newpassword_enter_rt.setOnClickListener(this);
    }


    @Override
    protected void initdata() {

        Intent intent = getIntent();
        newphone_number = intent.getStringExtra("forgetphone_number");

        newpassword = newpassword_setting_et.getText().toString().trim();
    }

    @Override
    protected int initlayout() {
        return R.layout.activity_new_password;
    }

    @Override
    public void onClick(View v) {
        newpassword = newpassword_setting_et.getText().toString().trim();
        switch (v.getId()){
            case R.id.newpassword_back_iv:
                finish();
                break;

            case R.id.newpassword_enter_rt:

                if (!newpassword.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,14}$")){
                    Toast toast = Toast.makeText(MyApp.activity,"密码格式不正确，密码须有6-14位数字、字母组成，请重新输入！",Toast.LENGTH_LONG);
                    //设置toast居中显示
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }else {
                    setPassword();
                }
                break;

        }
    }
    /**
     * 设置密码
     */
    private void setPassword(){

        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("phoneNumber",newphone_number)
                .add("password",newpassword)
                .build();
        Request request = new Request.Builder().url(URLApi.resetPasswordstring).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(NewPasswordActivity.this,"请求失败"+e.getMessage() ,Toast.LENGTH_SHORT).show();
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
                        NewPasswordBean newpasswordBean = gson.fromJson(string, NewPasswordBean.class);

                        if (newpasswordBean.getMeta().isSuccess()){

                            Intent intent = new Intent(MyApp.activity, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(NewPasswordActivity.this,"输入新密码正确---成功"+ string,Toast.LENGTH_SHORT).show();

//                            startActivity(new Intent(CodeActivity.this, UserPictureActivity.class));
                        }else {
                            Toast.makeText(NewPasswordActivity.this,newpasswordBean.getMeta().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
