package com.ytfu.yuntaifawu.ui.home.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.home.bean.AudioNavBean;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des  音频标题view
*
*/
public interface INavView extends BaseView {
    void onNavSuccess(AudioNavBean navBean);
    void onNavFalied();

}
