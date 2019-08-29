package com.example.ibb.login_register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibb.R;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.entity.CodeBean;
import com.example.ibb.entity.PasswordBean;
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

public class CodeActivity extends BaseActivity implements View.OnClickListener, TextWatcher, KeyListener {


    private ParallelViewHelper parallelViewHelper;
    private Button register_queren_button;
    private EditText register_yzm_et;
    private Button register_againyzm_button;
    private EditText register_mima_et;
    List<CodeBean.DataBean> dataList=new ArrayList<>();
    List<CodeBean.MetaBean> metaList=new ArrayList<>();

//    发送验证码   重新获取验证码状态   false为正常   true为当前倒计时
    private boolean SEND_CODE_FLG = false;
    private int i = 60;
    private Handler handler=new Handler();
    private ImageView back_iv;
    private TextView password_rules_tv;
    private String phone_number;
    private String code;
    private String mima;

    @Override
    protected void initview() {
        //如果之前使用过SP保存过，无论是否 保存，在这里获取一次用户信息
        //        如果用户昵称和头像都有，说明已经登录过
        SharedPreferences sharedPreferences=getSharedPreferences("sp_ibb", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);
        String nickname = sharedPreferences.getString("nickname", null);
        String portrait = sharedPreferences.getString("portrait", null);
        String phoneNumber = sharedPreferences.getString("phoneNumber", null);
        String registerTime = sharedPreferences.getString("registerTime", null);
        boolean hasPassword = sharedPreferences.getBoolean("hasPassword",true);
        Log.i("TAG","---"+id+"---"+nickname+"-----"+portrait+"-----"+phoneNumber+"-----"+registerTime+"-----"+hasPassword);

        //设置验证码的edittext
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        parallelViewHelper = new ParallelViewHelper(this,findViewById(R.id.code_imageView));
        password_rules_tv =(TextView)findViewById(R.id.password_rules_tv);
        password_rules_tv.setVisibility(View.GONE);

        back_iv =(ImageView)findViewById(R.id.back_iv);
        register_yzm_et =(EditText)findViewById(R.id.register_yzm_et);
        register_againyzm_button =(Button)findViewById(R.id.register_againyzm_button);
        register_queren_button =(Button)findViewById(R.id.register_queren_button);
        register_mima_et =(EditText)findViewById(R.id.register_mima_et);

        Intent intent=getIntent();
        phone_number = intent.getStringExtra("phone_number");
        Toast.makeText(this, phone_number, Toast.LENGTH_SHORT).show();

        back_iv.setOnClickListener(this);
        register_againyzm_button.setOnClickListener(this);
        register_queren_button.setOnClickListener(this);

        submit();
        register_yzm_et.addTextChangedListener(this);//创建EditText的三个方法
//        开启倒计时
        start_CountDown();

    }

    private void submit() {
        if (IStringUtils.isEmpty(code)){
            return;
        }
        if (IStringUtils.isEmpty(mima)){
            return;
        }
    }

