package com.example.ibb.ui.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;
import com.example.ibb.R;
import com.example.ibb.adapter.MineQuestionRecyclerAdapter;
import com.example.ibb.adapter.TwoBeen;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseActivity;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.QuestionBean;
import com.example.ibb.entity.User;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.answered.ProblemDetailsActivity;
import com.example.ibb.ui.ui.find.PeopleCencerActivity;
import com.example.ibb.utils.AppConfig;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * 我关注的问题
 */
public class MineAttentionActivity extends BaseNetActivity implements View.OnClickListener {

    private ImageView mineattention_back_iv;
    private List<QuestionBean> datas = new ArrayList<>();
    private MineQuestionRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_attention);
        initRefreshLayout();
        initview();
        requestData();
    }

    private void requestData() {
        Request request = buildPostListRequest(URLApi.getFollowQuestions, QuestionBean.class);
        if (datas.size() > 0) {
            QuestionBean questionBean = datas.get(datas.size() - 1);
            request.add("questionId", questionBean.getId());
        }
        doRequest(0, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (mRefreshLayout.getState() == RefreshState.Refreshing ||
                mRefreshLayout.getState() == RefreshState.None)
            if (datas.size() > 0) datas.clear();
        List<QuestionBean> requestData = (List) data.getData();
        if (requestData == null || requestData.size() == 0) loadMoreOver();
        if (requestData != null) datas.addAll(requestData);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void handle401(int what) {
        super.handle401(what);
    }

    protected void initview() {
        mineattention_back_iv = (ImageView) findViewById(R.id.mineattention_back_iv);
        mineattention_back_iv.setOnClickListener(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(MyApp.activity, LinearLayoutManager.VERTICAL, false));
        adapter = new MineQuestionRecyclerAdapter(R.layout.minequestion_recycler_item2, datas);
        mRecyclerView.setAdapter(adapter);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                datas.clear();
                requestData();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                requestData();
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProblemDetailsActivity.startQuestionDet(MineAttentionActivity.this,datas.get(position).getId());
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mineattention_back_iv:
                finish();
                break;
        }
    }
}
