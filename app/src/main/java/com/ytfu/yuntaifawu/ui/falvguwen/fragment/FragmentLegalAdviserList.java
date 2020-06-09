package com.ytfu.yuntaifawu.ui.falvguwen.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.ui.falvguwen.adaper.LegalAdviserRlAdaper;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserListBean;
import com.ytfu.yuntaifawu.ui.falvguwen.presenter.LegalAdviserListPresenter;
import com.ytfu.yuntaifawu.ui.falvguwen.view.ILegalAdviserListView;
import com.ytfu.yuntaifawu.utils.MyItemDecoration2;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import butterknife.BindView;

/**
 * @Auther gxy
 * @Date 2019/11/21
 * @Des 法律顾问fragment
 */
public class FragmentLegalAdviserList extends BaseFragment<ILegalAdviserListView, LegalAdviserListPresenter> implements ILegalAdviserListView {

    @BindView(R.id.recycle_flgw)
    RecyclerView recycleFlgw;
    private String id;
    private LegalAdviserRlAdaper rlAdaper;
    private int indicator_tag;
    private int currentIndex = -1;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_flgw_zz_list;
    }

    @Override
    protected LegalAdviserListPresenter createPresenter() {
        return new LegalAdviserListPresenter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != getArguments()) {
            indicator_tag = getArguments().getInt("indicator_tag");
            id = getArguments().getString("id");
        }
//        if (currentIndex != indicator_tag) {
        uid = SpUtil.getString(AppConstant.UID, "");
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("type",id);
        getPresenter().getFlgwLieBiao(map);
//        }

    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("type",id);
        getPresenter().getFlgwLieBiao(map);
    }

    @Override
    protected void initView(View rootView) {
//        hideLoading();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleFlgw.addItemDecoration(new MyItemDecoration2(0f, 0f));
        recycleFlgw.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        rlAdaper = new LegalAdviserRlAdaper(getContext());
        recycleFlgw.setAdapter(rlAdaper);
//        getPresenter().getFlgwLieBiao(id);
    }

    @Override
    public void onSuccess(LegalAdviserListBean liebiaoBean) {
        hideLoading();
        if (null == liebiaoBean || liebiaoBean.getList() == null || liebiaoBean.getList().isEmpty()) {
            showEmpty();
        } else {
            if (liebiaoBean.getStatus() == 200) {
                rlAdaper.setList(liebiaoBean.getList());
            } else {
                ToastUtil.showToast(liebiaoBean.getMsg());
            }
        }
    }

    @Override
    public void onFiled(String errorMessage) {

    }

}
