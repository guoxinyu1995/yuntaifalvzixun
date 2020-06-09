package com.ytfu.yuntaifawu.ui.home.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.helper.RefreshLayoutHelper;
import com.ytfu.yuntaifawu.ui.home.adaper.DataAdaper;
import com.ytfu.yuntaifawu.ui.home.bean.AudioListBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioTopImageBean;
import com.ytfu.yuntaifawu.ui.home.presenter.NavListPresenter;
import com.ytfu.yuntaifawu.ui.home.view.INavListView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.GlideManager;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/12
 * @Des 音频列表
 */
public class AudioListActivity extends BaseActivity<INavListView, NavListPresenter> implements INavListView {

    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.top_icon)
    ImageView topIcon;
    @BindView(R.id.edit_seatch)
    EditText editSeatch;
    @BindView(R.id.recyele_view)
    RecyclerView recyeleView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.scoll)
    NestedScrollView scoll;
    @BindView(R.id.lin_top)
    LinearLayout linTop;
    private String id;
    private String label;
    private int page = 1;
    private DataAdaper dataAdaper;
    private String keyword;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_audio_list;
    }

    @Override
    protected View provideLoadServiceRootView() {
        return refreshLayout;
    }

    @Override
    protected NavListPresenter createPresenter() {
        return new NavListPresenter(this);
    }

    @Override
    public void init() {
        super.init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
//      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.transparent_half));

        }
    }

    @Override
    protected void initView() {
//        hideLoading();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        label = intent.getStringExtra("label");
//        String navimg = intent.getStringExtra("navimg");
        tvTopTitle.setText(label);
//        GlideManager.loadImageByUrl(this,navimg,topIcon);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyeleView.setLayoutManager(linearLayoutManager);
        scoll.setFocusable(false);
        recyeleView.setNestedScrollingEnabled(true);
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setRefreshHeader(new MaterialHeader(App.getContext()).setColorSchemeResources(R.color.primaryColor, R.color.blue_3C, R.color.green_59));
        refreshLayout.setRefreshFooter(new BallPulseFooter(App.getContext()).setAnimatingColor(CommonUtil.getColor(R.color.primaryColor)));
        editSeatch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                keyword = editSeatch.getText().toString().trim();
                getPresenter().getNavList(id, page, keyword);
//                setData();
            }
        });
    }

    private void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) this
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        setData();
    }

    @Override
    protected void initData() {
        getPresenter().getNavTopImage(id);
        dataAdaper = new DataAdaper(this);
        recyeleView.setAdapter(dataAdaper);
        setData();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                setData();
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                setDatas();
                refreshLayout.finishLoadMore();      //加载完成
//                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    private void setDatas() {
        page++;
        getPresenter().getNavList(id, page, keyword);
    }

    private void setData() {
        page = 1;
        getPresenter().getNavList(id, page, keyword);
    }
    //

    @Override
    public void onNavListSuccess(AudioListBean beanList) {
        hideLoading();
        Log.e("beanList", "beanList-----" + beanList);
        if (beanList == null || beanList.getList() == null || beanList.getList().isEmpty()) {
            ToastUtil.showCenterToast("没有相关数据");
            showEmpty();
        } else {
            if (page == 1) {
                dataAdaper.setList(beanList.getList());
            } else {
                dataAdaper.addList(beanList.getList());
            }
        }
        RefreshLayoutHelper.getInstance().setLoadedState(beanList.getList(), page, refreshLayout, this);
    }

    @Override
    public void onNavListFiled() {

    }

    @Override
    public void onNavImageSuccess(AudioTopImageBean beanList) {
        if (beanList != null) {
            GlideManager.loadImageByUrl(this, beanList.getImg(), topIcon);
        }
    }


    @OnClick({R.id.iv_fanhui, R.id.lin_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.lin_top:
                scoll.fling(0);
                scoll.smoothScrollTo(0, 0);
                break;
        }
    }
}
