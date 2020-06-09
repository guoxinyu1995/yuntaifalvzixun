package com.ytfu.yuntaifawu.base.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

/**多条目实现*/
public class MultiQuickAdapter<T extends MultiItemEntity> extends BaseMultiItemQuickAdapter<T, BaseViewHolder> {

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, T t) {

    }
}
