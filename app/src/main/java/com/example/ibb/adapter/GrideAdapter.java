package com.example.ibb.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.SelectedData;
import com.example.ibb.utils.NumberFormatUtils;
import com.example.ibb.utils.ScreenDpiUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Created by ASUS on 2018/5/30.
 */
public class GrideAdapter extends BaseQuickAdapter<SelectedData.DataBean.MentionProductsBean,BaseViewHolder>{
    public GrideAdapter(int layoutResId, @Nullable List<SelectedData.DataBean.MentionProductsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectedData.DataBean.MentionProductsBean item) {
        List<String> imgs = item.getProduct().getImgs();
        Picasso.with(mContext).load(imgs.get(0)).placeholder(R.mipmap.placeholder).into((ImageView) helper.getView(R.id.image_Recommend_Recycler_goods));
        helper.setText(R.id.text_Recommend_Recycler_goods,item.getProduct().getName());
        helper.setRating(R.id.rating_bar, (float) (item.getProduct().getScore()*5/10));
        helper.setText(R.id.tv_score, NumberFormatUtils.getNewDouble(item.getProduct().getScore())+"分");
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) helper.itemView.getLayoutParams();
        int leftMargin = lp.leftMargin;
        int rightMargin = lp.rightMargin;
        int topMargin = lp.topMargin;
        int bottomMargin = lp.bottomMargin;
        if (helper.getAdapterPosition()==0){
            lp.setMargins((int) ScreenDpiUtils.dp2px(mContext,250), topMargin, rightMargin, bottomMargin);
        }else {
            lp.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        }
    }
}
