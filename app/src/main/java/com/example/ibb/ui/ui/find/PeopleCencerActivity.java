package com.example.ibb.ui.ui.find;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.ibb.R;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.User;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.answered.GoodsDetActivity;
import com.example.ibb.ui.ui.user.UserCenterBaseFragment;
import com.example.ibb.utils.ShareUtils;
import com.example.ibb.utils.ToastUtils;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.nohttp.rest.Request;

public class PeopleCencerActivity extends BaseNetActivity implements View.OnClickListener {

    private ImageView peoplecenter_back_iv;
    private ImageView peoplecenter_share_iv;
    private Button share_return_popup;
    private RadioButton all_rb;
    private RadioButton answer_rb;
    private RadioButton question_rb;
    private RadioButton comment_rb;
    private FrameLayout peoplecenter_fm;
    private UserCenterBaseFragment aFragment;
    private UserCenterBaseFragment bFragment;
    private UserCenterBaseFragment cFragment;
    private UserCenterBaseFragment dFragment;
    private ImageView imHead;
    private TextView tvName;
    private TextView tvWords;
    private CheckBox latFocus;
    private TextView tvFollowC;
    private TextView tvFollowerC;
    private TextView tvLikeC;
    private long userId;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people_cencer);
        userId = getIntent().getLongExtra("userId", -1000);
        initview();
        initdata();
        getUserData();
    }

    protected void initview() {
        //返回键
        peoplecenter_back_iv = (ImageView) findViewById(R.id.peoplecenter_back_iv);
        peoplecenter_back_iv.setOnClickListener(this);
        //分享
        peoplecenter_share_iv = (ImageView) findViewById(R.id.peoplecenter_share_iv);
        peoplecenter_share_iv.setOnClickListener(this);
        //全部
        all_rb = (RadioButton) findViewById(R.id.all_rb);
        all_rb.setOnClickListener(this);
        //TA回答的
        answer_rb = (RadioButton) findViewById(R.id.answer_rb);
        answer_rb.setOnClickListener(this);
        //TA评论的
        comment_rb = (RadioButton) findViewById(R.id.comment_rb);
        comment_rb.setOnClickListener(this);
        //TA提问的
        question_rb = (RadioButton) findViewById(R.id.question_rb);
        question_rb.setOnClickListener(this);
        //Fragemnt
        peoplecenter_fm = (FrameLayout) findViewById(R.id.peoplecenter_fm);
        peoplecenter_fm.setOnClickListener(this);
        //head
        imHead = findViewById(R.id.im_head);
        tvName = findViewById(R.id.tv_name);
        tvWords = findViewById(R.id.tv_words);
        latFocus = findViewById(R.id.lat_focus);
        latFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latFocus.setText(!latFocus.isChecked() ? "十 关注" : "已关注");
                if (latFocus.isChecked()) {
                    requestFocus();
                } else {
                    requestCancelFocus();
                }
            }
        });
        tvFollowC = findViewById(R.id.tv_follow_count);
        tvFollowerC = findViewById(R.id.tv_follower_count);
        tvLikeC = findViewById(R.id.tv_like_count);
    }

    public static void startPersonCenter(Context context, long userId) {
        Intent intent = new Intent(context, PeopleCencerActivity.class);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }

    private void getUserData() {
        Request request = buildRequest(URLApi.getUserinfo, User.class);
        request.add("userId", userId);
        doRequest(0, request);
    }

    private void requestFocus() {
        Request request = buildRequest(URLApi.followUser, HttpBaseEntity.class);
        request.add("userId", userId);
        doRequest(1, request);
    }

    private void requestCancelFocus() {
        Request request = buildRequest(URLApi.unFollowUser, HttpBaseEntity.class);
        request.add("userId", userId);
        doRequest(2, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what == 0) {
            user = (User) data.getData();
            Picasso.with(this).load(user.getPortrait()).placeholder(R.mipmap.b3).into(imHead);
            tvName.setText(user.getNickname());
            tvWords.setText(user.getSignature());
            tvFollowC.setText(user.getFollow() + "");
            tvFollowerC.setText(user.getFollower() + "");
            tvLikeC.setText(user.getLikes() + "");
            int relation = user.getRelation();
            if (relation == 0 || relation == 2) {
                latFocus.setVisibility(View.VISIBLE);
                latFocus.setChecked(false);
            } else if (relation == 4) {
                latFocus.setVisibility(View.GONE);
            } else if (relation == 1 || relation == 3) {
                latFocus.setVisibility(View.VISIBLE);
                latFocus.setChecked(true);
            }
            latFocus.setText(!latFocus.isChecked() ? "十 关注" : "已关注");
        } else if (what == 1) {
            ToastUtils.show("关注用户成功");
        } else if (what == 2) {
            ToastUtils.show("取消关注用户成功");
        }
    }

    @Override
    protected void handleException(int what, Exception e) {
        super.handleException(what, e);
        if (what == 0) finish();
        else if (what == 1 || what == 2) latFocus.setChecked(!latFocus.isChecked());
    }

    @Override
    protected void handleFailed(int what, String error) {
        super.handleFailed(what, error);
        if (what == 0) finish();
        else if (what == 1 || what == 2) latFocus.setChecked(!latFocus.isChecked());
    }

    @Override
    protected void handle401(int what) {
        super.handle401(what);
        if (what == 0) finish();
        else if (what == 1 || what == 2) latFocus.setChecked(!latFocus.isChecked());
    }

    @Override
    protected <T> void handle200False(int what, HttpBaseEntity<T> data) {
        super.handle200False(what, data);
        if (what == 0) finish();
        else if (what == 1 || what == 2) latFocus.setChecked(!latFocus.isChecked());
    }

    protected void initdata() {
        aFragment = UserCenterBaseFragment.getInstance(userId, 0);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.peoplecenter_fm, aFragment);
        transaction.show(aFragment);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        initHide(transaction);
        switch (v.getId()) {
            case R.id.all_rb:
                if (aFragment == null) {
                    aFragment = UserCenterBaseFragment.getInstance(userId, 0);
                    transaction.add(R.id.peoplecenter_fm, aFragment);
                } else {
                    transaction.show(aFragment);
                }
                transaction.commit();
                break;
            case R.id.answer_rb:
                if (bFragment == null) {
                    bFragment = UserCenterBaseFragment.getInstance(userId, 2);
                    transaction.add(R.id.peoplecenter_fm, bFragment);
                } else {
                    transaction.show(bFragment);
                }
                transaction.commit();
                break;
            case R.id.comment_rb:
                if (cFragment == null) {
                    cFragment = UserCenterBaseFragment.getInstance(userId, 3);
                    transaction.add(R.id.peoplecenter_fm, cFragment);
                } else {
                    transaction.show(cFragment);
                }
                transaction.commit();
                break;
            case R.id.question_rb:
                if (dFragment == null) {
                    dFragment = UserCenterBaseFragment.getInstance(userId, 1);
                    transaction.add(R.id.peoplecenter_fm, dFragment);
                } else {
                    transaction.show(dFragment);
                }
                transaction.commit();

                break;
            case R.id.peoplecenter_back_iv:
                finish();
                break;
            case R.id.peoplecenter_share_iv: //分享&举报
                showPopupMenu(peoplecenter_share_iv);
                break;
        }
    }

    /**
     * 隐藏Fragment
     */
    private void initHide(FragmentTransaction transaction) {
        if (aFragment != null) {
            transaction.hide(aFragment);
        }
        if (bFragment != null) {
            transaction.hide(bFragment);
        }
        if (cFragment != null) {
            transaction.hide(cFragment);
        }
        if (dFragment != null) {
            transaction.hide(dFragment);
        }
    }

    /**
     * 菜单
     */
    private void showPopupMenu(View view) {
        PopupMenu popupmenu = new PopupMenu(this, view);
        popupmenu.getMenuInflater().inflate(R.menu.main, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_share) {
                    ShareUtils.share(PeopleCencerActivity.this, "userId?id=" + userId
                            , "爱哔哔|" + user.getNickname() + "的个人主页获得" + user.getLikes() + "个点赞" + "," + user.getFollower() + "个粉丝"
                            , "个人简介:"+user.getSignature()
                            , user.getPortrait());
                    return true;
                } else if (item.getItemId() == R.id.action_jubao) {
                    ReportActivity.startReportFirst(PeopleCencerActivity.this, 2, userId);
                    return true;
                }

                return false;
            }
        });
        popupmenu.show();
    }
}
