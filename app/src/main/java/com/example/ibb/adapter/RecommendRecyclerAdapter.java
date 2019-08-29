package com.example.ibb.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ibb.R;
import com.example.ibb.app.MyApp;
import com.example.ibb.entity.SelectedData;
import com.example.ibb.ui.ui.answered.GoodsDetActivity;
import com.example.ibb.ui.ui.answered.ProblemDetailsActivity;
import com.example.ibb.ui.ui.answered.WDTDActivity;
import com.example.ibb.ui.ui.find.CommodityActivity;
import com.example.ibb.utils.ContentUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 张凯雅 on 2018/1/30.
 */

public class RecommendRecyclerAdapter extends RecyclerView.Adapter implements RecommendItemRecyclerAdapter.OnRecyclerViewListener {
    private Context context;
    private int item_width;
    private int item_width1;
    private List<SelectedData.DataBean> data;

    private List<String> imageUrlLists;
    private String imagContent = "";
    private List<SelectedData.DataBean.MentionProductsBean> mentionProducts;
    private String content;

    public RecommendRecyclerAdapter(Context context, int item_width, int item_width1, List<SelectedData.DataBean> data) {
        this.context = context;
        this.item_width = item_width;
        this.item_width1 = item_width1;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        //三种格式  1 : 显示商品 2 : 显示图片 3 : 不显示图片不显示商品
        mentionProducts = data.get(position).getMentionProducts();
        content = data.get(position).getContent();

        if (mentionProducts.size() > 0) {
            return 1;
        } else if (null != content && !content.equals("")) {
            return 2;
        } else {
            return 3;
        }

    }

    private void splitString(String str) {
        imageUrlLists = new ArrayList<String>();
        int start_pos = 0;
        int end_pos = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 2; i++) {
            if (str.indexOf("http:", start_pos) != -1) {
                start_pos = str.indexOf("http:", start_pos);
            } else {

                imagContent = str;

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
        imagContent = sb.toString();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == 1) {
            View view = View.inflate(context, R.layout.recommend_recycler_item, null);
            holder = new RecommendRecyclerHolder(view);
            return holder;
        } else if (viewType == 2) {
            View view = View.inflate(context, R.layout.recommend_recycler_item2, null);
            holder = new RecommendRecyclerHolderTwo(view);
            return holder;
        } else if (viewType == 3) {
            View view = View.inflate(context, R.layout.recommend_recycler_item5, null);
            holder = new RecommendRecyclerHolderFive(view);
            return holder;
        }
        return null;
    }

    @Override
    public void onClickGoods(int position, SelectedData.DataBean.MentionProductsBean bean) {
        GoodsDetActivity.startGoodsDet(context, bean.getProduct().getId());
    }

