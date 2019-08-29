package com.example.ibb.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.EvaluationBean;
import com.example.ibb.entity.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ASUS on 2018/5/17.
 * 商品评价适配器
 */

public class ProductEvaluationAdapter extends BaseQuickAdapter<EvaluationBean,BaseViewHolder> {
    public ProductEvaluationAdapter(int layoutResId, @Nullable List<EvaluationBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EvaluationBean item) {
        User user = item.getUser();
        Picasso.with(mContext).load(user.getPortrait()).placeholder(R.mipmap.b3).into((ImageView) helper.getView(R.id.im_icon));
        helper.setText(R.id.tv_name,user.getNickname());
        helper.setRating(R.id.rating_bar,item.getScore()*5/10);
        helper.setText(R.id.tv_score,item.getScore()+"分");
        helper.setText(R.id.tv_evaluation,item.getContent());
        helper.setText(R.id.tv_like_count,item.getLikeCount()+"赞");
        helper.setText(R.id.tv_evaluation_count,item.getReplyCount()+"评");
    }
}
