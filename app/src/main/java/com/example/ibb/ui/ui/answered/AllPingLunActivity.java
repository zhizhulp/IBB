package com.example.ibb.ui.ui.answered;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ibb.R;
import com.example.ibb.adapter.MyAllplListAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.ReplyBean;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.utils.ScreenDpiUtils;
import com.example.ibb.utils.ToastUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.List;

public class AllPingLunActivity extends BaseNetActivity implements View.OnClickListener {

    private ImageView allpl_back_iv;
    private List<ReplyBean> datas = new ArrayList<>();
    private int id;
    private MyAllplListAdapter adapter;
    private int paged = 1;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ping_lun);
        id = getIntent().getIntExtra("id", -1);
        initRefreshLayout();
        initview();
        requestData();
    }

    private void requestData() {
        Request request = buildPostListRequest(URLApi.getCommentsList, ReplyBean.class);
        request.add("answerId", id);
        request.add("index", paged);
        doRequest(0, request);
    }

    public static void startAll(Context context, int answerId) {
        Intent intent = new Intent(context, AllPingLunActivity.class);
        intent.putExtra("id", answerId);
        context.startActivity(intent);
    }

    protected void initview() {
        allpl_back_iv = (ImageView) findViewById(R.id.allpl_back_iv);
        allpl_back_iv.setOnClickListener(this);

        adapter = new MyAllplListAdapter(R.layout.allpl_list_item, datas);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                paged = 1;
                requestData();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                paged++;
                requestData();
            }
        });
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                showCommentsDialog(datas.get(position).getId());
            }
        });
    }

    private void requestApplyComment(CharSequence content, int replyId) {
        Request request = buildRequest(URLApi.applyComment, ReplyBean.class);
        request.add("answerId", id);
        request.add("replyId", replyId);
        request.add("content", content.toString());
        doRequest(1, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what == 0) {
            if (mRefreshLayout.getState() == RefreshState.Refreshing ||
                    mRefreshLayout.getState() == RefreshState.None)
                if (datas.size() > 0) datas.clear();
            List<ReplyBean> requestData = (List) data.getData();
            if (requestData == null || requestData.size() == 0) loadMoreOver();
            if (requestData != null) datas.addAll(requestData);
            adapter.notifyDataSetChanged();
        } else if (what == 1) {
            if (dialog != null && dialog.isShowing()) dialog.dismiss();
            ToastUtils.show("发布成功");
            paged = 1;
            requestData();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.allpl_back_iv:
                finish();
                break;
        }
    }

    private void showCommentsDialog(final int replyId) {
        dialog = new Dialog(this, R.style.dialog);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        View view = getLayoutInflater().inflate(R.layout.comment_dialog, null);
        LinearLayout.LayoutParams sLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        sLp.width = (int) (getResources().getDisplayMetrics().widthPixels - ScreenDpiUtils.dp2px(this, 40));//margin值由宽度来控制
        dialog.setContentView(view, sLp);
        dialog.findViewById(R.id.im_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final EditText editText = (EditText) dialog.findViewById(R.id.editText);
        dialog.findViewById(R.id.btn_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() == 0) {
                    ToastUtils.show("请输入内容再发布");
                } else {
                    requestApplyComment(editText.getText(), replyId);
                }
            }
        });
        dialog.show();
    }
}
