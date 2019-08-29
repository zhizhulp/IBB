package com.example.ibb.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.AnswerBean;
import com.example.ibb.entity.FocusBean;
import com.example.ibb.entity.QuestionBean;
import com.example.ibb.entity.ReplyBean;
import com.example.ibb.utils.ContentUtils;
import com.example.ibb.utils.TimeUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ASUS on 2018/5/22.
 */

public class DynamicAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {
    public DynamicAdapter(int layoutResId, @Nullable List<Object> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        if(item instanceof FocusBean){
            FocusBean focusBean = (FocusBean) item;
            int type = focusBean.getType();//123 提问 回答 评论
            switch (type){
                case 1:
                    QuestionBean questionBean = focusBean.getQuestion();
                    setQuestion(questionBean,helper);
                    break;
                case 2:
                    AnswerBean answerBean = focusBean.getAnswer();
                    setAnswer(answerBean,helper);
                    break;
                case 3:
                    ReplyBean replyBean = focusBean.getReply();
                    setReply(replyBean,helper);
                    break;
            }
        }else if(item instanceof QuestionBean){
            setQuestion((QuestionBean) item,helper);
        }else if(item instanceof AnswerBean){
            setAnswer((AnswerBean) item,helper);
        }else if(item instanceof ReplyBean){
            setReply((ReplyBean) item,helper);
        }
    }

    private void setReply(ReplyBean item, BaseViewHolder helper) {
        helper.setText(R.id.tv_type,"TA评论了");
        helper.setText(R.id.tv_time, TimeUtils.getTimeFormatText(item.getTime()));
        helper.setText(R.id.tv_title,item.getAnswer().getTitle());
        helper.setText(R.id.tv_content, ContentUtils.ellipsizeString(item.getContent()));
        helper.setText(R.id.tv_like_count,item.getAnswer().getLikeCount()+"赞");
        helper.setText(R.id.tv_comment_count,item.getAnswer().getReplyCount()+"评");
        try {
            helper.setGone(R.id.tv_tag,true);
            helper.setText(R.id.tv_tag,item.getAnswer().getMyProducts().get(0).getAttrs().get(0).getAttribute().getName());
        }catch (Exception e){
            helper.setGone(R.id.tv_tag,false);
        }
        try {
            helper.setGone(R.id.im_icon,true);
            Picasso.with(mContext).load(item.getAnswer().getMyProducts().get(0).getProduct().getImgs().get(0))
                    .placeholder(R.mipmap.placeholder).into((ImageView) helper.getView(R.id.im_icon));
        }catch (Exception e){
            helper.setGone(R.id.im_icon,false);
        }
    }

    private void setAnswer(AnswerBean item, BaseViewHolder helper) {
        helper.setText(R.id.tv_type,"TA回答了");
        helper.setText(R.id.tv_time, TimeUtils.getTimeFormatText(item.getTime()));
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_content, ContentUtils.ellipsizeString(item.getContent()));
        helper.setText(R.id.tv_like_count,item.getLikeCount()+"赞");
        helper.setText(R.id.tv_comment_count,item.getReplyCount()+"评");
        try {
            helper.setGone(R.id.tv_tag,true);
            helper.setText(R.id.tv_tag,item.getMyProducts().get(0).getAttrs().get(0).getAttribute().getName());
        }catch (Exception e){
            helper.setGone(R.id.tv_tag,false);
        }
        try {
            helper.setGone(R.id.im_icon,true);
            Picasso.with(mContext).load(item.getMyProducts().get(0).getProduct().getImgs().get(0))
            .placeholder(R.mipmap.placeholder).into((ImageView) helper.getView(R.id.im_icon));
        }catch (Exception e){
            helper.setGone(R.id.im_icon,false);
        }
    }

    private void setQuestion(QuestionBean item, BaseViewHolder helper) {
        helper.setText(R.id.tv_type,"TA提问了");
        helper.setText(R.id.tv_time, TimeUtils.getTimeFormatText(item.getTime()));
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_content, ContentUtils.ellipsizeString(item.getContent()));
        helper.setText(R.id.tv_like_count,item.getLikeCount()+"赞");
        helper.setText(R.id.tv_comment_count,item.getReplyCount()+"评");
        try {
            helper.setGone(R.id.tv_tag,true);
            helper.setText(R.id.tv_tag,item.getMentionProducts().get(0).getAttrs().get(0).getAttribute().getName());
        }catch (Exception e){
            helper.setGone(R.id.tv_tag,false);
        }
        try {
            helper.setGone(R.id.im_icon,true);
            Picasso.with(mContext).load(item.getMentionProducts().get(0).getProduct().getImgs().get(0))
                    .placeholder(R.mipmap.placeholder).into((ImageView) helper.getView(R.id.im_icon));
        }catch (Exception e){
            helper.setGone(R.id.im_icon,false);
        }
    }
}
