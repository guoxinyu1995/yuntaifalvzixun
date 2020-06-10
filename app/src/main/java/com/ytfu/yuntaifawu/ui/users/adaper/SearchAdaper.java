package com.ytfu.yuntaifawu.ui.users.adaper;

import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.adapter.QuickAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Auther gxy
 * @Date 2020/6/10
 * @Des 搜索adaper
 */
public class SearchAdaper extends QuickAdapter<String> implements LoadMoreModule {

    public SearchAdaper() {
        super(R.layout.home_recycle_view_item);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        super.convert(baseViewHolder, s);
    }
}
