package com.ytfu.yuntaifawu.ui.home.activity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.helper.ScaleTransitionPagerTitleView;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractListFirstBean;
import com.ytfu.yuntaifawu.ui.lvshihetong.fragment.ContractListSecondFragment;
import com.ytfu.yuntaifawu.utils.DensityUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther gxy
 * @Date 2019/11/18
 * @Des 合同列表分类
 */
public class ActivityContractList extends BaseActivity {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.et_collect_seatch)
    EditText etCollectSeatch;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.lin_view)
    View linView;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private List<String> indicatorList;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<ContractListFirstBean.ListBean> list;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_contract_list;
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
            window.setStatusBarColor(getResources().getColor(R.color.transparent_half));

        }
    }

    @Override
    protected void initView() {
//        hideLoading();
        tvTopTitle.setText("合同列表");

    }

    @Override
    protected void initData() {
        etCollectSeatch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getData();
    }

    //请求接口获取数据
    private void getData() {
        HttpUtil.getInstance().getApiService().getListFirst()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(RxLifecycleUtil.bindLifecycle(this))
                .subscribe(new BaseRxObserver<ContractListFirstBean>() {
                    @Override
                    public void onNextImpl(ContractListFirstBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus() && entity.getState() != null) {
                            onFirstList(entity);
                        } else {
                            onFiled();
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getNavTitle" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        onFiled();
                    }
                });
    }

    private void onFiled() {

    }

    private void onFirstList(ContractListFirstBean entity) {
        hideLoading();
        if (entity.getList() == null || entity.getList().isEmpty()) {
            return;
        } else {
            list = entity.getList();
            indicatorList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                indicatorList.add(list.get(i).getLabel());
            }
            initMagicIndicator();
            initViewPager();
        }
    }

    private void initViewPager() {
        fragmentList.clear();
        for (int i = 0; i < indicatorList.size(); i++) {
            ContractListSecondFragment visitorRecordFragment = new ContractListSecondFragment();
            Bundle contentBundle = new Bundle();
            contentBundle.putInt("indicator_tag", i);
            contentBundle.putString("id", list.get(i).getId());
            visitorRecordFragment.setArguments(contentBundle);
            fragmentList.add(visitorRecordFragment);
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
                    return indicatorList.size();
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
                return indicatorList == null ? 0 : indicatorList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
//                CustomPagerTitleView customPagerTitleView = new CustomPagerTitleView(context, index, indicatorList.size());
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(indicatorList.get(i));
                simplePagerTitleView.setTextSize(13);
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.title_text_select_color));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.textColor_99));
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
                indicator.setColors(getResources().getColor(R.color.title_text_select_color));
                indicator.setLineWidth(DensityUtil.dip2px(30));
                indicator.setLineHeight(DensityUtil.dip2px(3));
                indicator.setRoundRadius(DensityUtil.dip2px(2));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
    }

    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }
}
