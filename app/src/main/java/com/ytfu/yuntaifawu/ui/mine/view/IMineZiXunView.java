package com.ytfu.yuntaifawu.ui.mine.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.MineZiXunBean;
import com.ytfu.yuntaifawu.ui.mine.present.MinePresenter;

/**
*
*  @Auther  gxy
*
*  @Date    2020/5/19
*
*  @Des 我的咨询view
*
*/
public interface IMineZiXunView extends BaseView {
    void onMineZiXunSuccess(MineZiXunBean mineZiXunBean);
    void onMineZiXunFiled();
}
