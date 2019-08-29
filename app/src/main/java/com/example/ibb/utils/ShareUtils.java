package com.example.ibb.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.ibb.R;
import com.example.ibb.base.BaseNetActivity;
import com.example.ibb.ui.ui.answered.ProblemDetailsActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.zip.CheckedOutputStream;

import static com.example.ibb.utils.FinalUtils.SHARE_BASE;

/**
 * Created by ASUS on 2018/5/25.
 */

public class ShareUtils {
    private static String TAG="ShareUtils";
    public static void share(final BaseNetActivity context, boolean needAppend,String appendWebUrl, String title, String content, String imgUrl) {
        UMWeb web = new UMWeb(needAppend?SHARE_BASE + appendWebUrl:appendWebUrl);
        web.setTitle(title);//标题
        UMImage image = null;
        if (imgUrl == null) {
            image = new UMImage(context, R.mipmap.logo_ibb);
        } else {
            image = new UMImage(context, imgUrl);
        }
        web.setThumb(image);  //缩略图
        web.setDescription(content);//描述
        new ShareAction(context).withMedia(web)
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.d(TAG, "onStart: ");
                        //context.showDialog();
                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Log.d(TAG, "onResult: ");
                        //context.hideDialog();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Log.d(TAG, "onError: ");
                        //context.hideDialog();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        Log.d(TAG, "onCancel: ");
                        //context.hideDialog();
                        ToastUtils.show("分享已取消");
                    }
                })
                .open();
    }
    public static void share(final BaseNetActivity context, String appendWebUrl, String title, String content, String imgUrl) {
        share(context,true,appendWebUrl,title,content,imgUrl);
    }
}