    @Override
    public void onClickMore(int position) {
        /*Intent intent = new Intent(context, WDTDActivity.class);
        context.startActivity(intent);*/
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof RecommendRecyclerHolder) {
            //第一个布局：有左滑商品
            List<SelectedData.DataBean.MentionProductsBean> mentionProducts = data.get(position).getMentionProducts();
            ((RecommendRecyclerHolder) holder).recommend_rv.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            RecommendItemRecyclerAdapter itemrecommendRecyclerAdapter = new RecommendItemRecyclerAdapter(MyApp.activity, item_width, item_width1, mentionProducts);
            ((RecommendRecyclerHolder) holder).recommend_rv.setAdapter(itemrecommendRecyclerAdapter);
            itemrecommendRecyclerAdapter.setitemClick(this);
            ((RecommendRecyclerHolder) holder).text_Title.setText(data.get(position).getTitle());
            if (TextUtils.isEmpty(data.get(position).getContent())) {
                ((RecommendRecyclerHolder) holder).text_Content.setVisibility(View.GONE);
            } else {
                ((RecommendRecyclerHolder) holder).text_Content.setVisibility(View.VISIBLE);
                ((RecommendRecyclerHolder) holder).text_Content.setText(ContentUtils.ellipsizeString(data.get(position).getContent()));
            }
            ((RecommendRecyclerHolder) holder).text_likeCount.setText(data.get(position).getLikeCount() + "赞");
            ((RecommendRecyclerHolder) holder).text_replyCount.setText(data.get(position).getReplyCount() + "评");
        } else if (getItemViewType(position) == 2) {
            //第二个布局：有图片
            splitString(content);
            data.get(position).setContent(imagContent);
            if (imageUrlLists.size() > 0) {
                for (int i = 0; i < imageUrlLists.size(); i++) {
                    String ima_url = imageUrlLists.get(i);
                    String replace = ima_url.replace("image.", "www.");
                    Glide.with(context).load(replace).into(((RecommendRecyclerHolderTwo) holder).mImage_Two);
                }
            } else {
                ((RecommendRecyclerHolderTwo) holder).mImage_Two.setVisibility(View.GONE);
            }
            ((RecommendRecyclerHolderTwo) holder).text_Title.setText(data.get(position).getTitle());
            ((RecommendRecyclerHolderTwo) holder).text_Content.setText(ContentUtils.ellipsizeString(data.get(position).getContent()));
            ((RecommendRecyclerHolderTwo) holder).text_likeCount.setText(data.get(position).getLikeCount() + "赞");
            ((RecommendRecyclerHolderTwo) holder).text_replyCount.setText(data.get(position).getReplyCount() + "评");
        } else if (holder instanceof RecommendRecyclerHolderFive) {
            //第三个布局：没有图片没有商品
            ((RecommendRecyclerHolderFive) holder).text_Title.setText(data.get(position).getTitle());
            ((RecommendRecyclerHolderFive) holder).text_Content.setText(ContentUtils.ellipsizeString(data.get(position).getContent()));
            ((RecommendRecyclerHolderFive) holder).text_likeCount.setText(data.get(position).getLikeCount() + "赞");
            ((RecommendRecyclerHolderFive) holder).text_replyCount.setText(data.get(position).getReplyCount() + "评");
        }
    }

    @Override
    public int getItemCount() {

        return data.isEmpty() ? 0 : data.size();
    }


    class RecommendRecyclerHolder extends RecyclerView.ViewHolder {

        TextView text_Title, text_Content, text_likeCount, text_replyCount;
        RecyclerView recommend_rv;

        public RecommendRecyclerHolder(View itemView) {
            super(itemView);
            text_Title = itemView.findViewById(R.id.text_Recommend_Title_One);
            text_Content = itemView.findViewById(R.id.text_Recommend_Content_One);
            text_likeCount = itemView.findViewById(R.id.text_Recommend_likeCount_One);
            text_replyCount = itemView.findViewById(R.id.text_Recommend_replyCount_One);
            recommend_rv = itemView.findViewById(R.id.text_Recommend_Rv_One);
        }
    }

    class RecommendRecyclerHolderTwo extends RecyclerView.ViewHolder {

        TextView text_Title, text_Content, text_likeCount, text_replyCount;
        private ImageView mImage_Two;

        public RecommendRecyclerHolderTwo(View itemView) {
            super(itemView);
            text_Title = itemView.findViewById(R.id.text_Recommend_Title_Two);
            text_Content = itemView.findViewById(R.id.text_Recommend_Content_Two);
            text_likeCount = itemView.findViewById(R.id.text_Recommend_likeCount_Two);
            text_replyCount = itemView.findViewById(R.id.text_Recommend_replyCount_Two);
            mImage_Two = itemView.findViewById(R.id.image_Recommend_Two);
        }
    }

    class RecommendRecyclerHolderFive extends RecyclerView.ViewHolder {

        TextView text_Title, text_Content, text_likeCount, text_replyCount;

        public RecommendRecyclerHolderFive(View itemView) {
            super(itemView);
            text_Title = itemView.findViewById(R.id.text_Recommend_Title_Five);
            text_Content = itemView.findViewById(R.id.text_Recommend_Content_Five);
            text_likeCount = itemView.findViewById(R.id.text_Recommend_likeCount_Five);
            text_replyCount = itemView.findViewById(R.id.text_Recommend_replyCount_Five);
        }
    }
}
