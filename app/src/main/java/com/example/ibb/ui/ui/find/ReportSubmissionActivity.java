package com.example.ibb.ui.ui.find;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibb.R;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.utils.ToastUtils;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

public class ReportSubmissionActivity extends BaseNetActivity implements View.OnClickListener {


    private ImageView back_iv;
    private TextView tvTitle;
    private EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_submission);
        initview();
    }

    public static void startReport(Context context,String title,int type,long id){
        Intent intent=new Intent(context,ReportSubmissionActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("type",type);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }

    protected void initview() {
        back_iv = (ImageView)findViewById(R.id.back_iv);
        back_iv.setOnClickListener(this);
        findViewById(R.id.btn_apply).setOnClickListener(this);
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText(getIntent().getStringExtra("title"));
        etContent = findViewById(R.id.et_content);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_iv:
                finish();
                break;
            case R.id.btn_apply:
                if (etContent.getText().length()==0){
                    ToastUtils.show("请输入举报的描述");
                }else {
                    if(getIntent().getIntExtra("type",0)==0){
                        requestReportQuestion();
                    }else {
                        requestReportAnswer();
                    }
                }
                break;
        }
    }

    private void requestReportAnswer() {
        Request request = buildRequest(URLApi.answerRe, null);
        request.add("answerId", getIntent().getLongExtra("id",0));
        request.add("reason", getIntent().getStringExtra("title"));
        request.add("reasonDesc", etContent.getText().toString());
        doRequest(0, false,request,  new OnResponseListener() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response response) {

            }

            @Override
            public void onFailed(int what, Response response) {

            }

            @Override
            public void onFinish(int what) {
                ReportSubmissionActivity.this.finish();
                ToastUtils.show("举报成功");
            }
        });
    }

    private void requestReportQuestion() {
        Request request = buildRequest(URLApi.questionRe, null);
        request.add("questionId", getIntent().getLongExtra("id",0));
        request.add("reason", getIntent().getStringExtra("title"));
        request.add("reasonDesc", etContent.getText().toString());
        doRequest(0, false,request,  new OnResponseListener() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response response) {

            }

            @Override
            public void onFailed(int what, Response response) {

            }

            @Override
            public void onFinish(int what) {
                ReportSubmissionActivity.this.finish();
                ToastUtils.show("举报成功");
            }
        });
    }

    private void requestReportPeople() {
        Request request = buildRequest(URLApi.profileReport, null);
        request.add("userId", getIntent().getLongExtra("id",0));
        request.add("reason", getIntent().getStringExtra("title"));
        request.add("reasonDesc", etContent.getText().toString());
        doRequest(0, false,request,  new OnResponseListener() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response response) {

            }

            @Override
            public void onFailed(int what, Response response) {

            }

            @Override
            public void onFinish(int what) {
                ReportSubmissionActivity.this.finish();
                ToastUtils.show("举报成功");
            }
        });
    }

}
