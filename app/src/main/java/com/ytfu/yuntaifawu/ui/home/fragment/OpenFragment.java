package com.ytfu.yuntaifawu.ui.home.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.activity.ActivitySelectIndictment;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper.KtzsTitleListAdaper;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsListBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter.KtzsListPresenter;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IKtzsListView;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/3/20
 * @Des 开庭fragment
 */
public class OpenFragment extends BaseFragment<IKtzsListView, KtzsListPresenter> implements IKtzsListView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.recycle_ktzs)
    RecyclerView recycleKtzs;
    @BindView(R.id.btn_add_ktzs)
    Button btnAddKtzs;
    @BindView(R.id.v1)
    View v1;
    private String uid;
    private KtzsTitleListAdaper titleListAdaper;
    private LinearLayoutManager layoutManager;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_kaiting;
    }

    @Override
    protected KtzsListPresenter createPresenter() {
        return new KtzsListPresenter(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        uid = SpUtil.getString(AppConstant.UID, "");
    }

    @Override
    protected void initView(View rootView) {
        uid = SpUtil.getString(AppConstant.UID, "");
        ivFanhui.setVisibility(View.GONE);
        tvTopTitle.setText("开庭助手");
        EventBus.getDefault().register(this);
        Log.v("uid","-------v"+uid);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
        recycleKtzs.addItemDecoration(divider);
        recycleKtzs.setLayoutManager(layoutManager);
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", uid);
            mPresenter.setKtzsList(map);
        }
    }
    @Override
    protected void initData() {
        hideLoading();
        titleListAdaper = new KtzsTitleListAdaper(getContext());
        recycleKtzs.setAdapter(titleListAdaper);
        recycleKtzs.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if(lastVisibleItemPosition+1 == titleListAdaper.getItemCount()){
                    v1.setVisibility(View.VISIBLE);
                }else{
                    v1.setVisibility(View.GONE);
                }
            }
        });
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        mPresenter.setKtzsList(map);
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

    @OnClick(R.id.btn_add_ktzs)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), ActivitySelectIndictment.class);
//        intent.putExtra("kt_type",1);
        startActivity(intent);
//        getActivity().finish();
    }
    //接受event事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getKtEvent(MessageEvent messageEvent) {
        switch (messageEvent.getWhat()) {
            case AppConstant.KT_SUCCESS:
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                mPresenter.setKtzsList(map);
                break;
            default:
                break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
