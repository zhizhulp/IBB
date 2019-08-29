package com.example.ibb.ui.ui.answered;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ibb.R;
import com.example.ibb.adapter.ContentAdapter;
import com.example.ibb.adapter.MyCommdityRecyclerAdapter;
import com.example.ibb.adapter.MySanProblemRecyclerAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.AnsContent;
import com.example.ibb.entity.AnswerBean;
import com.example.ibb.entity.QuestionBean;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.find.ReportActivity;
import com.example.ibb.utils.ContentUtils;
import com.example.ibb.utils.FinalUtils;
import com.example.ibb.utils.ShareUtils;
import com.example.ibb.utils.ToastUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题详情
 */
public class ProblemDetailsActivity extends BaseNetActivity implements View.OnClickListener {

    private ImageView problem_back_iv;
    private RecyclerView problem_recycler2;
    private List<AnswerBean> anss = new ArrayList<>();
    private ImageView problemdetails_share_iv;
    private Button share_return_popup;
    private Button problem_answered_bt;
    private long id;
    private QuestionBean questionBean;
    private int paged = 1;
    private View mentionHead;
    private View answerHead;
    private TextView tvTitle;
    private TextView tvDesc;
    private TextView tvAnsCount;
    private TextView tvFocusCount;
    private CheckBox rbFocus;
    private MySanProblemRecyclerAdapter adapter;
    private TextView tvAnsCountHead;
    private RecyclerView qdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_details);
        getQuestionId();
        initview();
        requestQuestion();
        requestAnswerList();
    }


    protected void initview() {
        //head
        tvTitle = (TextView) findViewById(R.id.tv_question_title);
        tvDesc = (TextView) findViewById(R.id.tv_question_desc);
        tvAnsCount = (TextView) findViewById(R.id.tv_answer_count);
        tvFocusCount = (TextView) findViewById(R.id.tv_focus_count);
        tvAnsCountHead = findViewById(R.id.tv_answer_count_head);
        rbFocus = (CheckBox) findViewById(R.id.rb_focus);
        rbFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbFocus.setText(!rbFocus.isChecked() ? "十 关注" : "已关注");
                if (!rbFocus.isChecked()) {
                    requestFocus();
                } else {
                    requestCancelFocus();
                }
            }
        });

        problem_back_iv = (ImageView) findViewById(R.id.problem_back_iv);
        problem_back_iv.setOnClickListener(this);
        //提到的所有产品
        mentionHead = findViewById(R.id.lat_mention);
        problem_recycler2 = (RecyclerView) findViewById(R.id.problem_recycler2);
        //回答列表
        answerHead = findViewById(R.id.lat_answer_head);
        initRefreshLayout();
        adapter = new MySanProblemRecyclerAdapter(R.layout.san_recycler_item, anss);
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                paged = 1;
                requestAnswerList();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                paged++;
                requestAnswerList();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mRecyclerView.setAdapter(adapter);
        adapter.bindToRecyclerView(mRecyclerView);
        adapter.setEmptyView(R.layout.empty_no_answer);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                AnswerDetActivity.startAnsDet(ProblemDetailsActivity.this, anss.get(position).getId(), questionBean.getTitle());
            }
        });
        //分享
        problemdetails_share_iv = (ImageView) findViewById(R.id.problemdetails_share_iv);
        problemdetails_share_iv.setOnClickListener(this);
        //我来回答
        problem_answered_bt = (Button) findViewById(R.id.problem_answered_bt);
        problem_answered_bt.setOnClickListener(this);
        //问题描述
        qdesc = findViewById(R.id.question_desc_list);
        qdesc.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
    }

    private void requestCancelFocus() {
        Request request = buildRequest(URLApi.focusQuestion, RequestMethod.POST, HttpBaseEntity.class);
        request.add("questionId", id);
        doRequest(1, request);
    }

    private void requestFocus() {
        Request request = buildRequest(URLApi.cancelFocusQuestion, RequestMethod.POST, HttpBaseEntity.class);
        request.add("questionId", id);
        doRequest(2, request);
    }

    private void requestAnswerList() {
        Request request = buildRequest(URLApi.getListForQuestion, RequestMethod.POST, true, AnswerBean.class);
        request.add("questionId", id);
        request.add("index", paged);
        doRequest(3, request);
    }

    public static void startQuestionDet(Context context, long id) {
        Intent intent = new Intent(context, ProblemDetailsActivity.class);
        intent.putExtra("problem_id", id);
        context.startActivity(intent);
    }

    private void getQuestionId() {
        id = getIntent().getLongExtra("problem_id", -1000);
    }

    private void requestQuestion() {
        Request request = buildRequest(URLApi.questiondetailstring, RequestMethod.POST, QuestionBean.class);
        request.add("questionId", id);
        doRequest(0, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what == 0) {
            questionBean = (QuestionBean) data.getData();
            //setHead
            tvTitle.setText(questionBean.getTitle());
            final String content = ContentUtils.buildString(questionBean.getContent());
            final List<AnsContent> contents = new ArrayList<>();
            final ContentAdapter adapter = new ContentAdapter(R.layout.ans_content, contents);
            if (content == null || !content.contains(FinalUtils.IMGS_BASE)) {
                AnsContent ans = new AnsContent();
                ans.text = content;
                contents.add(ans);
            } else {
                View footerView = getLayoutInflater().inflate(R.layout.text, qdesc, false);
                footerView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.removeAllFooterView();
                        contents.clear();
                        String[] split = content.split("\\.\\.\\.");
                        for (int i = 0; i < split.length; i++) {
                            final String ss = split[i];
                            if (ss.contains(FinalUtils.IMGS_BASE)) {
                                AnsContent ansContent = new AnsContent();
                                ansContent.url = ss;
                                contents.add(ansContent);
                            } else {
                                AnsContent ansContent = new AnsContent();
                                ansContent.text = ss;
                                contents.add(ansContent);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
                adapter.addFooterView(footerView);
                AnsContent ans = new AnsContent();
                ans.text = ContentUtils.ellipsizeString(questionBean.getContent());
                contents.add(ans);
            }
            qdesc.setAdapter(adapter);
            tvAnsCount.setText(questionBean.getAnswerCount() + "回答");
            tvFocusCount.setText(questionBean.getFollowCount() + "关注");
            tvAnsCountHead.setText(questionBean.getAnswerCount() + "个回答");
            rbFocus.setChecked(questionBean.isFollow());
            rbFocus.setText(!rbFocus.isChecked() ? "十 关注" : "已关注");
            //提到的所有产品
            final List<QuestionBean.MentionProductsBean> mentionProducts = questionBean.getMentionProducts();
            if (mentionProducts == null || mentionProducts.size() == 0)
                mentionHead.setVisibility(View.GONE);
            else {
                mentionHead.setVisibility(View.VISIBLE);
                MyCommdityRecyclerAdapter recyclerAdapter = new MyCommdityRecyclerAdapter(R.layout.commodity_recycler_item, mentionProducts);
                problem_recycler2.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        GoodsDetActivity.startGoodsDet(ProblemDetailsActivity.this, mentionProducts.get(position).getProduct().getId());
                    }
                });
                problem_recycler2.setLayoutManager(new LinearLayoutManager(MyApp.activity, LinearLayoutManager.HORIZONTAL, false));
                problem_recycler2.setAdapter(recyclerAdapter);
            }
        } else if (what == 3) {
            List<AnswerBean> relateS = (List<AnswerBean>) data.getData();
            if (mRefreshLayout.getState() == RefreshState.Refreshing ||
                    mRefreshLayout.getState() == RefreshState.None)
                if (anss.size() > 0) this.anss.clear();
            if (relateS == null || relateS.size() == 0) loadMoreOver();
            if (relateS != null) this.anss.addAll(relateS);
            adapter.notifyDataSetChanged();
        } else if (what == 1) {
            ToastUtils.show("取消关注");
        } else if (what == 2) {
            ToastUtils.show("关注成功");
        }

    }

    @Override
    protected <T> void handle200False(int what, HttpBaseEntity<T> data) {
        super.handle200False(what, data);
        if (what == 0) finish();
        if (what == 1 || what == 2) rbFocus.setChecked(!rbFocus.isChecked());

    }

    @Override
    protected void handle401(int what) {
        super.handle401(what);
        if (what == 1 || what == 2) rbFocus.setChecked(!rbFocus.isChecked());
    }

    @Override
    protected void handleFailed(int what, String error) {
        super.handleFailed(what, error);
        if (what == 1 || what == 2) rbFocus.setChecked(!rbFocus.isChecked());
    }

    @Override
    protected void handleException(int what, Exception e) {
        super.handleException(what, e);
        if (what == 1 || what == 2) rbFocus.setChecked(!rbFocus.isChecked());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.problem_back_iv:
                finish();
                break;
            //分享
            case R.id.problemdetails_share_iv:
                showPopupMenu(problemdetails_share_iv);
                break;
            //我来回答
            case R.id.problem_answered_bt:
                MineAnsweredActivity.startAnsApply(this, questionBean.getId(), FinalUtils.REQUEST_ANSWER_APPLY);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == FinalUtils.REQUEST_ANSWER_APPLY) {
            paged = 1;
            requestAnswerList();
        }
    }

    private void showPopupMenu(View view) {
        PopupMenu popupmenu = new PopupMenu(MyApp.activity, view);
        popupmenu.getMenuInflater().inflate(R.menu.main, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_share) {
                    ShareUtils.share(ProblemDetailsActivity.this, "question?id=" + id, questionBean.getTitle()
                            , "提问了" + "当前问题，" + "已有" + questionBean.getAnswerCount() + "回答，" + questionBean.getFollowCount() + "关注"
                            , null);
                    return true;
                } else if (item.getItemId() == R.id.action_jubao) {
                    ReportActivity.startReportFirst(ProblemDetailsActivity.this, 0, id);
                    return true;
                }

                return false;
            }
        });
        popupmenu.show();
    }
}
