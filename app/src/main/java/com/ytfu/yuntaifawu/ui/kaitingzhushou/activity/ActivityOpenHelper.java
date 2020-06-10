package com.ytfu.yuntaifawu.ui.kaitingzhushou.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper.KtzsTitleListAdaper;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsListBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter.KtzsListPresenter;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IKtzsListView;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/23
 * @Des 开庭助手Activity
 */
public class ActivityOpenHelper extends BaseActivity<IKtzsListView, KtzsListPresenter> implements IKtzsListView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.recycle_ktzs)
    RecyclerView recycleKtzs;
    @BindView(R.id.btn_add_ktzs)
    Button btnAddQsz;
    @BindView(R.id.lin)
    RelativeLayout lin;
    @BindView(R.id.v1)
    View v1;
    private KtzsTitleListAdaper titleListAdaper;
    private String uid;
    private LinearLayoutManager layoutManager;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_ktzs;
    }

    @Override
    protected KtzsListPresenter createPresenter() {
        return new KtzsListPresenter(this);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ActivityOpenHelper.class);
//        starter.putExtra();
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
    protected void onResume() {
        super.onResume();
        uid = SpUtil.getString(AppConstant.UID, "");
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        getPresenter().setKtzsList(map);
    }

    @Override
    protected void initView() {
//        hideLoading();
        uid = SpUtil.getString(AppConstant.UID, "");
        tvTopTitle.setText("开庭助手");
//        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
//        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
//        btnAddQsz.measure(w,h);
//        int height =btnAddQsz.getMeasuredHeight();
//        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        lp.setMargins(0, 0, 0, height);
//        recycleKtzs.setLayoutParams(lp);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recycleKtzs.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        recycleKtzs.addItemDecoration(new MyItemDecoration2(0f, 0f));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recycleKtzs.addItemDecoration(divider);
        recycleKtzs.setLayoutManager(layoutManager);
        recycleKtzs.setHasFixedSize(true);
        recycleKtzs.setNestedScrollingEnabled(false);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        getPresenter().setKtzsList(map);
    }

    @Override
    protected void initData() {
        titleListAdaper = new KtzsTitleListAdaper(this);
        recycleKtzs.setAdapter(titleListAdaper);
        recycleKtzs.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == titleListAdaper.getItemCount()) {
                    v1.setVisibility(View.VISIBLE);
                } else {
                    v1.setVisibility(View.GONE);
                }
            }
        });
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        getPresenter().setKtzsList(map);
    }

    @OnClick({R.id.iv_fanhui, R.id.btn_add_ktzs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.btn_add_ktzs:
                startActivity(new Intent(ActivityOpenHelper.this, ActivitySelectIndictment.class));
//                showPopupWindow();
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess(KtzsListBean listBean) {
        hideLoading();
        if (listBean == null || listBean.getList() == null || listBean.getList().isEmpty()) {
            showEmpty();
        } else {
            titleListAdaper.setmList(listBean.getList());
        }
    }

    @Override
    public void onFiled(String error) {

    }

}
