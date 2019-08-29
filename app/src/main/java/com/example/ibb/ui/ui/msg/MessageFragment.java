package com.example.ibb.ui.ui.msg;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ibb.R;
import com.example.ibb.adapter.MsgListAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseNetFragment;
import com.example.ibb.entity.Msg;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.answered.AllPingLunActivity;
import com.example.ibb.ui.ui.answered.ProblemDetailsActivity;
import com.example.ibb.ui.ui.answered.AnswerDetActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页-消息
 */
public class MessageFragment extends BaseNetFragment {

    private List<Msg> msgList = new ArrayList<>();
    private MsgListAdapter adapter;
    private int paged = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new MsgListAdapter(R.layout.main_msg_list, msgList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Msg msg = msgList.get(position);
                int type = msg.getType();
                if (type == 2 ||type == 3) {
                   ProblemDetailsActivity.startQuestionDet(getActivity(),msg.getQuestion().getId());
                } else if ( type == 4 ||type ==1) {
                    AnswerDetActivity.startAnsDet(getActivity(),msg.getAnswer().getId(),msg.getQuestion().getTitle());
                } else if (type == 5 || type == 6) {
                    AllPingLunActivity.startAll(getActivity(),msg.getAnswer().getId());
                }
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                paged = 1;
                requestMsgList(0);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                paged++;
                requestMsgList(msgList.get(msgList.size() - 1).getId());
            }
        });
        requestMsgList(0);
    }

    private void requestMsgList(int id) {
        Request request = buildPostListRequest(URLApi.notification, Msg.class);
        if (paged != 1) request.add("id", id);
        request.add("index", paged);
        doRequest(0, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        if (what == 0) {
            if (mRefreshLayout.getState() == RefreshState.Refreshing ||
                    mRefreshLayout.getState() == RefreshState.None) {
                if (msgList.size() > 0) {
                    msgList.clear();
                }
            }
            List<Msg> requestData = (List<Msg>) data.getData();
            if (requestData == null || requestData.size() == 0) {
                loadMoreOver();
            }
            if (requestData != null) {
                msgList.addAll(requestData);
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected boolean canRefreshAndLoad() {
        return true;
    }
}
