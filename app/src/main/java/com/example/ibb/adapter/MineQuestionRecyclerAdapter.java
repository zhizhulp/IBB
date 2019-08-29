package com.example.ibb.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.QuestionBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/23.
 */

public class MineQuestionRecyclerAdapter extends BaseQuickAdapter<QuestionBean, BaseViewHolder> {

    public MineQuestionRecyclerAdapter(int layoutResId, @Nullable List<QuestionBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuestionBean item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_like_count, item.getLikeCount() + "赞");
        helper.setText(R.id.tv_comment_count, item.getReplyCount() + "评");
        try {
            helper.setGone(R.id.im_icon,true);
            Picasso.with(mContext).load(item.getMentionProducts().get(0).getProduct().getImgs().get(0))
                    .placeholder(R.mipmap.placeholder).into((ImageView) helper.getView(R.id.im_icon));
        } catch (Exception e) {
            helper.setGone(R.id.im_icon,false);
        }
        try {
            helper.setGone(R.id.tv_tag,true);
            helper.setText(R.id.tv_tag,item.getMentionProducts().get(0).getAttrs().get(0).getAttribute().getName());
        }catch (Exception e){
            helper.setGone(R.id.tv_tag,false);
        }
    }
}
