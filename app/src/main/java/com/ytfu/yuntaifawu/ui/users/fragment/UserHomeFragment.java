package com.ytfu.yuntaifawu.ui.users.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.lee.annotation.InjectLayout;
import com.github.lee.annotation.InjectPresenter;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseRecyclerViewFragment;
import com.ytfu.yuntaifawu.base.adapter.QuickAdapter;
import com.ytfu.yuntaifawu.ui.home.bean.HomeBannerBean;
import com.ytfu.yuntaifawu.ui.users.act.SearchListActivity;
import com.ytfu.yuntaifawu.ui.users.adaper.UserHomeAdaper;
import com.ytfu.yuntaifawu.ui.users.header.UserHomeHeader;
import com.ytfu.yuntaifawu.ui.users.p.UserHomePresenter;
import com.ytfu.yuntaifawu.ui.users.v.IUserHomeView;
import com.ytfu.yuntaifawu.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @Auther gxy
 * @Date 2020/6/9
 * @Des 用户端首页fragment
 */
@InjectLayout(value = R.layout.fragment_base_recycler, toolbarLayoutId = R.layout.layout_toolbar_center_title)
@InjectPresenter(UserHomePresenter.class)
public class UserHomeFragment extends BaseRecyclerViewFragment
        <String, IUserHomeView, UserHomePresenter> implements IUserHomeView {

    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;
    private UserHomeHeader homeHeader;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.user_home_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_search) {
            Log.e("search", "hhhhhhhhhhh");
        }
        return super.onOptionsItemSelected(item);

    }

    public static UserHomeFragment newInstance() {

        Bundle args = new Bundle();

        UserHomeFragment fragment = new UserHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        hideLoading();
        setHasOptionsMenu(true);
        setToolbarText(R.id.tv_global_title, "云台法律咨询");
        setToolbarTextColor(R.id.tv_global_title, Color.parseColor("#222222"));
        mToolbar.setBackgroundColor(CommonUtil.getColor(R.color.transparent_half));
        mToolbar.inflateMenu(R.menu.user_home_menu);
        linearLayout.setBackgroundResource(R.color.textcolor_f2);
        getAdapter().setOnItemClickListener((adapter, view, position) -> {

                SearchListActivity.start(getActivity());

        });
    }

    @Override
    protected void initData() {
        super.initData();
        homeHeader = new UserHomeHeader(getContext());
        getPresenter().getHomeBannerList();
    }

    @Override
    protected BaseQuickAdapter<String, BaseViewHolder> createAdapter() {
        return new UserHomeAdaper();
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

    //获取轮播图
    @Override
    public void userHomeBannerList(HomeBannerBean bannerBean) {
        hideLoading();
        if (bannerBean != null || bannerBean.getList() != null) {
            getAdapter().addHeaderView(homeHeader.getHeaderView());
            homeHeader.render(bannerBean);
        }
    }

    @Override
    public void userHomeFiled(String error) {

    }
}
