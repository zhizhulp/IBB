package com.example.ibb.ui.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ibb.R;
import com.example.ibb.adapter.MyDetailsMoreListAdapter2;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.Product;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.answered.ProductsPjActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.List;

public class MineCollectionActivity extends BaseNetActivity implements View.OnClickListener {

    private ImageView minecollection_back_iv;
    private List<Product> datas=new ArrayList<>();
    private MyDetailsMoreListAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_collection);
        initview();
        requestData();
    }

    protected void initview() {

        minecollection_back_iv = (ImageView)findViewById(R.id.minecollection_back_iv);
        minecollection_back_iv.setOnClickListener(this);
        initRefreshLayout();
        adapter = new MyDetailsMoreListAdapter2(R.layout.detailsmore_list_item,datas);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                ProductsPjActivity.startEvaluation(MineCollectionActivity.this,datas.get(position));
            }
        });
    }

    private void requestData() {
        Request request = buildPostListRequest(URLApi.getFavoriteProducts, Product.class);
        if (datas.size() > 0) {
            Product Product = datas.get(datas.size() - 1);
            request.add("productId", Product.getId());
        }
        doRequest(0, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what==0){
            if (mRefreshLayout.getState() == RefreshState.Refreshing ||
                    mRefreshLayout.getState() == RefreshState.None)
                if (datas.size() > 0) datas.clear();
            List<Product> requestData = (List) data.getData();
            if (requestData == null || requestData.size() == 0) loadMoreOver();
            if (requestData != null) datas.addAll(requestData);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.minecollection_back_iv:
                finish();
                break;

        }
    }
}
