package com.ytfu.yuntaifawu.ui.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.mine.adaper.CollectionContractAdaper;
import com.ytfu.yuntaifawu.ui.mine.bean.CollectionListBean;
import com.ytfu.yuntaifawu.ui.mine.present.CollectionPresenter;
import com.ytfu.yuntaifawu.ui.mine.view.ICollectionView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/15
 * @Des 我的收藏
 */
public class ActivityMineCollection extends BaseActivity<ICollectionView, CollectionPresenter> implements ICollectionView {


    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.et_collect_seatch)
    EditText etCollectSeatch;
    @BindView(R.id.rl_collection)
    RecyclerView rlCollection;
    private CollectionContractAdaper contractAdaper;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_mine_collection;
    }

    @Override
    protected View provideLoadServiceRootView() {
        return rlCollection;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, ActivityMineCollection.class);
        context.startActivity(intent);
    }

    @Override
    protected CollectionPresenter createPresenter() {
        return new CollectionPresenter(this);
    }

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initView() {
        tvTopTitle.setText("我的合同");
        uid = SpUtil.getString(AppConstant.UID, "");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rlCollection.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rlCollection.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        contractAdaper = new CollectionContractAdaper(this);
        rlCollection.setAdapter(contractAdaper);
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("type", String.valueOf(2));
        getPresenter().collectionAudio(map);
    }


    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onCollectionSuccess(CollectionListBean audioListBean) {
        hideLoading();
        if (audioListBean == null || audioListBean.getList() == null) {
            showEmpty();
        } else {
            contractAdaper.setmList(audioListBean.getList());
        }
    }

    @Override
    public void onFiled() {

    }
}
