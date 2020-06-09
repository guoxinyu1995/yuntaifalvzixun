package com.ytfu.yuntaifawu.ui.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.ui.MainActivity;
import com.ytfu.yuntaifawu.ui.falvguwen.activity.ActivityLegalAdviser;
import com.ytfu.yuntaifawu.ui.home.activity.ActivityContractList;
import com.ytfu.yuntaifawu.ui.home.adaper.HomeLvShiAdaper;
import com.ytfu.yuntaifawu.ui.home.adaper.HomePingJiaAdaper;
import com.ytfu.yuntaifawu.ui.home.adaper.RecommendTitleAdaper;
import com.ytfu.yuntaifawu.ui.home.bean.HomeBannerBean;
import com.ytfu.yuntaifawu.ui.home.bean.HomeLvShiBean;
import com.ytfu.yuntaifawu.ui.home.bean.HomePingJIaBean;
import com.ytfu.yuntaifawu.ui.home.bean.RecommendListBean;
import com.ytfu.yuntaifawu.ui.home.presenter.RecommendPresenter;
import com.ytfu.yuntaifawu.ui.home.view.IRecommendView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.activity.ActivityOpenHelper;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityQiSuZhuang;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther gxy
 * @Date 2019/11/13
 * @Des 首页
 */
public class HomeFragment1 extends BaseFragment<IRecommendView, RecommendPresenter> implements IRecommendView, View.OnClickListener {

    private LinearLayout lin_zixun, lin_contract, lin_flgw, lin_qsz, lin_ktzs;
    private Banner banner;
    private RecyclerView recycle_lvshi, recycle_pingjia;
    private List<String> bannerList = new ArrayList<>();
    private RecommendTitleAdaper titleAdaper;
    private ImageView iv_contract;
    private HomeLvShiAdaper homeLvShiAdaper;
    private int page = 1;
    private HomePingJiaAdaper homePingJiaAdaper;
    private SmartRefreshLayout refreshLayout;
    private NestedScrollView mScrollView;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home1;
    }

    @Override
    protected RecommendPresenter createPresenter() {
        return new RecommendPresenter(getContext());
    }

    public static HomeFragment1 newInstance() {
        return new HomeFragment1();
    }

    @Override
    protected void initView(View rootView) {
        banner = rootView.findViewById(R.id.banner);
        lin_qsz = rootView.findViewById(R.id.lin_qsz);
        lin_zixun = rootView.findViewById(R.id.lin_zixun);
        lin_ktzs = rootView.findViewById(R.id.lin_ktzs);
//        lin_contract = rootView.findViewById(R.id.lin_contract);
        lin_flgw = rootView.findViewById(R.id.lin_flgw);
        recycle_lvshi = rootView.findViewById(R.id.recycle_lvshi);
        recycle_pingjia = rootView.findViewById(R.id.recycle_pingjia);
        iv_contract = rootView.findViewById(R.id.iv_contract);
        refreshLayout = rootView.findViewById(R.id.refresh_layout);
        mScrollView = rootView.findViewById(R.id.scroll_view);
        lin_zixun.setOnClickListener(this);
        lin_ktzs.setOnClickListener(this);
        lin_flgw.setOnClickListener(this);
        lin_qsz.setOnClickListener(this);
        iv_contract.setOnClickListener(this);
//        lin_contract.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ActivityContractList.class);
//                getActivity().startActivity(intent);
//            }
//        });
        //首页推荐律师
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_lvshi.setLayoutManager(linearLayoutManager);
        //首页用户评价
        LinearLayoutManager linearLayoutManagerPj = new LinearLayoutManager(getActivity());
        linearLayoutManagerPj.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_pingjia.setLayoutManager(linearLayoutManagerPj);
        //设置样式，里面有很多种样式可以自己都看看效果
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        banner.setIndicatorGravity(BannerConfig.CENTER);
        // 解决调用 notifyItemChanged 闪烁问题,取消默认动画
        ((SimpleItemAnimator) recycle_pingjia.getItemAnimator())
                .setSupportsChangeAnimations(false);
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setRefreshHeader(new MaterialHeader(App.getContext()).setColorSchemeResources(R.color.primaryColor, R.color.blue_3C, R.color.green_59));
        refreshLayout.setRefreshFooter(new BallPulseFooter(App.getContext()).setAnimatingColor(CommonUtil.getColor(R.color.primaryColor)));
    }

    @Override
    protected void initData() {
        //首页推荐律师
        homeLvShiAdaper = new HomeLvShiAdaper(getContext());
        recycle_lvshi.setAdapter(homeLvShiAdaper);
        homeLvShiAdaper.setLawyerListClickListener(() -> {
            if (App.getInstance().getLoginFlag()) {
                MainActivity activity = (MainActivity) getActivity();
                Eyes.setStatusBarColor(getActivity(),CommonUtil.getColor(R.color.transparent_4c));
                activity.changeTagStatus(2);
            } else {
                toLogin();
            }
        });
        //首页用户评价
        homePingJiaAdaper = new HomePingJiaAdaper(getContext());
        homePingJiaAdaper.setHasStableIds(true);
        recycle_pingjia.setAdapter(homePingJiaAdaper);
        homePingJiaAdaper.setRateListClickListener(() -> {
            if (App.getInstance().getLoginFlag()) {
                MainActivity activity = (MainActivity) getActivity();
                Eyes.setStatusBarColor(getActivity(), CommonUtil.getColor(R.color.transparent_4c));
                activity.changeTagStatus(2);
            } else {
                toLogin();
            }
        });
//        getPresenter().getRecommendList();
        //首页推荐律师
        getPresenter().getHomeLvShi();
        //首页用户评价
        setData();
        getPresenter().getBannerList();
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    // 向下滑动
                }

                if (scrollY < oldScrollY) {
                    // 向上滑动
                }

                if (scrollY == 0) {
                    // 顶部
                    refreshLayout.setOnRefreshListener(new OnRefreshListener() {
                        @Override
                        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                            setData();
                            getPresenter().getHomeLvShi();
                            refreshLayout.finishRefresh();
                        }
                    });
                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    // 上拉刷新实现
                    refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
                        @Override
                        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                            setDatas();
                            getPresenter().getHomeLvShi();
                            refreshLayout.finishLoadMore();      //加载完成
