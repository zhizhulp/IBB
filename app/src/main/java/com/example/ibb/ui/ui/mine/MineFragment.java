package com.example.ibb.ui.ui.mine;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ibb.R;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.base.BaseNetFragment;
import com.example.ibb.entity.User;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.login_register.StartRegisterActivity;
import com.example.ibb.utils.AppConfig;
import com.example.ibb.utils.ShareUtils;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.nohttp.rest.Request;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseNetFragment implements View.OnClickListener {
    private LinearLayout mine_fans;
    private LinearLayout mine_guanzhu;
    private LinearLayout mine_question;
    private LinearLayout mine_answerd;
    private RelativeLayout mine_attention_relative;
    private RelativeLayout mine_follow_relative;
    private RelativeLayout mine_collection_relative;
    private RelativeLayout mine_setting_relative;
    private RelativeLayout mine_help_relative;
    private ImageView mine_header;
    private LinearLayout mine_login;
    private LinearLayout mine_login_rv;
    private TextView setname_text;
    private TextView setqianming_text;
    private RelativeLayout mine_share;
    private Button share_return_popup;
    private TextView tvFollowC;
    private TextView tvFollowerC;
    private TextView tvLikeC;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initview(view);
    }

    protected void initview(View view) {
        mine_question = (LinearLayout) view.findViewById(R.id.mine_question);//我的提问
        mine_answerd = (LinearLayout) view.findViewById(R.id.mine_answerd);//我的回答

        mine_header = (ImageView) view.findViewById(R.id.mine_header);//用户头像
        setname_text = view.findViewById(R.id.setname_text);//用户昵称
        setqianming_text = view.findViewById(R.id.setqianming_text);//用户个性话术
        tvFollowC = view.findViewById(R.id.tv_follow_count);
        tvFollowerC = view.findViewById(R.id.tv_follower_count);
        tvLikeC = view.findViewById(R.id.tv_like_count);

        mine_attention_relative = (RelativeLayout) view.findViewById(R.id.mine_attention_relative);
        mine_attention_relative.setOnClickListener(this);

        mine_header = view.findViewById(R.id.mine_header);
        mine_header.setOnClickListener(this);

        mine_follow_relative = (RelativeLayout) view.findViewById(R.id.mine_follow_relative);
        mine_follow_relative.setOnClickListener(this);

        mine_collection_relative = (RelativeLayout) view.findViewById(R.id.mine_collection_relative);
        mine_collection_relative.setOnClickListener(this);

        mine_setting_relative = (RelativeLayout) view.findViewById(R.id.mine_setting_relative);
        mine_setting_relative.setOnClickListener(this);

        mine_help_relative = (RelativeLayout) view.findViewById(R.id.mine_help_relative);
        mine_help_relative.setOnClickListener(this);

        mine_login = (LinearLayout) view.findViewById(R.id.mine_login);
        mine_login_rv = view.findViewById(R.id.mine_login_rv);

        mine_login.setOnClickListener(this);
        mine_login_rv.setOnClickListener(this);

        //分享IBB
        mine_share = (RelativeLayout) view.findViewById(R.id.mine_share);
        mine_share.setOnClickListener(this);

        mine_question.setOnClickListener(this);
        mine_answerd.setOnClickListener(this);

        mine_fans = (LinearLayout) view.findViewById(R.id.mine_fans);
        mine_fans.setOnClickListener(this);

        mine_guanzhu = (LinearLayout) view.findViewById(R.id.mine_guanzhu);
        mine_guanzhu.setOnClickListener(this);
        getUserData();
    }

    private void getUserData() {
        Request request = buildRequest(URLApi.getUserinfo, User.class);
        doRequest(0, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what == 0) {
            AppConfig.getInstance().putUser(((User) data.getData()));
            setUser();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setUser();
    }

    private void setUser() {
        AppConfig instance = AppConfig.getInstance();
        String token = instance.getString("token", null);
        if (token != null) {//设置用户信息
            mine_login.setVisibility(View.GONE);
            mine_login_rv.setVisibility(View.VISIBLE);
            Picasso.with(getActivity()).load(instance.getString("portrait", null)).placeholder(R.mipmap.b3).into(mine_header);
            setname_text.setText(instance.getString("nickname", null));
            setqianming_text.setText(instance.getString("signature", null));
            tvFollowC.setText(instance.getInt("follow", 0) + "");
            tvFollowerC.setText(instance.getInt("follower", 0) + "");
            tvLikeC.setText(instance.getInt("likes", 0) + "");
        } else {
            mine_login.setVisibility(View.VISIBLE);
            mine_login_rv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_login://登陆
                startActivity(new Intent(MyApp.activity, StartRegisterActivity.class));
                break;
            case R.id.mine_question://我的问题
                QuestionActivity.startMine(getActivity(), 0);
                break;
            case R.id.mine_answerd://我的回答
                QuestionActivity.startMine(getActivity(), 1);
                break;
            case R.id.mine_share://分享IBB
                ShareUtils.share((BaseNetActivity) getActivity(),false,"http://homepage.ibbtech.cn/","爱哔哔","商品咋样，一问便知",null);
                break;
            //点击头像
            case R.id.mine_header:
                startActivity(new Intent(MyApp.activity, PersonalInformationActivity.class));
                break;
            //我关注的人
            case R.id.mine_guanzhu:
                startActivity(new Intent(MyApp.activity, MineGuanZhuActivity.class));
                break;
            //我的粉丝
            case R.id.mine_fans:
                startActivity(new Intent(MyApp.activity, MineFansActivity.class));
                break;
            //我关注的问题
            case R.id.mine_attention_relative:
                startActivity(new Intent(MyApp.activity, MineAttentionActivity.class));
                break;
            //我收藏的回答
            case R.id.mine_follow_relative:
                startActivity(new Intent(MyApp.activity, MineFollowActivity.class));
                break;
            //我收藏的商品
            case R.id.mine_collection_relative:
                startActivity(new Intent(MyApp.activity, MineCollectionActivity.class));
                break;
            //设置
            case R.id.mine_setting_relative:
                startActivity(new Intent(MyApp.activity, MineSettingActivity.class));
                break;
            //帮助与反馈
            case R.id.mine_help_relative:
                startActivity(new Intent(MyApp.activity, MineHelpActivity.class));
                break;

        }
    }


}
