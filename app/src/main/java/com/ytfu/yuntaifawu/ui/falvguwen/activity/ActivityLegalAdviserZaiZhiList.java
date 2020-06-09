package com.ytfu.yuntaifawu.ui.falvguwen.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserSecondClassifyBean;
import com.ytfu.yuntaifawu.ui.falvguwen.fragment.FragmentLegalAdviserList;
import com.ytfu.yuntaifawu.ui.falvguwen.presenter.LegalAdviserSecondClassifyPresenter;
import com.ytfu.yuntaifawu.ui.falvguwen.view.ILegalAdviserClassifyView;
import com.ytfu.yuntaifawu.utils.GlideManager;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/21
 * @Des 法律顾问在职Activity
 */
public class ActivityLegalAdviserZaiZhiList extends BaseActivity<ILegalAdviserClassifyView, LegalAdviserSecondClassifyPresenter> implements ILegalAdviserClassifyView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.magic_indicator1)
    MagicIndicator magicIndicator1;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String id;
    private String label;
    private List<LegalAdviserSecondClassifyBean.ListBean> list;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_flgw_zz_list;
    }

    @Override
    protected LegalAdviserSecondClassifyPresenter createPresenter() {
        return new LegalAdviserSecondClassifyPresenter(this);
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
//        hideLoading();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        label = intent.getStringExtra("label");
        tvTopTitle.setText(label);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        getPresenter().getFlgwSecond(id);
    }

    @Override
    protected void initData() {
        getPresenter().getFlgwSecond(id);
    }

    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onSuccess(LegalAdviserSecondClassifyBean secondFenLeiBean) {
        hideLoading();
        if (secondFenLeiBean.getList() == null || secondFenLeiBean.getList().isEmpty()) {
            showEmpty();
        } else {
            if (secondFenLeiBean.getStatus() == 200) {
                list = secondFenLeiBean.getList();
                initMagicIndicator();
                initViewPager();
            } else {
                ToastUtil.showToast(secondFenLeiBean.getMsg());
            }

        }
    }

    @Override
    public void onFiled(String error) {

    }


    private void initViewPager() {
        fragmentList.clear();
        for (int i = 0; i < list.size(); i++) {
            FragmentLegalAdviserList fragmentFaLvGuWenZaiZhiList = new FragmentLegalAdviserList();
            Bundle contentBundle = new Bundle();
            contentBundle.putInt("indicator_tag", i);
//            if (i > 0) {
            contentBundle.putString("id", list.get(i).getId());
//            }
            fragmentFaLvGuWenZaiZhiList.setArguments(contentBundle);
            fragmentList.add(fragmentFaLvGuWenZaiZhiList);
        }
        if (getFragmentManager() != null) {
//            IndicatorVpAdapter adapter = new IndicatorVpAdapter(getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentList, indicatorList);
            viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return fragmentList.get(position);
                }

                @Override
                public int getCount() {
                    return list.size();
                }
            });
            ViewPagerHelper.bind(magicIndicator1, viewPager);
        }
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return list == null ? 0 : list.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int i) {
//                CustomPagerTitleView customPagerTitleView = new CustomPagerTitleView(context, i, list.size());
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);
                View customLayout = LayoutInflater.from(context).inflate(R.layout.simple_pager_title_layout, null);
                ImageView imageView = customLayout.findViewById(R.id.iv_title);
                TextView textView = customLayout.findViewById(R.id.tv_title);
                GlideManager.loadImageByUrl(ActivityLegalAdviserZaiZhiList.this, list.get(i).getImg(), imageView);
//                imageView.setImageResource(R.drawable.helper);
                textView.setText(list.get(i).getLabel());
                commonPagerTitleView.setContentView(customLayout);
                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {
                    @Override
                    public void onSelected(int i, int i1) {
                        textView.setTextColor(getResources().getColor(R.color.textColor_collect_audio_Select));
                        GlideManager.loadImageByUrl(ActivityLegalAdviserZaiZhiList.this, list.get(i).getNav_img(), imageView);
                    }

                    @Override
                    public void onDeselected(int i, int i1) {
                        textView.setTextColor(getResources().getColor(R.color.textColor_99));
                        GlideManager.loadImageByUrl(ActivityLegalAdviserZaiZhiList.this, list.get(i).getImg(), imageView);
                    }

                    @Override
                    public void onLeave(int i, int i1, float v, boolean b) {
//                        textView.setScaleX(0.8f + (0.5f - 0.8f) * v);
//                        textView.setScaleY(0.8f + (0.5f - 0.8f) * v);
                        textView.setTextSize(10);
                    }

                    @Override
                    public void onEnter(int i, int i1, float v, boolean b) {
//                        textView.setScaleX(0.5f + (0.8f - 0.5f) * v);
//                        textView.setScaleY(0.5f + (0.8f - 0.5f) * v);
                        textView.setTextSize(13);
                    }
                });
                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(i);
                    }
                });
                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magicIndicator1.setNavigator(commonNavigator);
    }
}
