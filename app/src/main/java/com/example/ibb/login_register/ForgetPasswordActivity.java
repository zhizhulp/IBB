package com.example.ibb.login_register;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.ibb.R;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.entity.ResetCodeBean;
import com.example.ibb.gyroutils.ParallelViewHelper;
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

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener {

    //    发送验证码   重新获取验证码状态   false为正常   true为当前倒计时
    private boolean SEND_CODE_FLG = false;
    private int i = 60;
    private Handler handler=new Handler();
    private ParallelViewHelper parallelViewHelper;
    private ImageView forget_back_iv;
    private EditText forget_phone_et;
    private EditText forgrt_code_et;
    private Button forget_huoqucode_button;
    private Button forget_next_rb;
    private String forgetCode;
    private String forgetPhone;
    List<ResetCodeBean.DataBean> dataList=new ArrayList<>();
    List<ResetCodeBean.MetaBean> metaList=new ArrayList<>();

    @Override
    protected void initview() {

        forget_back_iv =(ImageView)findViewById(R.id.forget_back_iv);
        forget_phone_et =(EditText)findViewById(R.id.forget_phone_et);
        forgrt_code_et =(EditText)findViewById(R.id.forgrt_code_et);
        forget_huoqucode_button =(Button)findViewById(R.id.forget_huoqucode_button);
        forget_next_rb =(Button)findViewById(R.id.forget_next_rb);

        forget_huoqucode_button.setOnClickListener(this);
        forget_next_rb.setOnClickListener(this);
        forget_back_iv.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_forget_password;
    }

    @Override
    public void onClick(View v) {

        forgetPhone = forget_phone_et.getText().toString().trim().replace("", "");
        forgetCode = forgrt_code_et.getText().toString().trim();

        switch (v.getId()){
            case R.id.forget_next_rb:
                if (IStringUtils.isEmpty(forgetCode) ){
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                } else if (forgetCode.length() != 4){
                    //输入到达上限时，输入框抖动提示用户
                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
                    findViewById(R.id.register_shoujihao_et).startAnimation(animation);
                    Toast.makeText(this, "验证码错误，请重新输入", Toast.LENGTH_SHORT).show();
                }else {
                    //判断验证码的请求接口
                    examineCode();
                }
                break;
            case R.id.forget_back_iv:
                finish();
                break;

            case R.id.forget_huoqucode_button:
                //判断手机号
                if (IStringUtils.isEmpty(forgetPhone)){
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                }
                else if (forgetPhone.length() > 11){
                    //输入到达上限时，输入框抖动提示用户
                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
                    findViewById(R.id.register_shoujihao_et).startAnimation(animation);

                    Toast.makeText(this, "输入已达上限", Toast.LENGTH_SHORT).show();
                } else if (NumberUtils.checkCellphone(forgetPhone) == false){
                    Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                }else {
                    OkHttpClient client = new OkHttpClient();
                    FormBody body = new FormBody.Builder()
                            .add("phoneNumber",forgetPhone)
                            .build();
                    Request request = new Request.Builder().url(URLApi.resetCodestring).post(body).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e("error", e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            final String string = response.body().string();
                            Log.i("TAG","!!!!!!"+string);
                            Gson gson = new Gson();
                            ResetCodeBean resetCodeBean = gson.fromJson(string, ResetCodeBean.class);
                            dataList.add(resetCodeBean.getData());
                            metaList.add(resetCodeBean.getMeta());
                            start_CountDown();
                        }
                    });

                }
                break;
        }
    }

    /**
     * 开始倒计时
     */
    public  void  start_CountDown(){
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
                        forget_huoqucode_button.setText(i+"秒后重试");//显示剩余时间
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
                    forget_huoqucode_button.setText("重新获取");//一轮倒计时结束  修改剩余时间为一分钟
                    forget_huoqucode_button.setEnabled(true);

                    SEND_CODE_FLG = false;


                }
            });
            i = 60;//修改倒计时剩余时间变量为60秒
        }
    }

    /**
     * 判断验证码是否正确
     */
    private void examineCode(){
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("phoneNumber",forgetPhone)
                .add("code",forgetCode)
                .build();
        Request build = new Request.Builder().url(URLApi.resetTestCodestring).post(body).build();
        client.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyApp.activity,"请求失败"+e.getMessage() ,Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("TAG","验证码是否正确--->"+ string);
                        Log.i("TAG","----------------------------------------------");

                        Gson gson=new Gson();
                        ResetCodeBean resetCodeBean = gson.fromJson(string, ResetCodeBean.class);
                        if(resetCodeBean.getMeta().isSuccess()){
                            Toast.makeText(MyApp.activity,"输入验证码正确"+ string,Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(MyApp.activity,NewPasswordActivity.class);
                            intent.putExtra("forgetphone_number", forgetPhone);
                            startActivity(intent);
//                            register_mima_et.setKeyListener(MyApp.activity);//可以输入密码

                        }else{
//                            register_mima_et.setKeyListener(null);//禁止输入
//                                        密码设置失败，提示服务器提示内容
                            Toast.makeText(MyApp.activity,resetCodeBean.getMeta().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