//                refreshLayout.finishLoadMoreWithNoMoreData();
                        }
                    });
                }
            }
        });


    }

    private void setDatas() {
        page++;
        getPresenter().getHomePingJia(page);
    }

    private void setData() {
        page = 1;
        getPresenter().getHomePingJia(page);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
//        getPresenter().getRecommendList();
        getPresenter().getBannerList();
    }

    @Override
    public void onRecommendSuccess(RecommendListBean listBeans) {
        hideLoading();
        if (listBeans != null || listBeans.getList() != null || !listBeans.getList().isEmpty()) {
//            List<RecommendListBean.HengfuBean> hengfu = listBeans.get(0).getHengfu();
//            List<RecommendListBean.ShufuBean> shufu = listBeans.get(0).getShufu();
            titleAdaper.setmList(listBeans.getList());
        }
    }

    @Override
    public void onRecommendFiled() {
        showToast("网络开小差");
    }

    //首页轮播图
    @Override
    public void onBannerSuccess(HomeBannerBean bannerBeans) {
        hideLoading();
        if (bannerBeans == null || bannerBeans.getList() == null || bannerBeans.getList().isEmpty()) {
            Logger.e("bannerBeans is empty");
        } else {
            GlideManager.loadImageByUrl(getContext(), bannerBeans.getUrl(), iv_contract);
            bannerList.clear();
            List<HomeBannerBean.ListBean> list = bannerBeans.getList();
            for (int i = 0; i < list.size(); i++) {
                bannerList.add(list.get(i).getPic());
            }
            //设置图片加载器
            banner.setImageLoader(new MyImageLoader());
            //设置图片集合
            banner.setImages(bannerList);
            banner.start();
        }
    }

    //首页推荐律师
    @Override
    public void onHomeLvShiSuccess(HomeLvShiBean homeLvShiBean) {
        hideLoading();
        if (homeLvShiBean == null && homeLvShiBean.getList() == null) {
            showEmpty();
        } else {
            homeLvShiAdaper.setBeanList(homeLvShiBean.getList());
        }
    }

    //首页用户评价
    @Override
    public void onHomePingJiaSuccess(HomePingJIaBean homePingJIaBean) {
        hideLoading();
        if (homePingJIaBean == null && homePingJIaBean.getList() == null) {
            showEmpty();
        } else {
            if (page == 1) {
                homePingJiaAdaper.setPingJiaBeanList(homePingJIaBean.getList());
            } else {
                homePingJiaAdaper.addPingJiaBeanList(homePingJIaBean.getList());
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lin_zixun:
                //&& DemoHelper.getInstance().isLoggedIn()
                if (App.getInstance().getLoginFlag()) {
//                    Intent intent = new Intent(getActivity(), ActivityContractList.class);
//                    getActivity().startActivity(intent);
                    MainActivity activity = (MainActivity) getActivity();
                    Eyes.setStatusBarColor(getActivity(), CommonUtil.getColor(R.color.transparent_4c));
                    activity.changeTagStatus(2);
                } else {
                    toLogin();
                }
                break;
            case R.id.lin_qsz:
                if (App.getInstance().getLoginFlag()) {
                    getActivity().startActivity(new Intent(getActivity(), ActivityQiSuZhuang.class));
                } else {
                    toLogin();
                }

                break;
            case R.id.lin_ktzs:
                if (App.getInstance().getLoginFlag()) {
                    getActivity().startActivity(new Intent(getActivity(), ActivityOpenHelper.class));
                } else {
                    toLogin();
                }
                break;
            case R.id.lin_flgw:
                if (App.getInstance().getLoginFlag()) {
                    getActivity().startActivity(new Intent(getActivity(), ActivityLegalAdviser.class));
                } else {
                    toLogin();
                }
                break;
            case R.id.iv_contract:
                if (App.getInstance().getLoginFlag()) {
                    getActivity().startActivity(new Intent(getActivity(), ActivityContractList.class));
                } else {
                    toLogin();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);

        }
    }

}
