package com.ytfu.yuntaifawu.ui.lawyer.transaction.frament;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.lee.annotation.InjectPresenter;
import com.lxj.xpopup.util.XPopupUtils;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseRecyclerViewFragment;
import com.ytfu.yuntaifawu.base.BaseRefreshView;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.adapter.TransactionAdapter;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.bean.TransactionResponseBean;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.p.TransactionPresenter;
import com.ytfu.yuntaifawu.utils.ItemDecoration;
import com.ytfu.yuntaifawu.utils.SpUtil;

@InjectPresenter(TransactionPresenter.class)
public class TransactionFragment extends
        BaseRecyclerViewFragment
                <TransactionResponseBean.DataBean,
                        BaseRefreshView<TransactionResponseBean.DataBean>,
                        TransactionPresenter> {

    public static TransactionFragment newInstance() {
        return new TransactionFragment();
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////

    @Override
    protected BaseQuickAdapter<TransactionResponseBean.DataBean, BaseViewHolder> createAdapter() {
        return new TransactionAdapter();
    }

    @Override
    protected void initData() {
        super.initData();
        //添加分割线
        int color = Color.parseColor("#F2F2F2");
        int lineHeight = XPopupUtils.dp2px(mContext, .5F);
        addItemDecoration(ItemDecoration.createVertical(color, lineHeight, 0));
    }

    @Override
    protected void onLoadMoreOrRefresh(boolean isLoadMore) {
        if (isLoadMore) {
            currentPage++;
        } else {
            currentPage = 1;
        }
        String uid = SpUtil.getString(AppConstant.UID, "");
        getPresenter().getTransaction(uid, currentPage, pageSize, isLoadMore);
    }
}
