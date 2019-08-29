package com.example.ibb.ui.ui.mine;


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
import com.example.ibb.adapter.MyFollowListAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseNetFragment;
import com.example.ibb.entity.AnswerBean;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.answered.AnswerDetActivity;
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
public class MineAnsweredFragment extends BaseNetFragment {

    private List<AnswerBean> datas=new ArrayList<>();
    private MyFollowListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine_answered, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MyFollowListAdapter(R.layout.myfollow_list_item,datas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
                AnswerDetActivity.startAnsDet(getActivity(),datas.get(position).getId(),datas.get(position).getTitle());
            }
        });
        requestData();
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (mRefreshLayout.getState() == RefreshState.Refreshing ||
                mRefreshLayout.getState() == RefreshState.None)
            if (datas.size() > 0) datas.clear();
        List<AnswerBean> requestData = (List) data.getData();
        if (requestData == null || requestData.size() == 0) loadMoreOver();
        if (requestData != null) datas.addAll(requestData);
        adapter.notifyDataSetChanged();
    }

    private void requestData() {
        Request request = buildPostListRequest(URLApi.getAnswers, AnswerBean.class);
        if (datas.size() > 0) {
            AnswerBean answerBean = datas.get(datas.size() - 1);
            request.add("answerId", answerBean.getId());
        }
        doRequest(0, request);
    }

    @Override
    protected boolean canRefreshAndLoad() {
        return true;
    }
}
