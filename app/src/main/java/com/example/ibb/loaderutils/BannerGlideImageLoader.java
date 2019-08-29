package com.example.ibb.loaderutils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by 张凯雅 on 2017/12/12.
 */

public class BannerGlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        //Glide加载图片简单用法

        Glide.with(context).load(path).into(imageView);
    }
}
