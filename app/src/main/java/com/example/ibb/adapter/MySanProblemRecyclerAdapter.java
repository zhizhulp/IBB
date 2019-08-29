package com.example.ibb.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.AnswerBean;
import com.example.ibb.entity.ProductBean;
import com.example.ibb.utils.ContentUtils;
import com.example.ibb.utils.NumberFormatUtils;
import com.example.ibb.utils.ScreenDpiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/14.
 */

public class MySanProblemRecyclerAdapter extends BaseQuickAdapter<AnswerBean, BaseViewHolder> {


    public MySanProblemRecyclerAdapter(int layoutResId, @Nullable List<AnswerBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnswerBean item) {
        Picasso.with(mContext).load(item.getUser().getPortrait()).placeholder(R.mipmap.b3)
                .into((ImageView) helper.getView(R.id.im_head));
        helper.setText(R.id.tv_name, item.getUser().getNickname());
        /*try {
            helper.setGone(R.id.im_icon,true);
            Picasso.with(mContext).load(item.getQuestionProducts().get(0).getProduct().getImgs().get(0)).placeholder(R.mipmap.placeholder)
                    .into((ImageView) helper.getView(R.id.im_icon));
        }catch (Exception e){
            helper.setGone(R.id.im_icon,false);
        }*/
        helper.setText(R.id.tv_content, ContentUtils.ellipsizeString(item.getContent()));
        helper.setText(R.id.tv_like_count,item.getFavoriteCount()+"赞");
        helper.setText(R.id.tv_evaluation_count,item.getReplyCount()+"评");
        List<ProductBean> attrs = item.getQuestionProducts();
        if (attrs == null || attrs.size() == 0) {
            helper.setGone(R.id.lat_tags,false);
        } else {
            helper.setGone(R.id.lat_tags,true);
            LinearLayout tagParent = helper.getView(R.id.lat_tags);
            for (int i = 0; i <attrs.size(); i++) {
                if (tagParent.getChildCount()==2) break;
                ProductBean productBean = attrs.get(i);
                double score = productBean.getProduct().getScore();
                if (score==0)
                    continue;
                View viewTag = LayoutInflater.from(mContext).inflate(R.layout.answer_list_tag,tagParent,false);
                ((TextView) viewTag.findViewById(R.id.tv_tag)).setText(productBean.getProduct().getName());
                ((RatingBar) viewTag.findViewById(R.id.rating_bar)).setRating((float) (Double.parseDouble(NumberFormatUtils.getNewDouble(score))*5/10));
                ((TextView) viewTag.findViewById(R.id.tv_score)).setText(NumberFormatUtils.getNewDouble(score)+"分");
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.topMargin = (int) ScreenDpiUtils.dp2px(mContext,5);
                tagParent.addView(viewTag,lp);
            }
        }
        List<ProductBean> attrss = item.getMyProducts();
        if (attrss == null || attrss.size() == 0) {
            helper.setGone(R.id.lat_tags,false);
        } else {
            helper.setGone(R.id.lat_tags,true);
            LinearLayout tagParent = helper.getView(R.id.lat_tags);
            for (int i = 0; i <attrss.size(); i++) {
                if (tagParent.getChildCount()==2) break;
                ProductBean productBean = attrss.get(i);
                double score = productBean.getProduct().getScore();
                if (score==0)
                    continue;
                View viewTag = LayoutInflater.from(mContext).inflate(R.layout.answer_list_tag,tagParent,false);
                ((TextView) viewTag.findViewById(R.id.tv_tag)).setText(productBean.getProduct().getName());
                ((RatingBar) viewTag.findViewById(R.id.rating_bar)).setRating((float) (Double.parseDouble(NumberFormatUtils.getNewDouble(score))*5/10));
                ((TextView) viewTag.findViewById(R.id.tv_score)).setText(NumberFormatUtils.getNewDouble(score)+"分");
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.topMargin = (int) ScreenDpiUtils.dp2px(mContext,5);
                tagParent.addView(viewTag,lp);
            }
        }
    }
}
