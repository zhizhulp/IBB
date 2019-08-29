package com.example.ibb.ui.ui.find;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ibb.R;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;

public class ReportActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout sq_linearlayout;
    private LinearLayout zz_linearlayout;
    private LinearLayout wf_linearlayout;
    private LinearLayout sx_linearlayout;
    private LinearLayout rm_linearlayout;
    private LinearLayout lj_linearlayout;
    private LinearLayout qf_linearlayout;
    private LinearLayout qt_linearlayout;
    private LinearLayout zy_linearlayout;
    private ImageView report_back_iv;
    private int type;
    private long id;

    @Override
    protected void initview() {

        report_back_iv = (ImageView)findViewById(R.id.report_back_iv);
        report_back_iv.setOnClickListener(this);
        //色情低俗
        sq_linearlayout = (LinearLayout)findViewById(R.id.sq_linearlayout);
        sq_linearlayout.setOnClickListener(this);

        //政治敏感
        zz_linearlayout = (LinearLayout)findViewById(R.id.zz_linearlayout);
        zz_linearlayout.setOnClickListener(this);
        //违法犯罪
        wf_linearlayout = (LinearLayout)findViewById(R.id.wf_linearlayout);
        wf_linearlayout.setOnClickListener(this);
        //涉嫌欺诈
        sx_linearlayout = (LinearLayout)findViewById(R.id.sx_linearlayout);
        sx_linearlayout.setOnClickListener(this);
        //辱骂谩骂
        rm_linearlayout = (LinearLayout)findViewById(R.id.rm_linearlayout);
        rm_linearlayout.setOnClickListener(this);
        //造谣传谣
        zy_linearlayout = (LinearLayout) findViewById(R.id.zy_linearlayout);
        zy_linearlayout.setOnClickListener(this);
        //垃圾广告
        lj_linearlayout = (LinearLayout)findViewById(R.id.lj_linearlayout);
        lj_linearlayout.setOnClickListener(this);
        //侵犯版权
        qf_linearlayout = (LinearLayout)findViewById(R.id.qf_linearlayout);
        qf_linearlayout.setOnClickListener(this);
        //其他
        qt_linearlayout = (LinearLayout)findViewById(R.id.qt_linearlayout);
        qt_linearlayout.setOnClickListener(this);

        type = getIntent().getIntExtra("type", 0);
        id = getIntent().getLongExtra("id", 0);

    }
    public static void startReportFirst(Context context,int type,long id){
        Intent intent=new Intent(context,ReportActivity.class);
        intent.putExtra("type",type);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }
    @Override
    protected void initdata() {

    }

    @Override
    protected int initlayout() {
        return R.layout.activity_report;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.report_back_iv:
                finish();
                break;
            //色情低俗
            case R.id.sq_linearlayout:
                ReportSubmissionActivity.startReport(this,"色情低俗",type,id);
                break;
            //政治敏感
            case R.id.zz_linearlayout:
                ReportSubmissionActivity.startReport(this,"政治敏感",type,id);
                break;
            //违法犯罪
            case R.id.wf_linearlayout:
                ReportSubmissionActivity.startReport(this,"违法犯罪",type,id);
                break;
            //涉嫌欺诈
            case R.id.sx_linearlayout:
                ReportSubmissionActivity.startReport(this,"涉嫌欺诈",type,id);
                break;
            //辱骂谩骂
            case R.id.rm_linearlayout:
                ReportSubmissionActivity.startReport(this,"辱骂谩骂",type,id);
                break;
            //造谣传谣
            case R.id.zy_linearlayout:
                ReportSubmissionActivity.startReport(this,"造谣传谣",type,id);
                break;
            //垃圾广告
            case R.id.lj_linearlayout:
                ReportSubmissionActivity.startReport(this,"垃圾广告",type,id);
                break;
            //侵犯版权
            case R.id.qf_linearlayout:
                ReportSubmissionActivity.startReport(this,"侵犯版权",type,id);
                break;
            //其他
            case R.id.qt_linearlayout:
                ReportSubmissionActivity.startReport(this,"其他",type,id);
                break;
        }
    }
}
