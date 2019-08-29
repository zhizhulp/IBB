package com.example.ibb.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.QuestionBean;
import com.example.ibb.utils.TimeUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ASUS on 2018/6/1.
 */

public class QuestionAdapter extends BaseQuickAdapter<QuestionBean,BaseViewHolder> {
    public QuestionAdapter(int layoutResId, @Nullable List<QuestionBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuestionBean item) {
        Picasso.with(mContext).load(item.getUser().getPortrait())
                .placeholder(R.mipmap.b3).into((ImageView) helper.getView(R.id.find_tx));
        helper.setText(R.id.find_title,item.getUser().getNickname());
        helper.setText(R.id.find_content,item.getTitle());
        helper.setText(R.id.find_time, TimeUtils.getTimeFormatText(item.getTime()));
        helper.addOnClickListener(R.id.tv_go_answer);
    }
}
