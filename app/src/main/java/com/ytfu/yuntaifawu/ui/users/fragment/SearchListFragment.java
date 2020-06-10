package com.ytfu.yuntaifawu.ui.users.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.lee.annotation.InjectPresenter;
import com.lxj.xpopup.util.XPopupUtils;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseRecyclerViewFragment;
import com.ytfu.yuntaifawu.base.adapter.QuickAdapter;
import com.ytfu.yuntaifawu.ui.users.adaper.SearchAdaper;
import com.ytfu.yuntaifawu.ui.users.p.SearchPresenter;
import com.ytfu.yuntaifawu.ui.users.v.SearchView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther gxy
 * @Date 2020/6/10
 * @Des 搜索fragment
 */
@InjectPresenter(SearchPresenter.class)
public class SearchListFragment extends BaseRecyclerViewFragment
        <String, SearchView, SearchPresenter> implements SearchView {

    public static SearchListFragment newInstance() {

        Bundle args = new Bundle();

        SearchListFragment fragment = new SearchListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        super.initData();
        int top = XPopupUtils.dp2px(mContext, 10);
        View view = new View(mContext);
        view.setBackgroundColor(Color.parseColor("#f2f2f2"));
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,top);
        view.setLayoutParams(layoutParams);
        getAdapter().addHeaderView(view);
//        getSwipeRefreshLayout().setPadding(0, top, 0, 0);
        getSwipeRefreshLayout().setBackgroundColor(Color.parseColor("#f2f2f2"));
    }

    @Override
    protected BaseQuickAdapter<String, BaseViewHolder> createAdapter() {
        return new SearchAdaper();
    }

    @Override
    protected void onLoadMoreOrRefresh(boolean isLoadMore) {
        new Thread() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                list.add("qw0-eu8w0j");
                list.add("qw0-eu8w0j");
                list.add("qw0-eu8w0j");
                list.add("qw0-eu8w0j");
                SystemClock.sleep(1000);
                runOnUi(() -> {
                    addData(list);
                    stopRefresh();
                });
            }
        }.start();
    }

    @Override
    public void searchList() {

    }

    @Override
    public void searchFiled() {

    }

}
