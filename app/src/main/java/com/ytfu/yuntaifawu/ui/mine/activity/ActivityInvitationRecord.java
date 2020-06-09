package com.ytfu.yuntaifawu.ui.mine.activity;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.mine.adaper.InvitationRecordAdaper;
import com.ytfu.yuntaifawu.ui.mine.bean.InvitationRecordListBean;
import com.ytfu.yuntaifawu.ui.mine.present.InvitionRecordPresenter;
import com.ytfu.yuntaifawu.ui.mine.view.IInvitionRecordView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.MyItemDecoration;
import com.ytfu.yuntaifawu.utils.MyItemDecoration2;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;
import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/15
*
*  @Des  邀请记录
*
*/
public class ActivityInvitationRecord extends BaseActivity<IInvitionRecordView, InvitionRecordPresenter> implements IInvitionRecordView, View.OnClickListener {

    private ImageView iv_fanhui;
    private TextView tv_top_title;
    private RecyclerView recyclerView;
    private Button btn;
    private InvitationRecordAdaper recordAdaper;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_invitation_record;
    }

    @Override
    protected View provideLoadServiceRootView() {
        return recyclerView;
    }

    @Override
    protected InvitionRecordPresenter createPresenter() {
        return new InvitionRecordPresenter(this);
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
        hideLoading();
        iv_fanhui = findViewById(R.id.iv_fanhui);
        tv_top_title = findViewById(R.id.tv_top_title);
        recyclerView = findViewById(R.id.recycle_invitation);
        btn = findViewById(R.id.btn_fenxiang);
        tv_top_title.setText("邀请记录");
        iv_fanhui.setOnClickListener(this);
        btn.setOnClickListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(new MyItemDecoration2(0f,0f));
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        recordAdaper = new InvitationRecordAdaper(this);
        recyclerView.setAdapter(recordAdaper);
        HashMap<String,String> map = new HashMap<>();
        uid = SpUtil.getString(AppConstant.UID, "");
        map.put("uid", uid);
        getPresenter().invitationRecord(map);
    }

    @Override
    public void onInvitionSuccess(InvitationRecordListBean listBean) {
        hideLoading();
        if(listBean == null || listBean.getList() == null || listBean.getList().isEmpty()){
            showEmpty();
        }else{
            List<InvitationRecordListBean.ListBean> list = listBean.getList();
            list.add(new InvitationRecordListBean.ListBean(listBean.getCount()));
            recordAdaper.setmList(listBean.getList());
        }
    }

    @Override
    public void onFiled() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.btn_fenxiang:
                break;
                default:
                    break;
        }
    }
}
