package com.ytfu.yuntaifawu.ui.mseeage.activity;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.custom.MyRadioGroup;
import com.ytfu.yuntaifawu.ui.mseeage.adaper.SuccessAnLiAdaper;
import com.ytfu.yuntaifawu.ui.mseeage.bean.SuccessAnLiBean;
import com.ytfu.yuntaifawu.ui.mseeage.presenter.SuccessAnLiPresenter;
import com.ytfu.yuntaifawu.ui.mseeage.view.ISuccessAnLiView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuccessAnLiActivity extends BaseActivity<ISuccessAnLiView, SuccessAnLiPresenter> implements ISuccessAnLiView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.flowlayout)
    MyRadioGroup flowlayout;
    @BindView(R.id.rl_content)
    RecyclerView rlContent;
//    @BindView(R.id.ll_empty)
//    LinearLayout llEmpty;
    private ViewGroup.MarginLayoutParams layoutParams;
    private String lid;
    private SuccessAnLiAdaper successAnLiAdaper;
    Bitmap a = null;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_success_anli;
    }

    @Override
    protected View provideLoadServiceRootView() {
        return rlContent;
    }

    @Override
    protected SuccessAnLiPresenter createPresenter() {
        return new SuccessAnLiPresenter(this);
    }

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initView() {
//        hideLoading();
        tvTopTitle.setText("成功案例");
        lid = getIntent().getStringExtra("lid");
        layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 20;
        layoutParams.rightMargin = 20;
        layoutParams.topMargin = 15;
        layoutParams.bottomMargin = 10;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlContent.setLayoutManager(layoutManager);
        successAnLiAdaper = new SuccessAnLiAdaper(this);
        rlContent.setAdapter(successAnLiAdaper);
    }

    @Override
    protected void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("lid", lid);
        mPresenter.getSuccessAnli(map);
    }


    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onAnliSuccess(SuccessAnLiBean successAnLiBean) {
        hideLoading();
        if (successAnLiBean != null && successAnLiBean.getCategory() != null) {

//            for (int i = 0; i < category.size(); i++) {
//                RadioButton view = new RadioButton(this);
//                view.setText(category.get(i).getName());
//                view.setTextColor(Color.parseColor("#44a2f7"));
//                view.setButtonDrawable(new BitmapDrawable(a));
//                view.setBackgroundResource(R.drawable.textview_color_selector);
//                ColorStateList csl = getResources().getColorStateList(R.color.selector_radio_check);
//                view.setTextColor(csl);
//                flowlayout.addView(view, layoutParams);
//            }
            List<SuccessAnLiBean.CategoryBean> category = successAnLiBean.getCategory();
            if (flowlayout.getChildCount() == 0) {
                for (int i = 0; i < category.size(); i++) {
//                    view = new TextView(this);
                    RadioButton button = new RadioButton(this);
                    button.setText(category.get(i).getName());
                    button.setButtonDrawable(new BitmapDrawable(a));
                    button.setBackgroundResource(R.drawable.textview_color_selector);
                    ColorStateList csl = getResources().getColorStateList(R.color.selector_radio_check);
                    button.setTextColor(csl);

                    int finalI = i;
                    button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                showWaitingDialog("加载中...", true);
                                int id = category.get(finalI).getId();
                                HashMap<String, String> map = new HashMap<>();
                                map.put("lid", lid);
                                map.put("cid", String.valueOf(id));
                                mPresenter.getSuccessAnli(map);

                            }
                        }
                    });
                    flowlayout.addView(button, layoutParams);
                }
//                RadioButton radio = (RadioButton) flowlayout.getChildAt(0);
//                radio.setChecked(true);
            }
        }
        if (successAnLiBean.getShuju().size() != 0) {
//            llEmpty.setVisibility(View.GONE);
//            rlContent.setVisibility(View.VISIBLE);
            hideWaitingDialog();
            successAnLiAdaper.setShujuBeanList(successAnLiBean.getShuju());
        } else {
            hideWaitingDialog();
            showEmpty();
//            llEmpty.setVisibility(View.VISIBLE);
//            rlContent.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnLiFiled() {

    }

}
