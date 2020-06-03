package com.ytfu.yuntaifawu.ui.mine.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.ShangChangLingYuBean;

/**
*
*  @Auther  gxy
*
*  @Date    2020/5/21
*
*  @Des  擅长领域view
*
*/
public interface IShanchanglingyuView extends BaseView {
    void onShangChangLingYuSuccess(ShangChangLingYuBean shangChangLingYuBean);
    void onShangChangLingYuFiled();
}
