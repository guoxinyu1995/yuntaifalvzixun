package com.ytfu.yuntaifawu.ui.users.v;

import com.ytfu.yuntaifawu.base.BaseRefreshView;
import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.home.bean.HomeBannerBean;

/**
*
*  @Auther  gxy
*
*  @Date    2020/6/9
*
*  @Des   用户端首页view
*
*/
public interface IUserHomeView extends BaseRefreshView<String> {
    //获取轮播图集合
    void userHomeBannerList(HomeBannerBean bannerBean);
    void userHomeFiled(String error);
}
