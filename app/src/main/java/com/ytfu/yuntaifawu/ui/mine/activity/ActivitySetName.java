package com.ytfu.yuntaifawu.ui.mine.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.mine.bean.SetNameBean;
import com.ytfu.yuntaifawu.ui.mine.present.SetNamePresenter;
import com.ytfu.yuntaifawu.ui.mine.view.ISetNameView;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/12/30
 * @Des 设置昵称
 */
public class ActivitySetName extends BaseActivity<ISetNameView, SetNamePresenter> implements ISetNameView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_qd)
    TextView tvQd;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_set_name;
    }

    @Override
    protected SetNamePresenter createPresenter() {
        return new SetNamePresenter(this);
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
        uid = SpUtil.getString(AppConstant.UID, "");
        tvTopTitle.setText("设置昵称");
        String user_login = getIntent().getStringExtra("user_login");
        etName.setText(user_login);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_fanhui, R.id.tv_qd, R.id.iv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_qd:
                setData();
                break;
            case R.id.iv_clear:
                etName.setText("");
                etName.requestFocusFromTouch();
                break;
        }
    }

    private void setData() {
        String et_name = etName.getText().toString();
        if(TextUtils.isEmpty(et_name)){
            ToastUtil.showCenterToast("昵称输入为空");
        }else{
            showWaitingDialog("修改中...");
            HashMap<String,String> map = new HashMap<>();
            map.put("uid",uid);
            map.put("name",et_name);
            getPresenter().setName(map);
        }
    }

    @Override
    public void onSetNameSuccess(SetNameBean setNameBean) {
        hideWaitingDialog();
        if(setNameBean!=null){
            int status = setNameBean.getStatus();
            if(status == 1){
                ToastUtil.showCenterToast("修改成功");
                finish();
            }else if(status == 0){
                ToastUtil.showCenterToast("修改失败");
            }else if(status == 2){
                ToastUtil.showCenterToast(setNameBean.getMsg());
            }
        }
    }

    @Override
    public void onSetNameFiled() {

    }
}
