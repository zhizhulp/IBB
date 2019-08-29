package com.example.ibb.login_register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibb.MainActivity;
import com.example.ibb.R;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.entity.AccountNumberBean;
import com.example.ibb.gyroutils.ParallelViewHelper;
import com.example.ibb.ui.ui.mine.MineFragment;
import com.example.ibb.ui.ui.mine.UserAgreementActivity;
import com.example.ibb.utils.AppConfig;
import com.example.ibb.utils.IStringUtils;
import com.example.ibb.utils.NumberUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PasswordLoginActivity extends BaseActivity implements View.OnClickListener {


    private ParallelViewHelper parallelViewHelper;
    private ImageView passwordlogin_userpicture_iv;
    private ImageView passwordlogin_back_iv;
    private TextView passwordlogin_forgrt_tv;
    private ImageView password_code_iv;
    private EditText passwordlogin_phone_et;
    private EditText passwordlogin_mima_et;
    private String phoneNumber;
    private String enterpassword;
    private String password;
    private String phone;
    private String loginphoneNumber;
    private Button passwordlogin_login_rt;
    private TextView passwordlogin_xytv;

    @Override
    protected void initview() {

        passwordlogin_back_iv =(ImageView)findViewById(R.id.passwordlogin_back_iv);
        passwordlogin_forgrt_tv =(TextView)findViewById(R.id.passwordlogin_forgrt_tv);
        password_code_iv =(ImageView)findViewById(R.id.password_code_iv);
        passwordlogin_phone_et =(EditText)findViewById(R.id.passwordlogin_phone_et);
        passwordlogin_mima_et =(EditText)findViewById(R.id.passwordlogin_mima_et);
        passwordlogin_login_rt =(Button) findViewById(R.id.passwordlogin_login_rt);

        passwordlogin_phone_et.setOnClickListener(this);
        passwordlogin_mima_et.setOnClickListener(this);
        passwordlogin_back_iv.setOnClickListener(this);
        passwordlogin_forgrt_tv.setOnClickListener(this);
        password_code_iv.setOnClickListener(this);
        passwordlogin_login_rt.setOnClickListener(this);

        //用户协议条款
        passwordlogin_xytv = (TextView)findViewById(R.id.passwordlogin_xytv);
        passwordlogin_xytv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {
        //SP存储 取
        SharedPreferences sp = getSharedPreferences("newsp_ibb", Context.MODE_PRIVATE);
        String sp_phoneNumer = sp.getString("sp_PhoneNumer", null);
        passwordlogin_phone_et.setText(sp_phoneNumer);

        Intent intent = getIntent();
        password = intent.getStringExtra("password");
    }

    @Override
    protected int initlayout() {
        return R.layout.activity_password_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //用户协议条款
            case R.id.passwordlogin_xytv:
                startActivity(new Intent(MyApp.activity,UserAgreementActivity.class));
                break;
            //返回
            case R.id.passwordlogin_back_iv:
                finish();
                break;

            case R.id.passwordlogin_forgrt_tv:
                startActivity(new Intent(MyApp.activity,ForgetPasswordActivity.class));
                break;

            case R.id.password_code_iv:
                finish();
                break;

            case R.id.passwordlogin_login_rt:
                loginphoneNumber = passwordlogin_phone_et.getText().toString().trim().replace("","");
                enterpassword = passwordlogin_mima_et.getText().toString().trim();
                //判断手机号
                if (IStringUtils.isEmpty(loginphoneNumber)){
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }
                else if (loginphoneNumber.length() > 11){
                    //输入到达上限时，输入框抖动提示用户
                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
                    findViewById(R.id.register_shoujihao_et).startAnimation(animation);

                    Toast.makeText(this, "输入已达上限", Toast.LENGTH_SHORT).show();
                } else if (NumberUtils.checkCellphone(loginphoneNumber) == false){
                    Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                }else if (IStringUtils.isEmpty(enterpassword)){
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (!enterpassword.matches("[0-9A-Za-z]{6,14}")){
                    Toast toast = Toast.makeText(MyApp.activity,"密码格式不正确，密码须有6-14位数字、字母组成，请重新输入！",Toast.LENGTH_LONG);
                    //设置toast居中显示
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                } else {
                    OkHttpClient client = new OkHttpClient.Builder()
                            .build();
                    FormBody body = new FormBody.Builder()
                            .add("username",loginphoneNumber)
                            .add("password", enterpassword)
                            .build();
                    Log.e("LOGIN","username:"+loginphoneNumber+",password:"+ enterpassword);
                    Request request = new Request.Builder().url(URLApi.accountNumberstring).post(body).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("error", e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String string = response.body().string();
                            Log.i("TAG","----"+string);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Gson gson = new Gson();
                                    AccountNumberBean accountNumberBean = gson.fromJson(string, AccountNumberBean.class);
                                    if (accountNumberBean.getMeta().isSuccess()){
                                        AppConfig.getInstance().putString("token",accountNumberBean.getData().getToken());
                                        AppConfig.getInstance().putUser(accountNumberBean.getData().getUser());
                                        //Intent intent = new Intent(PasswordLoginActivity.this,MainActivity.class);
                                        //intent.putExtra("flag",2);
                                        //startActivity(intent);
                                        setResult(RESULT_OK);
                                        PasswordLoginActivity.this.finish();
                                    }else {
                                        Toast.makeText(PasswordLoginActivity.this, accountNumberBean.getMeta().getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }
                    });

                }
                break;
        }

    }
}
