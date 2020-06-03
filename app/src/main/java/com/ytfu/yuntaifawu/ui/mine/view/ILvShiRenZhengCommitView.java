package com.ytfu.yuntaifawu.ui.mine.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.LvShiRenZhengCommitBean;

/**
*
*  @Auther  gxy
*
*  @Date    2020/5/22
*
*  @Des  律师认证提交view
*
*/
public interface ILvShiRenZhengCommitView extends BaseView {
    void onLvShiCommitSuccess(LvShiRenZhengCommitBean lvShiRenZhengCommitBean);
    void onLvShiCommitFiled();
}
