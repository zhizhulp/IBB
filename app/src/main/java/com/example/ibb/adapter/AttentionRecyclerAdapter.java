package com.example.ibb.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.custom_view.ImageView_circle;
import com.example.ibb.entity.FocusBean;
import com.example.ibb.utils.ContentUtils;
import com.example.ibb.utils.TimeUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/9.
 */

public class AttentionRecyclerAdapter extends BaseQuickAdapter<FocusBean, BaseViewHolder> {

    public AttentionRecyclerAdapter(int layoutResId, @Nullable List<FocusBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FocusBean item) {
        Picasso.with(mContext).load(item.getUser().getPortrait()).placeholder(R.mipmap.b3).into((ImageView) helper.getView(R.id.im_head));
        helper.setText(R.id.tv_name, item.getUser().getNickname());
        String type = null;
        switch (item.getType()) {//123 提问 回答 点赞
            case 1:
                type="提问了.";
                break;
            case 2:
                type="回答了.";
                helper.setText(R.id.tv_content, ContentUtils.ellipsizeString(item.getAnswer().getContent()));
                break;
            case 3:
                type="点赞了.";
                helper.setText(R.id.tv_content,ContentUtils.ellipsizeString(item.getAnswer().getContent()));
                break;
            default:
                type="未知的.";
                break;
        }
        helper.setText(R.id.tv_type_time, type + TimeUtils.getTimeFormatText(item.getTime()));
        helper.setText(R.id.tv_title,ContentUtils.ellipsizeString(item.getQuestion().getTitle()));
        helper.setText(R.id.tv_like_count,item.getQuestion().getLikeCount()+"赞");
        helper.setText(R.id.tv_commit_count,item.getQuestion().getReplyCount()+"评");
    }
}
