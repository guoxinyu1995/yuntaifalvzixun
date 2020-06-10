package com.ytfu.yuntaifawu.ui.users.adaper;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.adapter.QuickAdapter;

import org.jetbrains.annotations.NotNull;

/**
 * @Auther gxy
 * @Date 2020/6/10
 * @Des 用户首页Adaper
 */
public class UserHomeAdaper extends QuickAdapter<String> {
    public UserHomeAdaper() {
        super(R.layout.home_recycle_view_item);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        super.convert(baseViewHolder, s);
    }
}
