package com.ytfu.yuntaifawu.base;

import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public interface BaseRefreshView<T> extends BaseView {

    void showRefresh();

    void loadMoreComplete();

    void loadMoreEnd(boolean gone);

    void loadMoreFail();

    void stopRefresh();

    void enableRefresh(boolean enable);

    void enableLoadMore(boolean enable);

    void addData(T data);

    void addData(List<T> data);

    void setNewData(List<T> data);

    void setEmptyView(@LayoutRes int layoutId);

    void setEmptyView(View emptyView);

    BaseQuickAdapter<T, ?> getAdapter();

    void addHeaderView(View view);
    void addHeaderView(View view, int index);
    void addHeaderView(View view, int index, int orientation);
    void addFooterView(View view);
    void addFooterView(View view, int index);
    void addFooterView(View view, int index, int orientation);
    void removeHeader(View header);
    void removeAllHeader();
    void removeFooter(View footer);
    void removeAllFooter();

    int getCurrentPage();

    void setCurrentPage(int currentPage);

    void resetCurrentPage(boolean isLoadMore,int defaultPage);


    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////

    void onRefresh();

    void onLoadMore();

}
