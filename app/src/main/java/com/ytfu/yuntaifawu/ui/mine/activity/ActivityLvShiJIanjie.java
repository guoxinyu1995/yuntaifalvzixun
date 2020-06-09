package com.ytfu.yuntaifawu.ui.mine.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.util.XPopupUtils;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.mine.present.PersonalProfilePresent;
import com.ytfu.yuntaifawu.ui.mine.view.PersonalProfileView;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/5/20
 * @Des 律师认证  个人简介
 */
public class ActivityLvShiJIanjie extends BaseActivity<PersonalProfileView, PersonalProfilePresent>
        implements PersonalProfileView {


    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_qd)
    TextView tvQd;
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.tv_num)
    TextView tvNum;

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
    protected int provideContentViewId() {
        return R.layout.activity_lvshirzjianjie;
    }

    @Override
    protected PersonalProfilePresent createPresenter() {
        return new PersonalProfilePresent();
    }

    @Override
    protected void initView() {
        hideLoading();
        tvTopTitle.setText("个人简介");
    }

    @Override
    protected void initData() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Editable text = editText.getText();
                int length = text.length();
                tvNum.setText(length + "" + "/500");
                if (length > 500) {
                    ToastUtil.showCenterToast("超出字数限制");
                    int selEndIndex = Selection.getSelectionEnd(text);
                    String str = text.toString();
                    //截取新字符串
                    String newStr = str.substring(0, 500);
                    editText.setText(newStr);
                    text = editText.getText();

                    //新字符串的长度
                    int newLen = text.length();
                    //旧光标位置超过字符串长度
                    if (selEndIndex > newLen) {
                        selEndIndex = text.length();
                    }
                    //设置新光标所在的位置
                    Selection.setSelection(text, selEndIndex);
                }
            }
        });

        getPresenter().getResumeTemplate();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showRemind("当前内容还没有保存是否退出");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.iv_fanhui, R.id.tv_qd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                showRemind("当前内容还没有保存是否退出");
                break;
            case R.id.tv_qd:
                if (!TextUtils.isEmpty(editText.getText().toString())) {
                    EventBus.getDefault().post(new MessageEvent(AppConstant.LVSHI_RENZHENG_GRJJ, editText.getText().toString()));
                    finish();
                } else {
                    showCenterToast("输入不能为空");
                }
                break;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // View接口实现
    ///////////////////////////////////////////////////////////////////////////


    @Override
    public void onGetResumeTemplateSuccess(List<String> list) {
        findViewById(R.id.ll_resume_root_container).setVisibility(View.VISIBLE);
        findViewById(R.id.tv_resume_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().getRandomTemplate(list, 2);
            }
        });
        getPresenter().getRandomTemplate(list, 2);
    }

    @Override
    public void onGetResumeTemplateFail() {
        findViewById(R.id.ll_resume_root_container).setVisibility(View.GONE);
    }

    @Override
    public void getRandomTemplateToShow(List<String> list) {
        LinearLayout ll_resume_root = findViewById(R.id.ll_resume_root);
        ll_resume_root.removeAllViews();

        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            if (TextUtils.isEmpty(item)) {
                continue;
            }
            if (i != 0) {//添加分割线
                LinearLayout.LayoutParams lineParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        XPopupUtils.dp2px(this, 0.5F));
                int margin = XPopupUtils.dp2px(this, 16);
                lineParams.leftMargin = margin;
                lineParams.rightMargin = margin;
                View lineView = new View(this);
                lineView.setBackgroundColor(Color.parseColor("#F2F2F2"));
                ll_resume_root.addView(lineView, lineParams);
            }
            //添加文本
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView tv = new TextView(this);
            tv.setText(item);
            tv.setTextColor(Color.parseColor("#FF777777"));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            tv.setBackground(getDrawable(R.drawable.selector_white_to_gray));
            tv.setOnClickListener(v -> {
                editText.setText(item);
                editText.setSelection(item.length() );
                editText.requestFocus();
            });
            int paddingTop = XPopupUtils.dp2px(this, 14);
            int paddingLeft = XPopupUtils.dp2px(this, 16);
            tv.setPadding(paddingLeft, paddingTop, paddingLeft, paddingTop);
            ll_resume_root.addView(tv, params);
        }
    }
}
