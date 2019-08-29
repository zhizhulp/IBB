package com.example.ibb.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ibb.R;
import com.example.ibb.entity.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ASUS on 2018/5/22.
 */

public class FansAdapter extends BaseQuickAdapter<User, BaseViewHolder> {
    public FansAdapter(int layoutResId, @Nullable List<User> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, User item) {
        Picasso.with(mContext).load(item.getPortrait()).placeholder(R.mipmap.b3).into((ImageView) helper.getView(R.id.im_head));
        helper.setText(R.id.tv_name, item.getNickname());
        helper.setText(R.id.tv_words, item.getSignature());
        CheckBox rbFocus = (CheckBox) helper.getView(R.id.rb_focus);
        if (rbFocus != null) {
            helper.addOnClickListener(R.id.rb_focus);
            int relation = item.getRelation();
            if (relation == 0 || relation == 2) {
                rbFocus.setVisibility(View.VISIBLE);
                rbFocus.setChecked(false);
            } else if (relation == 4) {
                rbFocus.setVisibility(View.GONE);
            } else if (relation == 1 || relation == 3) {
                rbFocus.setVisibility(View.VISIBLE);
                rbFocus.setChecked(true);
            }
            rbFocus.setText(!rbFocus.isChecked() ? "关注" : "已关注");
        }


    }
}
