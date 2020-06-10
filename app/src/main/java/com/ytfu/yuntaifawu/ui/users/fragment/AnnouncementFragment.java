package com.ytfu.yuntaifawu.ui.users.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.lee.annotation.InjectPresenter;
import com.lxj.xpopup.util.XPopupUtils;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseRecyclerViewFragment;
import com.ytfu.yuntaifawu.base.adapter.QuickAdapter;
import com.ytfu.yuntaifawu.ui.users.adaper.AnnouncementAdaper;
import com.ytfu.yuntaifawu.ui.users.p.AnnouncementPresenter;
import com.ytfu.yuntaifawu.ui.users.v.AnnouncementView;
import com.ytfu.yuntaifawu.utils.ItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther gxy
 * @Date 2020/6/10
 * @Des 公告fragment
 */
@InjectPresenter(AnnouncementPresenter.class)
public class AnnouncementFragment extends BaseRecyclerViewFragment
        <String, AnnouncementView, AnnouncementPresenter> implements AnnouncementView {

    public static AnnouncementFragment newInstance() {

        Bundle args = new Bundle();

        AnnouncementFragment fragment = new AnnouncementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        super.initData();
        int color = Color.parseColor("#f2f2f2");
        int lineHeight = XPopupUtils.dp2px(mContext, 5);
        addItemDecoration(ItemDecoration.createVertical(color,lineHeight,0));
    }

    @Override
    protected BaseQuickAdapter<String, BaseViewHolder> createAdapter() {
        return new AnnouncementAdaper();
    }
    @Override
    protected void onLoadMoreOrRefresh(boolean isLoadMore) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                List<String> list = new ArrayList<>();
                list.add("djdjdhdh");
                list.add("djdjdhdh");
                list.add("djdjdhdh");
                list.add("djdjdhdh");
                list.add("djdjdhdh");
                SystemClock.sleep(1000);
                runOnUi(() -> {
                    addData(list);
                    stopRefresh();
                });
            }
        }.start();
    }

    //列表成功回调
    @Override
    public void announcementList() {

    }

    @Override
    public void announcementFiled() {

    }
}
