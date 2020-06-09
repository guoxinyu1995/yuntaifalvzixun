package com.ytfu.yuntaifawu.ui.lawyer.transaction.act;

import android.content.Context;
import android.content.Intent;
import android.widget.FrameLayout;

import com.github.lee.annotation.InjectLayout;
import com.github.lee.annotation.InjectPresenter;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.frament.TransactionFragment;

import butterknife.BindView;

/**
 * 律师零钱明细
 */
@InjectLayout(value = R.layout.activity_transaction, toolbarLayoutId = R.layout.layout_toolbar_center_title)
public class TransactionActivity
        extends BaseActivity<BaseView, BasePresenter<BaseView>> {

    public static void start(Context context) {
        context.startActivity(new Intent(context, TransactionActivity.class));
    }

    @Override
    protected void initData() {
        super.initData();
        hideLoading();
        changeStatusBarTextColor(false);
        setToolbarLeftImage(R.drawable.fanhui_bai, v -> onBackPressed());
        setToolbarText(R.id.tv_global_title, "零钱明细");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_transaction_content, TransactionFragment.newInstance())
                .commitNowAllowingStateLoss();
    }
}

//
///**
// * 交易记录界面
// */
//public class TransactionActivity extends BaseActivity<TransactionView, TransactionPresenter> implements TransactionView {
//
//    @BindView(R.id.tl_transaction_toolbar)
//    Toolbar tl_transaction_toolbar;
//    @BindView(R.id.tv_transaction_title)
//    TextView tv_transaction_title;
//
//    @BindView(R.id.srl_transaction_refresh)
//    SwipeRefreshLayout srl_transaction_refresh;
//    @BindView(R.id.rv_transaction_content)
//    RecyclerView rv_transaction_content;
//
//    private TransactionAdapter adapter;
//
//    private int page = 1;
//    private final int size = 10;
//
//    //===Desc:================================================================================
//
//
//    public static void start(Context context) {
//        Intent starter = new Intent(context, TransactionActivity.class);
//        context.startActivity(starter);
//    }
//
//
//    @Override
//    protected int provideContentViewId() {
//        return R.layout.activity_transaction;
//    }
//
//    @Override
//    protected TransactionPresenter createPresenter() {
//        return new TransactionPresenter();
//    }
//
//    @Override
//    protected void initView() {
//        srl_transaction_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                page = 1;
//                String uid = SpUtil.getString(AppConstant.UID, "");
//                getPresenter().getTransaction(uid, page, size);
//            }
//        });
//    }
//
//    @Override
//    protected void initData() {
//        tv_transaction_title.setText("零钱明细");
//        tl_transaction_toolbar.setTitle("");
//        setSupportActionBar(tl_transaction_toolbar);
//        tl_transaction_toolbar.setNavigationIcon(R.drawable.fanhui_bai);
//        tl_transaction_toolbar.setNavigationOnClickListener(v -> onBackPressed());
//
//        rv_transaction_content.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new TransactionAdapter();
//        adapter.getLoadMoreModule().setEnableLoadMore(true);
////        adapter.bindToRecyclerView(rv_transaction_content);
//        adapter.getLoadMoreModule().setEnableLoadMore(true);
//        adapter.getLoadMoreModule().setOnLoadMoreListener(new com.chad.library.adapter.base.listener.OnLoadMoreListener() {
//            @Override
//            public void onLoadMore() {
//                page++;
//                //获取数据
//                String uid = SpUtil.getString(AppConstant.UID, "");
//                getPresenter().getTransaction(uid, page, size);
//            }
//        });
//        rv_transaction_content.setAdapter(adapter);
//
//
//        srl_transaction_refresh.setColorSchemeColors(
//                CommonUtil.generateBeautifulColor(),
//                CommonUtil.generateBeautifulColor(),
//                CommonUtil.generateBeautifulColor(),
//                CommonUtil.generateBeautifulColor()
//        );
//        srl_transaction_refresh.setRefreshing(true);
//        //获取数据
//        String uid = SpUtil.getString(AppConstant.UID, "");
//        getPresenter().getTransaction(uid, page, size);
//
//    }
//
//    //===Desc:=================================================================
//    @Override
//    public void onGetTransactionSuccess(TransactionResponseBean bean) {
//        srl_transaction_refresh.setRefreshing(false);
//        List<TransactionResponseBean.DataBean> data = bean.getData();
//        if (page == 1) {
//            if (null == data || data.isEmpty()) {
//                adapter.setEmptyView(R.layout.layout_empty);
//            } else {
//                adapter.setNewInstance(data);
//            }
//        } else {
//            if (null == data || data.isEmpty()) {
//                adapter.getLoadMoreModule().loadMoreEnd();
//            } else {
//                adapter.addData(data);
//                adapter.getLoadMoreModule().loadMoreComplete();
//            }
//        }
//        hideLoading();
//    }
//
//    @Override
//    public void onGetTransactionFail(String msg) {
//        page--;
//        if (page <= 0) {
//            page = 1;
//        }
//        adapter.getLoadMoreModule().loadMoreComplete();
//        srl_transaction_refresh.setRefreshing(false);
//        hideLoading();
//        adapter.setEmptyView(R.layout.layout_empty);
//    }
//}
