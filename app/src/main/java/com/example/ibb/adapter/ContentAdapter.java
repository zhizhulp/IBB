package com.example.ibb.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.AnsContent;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ASUS on 2018/5/29.
 */

public class ContentAdapter extends BaseQuickAdapter<AnsContent,BaseViewHolder> {

    public ContentAdapter(int layoutResId, @Nullable List<AnsContent> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AnsContent item) {
        if (item.url==null){
            helper.setGone(R.id.im_icon,false);
            helper.setGone(R.id.tv_content,true);
            helper.setText(R.id.tv_content,item.text);
        }else {
            helper.setGone(R.id.im_icon,true);
            helper.setGone(R.id.tv_content,false);
            Picasso.with(mContext).load(item.url).placeholder(R.mipmap.placeholder).into((ImageView) helper.getView(R.id.im_icon));
        }
    }
}
