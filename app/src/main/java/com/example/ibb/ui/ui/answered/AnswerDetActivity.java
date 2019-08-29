package com.example.ibb.ui.ui.answered;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ibb.R;
import com.example.ibb.adapter.ContentAdapter;
import com.example.ibb.adapter.MyShoppingCPAdapter;
import com.example.ibb.adapter.MyShoppingRecyclerAdapter;
import com.example.ibb.adapter.MyShoppingRecyclerAdapter2;
import com.example.ibb.apimanager.URLApi;
import com.example.ibb.app.MyApp;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.entity.AnsContent;
import com.example.ibb.entity.AnswerBean;
import com.example.ibb.entity.ProductBean;
import com.example.ibb.entity.QuestionBean;
import com.example.ibb.entity.ReplyBean;
import com.example.ibb.entity.base_entity.HttpBaseEntity;
import com.example.ibb.ui.ui.find.ReportActivity;
import com.example.ibb.utils.ContentUtils;
import com.example.ibb.utils.FinalUtils;
import com.example.ibb.utils.ScreenDpiUtils;
import com.example.ibb.utils.ShareUtils;
import com.example.ibb.utils.ToastUtils;
import com.squareup.picasso.Picasso;
import com.yanzhenjie.nohttp.rest.Request;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AnswerDetActivity extends BaseNetActivity implements View.OnClickListener {
    private List<ProductBean> datas = new ArrayList<>();
    private List<QuestionBean> q2as = new ArrayList<>();
    private RecyclerView shopping_recy;
    private RecyclerView shopping_recyview2;
    private MyShoppingRecyclerAdapter2 recyclerAdapter2;
    private ImageView shoppingdetails_back_iv;
    private RecyclerView shoppingcp_recy;
    private ImageView shoppingdetails_pl_iv;
    private ImageView shoppingdetils_share_iv;
    private TextView tvTitle;
    private ImageView imHead;
    private TextView tvUserName;
    private TextView tvUserWords;
    private CheckBox rbFocus;
    private TextView tvContent;
    private int id;
    private String title;
    private TextView tvSeeAllAns;
    private AnswerBean answerBean;
    private RadioButton rbLike;
    private RadioButton rbUnLike;
    private View latRalatedProductHead;
    private View lineRelated;
    private View latRalatedQ2A;
    private View lineRalatedQ2A;
    private View latRalatefComments;
    private View latRalatedCommentsFooter;
    private View lineComment;
    private CheckBox cbCollect;
    private CheckBox cbLike;
    private Dialog dialog;
    private TextView tvTime;
    private RecyclerView contentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_details);
        id = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        initview();
        requestAnswerDet();
        requestCommentsList();
    }

    protected void initview() {
        shopping_recy = (RecyclerView) findViewById(R.id.shopping_recy);
        latRalatedQ2A = findViewById(R.id.tv_ralated_q2a);
        lineRalatedQ2A = findViewById(R.id.line_ralated_q2a);

        shopping_recyview2 = (RecyclerView) findViewById(R.id.shopping_recyview2);
        latRalatefComments = findViewById(R.id.tv_ralated_comments);
        latRalatedCommentsFooter = findViewById(R.id.tv_comments_footer);
        latRalatedCommentsFooter.setOnClickListener(this);
        lineComment = findViewById(R.id.line_commets);

        shoppingcp_recy = (RecyclerView) findViewById(R.id.shoppingcp_recy);
        latRalatedProductHead = findViewById(R.id.tv_related_product_head);
        lineRelated = findViewById(R.id.line_related_product);

        //返回按钮
        shoppingdetails_back_iv = (ImageView) findViewById(R.id.shoppingdetails_back_iv);
        shoppingdetails_back_iv.setOnClickListener(this);
        //评论
        shoppingdetails_pl_iv = (ImageView) findViewById(R.id.shoppingdetails_pl_iv);
        shoppingdetails_pl_iv.setOnClickListener(this);
        //分享
        shoppingdetils_share_iv = (ImageView) findViewById(R.id.shoppingdetils_share_iv);
        shoppingdetils_share_iv.setOnClickListener(this);
        //head
        tvTitle = findViewById(R.id.tv_title);
        tvSeeAllAns = findViewById(R.id.tv_go_all_question);
        tvSeeAllAns.setOnClickListener(this);
        imHead = findViewById(R.id.im_head);
        tvUserName = findViewById(R.id.tv_user_name);
        tvUserWords = findViewById(R.id.tv_user_words);
        rbFocus = findViewById(R.id.rb_focus);
        rbFocus.setOnClickListener(this);
        tvContent = findViewById(R.id.tv_content);
        //点赞&踩
        rbLike = findViewById(R.id.rb_like);
        rbUnLike = findViewById(R.id.rb_un_like);
        rbLike.setOnClickListener(this);
        rbUnLike.setOnClickListener(this);
        //收藏/取消收藏
        cbCollect = findViewById(R.id.cb_collect);
        cbCollect.setOnClickListener(this);
        //底部点赞&踩
        cbLike = findViewById(R.id.cb_like);
        cbLike.setOnClickListener(this);
        //写评论
        findViewById(R.id.tv_input).setOnClickListener(this);
        //底部分享
        findViewById(R.id.im_go_share).setOnClickListener(this);
        //时间
        tvTime = findViewById(R.id.tv_time);
        //内容列表
        contentList = findViewById(R.id.content_list);
    }

    public static void startAnsDet(Context context, int id, String title) {
        Intent intent = new Intent(context, AnswerDetActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    private void requestAnswerDet() {
        Request request = buildRequest(URLApi.answerDet, AnswerBean.class);
        request.add("answerId", id);
        doRequest(0, request);
    }

    private void requestFocus() {
        Request request = buildRequest(URLApi.followUser, HttpBaseEntity.class);
        request.add("userId", answerBean.getUser().getId());
        doRequest(1, request);
    }

    private void requestCancelFocus() {
        Request request = buildRequest(URLApi.unFollowUser, HttpBaseEntity.class);
        request.add("userId", answerBean.getUser().getId());
        doRequest(2, request);
    }

    private void requestLike() {
        Request request = buildRequest(URLApi.likeAns, HttpBaseEntity.class);
        request.add("answerId", id);
        doRequest(3, request);
    }

    private void requestUnLike() {
        Request request = buildRequest(URLApi.unLikeAns, HttpBaseEntity.class);
        request.add("answerId", id);
        doRequest(4, request);
    }

    private void requestCommentsList() {
        Request request = buildPostListRequest(URLApi.getCommentsList, ReplyBean.class);
        request.add("answerId", id);
        request.add("index", 1);
        doRequest(5, request);
    }

    private void requestCollect() {
        Request request = buildRequest(URLApi.favorite, HttpBaseEntity.class);
        request.add("answerId", id);
        doRequest(6, request);
    }

    private void requestCancelCollect() {
        Request request = buildRequest(URLApi.deleteFavorite, HttpBaseEntity.class);
        request.add("answerId", id);
        doRequest(7, request);
    }

    private void requestApplyComment(CharSequence content) {
        Request request = buildRequest(URLApi.applyComment, ReplyBean.class);
        request.add("answerId", id);
        request.add("content", content.toString());
        doRequest(8, request);
    }

    @Override
    protected <T> void handle200True(int what, HttpBaseEntity<T> data) {
        super.handle200True(what, data);
        if (what == 0) {
            answerBean = (AnswerBean) data.getData();
            tvTime.setText(new SimpleDateFormat("MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(answerBean.getTime())));
            tvTitle.setText(title);
            rbLike.setChecked(answerBean.isLike());
            rbUnLike.setChecked(!rbLike.isChecked());
            cbCollect.setChecked(answerBean.isFavorite());
            cbLike.setChecked(answerBean.isLike());
            Picasso.with(this).load(answerBean.getUser().getPortrait()).placeholder(R.mipmap.b3).into(imHead);
            tvUserName.setText(answerBean.getUser().getNickname());
            tvUserWords.setText(answerBean.getUser().getSignature());
            String content = answerBean.getContent();
            parseAndSetContent(content);
            tvSeeAllAns.setText("查看全部" + answerBean.getReplyCount() + "个回答");
            int relation = answerBean.getUser().getRelation();
            setFocus(relation);

            //提到的商品
            List<ProductBean> myProducts = answerBean.getMyProducts();
            if (myProducts == null || myProducts.size() == 0) {
                latRalatedProductHead.setVisibility(View.GONE);
                lineRelated.setVisibility(View.GONE);
            } else {
                latRalatedProductHead.setVisibility(View.VISIBLE);
                lineRelated.setVisibility(View.VISIBLE);
                datas.clear();
                datas.addAll(myProducts);
                MyShoppingCPAdapter shoppingCPAdapter = new MyShoppingCPAdapter(R.layout.shoppingcp_recycler_item, datas);
                shoppingcp_recy.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        GoodsDetActivity.startGoodsDet(AnswerDetActivity.this, datas.get(position).getProductId());
                    }
                });
                shoppingcp_recy.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                shoppingcp_recy.setAdapter(shoppingCPAdapter);
            }
            //相关问答
            List<QuestionBean> relateQuestions = answerBean.getRelateQuestions();
            if (relateQuestions == null || relateQuestions.size() == 0) {
                latRalatedQ2A.setVisibility(View.GONE);
                lineRalatedQ2A.setVisibility(View.GONE);
            } else {
                latRalatedQ2A.setVisibility(View.VISIBLE);
                lineRalatedQ2A.setVisibility(View.VISIBLE);
                q2as.clear();
                q2as.addAll(relateQuestions);
                MyShoppingRecyclerAdapter shoppingCPAdapter = new MyShoppingRecyclerAdapter(R.layout.shopping_item, q2as);
                shopping_recy.setLayoutManager(new LinearLayoutManager(this){
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }
                });
                shopping_recy.setAdapter(shoppingCPAdapter);
                shopping_recy.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ProblemDetailsActivity.startQuestionDet(AnswerDetActivity.this, q2as.get(position).getId());
                    }
                });
            }
        } else if (what == 1) {
            ToastUtils.show("关注用户成功");
        } else if (what == 2) {
            ToastUtils.show("取消关注用户成功");
        } else if (what == 3) {
            ToastUtils.show("点赞成功");
            answerBean.setLike(true);
            cbLike.setChecked(true);
            rbLike.setChecked(true);
            rbUnLike.setChecked(false);
        } else if (what == 4) {
            answerBean.setLike(false);
            ToastUtils.show("取消点赞");
            cbLike.setChecked(false);
            rbLike.setChecked(false);
            rbUnLike.setChecked(true);
        } else if (what == 5) {//评论列表
            List<ReplyBean> answerBeans = (List<ReplyBean>) data.getData();
            if (answerBeans == null || answerBeans.size() == 0) {
                latRalatefComments.setVisibility(View.GONE);
                lineComment.setVisibility(View.GONE);
                latRalatedCommentsFooter.setVisibility(View.GONE);
            } else {
                latRalatefComments.setVisibility(View.VISIBLE);
                lineComment.setVisibility(View.VISIBLE);
                if (answerBeans.size() > 2) {
                    for (int i = 0; i < answerBeans.size(); i++) {
                        if (i > 2) answerBeans.remove(i);
                    }
                    latRalatedCommentsFooter.setVisibility(View.VISIBLE);
                } else {
                    latRalatedCommentsFooter.setVisibility(View.GONE);
                }
                recyclerAdapter2 = new MyShoppingRecyclerAdapter2(R.layout.shopping_item2, answerBeans);
                shopping_recyview2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                shopping_recyview2.setAdapter(recyclerAdapter2);
            }
        } else if (what == 6) {
            answerBean.setFavorite(true);
            ToastUtils.show("收藏回答");
        } else if (what == 7) {
            answerBean.setFavorite(false);
            ToastUtils.show("取消收藏回答");
        } else if (what == 8) {
            if (dialog != null && dialog.isShowing()) dialog.dismiss();
            ToastUtils.show("发布成功");
            requestCommentsList();
        }
    }

    private void setFocus(int relation) {
        if (relation == 0 || relation == 2) {
            rbFocus.setVisibility(View.VISIBLE);
            rbFocus.setChecked(false);
        } else if (relation == 4) {
            rbFocus.setVisibility(View.GONE);
        } else if (relation == 1 || relation == 3) {
            rbFocus.setVisibility(View.VISIBLE);
            rbFocus.setChecked(true);
        }
        rbFocus.setText(!rbFocus.isChecked() ? "十 关注" : "已关注");
    }

    @Override
    protected <T> void handle200False(int what, HttpBaseEntity<T> data) {
        super.handle200False(what, data);
        //if (what == 1 || what == 2) rbFocus.setChecked(!rbFocus.isChecked());

    }

    @Override
    protected void handle401(int what) {
        super.handle401(what);
        //if (what == 1 || what == 2) rbFocus.setChecked(!rbFocus.isChecked());
    }

    @Override
    protected void handleFailed(int what, String error) {
        super.handleFailed(what, error);
        //if (what == 1 || what == 2) rbFocus.setChecked(!rbFocus.isChecked());
    }

    @Override
    protected void handleException(int what, Exception e) {
        super.handleException(what, e);
        //if (what == 1 || what == 2) rbFocus.setChecked(!rbFocus.isChecked());
    }

    private void parseAndSetContent(String content) {
        Log.d(TAG, "parseAndSetContent: " + content);
        List<AnsContent> contents = new ArrayList<>();
        if (!content.contains(FinalUtils.IMGS_BASE)) {
            AnsContent a = new AnsContent();
            a.text = content;
            contents.add(a);
        } else {
            final String newContent = ContentUtils.buildString(content);
            Log.d(TAG, "parseAndSetContent_new: " + newContent);

            String[] split = newContent.split("\\.\\.\\.");
            for (int i = 0; i < split.length; i++) {
                final String ss = split[i];
                if (ss.contains(FinalUtils.IMGS_BASE)) {
                    AnsContent ansContent = new AnsContent();
                    ansContent.url = ss;
                    contents.add(ansContent);
                } else {
                    AnsContent ansContent = new AnsContent();
                    ansContent.text = ss;
                    contents.add(ansContent);
                }
            }
        }
        ContentAdapter adapter = new ContentAdapter(R.layout.ans_content, contents);
        contentList.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        contentList.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shoppingdetails_back_iv:
                finish();
                break;
            case R.id.shoppingdetails_pl_iv:
                AllPingLunActivity.startAll(this, id);
                break;
            case R.id.tv_comments_footer:
                AllPingLunActivity.startAll(this, id);
                break;
            case R.id.shoppingdetils_share_iv://分享
                showPopupMenu(shoppingdetils_share_iv);
                break;
            case R.id.tv_go_all_question://查看全部回答
                ProblemDetailsActivity.startQuestionDet(this, answerBean.getQuestionId());
                break;
            case R.id.rb_focus://关注/取消关注
                rbFocus.setText(!rbFocus.isChecked() ? "十 关注" : "已关注");
                if (rbFocus.isChecked()) {
                    requestFocus();
                } else {
                    requestCancelFocus();
                }
                break;
            case R.id.rb_like:
                requestLike();
                break;
            case R.id.rb_un_like:
                requestUnLike();
                break;
            case R.id.cb_collect:
                if (answerBean.isFavorite())
                    requestCancelCollect();
                else
                    requestCollect();
                break;
            case R.id.cb_like://点赞/踩
                if (answerBean.isLike())
                    requestUnLike();
                else
                    requestLike();
                break;
            case R.id.tv_input://写评论
                showCommentsDialog();
                break;
            case R.id.im_go_share://底部分享
                showShare();
                break;
        }
    }

    private void showCommentsDialog() {
        dialog = new Dialog(this, R.style.dialog);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        View view = getLayoutInflater().inflate(R.layout.comment_dialog, null);
        LinearLayout.LayoutParams sLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        sLp.width = (int) (getResources().getDisplayMetrics().widthPixels - ScreenDpiUtils.dp2px(this, 40));//margin值由宽度来控制
        dialog.setContentView(view, sLp);
        dialog.findViewById(R.id.im_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        final EditText editText = (EditText) dialog.findViewById(R.id.editText);
        dialog.findViewById(R.id.btn_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().length() == 0) {
                    ToastUtils.show("请输入内容再发布");
                } else {
                    requestApplyComment(editText.getText());
                }
            }
        });
        dialog.show();
    }

    /**
     * 菜单
     */
    private void showPopupMenu(View view) {
        PopupMenu popupmenu = new PopupMenu(MyApp.activity, view);
        popupmenu.getMenuInflater().inflate(R.menu.main, popupmenu.getMenu());
        popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_share) {
                    showShare();
                    return true;
                } else if (item.getItemId() == R.id.action_jubao) {
                    ReportActivity.startReportFirst(AnswerDetActivity.this, 1, id);
                    return true;
                }

                return false;
            }
        });
        popupmenu.show();
    }

    private void showShare() {
        ShareUtils.share(AnswerDetActivity.this, "answer?id=" + answerBean.getQuestionId() + "&answerId=" + answerBean.getId(), answerBean.getTitle()
                , answerBean.getUser().getNickname() + "向你推荐了" + "当前回答，" + "已获得了" + answerBean.getLikeCount() + "关注"
                , null);
    }
}
