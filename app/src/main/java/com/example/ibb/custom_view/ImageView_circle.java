package com.example.ibb.custom_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by 张凯雅 on 2018/1/4.
 * 圆形头像
 */

public class ImageView_circle extends BitmapTransformation {

    public ImageView_circle(Context context) {
        super(context);
    }

    @Override    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return getBitmap(pool,toTransform);
    }

    private Bitmap getBitmap(BitmapPool pool, Bitmap bit) {

        int height = bit.getHeight();//获取高
        int width = bit.getWidth();//获取宽
        int min = Math.min(width, height);//起点，最小值
        int h_y= height/2-min/2;
        int w_x= width/2-min/2;

        Bitmap bitmap = Bitmap.createBitmap(bit, w_x, h_y, min, min);//裁剪

        Bitmap bitmap2 = pool.get(min, min, Bitmap.Config.ARGB_8888); //获取透明图片

        if (bitmap2==null){
            bitmap2= Bitmap.createBitmap(min,min, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap2);//画布,虚化其他部分
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        int x_y_ban=min/2;
        canvas.drawCircle(x_y_ban,x_y_ban,x_y_ban,paint);
        return bitmap2;
    }
    @Override
    public String getId() {
        return getClass().getSimpleName();
    }
}
