package com.ytfu.yuntaifawu.ui.mine.fragment;

import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.ui.mine.adaper.CollectionContractAdaper;
import com.ytfu.yuntaifawu.ui.mine.bean.CollectionListBean;
import com.ytfu.yuntaifawu.ui.mine.present.CollectionPresenter;
import com.ytfu.yuntaifawu.ui.mine.view.ICollectionView;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

/**
 * @Auther gxy
 * @Date 2019/11/16
 * @Des 我的收藏合同
 */
public class CollectionContractFragment extends BaseFragment<ICollectionView, CollectionPresenter> implements ICollectionView {

    private RecyclerView collection_contract_recycle;
    private CollectionContractAdaper contractAdaper;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_collection_contract;
    }

    @Override
    protected CollectionPresenter createPresenter() {
        return new CollectionPresenter(getContext());
    }
    //接受event事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getDataEvent(MessageEvent messageEvent){
        switch (messageEvent.getWhat()){
            case AppConstant.EVENT_BUS_WDSC_keyword:
                String keyword = messageEvent.getMessage();
                HashMap<String,String> map = new HashMap<>();
                map.put("uid",uid);
                map.put("type",String.valueOf(2));
                map.put("keyword",keyword);
                getPresenter().collectionAudio(map);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        uid = SpUtil.getString(AppConstant.UID, "");
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("type",String.valueOf(2));
        getPresenter().collectionAudio(map);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("type",String.valueOf(2));
        getPresenter().collectionAudio(map);
    }

    @Override
    protected void initView(View rootView) {
        hideLoading();
        uid = SpUtil.getString(AppConstant.UID, "");
        EventBus.getDefault().register(this);
        collection_contract_recycle = rootView.findViewById(R.id.collection_contract_recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        collection_contract_recycle.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        collection_contract_recycle.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        contractAdaper = new CollectionContractAdaper(getContext());
        collection_contract_recycle.setAdapter(contractAdaper);
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("type",String.valueOf(2));
        getPresenter().collectionAudio(map);
    }

    @Override
    public void onCollectionSuccess(CollectionListBean collectionListBean) {
        hideLoading();
        if(collectionListBean == null || collectionListBean.getList() == null || collectionListBean.getList().isEmpty()){
            showEmpty();
//            showToast("暂无相关数据");
        }else{
            contractAdaper.setmList(collectionListBean.getList());
        }
    }

    @Override
    public void onFiled() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
