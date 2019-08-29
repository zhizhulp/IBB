package com.example.ibb.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.AccountNumberBean;
import com.example.ibb.entity.CodeBean;
import com.example.ibb.entity.ThirdLoginBean;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.mine.UserAgreementActivity;
import com.example.ibb.utils.AppConfig;
import com.example.ibb.utils.FinalUtils;
import com.example.ibb.utils.IStringUtils;
import com.example.ibb.utils.NumberUtils;
import com.example.ibb.utils.ToastUtils;
import com.google.gson.Gson;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StartRegisterActivity extends BaseNetActivity implements View.OnClickListener, UMAuthListener {

    //    发送验证码   重新获取验证码状态   false为正常   true为当前倒计时
    private boolean SEND_CODE_FLG = false;
    private int i = 60;
    private Handler handler = new Handler();
    List<CodeBean.DataBean> dataList = new ArrayList<>();
    List<CodeBean.MetaBean> metaList = new ArrayList<>();
    private EditText register_shoujihao_et;
    private Button register_yzm_button;
    private String phone;
    private ImageView register_passwordlogin_iv;
    private ImageView register_qq_iv;
    private EditText register_code_et;
    private Button register_login_rb;
    private String code;
    private TextView userAgreement_tv;

    protected void initview() {

        register_shoujihao_et = (EditText) findViewById(R.id.register_shoujihao_et);
        register_yzm_button = (Button) findViewById(R.id.register_huoquyzm_button);
        register_passwordlogin_iv = (ImageView) findViewById(R.id.register_passwordlogin_iv);
        register_qq_iv = (ImageView) findViewById(R.id.register_qq_iv);
        register_code_et = (EditText) findViewById(R.id.register_code_et);
        register_login_rb = (Button) findViewById(R.id.register_login_rb);

        findViewById(R.id.weixin_reg).setOnClickListener(this);
        findViewById(R.id.weibo_reg).setOnClickListener(this);
        register_yzm_button.setOnClickListener(this);
        register_passwordlogin_iv.setOnClickListener(this);
        register_qq_iv.setOnClickListener(this);

        register_login_rb.setOnClickListener(this);

        //用户注册协议
        userAgreement_tv = (TextView) findViewById(R.id.userAgreement_tv);
        userAgreement_tv.setOnClickListener(this);

        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strat__register);
        initview();
    }


    @Override
    public void onClick(View v) {

        phone = register_shoujihao_et.getText().toString().trim().replace("", "");
        code = register_code_et.getText().toString().trim();

        switch (v.getId()) {
            case R.id.register_login_rb://验证码登陆
                if (IStringUtils.isEmpty(code)) {
                    Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                } else if (code.length() != 4) {
                    //输入到达上限时，输入框抖动提示用户
                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
                    findViewById(R.id.register_shoujihao_et).startAnimation(animation);
                    Toast.makeText(this, "验证码错误，请重新输入", Toast.LENGTH_SHORT).show();
                } else {
                    //判断验证码的请求接口
                    examineCode();
                }
                break;

            case R.id.register_huoquyzm_button:
                //判断手机号
                if (IStringUtils.isEmpty(phone)) {
                    Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                } else if (phone.length() > 11) {
                    //输入到达上限时，输入框抖动提示用户
                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
                    findViewById(R.id.register_shoujihao_et).startAnimation(animation);

                    Toast.makeText(this, "输入已达上限", Toast.LENGTH_SHORT).show();
                } else if (NumberUtils.checkCellphone(phone) == false) {
                    Toast.makeText(this, "请输入正确手机号", Toast.LENGTH_SHORT).show();
                } else {
                    if (SEND_CODE_FLG) {
                        Log.e("TAG", "----->" + "六十秒倒计时还没到，不做任何操作");
                    } else {
                        OkHttpClient client = new OkHttpClient();
                        FormBody body = new FormBody.Builder().add("phoneNumber", phone).build();
                        Request build = new Request.Builder().url(URLApi.codestring).post(body).build();
                        client.newCall(build).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.e("error", e.getMessage());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                final String string = response.body().string();
                                Log.i("TAG", "----" + string);
                                Gson gson = new Gson();
                                CodeBean codeBean = gson.fromJson(string, CodeBean.class);
                                dataList.add(codeBean.getData());
                                metaList.add(codeBean.getMeta());
                                start_CountDown();
                            }
                        });
                    }
                }
                break;

            case R.id.register_passwordlogin_iv://账号密码登陆
                startActivityForResult(new Intent(StartRegisterActivity.this, PasswordLoginActivity.class), FinalUtils.REQUEST_PP_LOGIN);
                break;

            case R.id.register_qq_iv://qq登陆
                if (!UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.QQ)) {
                    ToastUtils.show("您还没有安装QQ");
                    return;
                }
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.QQ, this);
                break;
            case R.id.weixin_reg://微信登陆
                if (!UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.WEIXIN)) {
                    ToastUtils.show("您还没有安装微信");
                    return;
                }
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.WEIXIN, this);
                break;
            case R.id.weibo_reg://微博登陆
                if (!UMShareAPI.get(this).isInstall(this, SHARE_MEDIA.SINA)) {
                    ToastUtils.show("您还没有安装微博");
                    return;
                }
                UMShareAPI.get(this).getPlatformInfo(this, SHARE_MEDIA.SINA, this);
                break;
            case R.id.userAgreement_tv://用户协议
                startActivity(new Intent(MyApp.activity, UserAgreementActivity.class));
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK && requestCode == FinalUtils.REQUEST_PP_LOGIN) {
            finish();
        }
    }

    public void start_CountDown() {
        new Thread(new ClassCut()).start();//开启倒计时
    }

    private class ClassCut implements Runnable {
        //倒计时逻辑子线程
        @Override
        public void run() {


            SEND_CODE_FLG = true;

            while (i > 0) {//整个倒计时执行的循环
                i--;
                handler.post(new Runnable() {
                    @Override
                    public void run() {//通过它在UI主线程中修改显示的剩余时间
                        register_yzm_button.setText(i + "秒后重试");//显示剩余时间
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
                    register_yzm_button.setText("重新获取");//一轮倒计时结束  修改剩余时间为一分钟
                    register_yzm_button.setEnabled(true);
                    SEND_CODE_FLG = false;


                }
            });
            i = 60;//修改倒计时剩余时间变量为60秒
        }
    }

    //验证码登陆
    private void examineCode() {
        OkHttpClient client = new OkHttpClient.Builder().build();

        FormBody body = new FormBody.Builder()
                .add("phoneNumber", phone)
                .add("code", code)
                .build();
        Request build = new Request.Builder().url(URLApi.testCodestring).post(body).build();
        client.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MyApp.activity, "请求失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("TAG", "验证码是否正确--->" + string);
                        Log.i("TAG", "----------------------------------------------");

                        Gson gson = new Gson();
                        AccountNumberBean accountNumberBean = gson.fromJson(string, AccountNumberBean.class);
                        if (accountNumberBean.getMeta().isSuccess()) {
                            ToastUtils.show("验证码输入正确");
                            AppConfig.getInstance().putString("token", accountNumberBean.getData().getToken());
                            AppConfig.getInstance().putUser(accountNumberBean.getData().getUser());
                            Log.i("TGA", "---" + string);
                            /*Intent intent = new Intent(MyApp.activity, PasswordActivity.class);
                            intent.putExtra("phone_number", phone);
                            intent.putExtra("token", accountNumberBean.getData().getToken());
                            startActivity(intent);*/
                            Intent intent=new Intent(StartRegisterActivity.this,MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MyApp.activity, accountNumberBean.getMeta().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    //第三方登陆
    @Override
    public void onStart(SHARE_MEDIA share_media) {

    }

    @Override
    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
        String uid = map.get("uid");
        String name = map.get("name");
        String gender = map.get("gender");
        String iconurl = map.get("iconurl");
        requestThirdPartyLogin(uid, name, gender, iconurl, share_media);
    }

    @Override
    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
        ToastUtils.show("登陆出了点问题");
    }

    @Override
    public void onCancel(SHARE_MEDIA share_media, int i) {
        ToastUtils.show("登陆已取消");
    }

    private void requestThirdPartyLogin(String uid, String name, String gender, String iconurl, SHARE_MEDIA share_media) {
        com.yanzhenjie.nohttp.rest.Request request = buildRequest(URLApi.openidLogin, ThirdLoginBean.class);
        request.add("openid", uid);
        request.add("name", name);
        switch (share_media) {
            case QQ:
                request.add("status", "qq");
                break;
            case SINA:
                request.add("status", "weibo");
                break;
            case WEIXIN:
                request.add("status", "wechat");
                break;
        }
        request.add("wuserSrc", iconurl);
        request.add("sex", "男".equals(gender) ? 1 : 0);
        doRequest(0, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what == 0) {
            ThirdLoginBean thirdLoginBean = (ThirdLoginBean) data.getData();
            AppConfig.getInstance().putString("token", thirdLoginBean.getToken());
            AppConfig.getInstance().putUser(thirdLoginBean.getUserinfo());
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
