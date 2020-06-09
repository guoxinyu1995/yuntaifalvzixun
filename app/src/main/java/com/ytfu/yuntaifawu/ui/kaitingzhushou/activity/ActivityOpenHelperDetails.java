package com.ytfu.yuntaifawu.ui.kaitingzhushou.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.helper.ScaleTransitionPagerTitleView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsXqTitleBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.fragment.FragmentKTZSXQList;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter.KtzsXqTitlePresenter;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IKtzsXqTitleView;
import com.ytfu.yuntaifawu.utils.DensityUtil;
import com.ytfu.yuntaifawu.utils.GlideManager;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/22
 * @Des 开庭助手详情
 */
public class ActivityOpenHelperDetails extends BaseActivity<IKtzsXqTitleView, KtzsXqTitlePresenter> implements IKtzsXqTitleView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.image_ktzs_png)
    ImageView imageKtzsPng;
    @BindView(R.id.tv_ktzs_xq_title)
    TextView tvKtzsXqTitle;
    @BindView(R.id.tv_come_in)
    TextView tvComeIn;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.rl_ktzs_title_icon)
    RelativeLayout rlKtzsTitleIcon;
    private List<Fragment> fragmentList = new ArrayList<>();
    private int img_type;
    private String id;
    private List<KtzsXqTitleBean.ListBean> list;
    /**
     * AppBarLayout是否已折叠
     */
    private boolean folded = false;
    private int jump_type;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_ktzs_xiangqing;
    }

    @Override
    protected KtzsXqTitlePresenter createPresenter() {
        return new KtzsXqTitlePresenter(this);
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
        tvTopTitle.setText("开庭助手");
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        img_type = intent.getIntExtra("img_type", -1);
        jump_type = intent.getIntExtra("jump_type", -1);
        if (img_type == 0) {
            rlKtzsTitleIcon.setVisibility(View.GONE);
        } else if (img_type == 1) {
            rlKtzsTitleIcon.setVisibility(View.VISIBLE);
        }
    }

    private void initViewPager() {
        fragmentList.clear();
        for (int i = 0; i < list.size(); i++) {
            FragmentKTZSXQList fragmentKTZSXQList = new FragmentKTZSXQList();
            Bundle contentBundle = new Bundle();
            contentBundle.putInt("indicator_tag", i);
            contentBundle.putString("id", list.get(i).getId());
            contentBundle.putString("sid",id);
            fragmentKTZSXQList.setArguments(contentBundle);
            fragmentList.add(fragmentKTZSXQList);
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
            ViewPagerHelper.bind(magicIndicator, viewPager);
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
            public IPagerTitleView getTitleView(Context context, final int i) {
//                CustomPagerTitleView customPagerTitleView = new CustomPagerTitleView(context, index, indicatorList.size());
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(list.get(i).getName());
                simplePagerTitleView.setTextSize(13);
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.textColor_collect_audio_Select));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.textColor_Details_Unselect));
                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(i);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setColors(getResources().getColor(R.color.textColor_collect_audio_Select));
                indicator.setLineWidth(DensityUtil.dip2px(43));
                indicator.setLineHeight(DensityUtil.dip2px(1));
                indicator.setRoundRadius(DensityUtil.dip2px(1));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
    }

    @Override
    protected void initData() {
        getPresenter().setKtzsXqTitle(id);
    }


    @OnClick({R.id.iv_fanhui, R.id.image_ktzs_png, R.id.tv_ktzs_xq_title, R.id.tv_come_in, R.id.rl_ktzs_title_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.image_ktzs_png:
            case R.id.tv_ktzs_xq_title:
            case R.id.tv_come_in:
                if(jump_type == 1){
                    //跳转到交通
                    startActivity(new Intent(ActivityOpenHelperDetails.this,ActivityScjd.class));
                }else if(jump_type == 2){
                    //跳转到劳动
                    startActivity(new Intent(ActivityOpenHelperDetails.this,ActivityGsjd.class));
                }
                break;
            case R.id.rl_ktzs_title_icon:
                break;
            default:
                break;
        }
    }
    @Override
    public void onSuccess(KtzsXqTitleBean titleBean) {
        hideLoading();
        if (titleBean == null || titleBean.getList() == null || titleBean.getList().isEmpty()) {
            return;
        } else {
            list = titleBean.getList();
            String type_id = titleBean.getType_id();
            initMagicIndicator();
            initViewPager();
        }
        if (titleBean == null || titleBean.getImg() == null) {
            return;
        } else {
            KtzsXqTitleBean.ImgBean img = titleBean.getImg();
            GlideManager.loadImageByUrl(this, img.getImg(), imageKtzsPng);
            tvKtzsXqTitle.setText(img.getLabel());
        }
    }

    @Override
    public void onFiled() {

    }
}
