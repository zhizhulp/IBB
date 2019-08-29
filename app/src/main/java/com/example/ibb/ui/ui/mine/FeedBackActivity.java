package com.example.ibb.ui.ui.mine;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ibb.R;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.entity.FeedBackBean;
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

public class FeedBackActivity extends BaseActivity implements View.OnClickListener {

    private ImageView feedback_back_iv;
    private Button submit_bt;
    private EditText commit_feedback_et;
    private EditText contact_et;

    @Override
    protected void initview() {
        //返回
        feedback_back_iv = (ImageView)findViewById(R.id.feedback_back_iv);
        feedback_back_iv.setOnClickListener(this);
        //提交反馈
        submit_bt = (Button)findViewById(R.id.submit_bt);
        submit_bt.setOnClickListener(this);
        //请输入您的反馈意见
        commit_feedback_et = (EditText)findViewById(R.id.commit_feedback_et);
        commit_feedback_et.setOnClickListener(this);
        //联系方式
        contact_et = (EditText)findViewById(R.id.contact_et);
        contact_et.setOnClickListener(this);
    }

    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_feed_back;
    }

    @Override
    public void onClick(View v) {
        String feedback = commit_feedback_et.getText().toString().trim();
        String contact = contact_et.getText().toString().trim();
        switch (v.getId()){
            //返回
            case R.id.feedback_back_iv:
                finish();
                break;
            //提交反馈按钮
            case R.id.submit_bt:
                if (IStringUtils.isEmpty(feedback)){
                    Toast.makeText(this, "反馈意见不能为空哦~", Toast.LENGTH_SHORT).show();

                }else {
                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request()
                                    .newBuilder()
                                    .addHeader("token",getSharedPreferences("user", Context.MODE_PRIVATE).getString("token", ""))
                                    .build();
                            return chain.proceed(request);
                        }
                    }).build();
                    FormBody body = new FormBody.Builder()
                            .add("feedback",feedback)
                            .add("contact",contact)
                            .build();
                    Request request = new Request.Builder().url(URLApi.feedbackstring).post(body).build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String string = response.body().string();
                            Log.i("TAG","提交反馈--"+string);
                            Gson gson = new Gson();
                            FeedBackBean feedBackBean = gson.fromJson(string, FeedBackBean.class);
                            if (feedBackBean.getMeta().isSuccess()){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ToastUtils.show("您的反馈已经提交给平台。");
                                        finish();
                                    }
                                });
                            }
                        }
                    });
                }

                break;

        }
    }
}
