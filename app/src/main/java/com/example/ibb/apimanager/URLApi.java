package com.example.ibb.apimanager;

import com.example.ibb.BuildConfig;

/**
 * Created by 张凯雅 on 2017/12/29.
 */

public class URLApi {

    public static String urlstring = BuildConfig.urlstring;
    //获取验证码接口
    public static String codestring = urlstring + "login/getCaptcha";
    //验证码是否正确
    public static String testCodestring = urlstring + "login/validCaptcha/";
    //设置密码接口
    public static String mimastring = urlstring + "setPassword/";
    //账号密码登录接口
    public static String accountNumberstring = urlstring + "login/";


    //重置密码
    //重置密码获取验证码接口
    public static String resetCodestring = urlstring + "resetPassword/getCaptcha/";
    //重置密码 验证码是否正确
    public static String resetTestCodestring = urlstring + "resetPassword/validCaptcha/";
    //重置密码 重置密码接口
    public static String resetPasswordstring = urlstring + "resetPassword/";

    //首页--问答
    //活动banner
    public static String bannerstring = urlstring + "banner/get/";
    //精选问题
    public static String questionstring = urlstring + "question/omnibus";
    //搜索接口
    public static String answeredsearchstring = urlstring + "search/all";
    //搜索问答
    public static String qasearchstring = urlstring + "search/question";
    //问题详情
    public static String questiondetailstring = urlstring + "question/get";

    //发现模块
    //最新问题
    public static String lateststring = urlstring + "discovery/latest";
    //热门答主
    public static String hotAnswererstring = urlstring + "discovery/hotAnswerer";
    //高分商品
    public static String highstring = urlstring + "discovery/highProducts";

    //发布
    //发布接口
    public static String publishstring = urlstring + "question/post/";
    //搜索商品
    public static String searchstring = urlstring + "search/product";
    //商品分类
    public static String categoriesstring = urlstring + "category/parent";
    //商品子分类
    public static String childcategoriesstring = urlstring + "category/children";
    //热门商品/美妆
    public static String hotstring = urlstring + "product/hotProduct";

    //消息
    public static String notification = urlstring + "notification/list";
    //设置签名
    public static String setSignature = urlstring + "profile/setSignature";
    //设置昵称
    public static String setNickname = urlstring + "profile/setNickname";
    //个人信息
    public static String getUserinfo = urlstring + "profile/getUserinfo";
    //设置位置
    public static String setLocation = urlstring + "profile/setLocation";
    //设置性别
    public static String setSex = urlstring + "profile/setSex";
    //问题反馈
    public static String feedbackstring = urlstring + "profile/feedback";

    public static String focusQuestion = urlstring + "follow/question";//关注问题
    public static String cancelFocusQuestion = urlstring + "unfollow/question";//取消关注问题
    public static String goodsDetails = urlstring + "product/get";//商品详情
    public static String goodsCommits = urlstring + "product/getProductEvaluateList";//商品评价
    public static String interlocution = urlstring + "product/interlocution";//商品相关问答
    public static String getListForQuestion = urlstring + "answer/getListForQuestion";//问题详情的回答列表
    public static String uploadImage = "http://www.ibbtech.cn/" + "api/a/uploadImage";//上传图片
    public static String answerPOST = urlstring + "answer/post";//发布回答
    public static String following = urlstring + "following";//首页-关注列表
    public static String answerDet = urlstring + "answer/detail";//回答详情
    public static String followUser = urlstring + "follow/user";//关注用户
    public static String unFollowUser = urlstring + "unfollow/user";//取消关注用户
    public static String likeAns = urlstring + "answer/like";//点赞回答
    public static String unLikeAns = urlstring + "answer/unlike";//取消点赞回答
    public static String getCommentsList = urlstring + "reply/getListForAnswer";//回答详情-评论列表

    public static String allDynamic = urlstring + "profile/allDynamic";//个人中心-全部动态
    public static String getQuestions = urlstring + "profile/getQuestions";//个人中心-问题列表
    public static String getAnswers = urlstring + "profile/getAnswers";//个人中心-回答列表
    public static String replys = urlstring + "profile/replys";//个人中心-评论列表

    public static String getFollowers = urlstring + "profile/getFollowers";//我的-我的粉丝
    public static String getFollowingUser = urlstring + "profile/getFollowingUser";//我的-我关注的人//profile/getFollowQuestions

    public static String getFollowQuestions = urlstring + "profile/getFollowQuestions";//我的-我关注的问题
    public static String getFavoriteAnswers = urlstring + "profile/getFavoriteAnswers";//我的-我收藏的问题
    public static String getFavoriteProducts = urlstring + "profile/getFavoriteProducts";//我的-我收藏的商品

    public static String openidLogin = urlstring + "openidLogin";//第三方登陆
    public static String favorite = urlstring + "answer/favorite";//收藏回答
    public static String deleteFavorite = urlstring + "answer/deleteFavorite";//取消收藏回答
    public static String applyComment = urlstring + "reply/post";//发布评论

    public static String answerRe = urlstring + "answer/report";//举报回答
    public static String questionRe = urlstring + "question/report";//举报问题
    public static String profileReport = urlstring + "profile/report";//举报用户
    public static String productFavorite = urlstring + "product/favorite";//收藏商品
    public static String productDeleteFavorite = urlstring + "product/deleteFavorite";//取消收藏商品

    public static String hotSearch = urlstring + "search/hotSearch";//热门标签
    public static String setPortrait = urlstring + "profile/setPortrait";//设置头像

    public static String updateApp = urlstring + "apk/latest";//设置头像
}
