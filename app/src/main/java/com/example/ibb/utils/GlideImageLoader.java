package com.example.ibb.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.youth.banner.loader.ImageLoader;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by 张凯雅 on 2018/1/30.
 */

public class GlideImageLoader extends ImageLoader {

    //加载矩形图片
    // .bitmapTransform(new CenterCrop(context),new RoundedCornersTransformation(context,15,0, RoundedCornersTransformation.CornerType.ALL))
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        Glide.with(context)
                .load(path)
                .diskCacheStrategy( DiskCacheStrategy.NONE )
                .skipMemoryCache( true )
                .into(imageView);

    }
}
