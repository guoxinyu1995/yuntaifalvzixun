package com.ytfu.yuntaifawu.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.ytfu.yuntaifawu.R;

import java.io.File;

/**
 * @author fans
 * @date 2018/6/13
 * @description 图片加载管理
 */
public class GlideManager {

    /**
     * 加载网络图片
     */
    public static void loadImageByUrl(Context context, String imgUrl, ImageView imageView) {

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.icon_seat)
                .error(R.drawable.icon_seat)
                // 设置缓存
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                // 缩略图
                .thumbnail(Glide.with(context).load(imgUrl))
                .into(imageView);

    }

    /**
     * 加载网络图片
     */
    public static void loadImageByUrlVertical(Context context, String imgUrl, ImageView imageView) {

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.icon_seat)
                .error(R.drawable.icon_seat)
                // 设置缓存
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                // 缩略图
                .thumbnail(Glide.with(context).load(imgUrl))
                .into(imageView);

    }

    /**
     * 加载res下的图片
     */
    public static void loadImageById(Context context, int resourceId, ImageView imageView) {

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.icon_seat)
                .error(R.drawable.icon_seat)
                .diskCacheStrategy(DiskCacheStrategy.DATA);

        Glide.with(context)
                .load(resourceId)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载本地Gif图片
     */
    public static void loadGifById(Context context, int resourceId, ImageView imageView) {
        // 设置只缓存原始图片
        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA);
        Glide.with(context)
                .asGif()
                .load(resourceId)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     * imgUrl 图片url
     */
    public static void loadCircleImage(Context context, String imgUrl, ImageView imageView) {

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.icon_seat)
                .error(R.drawable.icon_seat)
                // 解决圆形图占位问题
                .dontAnimate()
                .transform(new CircleCrop())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        if (context != null) {
            Glide.with(context)
                    .load(imgUrl)
                    .apply(options)
                    .into(imageView);
        }
    }

    /**
     * 加载圆形图片id
     */
    public static void loadCircleImage(Context context, int resourceId, ImageView imageView) {

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.icon_seat)
                .error(R.drawable.icon_seat)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transform(new CircleCrop())
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(resourceId)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载圆形图片file
     */
    public static void loadCircleImage(Context context, File file, ImageView imageView) {

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.icon_seat)
                .error(R.drawable.icon_seat)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .transform(new CircleCrop())
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(file)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param imgUrl         图片url
     * @param roundingRadius 圆角角度 dp
     */
    public static void loadRadiusImage(Context context, String imgUrl, ImageView imageView, int roundingRadius) {
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(DensityUtil.dip2px(roundingRadius));

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.icon_seat)
                .error(R.drawable.icon_seat)
                .centerInside()
                .transform(roundedCorners)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(imgUrl)
                .apply(options)
                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param bitmap         图片bitmap
     * @param roundingRadius 圆角角度 dp
     */
    public static void loadRadiusImage(Context context, Bitmap bitmap, ImageView imageView, int roundingRadius) {
        //设置图片圆角角度
        RoundedCorners roundedCorners = new RoundedCorners(DensityUtil.dip2px(roundingRadius));

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.icon_seat)
                .error(R.drawable.icon_seat)
                .transform(roundedCorners)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(bitmap)
                .apply(options)
                .into(imageView);
    }

    /**
     * 缓存图片到本地
     */
    public static File cacheFile(Context context, String imgUrl) {
        File cacheFile = null;
        FutureTarget<File> future = Glide.with(context)
                .load(imgUrl)
                .downloadOnly(500, 500);
        try {
            cacheFile = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cacheFile;
    }

}
