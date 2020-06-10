package com.ytfu.yuntaifawu.base.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.LoadMoreListenerImp;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**基础的adapter*/
public class QuickAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> implements LoadMoreModule {

    public QuickAdapter(int layoutResId) {
        super(layoutResId, new ArrayList<>());
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, T t) {

    }
}
