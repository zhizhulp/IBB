package com.example.ibb.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/8.
 */

public class MyDetailsMoreListAdapter2 extends BaseQuickAdapter<Product,BaseViewHolder> {

    public MyDetailsMoreListAdapter2(int layoutResId, @Nullable List<Product> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {
        Picasso.with(mContext).load(item.getImgs().get(0)).placeholder(R.mipmap.placeholder).into((ImageView) helper.getView(R.id.im_icon));
        helper.setText(R.id.tv_name,item.getName());
        helper.setText(R.id.tv_score,item.getScore()+"分");
        helper.setRating(R.id.rating_bar, (float) (item.getScore()*5/10));
        helper.setText(R.id.tv_price,"￥"+item.getMinPrice()+"-"+item.getMaxPrice());
    }
}
