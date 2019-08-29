package com.example.ibb.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.QuestionBean;
import com.example.ibb.utils.FinalUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by AUSU on 2018/5/15.
 */

public class MyCommdityRecyclerAdapter extends BaseQuickAdapter<QuestionBean.MentionProductsBean, BaseViewHolder> {


    public MyCommdityRecyclerAdapter(int layoutResId, @Nullable List<QuestionBean.MentionProductsBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, QuestionBean.MentionProductsBean item) {
        RatingBar ratingBar = helper.getView(R.id.rating_bar);
        ratingBar.setRating(item.getProduct().getScore()*5/ FinalUtils.RATING_MAX_SCORE);
        helper.setText(R.id.tv_goods_name,item.getProduct().getName());
        helper.setText(R.id.tv_score,item.getProduct().getScore()+"分");
        Log.d(TAG, "convert: ");
        Picasso.with(mContext).load(item.getProduct().getImgs().get(0)).placeholder(R.mipmap.placeholder)
                .into((ImageView) helper.getView(R.id.im_goods));
    }

}
