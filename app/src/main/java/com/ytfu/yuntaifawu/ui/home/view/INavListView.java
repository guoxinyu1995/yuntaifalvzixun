package com.ytfu.yuntaifawu.ui.home.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.home.bean.AudioListBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioTopImageBean;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des  音频列表view
*
*/
public interface INavListView extends BaseView {
//    void onNavListSuccess(List<AudioListBean> beanList);
    void onNavListSuccess(AudioListBean beanList);
    void onNavListFiled();
//    void onNavImageSuccess(List<AudioTopImageBean> beanList);
    void onNavImageSuccess(AudioTopImageBean beanList);

}
