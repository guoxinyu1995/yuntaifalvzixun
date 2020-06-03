package com.ytfu.yuntaifawu.callback;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.kingja.loadsir.callback.Callback;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.utils.GlideManager;

/**
 *
 * @作者  gxy
 *
 * @创建时间  2019/11/9
 *
 * @描述
 */
public class LoadingCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_loading;
    }

    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
//       ImageView imageView =  view.findViewById(R.id.iv_gif);
//        GlideManager.loadGifById(context.getApplicationContext(),R.drawable.loding_01,imageView);
        return true;
    }
}
