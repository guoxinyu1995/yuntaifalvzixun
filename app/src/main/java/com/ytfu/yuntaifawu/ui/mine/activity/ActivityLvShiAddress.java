package com.ytfu.yuntaifawu.ui.mine.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/5/21
 * @Des 律师地址
 */
public class ActivityLvShiAddress extends BaseActivity {


    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_qd)
    TextView tvQd;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.iv_clear)
    ImageView ivClear;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_lvshi_address;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    @Override
    public void init() {
        super.init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
//      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.transparent_f2));

        }
    }
    @Override
    protected void initView() {
        hideLoading();
        tvTopTitle.setText("律所地址");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_fanhui, R.id.tv_qd, R.id.iv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                showRemind("当前内容还没有保存是否退出");
                break;
            case R.id.tv_qd:
                if(!TextUtils.isEmpty(etAddress.getText().toString())){
                    EventBus.getDefault().post(new MessageEvent(AppConstant.LVSHI_RENZHENG_ADDRESS, etAddress.getText().toString().trim()));
                    finish();
                }else{
                    showCenterToast("输入不能为空");
                }
                break;
            case R.id.iv_clear:
                etAddress.setText("");
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showRemind("当前内容还没有保存是否退出");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
