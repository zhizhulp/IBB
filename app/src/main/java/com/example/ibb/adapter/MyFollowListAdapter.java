package com.example.ibb.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.AnswerBean;
import com.example.ibb.utils.ContentUtils;
import com.example.ibb.utils.TimeUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/5.
 */

public class MyFollowListAdapter extends BaseQuickAdapter<AnswerBean,BaseViewHolder> {

    public MyFollowListAdapter(int layoutResId, @Nullable List<AnswerBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnswerBean item) {
        Picasso.with(mContext).load(item.getUser().getPortrait()).placeholder(R.mipmap.b3).into((ImageView) helper.getView(R.id.im_head));
        helper.setText(R.id.tv_name,item.getUser().getNickname());
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_content, ContentUtils.ellipsizeString(item.getContent()));
        helper.setText(R.id.tv_like_count,item.getLikeCount()+"赞");
        helper.setText(R.id.tv_comment_count,item.getReplyCount()+"评");
        helper.setText(R.id.tv_time, TimeUtils.getTimeFormatText(item.getTime()));
    }
}
