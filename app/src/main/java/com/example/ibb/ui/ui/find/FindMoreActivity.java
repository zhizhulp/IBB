package com.example.ibb.ui.ui.find;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ibb.R;
import com.example.ibb.adapter.QuestionAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.QuestionBean;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.answered.MineAnsweredActivity;
import com.example.ibb.ui.ui.answered.ProblemDetailsActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现模块最新问题（查看更多）
 */
public class FindMoreActivity extends BaseNetActivity implements View.OnClickListener {

    private ImageView findmore_back_iv;
    private QuestionAdapter adapter;
    private List<QuestionBean> beans = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_more);
        initview();
    }

    protected void initview() {
        initRefreshLayout();
        findmore_back_iv = (ImageView) findViewById(R.id.findmore_back_iv);
        findmore_back_iv.setOnClickListener(this);
        adapter = new QuestionAdapter(R.layout.list_item, beans);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProblemDetailsActivity.startQuestionDet(FindMoreActivity.this, beans.get(position).getId());
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MineAnsweredActivity.startAnsApply(FindMoreActivity.this, beans.get(position).getId(), 110);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        mRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                requestData();
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                beans.clear();
                adapter.notifyDataSetChanged();
                requestData();
            }
        });
        requestData();
    }

    private void requestData() {
        Request request = buildPostListRequest(URLApi.lateststring, QuestionBean.class);
        if (beans.size()>0){
            request.add("questionId", beans.get(beans.size()-1).getId());
        }
        doRequest(0, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (mRefreshLayout.getState() == RefreshState.Refreshing ||
                mRefreshLayout.getState() == RefreshState.None)
            if (beans.size() > 0) beans.clear();
        List requestData = (List) data.getData();
        if (requestData == null || requestData.size() == 0) loadMoreOver();
        if (requestData != null) beans.addAll(requestData);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.findmore_back_iv:
                finish();
                break;
        }
    }
}
