package com.example.ibb.ui.ui.mine;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ibb.R;
import com.example.ibb.adapter.MineQuestionListAdapter;
import com.example.ibb.adapter.MineQuestionRecyclerAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseFragment;
import com.example.ibb.base.BaseNetFragment;
import com.example.ibb.entity.QuestionBean;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.answered.ProblemDetailsActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineQuestionFragment extends BaseNetFragment {

    private List<QuestionBean> datas = new ArrayList<>();
    private MineQuestionRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine_question, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                ProblemDetailsActivity.startQuestionDet(getActivity(),datas.get(position).getId());
            }
        });
        requestData();

    }

    private void requestData() {
        Request request = buildPostListRequest(URLApi.getQuestions, QuestionBean.class);
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
    protected boolean canRefreshAndLoad() {
        return true;
    }
}
