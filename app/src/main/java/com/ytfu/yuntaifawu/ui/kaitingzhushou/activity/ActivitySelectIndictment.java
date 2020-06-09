package com.ytfu.yuntaifawu.ui.kaitingzhushou.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
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
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper.SelectZhuShouAdaper;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.AddQszBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SelectZhuShouBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter.SelectQszPresenter;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.ISelectQszView;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/12/17
 * @Des 添加开庭助手
 */
public class ActivitySelectIndictment extends BaseActivity<ISelectQszView, SelectQszPresenter> implements ISelectQszView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.recycle_select_qsz)
    RecyclerView recycleSelectQsz;
    @BindView(R.id.btn_add_ktzs)
    Button btnAddKtzs;
    @BindView(R.id.v1)
    View v1;
    private String uid;
    private SelectZhuShouAdaper zhuShouAdaper;
    private List<String> selectQszList = new ArrayList<>();
    private List<SelectZhuShouBean.ListBean> list;
    private LinearLayoutManager layoutManager;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_select_qsz;
    }

    @Override
    protected View provideLoadServiceRootView() {
        return recycleSelectQsz;
    }

    @Override
    protected SelectQszPresenter createPresenter() {
        return new SelectQszPresenter(this);
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
        tvTopTitle.setText("选择起诉状");
        uid = SpUtil.getString(AppConstant.UID, "");
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recycleSelectQsz.addItemDecoration(divider);
        recycleSelectQsz.setLayoutManager(layoutManager);
        recycleSelectQsz.setHasFixedSize(true);
        recycleSelectQsz.setNestedScrollingEnabled(false);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        getPresenter().setSelectZhuShou(map);
    }

    @Override
    protected void initData() {
        zhuShouAdaper = new SelectZhuShouAdaper(this);
        recycleSelectQsz.setAdapter(zhuShouAdaper);
        recycleSelectQsz.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if(lastVisibleItemPosition+1 == zhuShouAdaper.getItemCount()){
                    v1.setVisibility(View.VISIBLE);
                }else{
                    v1.setVisibility(View.GONE);
                }
            }
        });
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        getPresenter().setSelectZhuShou(map);
        zhuShouAdaper.setItemClickListener(new SelectZhuShouAdaper.SelectQszOnItemClickListener() {
            @Override
            public void onItemClickListener(int position, boolean check) {
                if (check) {
                    if (selectQszList.size() <= 50) {
                        selectQszList.add(list.get(position).getId());
                    } else {
                        zhuShouAdaper.setItemCheck(position, false);
                    }
                } else {
                    for (int i = 0; i < selectQszList.size(); i++) {
                        if (TextUtils.equals(list.get(position).getId(), selectQszList.get(i))) {
                            selectQszList.remove(i);
                            break;
                        }
                    }
                }
            }
        });
    }
    /**
     * list 转为 String 加空格
     *
     * @param list
     * @return
     */
    private String list2String(List<String> list) {
        StringBuilder codeString = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                codeString.append(list.get(i)).append(",");
            } else {
                codeString.append(list.get(i));
            }
        }
        return codeString.toString();
    }
    @Override
    public void onSelectZhuShouSuccess(SelectZhuShouBean zhuShouBean) {
        hideLoading();
        if (zhuShouBean == null || zhuShouBean.getList() == null || zhuShouBean.getList().isEmpty()) {
            showEmpty();
        } else {
            list = zhuShouBean.getList();
            zhuShouAdaper.setmList(zhuShouBean.getList());
        }
    }
private final int TYPE = 1;
    @Override
    public void onAddQszSuccess(AddQszBean addQszBean) {
        if(addQszBean != null){
            int status = addQszBean.getStatus();
            switch (status){
                case 200:
                    showToast(addQszBean.getMsg());
                    EventBus.getDefault().post(new MessageEvent(AppConstant.KT_SUCCESS));
                    finish();
                    break;
                case 202:
                    showToast(addQszBean.getMsg());
                    break;
                    default:
                        break;
            }
        }
    }

    @Override
    public void onFiled(String error) {

    }

    @OnClick({R.id.iv_fanhui, R.id.btn_add_ktzs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.btn_add_ktzs:
                showAddList();
                break;
                default:
                    break;
        }
    }

    private void showAddList() {
        String str = list2String(selectQszList);
        Log.e("str","--------"+str);
        if(TextUtils.isEmpty(str)){
            showToast("请先选择要添加的内容");
        }else {
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", uid);
            map.put("zhuid", str);
            getPresenter().setAddZhuShou(map);
        }
    }
}
