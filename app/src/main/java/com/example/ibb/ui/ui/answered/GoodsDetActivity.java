package com.example.ibb.ui.ui.answered;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ibb.R;
import com.example.ibb.adapter.GoodsDetAdapter;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.base.WebViewBaseActivity;
import com.example.ibb.entity.Product;
import com.example.ibb.entity.RelatedQ2A;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.find.MyRadar;
import com.example.ibb.utils.NumberFormatUtils;
import com.example.ibb.utils.ScreenDpiUtils;
import com.example.ibb.utils.ShareUtils;
import com.example.ibb.utils.ToastUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 商品详情
 */
public class GoodsDetActivity extends BaseNetActivity {
    private int id = 2;
    private View headView;
    private GoodsDetAdapter adapter;
    private List<RelatedQ2A> data = new ArrayList<>();
    private ImageView imGoods;
    private TextView tvName;
    private TextView tvPrice;
    private LinearLayout latTags;
    private TextView tvScore;
    private RatingBar ratingBar;
    private TextView tvGoScore;
    private MyRadar raDar;
    private CheckBox cbCollect;
    private View imShare;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_det);
        //head
        findViewById(R.id.im_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //收藏
        cbCollect = findViewById(R.id.cb_collect);
        cbCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (product.isFavorite())
                    requestCancelCollect();
                else
                    requestCollect();
            }
        });
        //分享
        imShare = findViewById(R.id.im_share);
        imShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        id = getIntent().getIntExtra("id", -1);
        initRefreshLayout();
        adapter = new GoodsDetAdapter(R.layout.commodity_list_item, data);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.d(TAG, "onSimpleItemClick: " + position);
                RelatedQ2A relatedQ2A = data.get(position);
                ProblemDetailsActivity.startQuestionDet(GoodsDetActivity.this, relatedQ2A.getQuestion().getId());
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                data.clear();
                adapter.notifyDataSetChanged();
                requestProduct();
                requestRelated();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                requestRelated();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        requestProduct();
        requestRelated();
    }

    private void requestProduct() {
        Request request = buildRequest(URLApi.goodsDetails, RequestMethod.POST, Product.class);
        request.add("productId", id);
        doRequest(0, true, request);
    }

    private void requestRelated() {
        Request request = buildRequest(URLApi.interlocution, RequestMethod.POST, true, RelatedQ2A.class);
        request.add("productId", id);
        if (data.size() > 0) {
            request.add("lastId", data.get(data.size() - 1).getId());
        }
        doRequest(1, false, request);
    }

    private void requestCancelCollect() {
        Request request = buildRequest(URLApi.productDeleteFavorite, HttpBaseEntity.class);
        request.add("productId", id);
        doRequest(2, request);
    }

    private void requestCollect() {
        Request request = buildPostListRequest(URLApi.productFavorite, HttpBaseEntity.class);
        request.add("productId", id);
        doRequest(3, request);
    }

    public static void startGoodsDet(Context context, int id) {
        Intent intent = new Intent(context, GoodsDetActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what == 0) {
            product = (Product) data.getData();
            if (product != null) {
                if (headView == null) {
                    headView = getLayoutInflater().inflate(R.layout.goods_det_head, null);
                    imGoods = headView.findViewById(R.id.im_icon);
                    tvName = headView.findViewById(R.id.tv_name);
                    tvPrice = headView.findViewById(R.id.tv_price);
                    latTags = headView.findViewById(R.id.lat_tags);
                    latTags.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebViewBaseActivity.start(GoodsDetActivity.this, "产品信息", product.getInfoUrl());
                        }
                    });
                    headView.findViewById(R.id.im_go_h5).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebViewBaseActivity.start(GoodsDetActivity.this, "产品信息", product.getInfoUrl());
                        }
                    });
                    tvScore = headView.findViewById(R.id.tv_score);
                    ratingBar = headView.findViewById(R.id.rating_bar);
                    tvGoScore = headView.findViewById(R.id.tv_go_score);
                    tvGoScore.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ProductsPjActivity.startEvaluation(GoodsDetActivity.this, product);
                        }
                    });
                    raDar = headView.findViewById(R.id.myRadar);
                    adapter.addHeaderView(headView);
                }
                Picasso.with(this).load(product.getImgs().get(0)).placeholder(R.mipmap.placeholder).into(imGoods);
                tvName.setText(product.getName());
                tvPrice.setText(String.format(Locale.getDefault(), "￥%d-%d", product.getMinPrice(), product.getMaxPrice()));
                tvScore.setText(String.format("%s分", NumberFormatUtils.getNewDouble(product.getScore())));
                cbCollect.setChecked(product.isFavorite());
                Log.d(TAG, "handle200True: " + product.isFavorite());
                ratingBar.setRating((float) (product.getScore() * 5 / 10));
                tvGoScore.setText("共" + product.getEvaluateCount() + "人评论");
                List<Product.BaseAttributeBean> attributeBeans = product.getBaseAttribute();
                String[] titles = new String[attributeBeans.size()];
                double[] datas = new double[attributeBeans.size()];
                for (int i = 0; i < attributeBeans.size(); i++) {
                    Product.BaseAttributeBean bean = attributeBeans.get(i);
                    String name = bean.getName();
                    double score = Double.parseDouble(NumberFormatUtils.getNewDouble(bean.getScore()));
                    datas[i] = score;
                    titles[i] = name + ":" + score;
                }
                raDar.setData(datas);
                raDar.setTitles(titles);
                List<Product.InfosBean> infos = product.getInfos();
                latTags.removeAllViews();
                for (int i = 0; i < infos.size(); i++) {
                    Product.InfosBean infosBean = infos.get(i);
                    TextView tag = (TextView) getLayoutInflater().inflate(R.layout.tag_text, latTags, false);
                    tag.setText(infosBean.getName());
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    lp.gravity = Gravity.CENTER_VERTICAL;
                    lp.leftMargin = (int) ScreenDpiUtils.dp2px(this, 15);
                    lp.rightMargin = (int) ScreenDpiUtils.dp2px(this, 15);
                    latTags.addView(tag, lp);
                }
            }
        } else if (what == 1) {
            List<RelatedQ2A> relateS = (List<RelatedQ2A>) data.getData();
            if (mRefreshLayout.getState() == RefreshState.Refreshing || mRefreshLayout.getState() == RefreshState.None) {
                if (this.data.size() > 0) this.data.clear();
            }
            if (relateS == null || relateS.size() == 0) {
                loadMoreOver();
            }
            if (relateS != null) {
                for (int i = 0; i < relateS.size(); i++) {
                    RelatedQ2A relatedQ2A = relateS.get(i);
                    if (relatedQ2A.getQuestion() != null) {
                        this.data.add(relatedQ2A);
                    }
                }
            }
            adapter.notifyDataSetChanged();
        } else if (what == 2) {
            product.setFavorite(false);
            ToastUtils.show("取消收藏成功");

        } else if (what == 3) {
            product.setFavorite(true);
            ToastUtils.show("收藏成功");
        }
    }

    private void showShare() {
//        View view = LayoutInflater.from(MyApp.activity).inflate(R.layout.share_popupwindow, null);
//        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new ColorDrawable());
//        popupWindow.showAtLocation(imShare, Gravity.BOTTOM, 0, 0);//parent view随意
//        Button share_return_popup = (Button) view.findViewById(R.id.share_return_popup);
//        share_return_popup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
        List<Product.BaseAttributeBean> baseAttributeBeans = product.getBaseAttribute();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < product.getBaseAttribute().size(); i++) {
            Product.BaseAttributeBean bean = baseAttributeBeans.get(i);
            sb.append(bean.getName()).append(":").append(NumberFormatUtils.getNewDouble(bean.getScore()));
        }
        ShareUtils.share(GoodsDetActivity.this, "product?id=" + product.getId(), "爱哔哔|" + product.getName()
                , sb.toString()
                , product.getImgs().get(0));
    }

}
