package com.example.ibb.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ibb.R;
import com.example.ibb.app.MyApp;
import com.example.ibb.entity.SelectedData;
import com.example.ibb.utils.NumberFormatUtils;
import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/23.
 */

public class RecommendItemRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private int item_width;
    private int item_width1;
    private RecyclerView.LayoutParams params;
    private List<SelectedData.DataBean.MentionProductsBean> mentionProducts;
    private static final int ITEM_TYPE_FOOTER = 0x00001;
    private static final int ITEM_TYPE_DEFAUL = 0x00002;
    // 采用接口回调的方式实现RecyclerView的ItemClick(在适配器中，实例化接口)
    public OnRecyclerViewListener mOnRecyclerViewListener;

    // 接口回调第二步: 初始化接口的引用
    public void setitemClick(OnRecyclerViewListener listener) {
        this.mOnRecyclerViewListener = listener;
    }

    public RecommendItemRecyclerAdapter(Context context, int item_width1, int item_width, List<SelectedData.DataBean.MentionProductsBean> mentionProducts) {
        this.context = context;
        this.item_width = item_width;
        this.item_width1 = item_width1;
        this.mentionProducts = mentionProducts;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 3) {
            return ITEM_TYPE_FOOTER;
        } else {
            return ITEM_TYPE_DEFAUL;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        View inflate;
        switch (viewType) {
            case ITEM_TYPE_FOOTER:

                inflate = LayoutInflater.from(context).inflate(R.layout.recommenditem_recycler_item2, parent, false);
                holder = new FO0TERHolder(inflate);
                break;

            case ITEM_TYPE_DEFAUL:

                inflate = LayoutInflater.from(context).inflate(R.layout.recommenditem_recycler_item, parent, false);
                holder = new DEFAULHolder(inflate);
                break;
        }
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (getItemViewType(position) == ITEM_TYPE_FOOTER) {

            ((FO0TERHolder) holder).item2_linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnRecyclerViewListener.onClickMore(position);
                }
            });
        } else if (getItemViewType(position) == ITEM_TYPE_DEFAUL) {
            ((DEFAULHolder) holder).item_linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnRecyclerViewListener.onClickGoods(position,mentionProducts.get(position));
                }
            });
            if (mentionProducts.size() > 0) {
                SelectedData.DataBean.MentionProductsBean.ProductBeanX product = mentionProducts.get(position).getProduct();
                if (!product.equals("")) {
                    List<String> imgs = product.getImgs();
                    if (imgs.size() > 0) {
                        DEFAULHolder defaulHolder = (DEFAULHolder) holder;
                        defaulHolder.recommidityitem_phoneName.setText(product.getName());
                        Picasso.with(context).load(product.getImgs().get(0)).placeholder(R.mipmap.placeholder).into(defaulHolder.imageView);
                        defaulHolder.ratingBar.setRating((float) (product.getScore()*5/10));
                        defaulHolder.tvScore.setText(NumberFormatUtils.getNewDouble(product.getScore()));
                    }
                }
            }
        }
        if (position == 0) {
            params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            params.setMargins(item_width * 2, 0, 0, 0);
        } else {
            params.setMargins(item_width1 * 2, 0, 0, 0);
        }

    }

    @Override
    public int getItemCount() {
        return mentionProducts.size();
    }


    //手机评分布局
    class DEFAULHolder extends RecyclerView.ViewHolder {

        TextView recommidityitem_phoneName;
        ImageView imageView;
        RatingBar ratingBar;
        TextView tvScore;
        LinearLayout item_linearLayout;

        public DEFAULHolder(View itemView) {
            super(itemView);
            recommidityitem_phoneName = itemView.findViewById(R.id.text_Recommend_Recycler_goods);
            imageView = itemView.findViewById(R.id.image_Recommend_Recycler_goods);
            item_linearLayout = itemView.findViewById(R.id.item_linearLayout);
            ratingBar = itemView.findViewById(R.id.rating_bar);
            tvScore = itemView.findViewById(R.id.tv_score);
        }

    }

    //查看全部布局
    private class FO0TERHolder extends RecyclerView.ViewHolder {
        LinearLayout item2_linearLayout;

        public FO0TERHolder(View itemView) {
            super(itemView);
            item2_linearLayout = itemView.findViewById(R.id.item2_linearLayout);
        }
    }

    // 接口回调第一步: 定义接口和接口中的方法
    public interface OnRecyclerViewListener {
        void onClickGoods(int position, SelectedData.DataBean.MentionProductsBean mentionProductsBean);

        void onClickMore(int position);
    }


}
