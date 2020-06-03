package com.ytfu.yuntaifawu.ui.home.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.home.bean.HomeBannerBean;
import com.ytfu.yuntaifawu.ui.home.bean.HomeLvShiBean;
import com.ytfu.yuntaifawu.ui.home.bean.HomePingJIaBean;
import com.ytfu.yuntaifawu.ui.home.bean.RecommendListBean;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des  首页view
*
*/
public interface IRecommendView extends BaseView {
    //推荐列表
//    void onRecommendSuccess(List<RecommendListBean> listBeans);
    void onRecommendSuccess(RecommendListBean listBeans);
    void onRecommendFiled();
    //banner轮播
//    void onBannerSuccess(List<HomeBannerBean> bannerBeans);
    void onBannerSuccess(HomeBannerBean bannerBeans);
    //首页推荐律师
    void onHomeLvShiSuccess(HomeLvShiBean homeLvShiBean);
    //首页用户评价
    void onHomePingJiaSuccess(HomePingJIaBean homePingJIaBean);
}
