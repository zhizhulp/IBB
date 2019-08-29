package com.example.ibb.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.Msg;
import com.example.ibb.utils.ContentUtils;
import com.example.ibb.utils.TimeUtils;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by ASUS on 2018/5/14.
 * 主页-消息列表
 */

public class MsgListAdapter extends BaseQuickAdapter<Msg, BaseViewHolder> {

    public MsgListAdapter(int layoutResId, @Nullable List<Msg> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Msg item) {
        helper.setText(R.id.tv_time, TimeUtils.getTimeFormatText(item.getTime()));
        helper.setText(R.id.tv_msg, getText(item));
        loadIcon(item.getSender().getPortrait(), helper);
    }

    private SpannableStringBuilder getText(Msg item) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        Msg.SenderBean sender = item.getSender();
        String nickname = sender.getNickname();
        String name = "来自" + nickname;
        String conn1 = null;
        String questionStr = item.getQuestion().getTitle();
        String conn2 = "  下我的回答  ";
        Msg.AnswerBean answer = item.getAnswer();
        String answerStr=null;
        if (answer != null) answerStr = ContentUtils.ellipsizeString(answer.getContent());
        switch (item.getType()) {
            case 1://回答了我的问题 question+answer
                conn1 = "  回答了我的问题  ";
                return setSS(name, conn1, questionStr, null, null);
            case 2://关注了我的问题 question
                conn1 = "  关注了我的问题  ";
                return setSS(name, conn1, questionStr, conn2, answerStr);
            case 3://收藏了我的问题 answer
                conn1 = "  收藏了我的问题  ";
                return setSS(name, conn1, questionStr, conn2, answerStr);
            case 4://点赞了我的回答 question+answer
                conn1 = "  点赞了我的回答  ";
                return setSS(name, conn1, questionStr, conn2, answerStr);
            case 5://评论了我的回答 question+answer
                conn1 = "  评论了我的问题  ";
                return setSS(name, conn1, questionStr, conn2, answerStr);
            case 6://回复了xxx下我的评论 question_reply
                conn1 = "  回复了我的问题  ";
                conn2 = "  下我的评论  ";
                return setSS(name, conn1, questionStr, conn2, answerStr);
            default:
                break;
        }

        return builder;
    }

    private SpannableStringBuilder setSS(String name, String conn1, String questionStr, String conn2, String answerStr) {
        if (questionStr.length() > 36) {
            questionStr = questionStr.substring(0, 33) + "...";
        }
        if (answerStr != null && answerStr.length() > 36) {
            answerStr = answerStr.substring(0, 33) + "...";
        }
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.append(name).append(conn1).append(questionStr);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#666666")), 0, name.length()
                , Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")), name.length(), (name + conn1).length()
                , Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), (name + conn1).length(), (name + conn1 + questionStr).length()
                , Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        if (conn2 != null) {
            ssb.append(conn2);
            ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#999999")), (name + conn1 + questionStr).length(),
                    (name + conn1 + questionStr + conn2).length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        if (answerStr != null) {
            ssb.append(answerStr);
            ssb.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), (name + conn1 + questionStr + conn2).length(),
                    ssb.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return ssb;
    }

    private void loadIcon(String iconUrl, BaseViewHolder helper) {
        Picasso.with(mContext).load(iconUrl).placeholder(R.mipmap.placeholder).into((ImageView) helper.getView(R.id.im_head));
    }
}
