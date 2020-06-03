package com.ytfu.yuntaifawu.callback;

import android.content.Context;
import android.view.View;

import com.kingja.loadsir.callback.Callback;
import com.ytfu.yuntaifawu.R;

/**
*
*  @Auther  gxy
*
*  @Date    2019/12/6
*
*  @Des
*
*/
public class EmptyCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_empty;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
