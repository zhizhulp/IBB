package com.example.ibb.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.QuestionBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/2/7.
 */

public class MyShoppingRecyclerAdapter extends BaseQuickAdapter<QuestionBean,BaseViewHolder> {


    public MyShoppingRecyclerAdapter(int layoutResId, @Nullable List<QuestionBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuestionBean item) {
        helper.setText(R.id.tv_title,item.getTitle());
        Picasso.with(mContext).load(item.getAnswer().getQuestionProducts().get(0).getProduct().getImgs().get(0))
                .placeholder(R.mipmap.placeholder).into((ImageView) helper.getView(R.id.im_icon));
        helper.setText(R.id.tv_like_count,item.getLikeCount()+"赞");
        helper.setText(R.id.tv_commit_count,item.getReplyCount()+"评");
    }
}
