package com.ytfu.yuntaifawu.ui.falvguwen.activity;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.falvguwen.adaper.LegalAdviserRlAdaper;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserListBean;
import com.ytfu.yuntaifawu.ui.falvguwen.presenter.LegalAdviserListPresenter;
import com.ytfu.yuntaifawu.ui.falvguwen.view.ILegalAdviserListView;
import com.ytfu.yuntaifawu.utils.MyItemDecoration2;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class ActivityLegalAdviserLizhiRuzhiList extends BaseActivity<ILegalAdviserListView, LegalAdviserListPresenter> implements ILegalAdviserListView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.recycle_flgw_list)
    RecyclerView recycleFlgwList;
    private String id;
    private String label;
    private LegalAdviserRlAdaper rlAdaper;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_flgw_list;
    }

    @Override
    protected LegalAdviserListPresenter createPresenter() {
        return new LegalAdviserListPresenter(this);
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
        uid = SpUtil.getString(AppConstant.UID, "");
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        label = intent.getStringExtra("label");
        tvTopTitle.setText(label);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleFlgwList.addItemDecoration(new MyItemDecoration2(0f,0f));
        recycleFlgwList.setLayoutManager(layoutManager);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("type",id);
        mPresenter.getFlgwLieBiao(map);
    }

    @Override
    protected void initData() {
        rlAdaper = new LegalAdviserRlAdaper(this);
        recycleFlgwList.setAdapter(rlAdaper);
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("type",id);
        mPresenter.getFlgwLieBiao(map);
    }

    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onSuccess(LegalAdviserListBean liebiaoBean) {
        hideLoading();
        if(null==liebiaoBean || liebiaoBean.getList() == null || liebiaoBean.getList().isEmpty()){
            showEmpty();
        }else{
            rlAdaper.setList(liebiaoBean.getList());
        }
    }

    @Override
    public void onFiled(String errorMessage) {

    }
}
