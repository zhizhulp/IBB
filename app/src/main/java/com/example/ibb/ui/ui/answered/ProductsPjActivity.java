package com.example.ibb.ui.ui.answered;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ibb.R;
import com.example.ibb.adapter.ProductEvaluationAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.EvaluationBean;
import com.example.ibb.entity.Product;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.find.MyRadar;
import com.example.ibb.utils.NumberFormatUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品评价页
 */
public class ProductsPjActivity extends BaseNetActivity {


    private List<EvaluationBean> evus = new ArrayList<>();
    private ProductEvaluationAdapter adapter;
    private int paged = 1;
    private TextView tvName;
    private TextView tvScore;
    private RatingBar ratingBar;
    private TextView tvCount;
    private MyRadar radar;
    private Product product;
    private TextView tvHeadCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_pj);
        product = (Product) getIntent().getSerializableExtra("product");
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initRefreshLayout();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductEvaluationAdapter(R.layout.productspj_list_item, evus);
        View headView = getLayoutInflater().inflate(R.layout.goods_evaluation_head, mRecyclerView, false);
        tvName = (TextView) headView.findViewById(R.id.tv_name);
        tvScore = (TextView) headView.findViewById(R.id.tv_score);
        ratingBar = (RatingBar) headView.findViewById(R.id.rating_bar);
        tvCount = (TextView) headView.findViewById(R.id.tv_count);
        radar = (MyRadar) headView.findViewById(R.id.radar);
        tvHeadCount = headView.findViewById(R.id.tv_head_count);
        //发表评论
        findViewById(R.id.btn_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (evus.size() > 0) {
                    AnswerDetActivity.startAnsDet(ProductsPjActivity.this, evus.get(0).getAnswerId(), null);
                }
            }
        });
        adapter.addHeaderView(headView);
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
        requestData();
    }

    private void requestData() {
        Request request = buildRequest(URLApi.goodsCommits, RequestMethod.POST, true, EvaluationBean.class);
        request.add("productId", product.getId());
        request.add("index", paged);
        doRequest(0, request);
    }

    public static void startEvaluation(Context context, Product product) {
        Intent intent = new Intent(context, ProductsPjActivity.class);
        intent.putExtra("product", product);
        context.startActivity(intent);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what == 0) {
            List<EvaluationBean> evaluationBeans = (List<EvaluationBean>) data.getData();
            if (mRefreshLayout.getState() == RefreshState.Refreshing ||
                    mRefreshLayout.getState() == RefreshState.None)
                if (evus.size() > 0) evus.clear();
            if (evaluationBeans == null || evaluationBeans.size() == 0) loadMoreOver();
            if (evaluationBeans != null) evus.addAll(evaluationBeans);
            tvName.setText(product.getName());
            tvScore.setText(NumberFormatUtils.getNewDouble(product.getScore()) + "分");
            ratingBar.setRating((float) (product.getScore() * 5 / 10));
            tvCount.setText(product.getEvaluateCount() + "人评价");
            if (product.getEvaluateCount() == 0) {
                tvHeadCount.setVisibility(View.GONE);
            } else {
                tvHeadCount.setVisibility(View.VISIBLE);
                tvHeadCount.setText("网友评价" + " （" + product.getEvaluateCount() + "）");
            }
            radar.setVisibility(View.VISIBLE);
            List<Product.BaseAttributeBean> attributeBeans = product.getBaseAttribute();
            String[] titles = new String[attributeBeans.size()];
            double[] datas = new double[attributeBeans.size()];
            for (int i = 0; i < attributeBeans.size(); i++) {
                Product.BaseAttributeBean bean = attributeBeans.get(i);
                String name = bean.getName();
                double score = bean.getScore();
                datas[i] = Double.parseDouble(NumberFormatUtils.getNewDouble(score));
                titles[i] = name + ":" + score;
            }
            radar.setData(datas);
            radar.setTitles(titles);
            adapter.notifyDataSetChanged();
        }
    }
}
