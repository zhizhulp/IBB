package com.example.ibb.ui.ui.answered;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ibb.R;
import com.example.ibb.adapter.AttentionRecyclerAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseNetFragment;
import com.example.ibb.entity.FocusBean;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
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
public class AttentionFragment extends BaseNetFragment {
    private AttentionRecyclerAdapter adapter;
    private List<FocusBean> focusBeanList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_attention, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview();
        requestData();
    }

    private void requestData() {
        Request request = buildPostListRequest(URLApi.following, FocusBean.class);
        if (focusBeanList.size() != 0) {
            FocusBean focusBean = focusBeanList.get(focusBeanList.size() - 1);
            request.add("type", focusBean.getType());
            request.add("userId", focusBean.getUser().getId());
            request.add("questionId", focusBean.getQuestion().getId());
            if (focusBean.getAnswer() != null)
                request.add("answerId", focusBean.getAnswer().getId());
        }
        doRequest(0, request);
    }

    protected void initview() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MyApp.activity, LinearLayoutManager.VERTICAL, false));
        adapter = new AttentionRecyclerAdapter(R.layout.attention_recycler_item, focusBeanList);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                FocusBean focusBean = focusBeanList.get(position);
                ProblemDetailsActivity.startQuestionDet(getActivity(), focusBean.getQuestion().getId());
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                focusBeanList.clear();
                adapter.notifyDataSetChanged();
                requestData();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                requestData();
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what == 0) {
            List<FocusBean> localData = (List<FocusBean>) data.getData();
            if (mRefreshLayout.getState() == RefreshState.Refreshing ||
                    mRefreshLayout.getState() == RefreshState.None) {
                if (focusBeanList.size() > 0) {
                    focusBeanList.clear();
                }
            }
            if (localData == null || localData.size() == 0) {
                loadMoreOver();
            }
            if (localData != null) {
                focusBeanList.addAll(localData);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void handle401(int what) {
        //super.handle401(what);
    }

    @Override
    protected boolean canRefreshAndLoad() {
        return true;
    }
}
