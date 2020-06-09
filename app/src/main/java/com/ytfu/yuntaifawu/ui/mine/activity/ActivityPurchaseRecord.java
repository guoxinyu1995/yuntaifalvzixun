package com.ytfu.yuntaifawu.ui.mine.activity;

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
import com.ytfu.yuntaifawu.ui.mine.adaper.PuechaseRecordAdaper;
import com.ytfu.yuntaifawu.ui.mine.bean.PurchaseRecordBean;
import com.ytfu.yuntaifawu.ui.mine.present.PurchaseRecordPresenter;
import com.ytfu.yuntaifawu.ui.mine.view.IPurchassRecordView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/15
 * @Des 购买记录
 */
public class ActivityPurchaseRecord extends BaseActivity<IPurchassRecordView, PurchaseRecordPresenter> implements IPurchassRecordView {

    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.recycle_purchase)
    RecyclerView recyclePurchase;
    //    private ImageView iv_fanhui;
//    private TextView tv_top_title;
//    private RecyclerView recyclerView;
    private PuechaseRecordAdaper recordAdaper;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_purchase_record;
    }

    @Override
    protected View provideLoadServiceRootView() {
        return recyclePurchase;
    }

    @Override
    protected PurchaseRecordPresenter createPresenter() {
        return new PurchaseRecordPresenter(this);
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
        tvTopTitle.setText("购买记录");
        uid = SpUtil.getString(AppConstant.UID, "");
        recyclePurchase = findViewById(R.id.recycle_purchase);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclePurchase.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        recordAdaper = new PuechaseRecordAdaper(this);
        recyclePurchase.setAdapter(recordAdaper);
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        getPresenter().purchaseRecord(map);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        getPresenter().purchaseRecord(map);
    }

    @Override
    public void onPurcheRecordSuccess(PurchaseRecordBean recordBean) {
        hideLoading();
        if (recordBean == null || recordBean.getList() == null || recordBean.getList().isEmpty()) {
            showEmpty();
        } else {
            recordAdaper.setmList(recordBean.getList());
        }
    }

    @Override
    public void onFiled() {

    }


    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }
}
