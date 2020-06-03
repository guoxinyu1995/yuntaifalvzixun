package com.ytfu.yuntaifawu.app;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpGlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.module.LibraryGlideModule;

import java.io.InputStream;

import okhttp3.OkHttpClient;

/**
 * @Auther gxy
 * @Date 2020/1/9
 * @Des 解决glide加载https图片加载不出问题
 */
@GlideModule
public class OkHttpAppGlideModule extends LibraryGlideModule {

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        registry.replace(
                GlideUrl.class,
                InputStream.class,
                new OkHttpUrlLoader.Factory(UnsafeOkHttpClient.getUnsafeOkHttpClient())
        );
    }

    //    @Override
    //    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
    //        super.registerComponents(context, glide, registry);
    //
    //        OkHttpClient client = UnsafeOkHttpClient.getUnsafeOkHttpClient();
    //        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(client));
    //    }

    //    @Override
    //    public boolean isManifestParsingEnabled() {
    //        return false;
    //    }
}
