package com.example.ibb.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.Product;
import com.example.ibb.entity.ProductBean;
import com.example.ibb.utils.NumberFormatUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 张凯雅 on 2018/3/12.
 */

public class MyShoppingCPAdapter extends BaseQuickAdapter<ProductBean, BaseViewHolder> {


    public MyShoppingCPAdapter(int layoutResId, @Nullable List<ProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductBean item) {
        Picasso.with(mContext).load(item.getProduct().getImgs().get(0))
                .placeholder(R.mipmap.placeholder).into((ImageView) helper.getView(R.id.im_icon));
        helper.setText(R.id.tv_name, item.getProduct().getName());
        helper.setRating(R.id.rating_bar, item.getScore() * 5 / 10);
        helper.setText(R.id.tv_score, NumberFormatUtils.getNewDouble(item.getScore()) + "分");
        List<Product.BaseAttributeBean> baseAttribute = item.getProduct().getBaseAttribute();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < baseAttribute.size(); i++) {
            Product.BaseAttributeBean baseAttributeBean = baseAttribute.get(i);
            sb.append(baseAttributeBean.getName()).append(":").append(NumberFormatUtils.getNewDouble(baseAttributeBean.getScore())).append("  ");
        }
        helper.setText(R.id.tv_tags, sb.toString());
    }
}
