package com.example.ibb.ui.ui.user;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ibb.R;
import com.example.ibb.adapter.DynamicAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseNetFragment;
import com.example.ibb.entity.AnswerBean;
import com.example.ibb.entity.FocusBean;
import com.example.ibb.entity.Msg;
import com.example.ibb.entity.QuestionBean;
import com.example.ibb.entity.ReplyBean;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.answered.ProblemDetailsActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.nohttp.rest.Request;

import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserCenterBaseFragment extends BaseNetFragment {
    private int type;//0123 all/提问/回答/评论
    private long userId;
    private DynamicAdapter adapter;
    private List datas = new ArrayList<>();
    private int paged = 1;

    public UserCenterBaseFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_center_base, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        type = getArguments().getInt("type", 0);
        userId = getArguments().getLong("userId", -1000);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DynamicAdapter(R.layout.item_b, datas);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Object item = datas.get(position);
                if(item instanceof QuestionBean){
                    ProblemDetailsActivity.startQuestionDet(getActivity(),((QuestionBean) item).getId());
                }else if(item instanceof AnswerBean){
                    ProblemDetailsActivity.startQuestionDet(getActivity(),((AnswerBean) item).getQuestionId());
                }else if(item instanceof ReplyBean){
                    ProblemDetailsActivity.startQuestionDet(getActivity(),((ReplyBean) item).getAnswer().getQuestionId());
                }else if(item instanceof FocusBean){
                    FocusBean foc = (FocusBean) item;
                    int type = foc.getType();//123 提问 回答 评论
                    switch (type){
                        case 1:
                            QuestionBean questionBean = foc.getQuestion();
                            ProblemDetailsActivity.startQuestionDet(getActivity(),questionBean.getId());
                            break;
                        case 2:
                            AnswerBean answerBean = foc.getAnswer();
                            ProblemDetailsActivity.startQuestionDet(getActivity(),answerBean.getQuestionId());
                            break;
                        case 3:
                            ReplyBean replyBean = foc.getReply();
                            ProblemDetailsActivity.startQuestionDet(getActivity(),replyBean.getAnswer().getQuestionId());
                            break;
                    }
                }
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                datas.clear();
                adapter.notifyDataSetChanged();
                selectRequest();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                paged++;
                selectRequest();
            }
        });
        selectRequest();
    }

    private void requestAll() {
        Request request = buildPostListRequest(URLApi.allDynamic, FocusBean.class);
        if (datas.size() > 0) {
            FocusBean focusBean = (FocusBean) datas.get(datas.size() - 1);
            request.add("type", focusBean.getType());
            if (focusBean.getQuestion() != null)
                request.add("questionId", focusBean.getQuestion().getId());
            if (focusBean.getAnswer() != null)
                request.add("ansewerId", focusBean.getAnswer().getId());
            if (focusBean.getReply() != null)
                request.add("replyId", focusBean.getReply().getId());
        }
        request.add("userId", userId);
        doRequest(0, request);
    }

    private void requestQuestions() {
        Request request = buildPostListRequest(URLApi.getQuestions, QuestionBean.class);
        if (datas.size() > 0) {
            QuestionBean questionBean = (QuestionBean) datas.get(datas.size() - 1);
            request.add("questionId", questionBean.getId());
        }
        request.add("userId", userId);
        doRequest(0, request);
    }

    private void requestAnswers() {
        Request request = buildPostListRequest(URLApi.getAnswers, AnswerBean.class);
        if (datas.size() > 0) {
            AnswerBean answerBean = (AnswerBean) datas.get(datas.size() - 1);
            request.add("answerId", answerBean.getId());
        }
        request.add("userId", userId);
        doRequest(0, request);
    }

    private void requestComments() {
        Request request = buildPostListRequest(URLApi.replys, ReplyBean.class);
        request.add("userId", userId);
        request.add("index", paged);
        doRequest(0, request);
    }

    public static UserCenterBaseFragment getInstance(long userId, int type) {
        UserCenterBaseFragment fragment = new UserCenterBaseFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putLong("userId", userId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what == 0) {
            if (mRefreshLayout.getState() == RefreshState.Refreshing ||
                    mRefreshLayout.getState() == RefreshState.None)
                if (datas.size() > 0) datas.clear();
            List requestData = (List) data.getData();
            if (requestData == null || requestData.size() == 0) loadMoreOver();
            if (requestData != null) datas.addAll(requestData);
            adapter.notifyDataSetChanged();
        }
    }

    private void selectRequest() {
        switch (type) {
            case 0:
                requestAll();
                break;
            case 1:
                requestQuestions();
                break;
            case 2:
                requestAnswers();
                break;
            case 3:
                requestComments();
                break;
        }
    }

    @Override
    protected boolean canRefreshAndLoad() {
        return true;
    }
}
