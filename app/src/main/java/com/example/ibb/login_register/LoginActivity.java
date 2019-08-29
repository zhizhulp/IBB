package com.example.ibb.login_register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ibb.MainActivity;
import com.example.ibb.R;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.custom_view.ImageView_circle;
import com.example.ibb.entity.AccountNumberBean;
import com.example.ibb.entity.CodeBean;
import com.example.ibb.gyroutils.ParallelViewHelper;
import com.example.ibb.okhttp.OkHttpUtils;
import com.example.ibb.utils.IStringUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    //    发送验证码   重新获取验证码状态   false为正常   true为当前倒计时
    private boolean SEND_CODE_FLG = false;

    private ImageView login_userpicture_iv;
    private TextView login_username_tv;
    private String name;
    private String portrait;
    private Button login_againyzm_button;
    private String phone_number;
    private ImageView login_back_iv;
    private Button login_queren_button;

    List<CodeBean.DataBean> dataList=new ArrayList<>();
    List<CodeBean.MetaBean> metaList=new ArrayList<>();

    /**
     * 倒计时
     */
    private int i = 60;
    private Handler handler=new Handler();
    private EditText login_yzm_et;
    private ParallelViewHelper parallelViewHelper;

    @Override
    protected void initview() {

        login_back_iv =(ImageView)findViewById(R.id.login_back_iv);
        login_userpicture_iv =(ImageView)findViewById(R.id.login_userpicture_iv);
        login_username_tv =(TextView)findViewById(R.id.login_username_tv);
        login_yzm_et =(EditText)findViewById(R.id.login_yzm_et);
        login_againyzm_button =(Button)findViewById(R.id.login_againyzm_button);
        Glide.with(this).load(R.mipmap.b3).transform(new ImageView_circle(this)).into(login_userpicture_iv);
        login_queren_button =(Button)findViewById(R.id.login_queren_button);
        parallelViewHelper = new ParallelViewHelper(this, findViewById(R.id.login_imageView));

        login_back_iv.setOnClickListener(this);
        login_userpicture_iv.setOnClickListener(this);
        login_username_tv.setOnClickListener(this);
        login_againyzm_button.setOnClickListener(this);
        login_queren_button.setOnClickListener(this);

        //        开启倒计时
        start_CountDown();
    }


    @Override
    public void onResume() {
        super.onResume();
        parallelViewHelper.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        parallelViewHelper.stop();
    }
    @Override
    protected void initdata() {

        Intent intent=getIntent();
        phone_number = intent.getStringExtra("phone_number");


        SharedPreferences sharedPreferences=getSharedPreferences("sp_ibb", Context.MODE_PRIVATE);
        String nickname = sharedPreferences.getString("nickname", null);
        String portrait = sharedPreferences.getString("portrait", null);
        login_username_tv.setText(nickname);


    }

    @Override
    protected int initlayout() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        String login_code = login_yzm_et.getText().toString().trim();

        switch (v.getId()){
            case R.id.login_back_iv:
                finish();
                break;
            case R.id.login_userpicture_iv:

                break;
            case R.id.login_username_tv:

                break;

            case R.id.login_againyzm_button:

                if(SEND_CODE_FLG){

                    Log.e("TAG","----->"+"六十秒倒计时还没到，不做任何操作");
                }else {

                    OkHttpUtils.getInstance().get(URLApi.codestring + phone_number, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("error", e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            final String string = response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Gson gson = new Gson();
                                    CodeBean codeBean = gson.fromJson(string, CodeBean.class);
                                    dataList.add(codeBean.getData());
                                    metaList.add(codeBean.getMeta());

                                    if(codeBean.getMeta().isSuccess()){
                                        Toast.makeText(LoginActivity.this,"验证码重新发送成功",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(LoginActivity.this,codeBean.getMeta().getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                    start_CountDown();
                                }
                            });
                        }
                    });
                }

                break;

            case R.id.login_queren_button:

                if (IStringUtils.isEmpty(login_code)){
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                }else if (login_code.length() != 4){
                    Toast.makeText(this, "验证码错误，请重新输入", Toast.LENGTH_SHORT).show();
                }else {
                    OkHttpClient okHttpClient=new OkHttpClient();

                    RequestBody body = new FormBody.Builder().build();

                    Request request=new Request.Builder().url(URLApi.codestring + phone_number+"/"+login_code).post(body).build();
                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, final IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this,"请求失败"+e.getMessage() ,Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            final String string = response.body().string();

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Log.i("TAG","验证码是否正确--->"+string);
                                    Log.i("TAG","----------------------------------------------");

                                    Gson gson=new Gson();
                                    AccountNumberBean accountNumberBean = gson.fromJson(string, AccountNumberBean.class);
                                    if(accountNumberBean.getMeta().isSuccess()){
                                        SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor edit = user.edit();
                                        edit.putBoolean("loginzhuangtai",accountNumberBean.getMeta().isSuccess());
                                        edit.putString("token",accountNumberBean.getData().getToken());
                                        edit.putLong("id",accountNumberBean.getData().getUser().getId());
                                        edit.commit();
                                        Toast.makeText(LoginActivity.this,"验证码正确----登录成功",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    }else{
                                        Toast.makeText(LoginActivity.this,accountNumberBean.getMeta().getMessage(),Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }
                    });
                }
                break;
      }
                }

                /**
                 * 倒计时
                 */

     private void start_CountDown() {

         new Thread(new ClassCut()).start();//开启倒计时

        }

        private class ClassCut implements Runnable {
            //倒计时逻辑子线程
            @Override
            public void run() {


                SEND_CODE_FLG = true;

                while (i>0){//整个倒计时执行的循环
                    i--;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {//通过它在UI主线程中修改显示的剩余时间
                            login_againyzm_button.setText(i+"秒后重试");//显示剩余时间
                        }
                    });

                    try {
                        Thread.sleep(1000);//线程休眠一秒钟     这个就是倒计时的间隔时间
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //下面是倒计时结束逻辑
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        login_againyzm_button.setText("重新获取");//一轮倒计时结束  修改剩余时间为一分钟
                        login_againyzm_button.setEnabled(true);

                        SEND_CODE_FLG = false;


                    }
                });
                i = 60;//修改倒计时剩余时间变量为60秒
            }
        }
}