    /**
     * EditText按删除键没反应
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        super.dispatchKeyShortcutEvent(event);
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
            //监听到删除按钮被按下
            String text = register_mima_et.getText().toString();
            if (text.length() > 0){//判断文本框是否有文字，如果有就去掉最后一位
                String newstring = text.substring(0, text.length()-1);
                register_mima_et.setText(newstring);
                register_mima_et.setSelection(newstring.length());//设置焦点在最后
            }

        }
        return super.dispatchKeyEvent(event);
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
        register_mima_et.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //弹出密码规则
                password_rules_tv.setVisibility(View.VISIBLE);
                return false;
            }
        });

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_code;
    }

    @Override
    public void onClick(View v) {

        code = register_yzm_et.getText().toString().trim();
        mima = register_mima_et.getText().toString().trim();

        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.register_againyzm_button:
                if(SEND_CODE_FLG){
                    Log.e("TAG","----->"+"六十秒倒计时还没到，不做任何操作");
                }else {
                    OkHttpClient okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder().url(URLApi.codestring + phone_number).build();

                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
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
                                        Toast.makeText(CodeActivity.this,"验证码重新发送成功",Toast.LENGTH_SHORT).show();

                                    }else{
                                        Toast.makeText(CodeActivity.this,codeBean.getMeta().getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                    start_CountDown();
                                }
                            });
                        }
                    });
                }
                break;
            case R.id.register_queren_button:

                if (IStringUtils.isEmpty(code) || IStringUtils.isEmpty(mima)){
                    Toast.makeText(this, "验证码和密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (code.length() != 4){
                    //输入到达上限时，输入框抖动提示用户
                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
                    findViewById(R.id.register_shoujihao_et).startAnimation(animation);
                    Toast.makeText(this, "验证码错误，请重新输入", Toast.LENGTH_SHORT).show();

                }else if (!mima.matches("[0-9A-Za-z]{6,16}")){
                    Toast.makeText(this, "密码格式错误", Toast.LENGTH_SHORT).show();
                }else {

                    setPassword();
//                    examineCode();
                }
                break;
        }
    }

    /**
     * 判断验证码是否正确
     */
    private void examineCode(){
        RequestBody body = new FormBody.Builder().build();
        OkHttpUtils.getInstance().post(URLApi.codestring + phone_number + "/" + code, body, new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CodeActivity.this,"请求失败"+e.getMessage() ,Toast.LENGTH_SHORT).show();
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
                        CodeBean codeBean = gson.fromJson(string, CodeBean.class);
                        if(codeBean.getMeta().isSuccess()){
                            Toast.makeText(CodeActivity.this,"输入验证码正确"+ string,Toast.LENGTH_SHORT).show();

                            register_mima_et.setKeyListener(CodeActivity.this);//可以输入密码

                        }else{
                            register_mima_et.setKeyListener(null);//禁止输入
//                                        密码设置失败，提示服务器提示内容
                            Toast.makeText(CodeActivity.this,codeBean.getMeta().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    /**
     * 设置密码
     */
    private void setPassword(){
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody build=new FormBody.Builder().build();
        String format = String.format(URLApi.mimastring + phone_number + "/" + mima);
        Request build1 = new Request.Builder().url(format).put(build).build();
        okHttpClient.newCall(build1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(CodeActivity.this,"请求失败"+e.getMessage() ,Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(CodeActivity.this,"输入密码正确---注册成功"+ string,Toast.LENGTH_SHORT).show();

//                            startActivity(new Intent(CodeActivity.this, UserPictureActivity.class));
                        }else {
                            Toast.makeText(CodeActivity.this,passwordBean.getMeta().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    /**
     * 开始倒计时
     */
    public  void  start_CountDown(){
        new Thread(new ClassCut()).start();//开启倒计时
    }

    /**
     * EditText的三个方法
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        int length = s.length();
        Log.e("TAG","----验证码动态获取--->"+s.toString());
//        判断验证码输入框每次输入完以后长度是否是4位
        if (length == 4){
            code = register_yzm_et.getText().toString().trim();

            examineCode();

//            如果是四位，进行网络请求，判断当前4位验证码是否正确
        }
    }

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_TEXT;//可以输入
    }

    @Override
    public boolean onKeyDown(View view, Editable text, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean onKeyOther(View view, Editable text, KeyEvent event) {
        return false;
    }

    @Override
    public void clearMetaKeyState(View view, Editable content, int states) {

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
                        register_againyzm_button.setText(i+"秒后重试");//显示剩余时间
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
                    register_againyzm_button.setText("重新获取");//一轮倒计时结束  修改剩余时间为一分钟
                    register_againyzm_button.setEnabled(true);

                    SEND_CODE_FLG = false;


                }
            });
            i = 60;//修改倒计时剩余时间变量为60秒
        }
    }


}
