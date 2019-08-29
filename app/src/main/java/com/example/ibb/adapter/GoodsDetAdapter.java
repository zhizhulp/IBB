package com.example.ibb.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.CategoryBean;
import com.example.ibb.entity.RelatedQ2A;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ASUS on 2018/5/16.
 */

public class GoodsDetAdapter extends BaseQuickAdapter<RelatedQ2A, BaseViewHolder> {
    public GoodsDetAdapter(int layoutResId, @Nullable List<RelatedQ2A> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RelatedQ2A item) {
        if (item.getQuestion() == null) {
            helper.setGone(R.id.lat_all, false);
        } else {
            helper.setVisible(R.id.lat_all, true);
            helper.setText(R.id.tv_name, item.getQuestion().getTitle());
            helper.setText(R.id.tv_like_count, item.getQuestion().getLikeCount() + "赞");
            helper.setText(R.id.tv_commit_count, item.getQuestion().getReplyCount() + "评");
            ImageView imageView = (ImageView) helper.getView(R.id.im_icon);
            String content = item.getQuestion().getContent();
            String httpUrl = getHttpUrl(content);
            if (httpUrl != null) {
                Picasso.with(mContext).load(httpUrl).placeholder(R.mipmap.placeholder).into(imageView);
                imageView.setVisibility(View.VISIBLE);
            } else {
                imageView.setVisibility(View.GONE);
            }
            List<CategoryBean> categorys = item.getQuestion().getCategorys();
            if (categorys == null || categorys.size() == 0){
                helper.setGone(R.id.tv_tag,false);
            }else{
                helper.setGone(R.id.tv_tag,true);
                helper.setText(R.id.tv_tag, categorys.get(0).getName());
            }
        }
    }

    private String getHttpUrl(String content) {
        if (content==null) return null;
        int indexOf = content.indexOf("http://");
        if (indexOf == -1) {
            return null;
        } else {
            int indexOfJpg = content.indexOf("jpg");
            if (indexOfJpg == -1) {
                return null;
            } else {
                return content.substring(indexOf, indexOfJpg + "jpg".length());
            }
        }
    }
}
