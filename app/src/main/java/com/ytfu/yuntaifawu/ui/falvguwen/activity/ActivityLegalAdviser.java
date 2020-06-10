package com.ytfu.yuntaifawu.ui.falvguwen.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.ui.falvguwen.adaper.LegalAdviserClassifyAdaper;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserClassifyBean;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther gxy
 * @Date 2019/11/20
 * @Des 法律顾问Activity
 */
public class ActivityLegalAdviser extends BaseActivity {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_tishi)
    TextView tvTishi;
    @BindView(R.id.recycle_flgw)
    RecyclerView recycleFlgw;
    private LegalAdviserClassifyAdaper legalAdviserClassifyAdaper;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_flgw;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityLegalAdviser.class);
//    starter.putExtra();
        context.startActivity(starter);
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
        tvTopTitle.setText("法律顾问");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleFlgw.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        getData();
    }

    @Override
    protected void initData() {
        legalAdviserClassifyAdaper = new LegalAdviserClassifyAdaper(this);
        recycleFlgw.setAdapter(legalAdviserClassifyAdaper);
        //加载数据
        getData();
    }

    //加载数据
    private void getData() {
        HttpUtil.getInstance().getApiService().getFLGW()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(RxLifecycleUtil.bindLifecycle(this))
                .subscribe(new BaseRxObserver<LegalAdviserClassifyBean>() {
                    @Override
                    public void onNextImpl(LegalAdviserClassifyBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            onSuccess(entity);
                        } else {
                            onSuccess(null);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getNavTitle" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        showTimeout();
                        onFiled(errorMessage);
                    }
                });
    }

    private void onFiled(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    private void onSuccess(LegalAdviserClassifyBean entity) {
        hideLoading();
        if (entity.getList() == null || entity.getList().isEmpty()) {
            showEmpty();
        } else {
            tvTishi.setText("已有" + entity.getSum() + "人购买");
            legalAdviserClassifyAdaper.setmList(entity.getList());
        }
    }


    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }
}
