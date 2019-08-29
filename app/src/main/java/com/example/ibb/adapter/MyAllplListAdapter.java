package com.example.ibb.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.ReplyBean;
import com.example.ibb.entity.User;
import com.example.ibb.utils.TimeUtils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by 张凯雅 on 2018/3/12.
 */

public class MyAllplListAdapter extends BaseQuickAdapter<ReplyBean,BaseViewHolder> {

    public MyAllplListAdapter(int layoutResId, @Nullable List<ReplyBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReplyBean item) {
        Picasso.with(mContext).load(item.getUser().getPortrait()).placeholder(R.mipmap.b3).into((ImageView) helper.getView(R.id.im_head));
        helper.setText(R.id.tv_name,item.getUser().getNickname());
        helper.setText(R.id.tv_time, TimeUtils.getTimeFormatText(item.getTime()));
        User replyUser = item.getReplyUser();
        if (replyUser==null){
            helper.setText(R.id.tv_content,item.getContent());
        }else {
            SpannableStringBuilder ssb=new SpannableStringBuilder("回复 "+replyUser.getNickname()+": ");
            ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#4c4c4c")),0,ssb.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            ssb.append(item.getContent());
            ((TextView) helper.getView(R.id.tv_content)).setText(ssb);
        }
    }
}
