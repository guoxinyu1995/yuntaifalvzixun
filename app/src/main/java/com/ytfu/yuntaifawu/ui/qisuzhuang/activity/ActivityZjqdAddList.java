package com.ytfu.yuntaifawu.ui.qisuzhuang.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.activity.ActivityZhengJuQingDan;
import com.ytfu.yuntaifawu.ui.qisuzhuang.adaper.AddZjqdAdaper;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.AddZjqdBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.presenter.AddZjqdPresenter;
import com.ytfu.yuntaifawu.ui.qisuzhuang.view.IAddZjqdView;
import com.ytfu.yuntaifawu.utils.MyItemDecoration2;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/12/17
 * @Des 添加证据清单
 */
public class ActivityZjqdAddList extends BaseActivity<IAddZjqdView, AddZjqdPresenter> implements IAddZjqdView{

    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.recycle_view_add)
    RecyclerView recycleViewAdd;
    @BindView(R.id.btn_add_zjqd)
    Button btnAddZjqd;
    @BindView(R.id.v1)
    View v1;
    private String uid;
    private String id;
    private AddZjqdAdaper zjqdAdaper;
    private LinearLayoutManager layoutManager;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_zjqd_add_list;
    }

    @Override
    protected View provideLoadServiceRootView() {
        return recycleViewAdd;
    }

    @Override
    protected AddZjqdPresenter createPresenter() {
        return new AddZjqdPresenter(this);
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
        tvTopTitle.setText("证据清单");
        uid = SpUtil.getString(AppConstant.UID, "");
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
       recycleViewAdd.addItemDecoration(new MyItemDecoration2(0f, 0f));
       recycleViewAdd.setLayoutManager(layoutManager);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("szid",id);
        getPresenter().setAddZjqd(map);
    }

    @Override
    protected void initData() {
        zjqdAdaper = new AddZjqdAdaper(this);
        recycleViewAdd.setAdapter(zjqdAdaper);
        recycleViewAdd.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if(lastVisibleItemPosition+1 == zjqdAdaper.getItemCount()){
                    v1.setVisibility(View.VISIBLE);
                }else{
                    v1.setVisibility(View.GONE);
                }
            }
        });
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("szid",id);
        getPresenter().setAddZjqd(map);
    }


    @OnClick({R.id.iv_fanhui, R.id.btn_add_zjqd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.btn_add_zjqd:
                Intent intent = new Intent(ActivityZjqdAddList.this, ActivityZhengJuQingDan.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }

    @Override
    public void onAssSuccess(AddZjqdBean zjqdBean) {
        hideLoading();
        if(zjqdBean == null || zjqdBean.getList() == null || zjqdBean.getList().isEmpty()){
            showEmpty();
        }else{
            zjqdAdaper.setmList(zjqdBean.getList());
        }
    }

    @Override
    public void onAddFiled() {

    }
}
