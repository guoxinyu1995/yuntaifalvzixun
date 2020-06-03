package com.ytfu.yuntaifawu.ui.lawyer.transaction.act;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.adapter.TransactionAdapter;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.bean.TransactionResponseBean;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.p.TransactionPresenter;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.v.TransactionView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 交易记录界面
 */
public class TransactionActivity extends BaseActivity<TransactionView, TransactionPresenter> implements TransactionView {

    @BindView(R.id.tl_transaction_toolbar)
    Toolbar tl_transaction_toolbar;
    @BindView(R.id.tv_transaction_title)
    TextView tv_transaction_title;

    @BindView(R.id.srl_transaction_refresh)
    SwipeRefreshLayout srl_transaction_refresh;
    @BindView(R.id.rv_transaction_content)
    RecyclerView rv_transaction_content;

    private TransactionAdapter adapter;

    private int page = 1;
    private final int size = 10;

    //===Desc:=================================================================


    public static void start(Context context) {
        Intent starter = new Intent(context, TransactionActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_transaction;
    }

    @Override
    protected TransactionPresenter createPresenter() {
        return new TransactionPresenter();
    }

    @Override
    protected void initView() {
        srl_transaction_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                String uid = SpUtil.getString(AppConstant.UID, "");
                mPresenter.getTransaction(uid, page, size);
            }
        });
    }

    @Override
    protected void initData() {
        tv_transaction_title.setText("零钱明细");
        tl_transaction_toolbar.setTitle("");
        setSupportActionBar(tl_transaction_toolbar);
        tl_transaction_toolbar.setNavigationIcon(R.drawable.fanhui_bai);
        tl_transaction_toolbar.setNavigationOnClickListener(v -> onBackPressed());

        rv_transaction_content.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TransactionAdapter();
        adapter.setEnableLoadMore(true);
        adapter.bindToRecyclerView(rv_transaction_content);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {

            @Override
            public void onLoadMoreRequested() {
                page++;
                //获取数据
                String uid = SpUtil.getString(AppConstant.UID, "");
                mPresenter.getTransaction(uid, page, size);
            }
        }, rv_transaction_content);
        rv_transaction_content.setAdapter(adapter);


        srl_transaction_refresh.setColorSchemeColors(
                CommonUtil.generateBeautifulColor(),
                CommonUtil.generateBeautifulColor(),
                CommonUtil.generateBeautifulColor(),
                CommonUtil.generateBeautifulColor()
        );
        srl_transaction_refresh.setRefreshing(true);
        //获取数据
        String uid = SpUtil.getString(AppConstant.UID, "");
        mPresenter.getTransaction(uid, page, size);

    }

    //===Desc:=================================================================
    @Override
    public void onGetTransactionSuccess(TransactionResponseBean bean) {
        srl_transaction_refresh.setRefreshing(false);
        List<TransactionResponseBean.DataBean> data = bean.getData();
        if (page == 1) {
            if (null == data || data.isEmpty()) {
                adapter.setEmptyView(R.layout.layout_empty);
            } else {
                adapter.setNewData(data);
            }
        } else {
            if (null == data || data.isEmpty()) {
                adapter.loadMoreEnd();
            } else {
                adapter.addData(data);
                adapter.loadMoreComplete();
            }
        }
        hideLoading();
    }

    @Override
    public void onGetTransactionFail(String msg) {
        page--;
        if (page <= 0) {
            page = 1;
        }
        adapter.loadMoreComplete();
        srl_transaction_refresh.setRefreshing(false);
        hideLoading();
        adapter.setEmptyView(R.layout.layout_empty);
    }
}
