package com.ytfu.yuntaifawu.ui.qisuzhuang.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.qisuzhuang.adaper.QszXqFenleiAdaper;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszXqFlBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.presenter.QszXqFlPresenter;
import com.ytfu.yuntaifawu.ui.qisuzhuang.view.IQszXqFlView;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/12/16
 * @Des 起诉状分类
 */
public class ActivityQszXqClassify extends BaseActivity<IQszXqFlView, QszXqFlPresenter> implements IQszXqFlView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_tishi)
    TextView tvTishi;
    @BindView(R.id.recycle_fenlei)
    RecyclerView recycleFenlei;
    private String uid;
    private String id;
    private QszXqFenleiAdaper fenleiAdaper;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_qsz_xq_classify;
    }

    @Override
    protected View provideLoadServiceRootView() {
        return recycleFenlei;
    }

    @Override
    protected QszXqFlPresenter createPresenter() {
        return new QszXqFlPresenter(this);
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
    protected void onResume() {
        super.onResume();
        uid = SpUtil.getString(AppConstant.UID, "");
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("szid",id);
        getPresenter().setQszXqFl(map);
    }

    @Override
    protected void initView() {
//        hideLoading();
        tvTopTitle.setText("起诉状");
        uid = SpUtil.getString(AppConstant.UID, "");
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleFenlei.setLayoutManager(layoutManager);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("szid",id);
        getPresenter().setQszXqFl(map);
    }

    @Override
    protected void initData() {
        fenleiAdaper = new QszXqFenleiAdaper(this);
        recycleFenlei.setAdapter(fenleiAdaper);
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("szid",id);
        getPresenter().setQszXqFl(map);
    }


    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onSuccess(QszXqFlBean xqFlBean) {
        hideLoading();
        if(xqFlBean == null || xqFlBean.getList() == null || xqFlBean.getList().isEmpty()){
            showEmpty();
        }else{
            fenleiAdaper.setmList(xqFlBean.getList());
        }
    }

    @Override
    public void onFiled() {

    }

}
