package com.example.ibb.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ibb.R;
import com.example.ibb.entity.SelectedData;
import com.example.ibb.ui.ui.answered.GoodsDetActivity;
import com.example.ibb.utils.ContentUtils;
import com.example.ibb.utils.FinalUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2018/5/30.
 */

public class HomeRecommendAdapter extends BaseMultiItemQuickAdapter<SelectedData.DataBean, BaseViewHolder> {
    private int goodsClickPosition = -1;
    private long startTime;

    public HomeRecommendAdapter(List<SelectedData.DataBean> data) {
        super(data);
        addItemType(1, R.layout.home_qa_item);
        addItemType(2, R.layout.recommend_recycler_item2);
        addItemType(3, R.layout.recommend_recycler_item5);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectedData.DataBean item) {
        switch (helper.getItemViewType()) {
            case 1:
                RecyclerView recyclerView = helper.getView(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
                final List<SelectedData.DataBean.MentionProductsBean> mentionProducts = item.getMentionProducts();
                GrideAdapter adapter = new GrideAdapter(R.layout.recommenditem_recycler_item, mentionProducts);
                recyclerView.addOnItemTouchListener(new com.chad.library.adapter.base.listener.OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if (goodsClickPosition == position && System.currentTimeMillis() - startTime < 200) return;
                        goodsClickPosition = position;
                        startTime=System.currentTimeMillis();
                        GoodsDetActivity.startGoodsDet(mContext, mentionProducts.get(position).getProduct().getId());
                    }
                });
                recyclerView.setAdapter(adapter);
                helper.setText(R.id.tv_title, item.getTitle());
                helper.addOnClickListener(R.id.tv_title);
                helper.addOnClickListener(R.id.tv_content);
                helper.addOnClickListener(R.id.tv_count);
                helper.setText(R.id.tv_content, ContentUtils.ellipsizeString(item.getContent()));
                helper.setText(R.id.tv_count, item.getLikeCount() + "赞 . " + item.getReplyCount() + "评");
                break;
            case 2:
                String s = ContentUtils.buildString(item.getContent());
                String[] split = s.split("\\.\\.\\.");
                for (int i = 0; i < split.length; i++) {
                    if (split[i].contains(FinalUtils.IMGS_BASE)) {
                        Picasso.with(mContext).load(split[i]).placeholder(R.mipmap.placeholder).into((ImageView) helper.getView(R.id.image_Recommend_Two));
                        break;
                    }
                }
                helper.setText(R.id.text_Recommend_Title_Two, item.getTitle());
                helper.setText(R.id.text_Recommend_Content_Two, ContentUtils.ellipsizeString(item.getContent()));
                helper.setText(R.id.text_Recommend_likeCount_Two, item.getLikeCount() + "赞");
                helper.setText(R.id.text_Recommend_replyCount_Two, item.getReplyCount() + "评");
                break;
            case 3:
                helper.setText(R.id.text_Recommend_Title_Five, item.getTitle());
                helper.setText(R.id.text_Recommend_Content_Five, ContentUtils.ellipsizeString(item.getContent()));
                helper.setText(R.id.text_Recommend_likeCount_Five, item.getLikeCount() + "赞");
                helper.setText(R.id.text_Recommend_replyCount_Five, item.getReplyCount() + "评");
                break;
        }
    }

    private List<String> splitString(String str) {
        List<String> imageUrlLists = new ArrayList<String>();
        int start_pos = 0;
        int end_pos = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            if (str.indexOf("http:", start_pos) != -1) {
                start_pos = str.indexOf("http:", start_pos);
            } else {
                break;
            }
            sb.append(str.substring(end_pos, start_pos));
            if (str.indexOf(".jpg", start_pos) != -1) {
                end_pos = str.indexOf(".jpg", start_pos) + 4;
            } else if (str.indexOf(".png", start_pos) != -1) {
                end_pos = str.indexOf(".png", start_pos) + 4;
            } else if (str.indexOf(".jpeg", start_pos) != -1) {
                end_pos = str.indexOf(".jpeg", start_pos) + 5;
            }
            imageUrlLists.add(str.substring(start_pos, end_pos));
            start_pos = end_pos;
        }
        sb.append(str.substring(end_pos, str.length()));
        return imageUrlLists;
    }
}
