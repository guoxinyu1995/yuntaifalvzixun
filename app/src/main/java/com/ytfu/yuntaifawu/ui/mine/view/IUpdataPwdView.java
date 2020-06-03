package com.ytfu.yuntaifawu.ui.mine.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.UpdataPwdBean;

/**
*
*  @Auther  gxy
*
*  @Date    2020/1/6
*
*  @Des 修改密码view
*
*/
public interface IUpdataPwdView extends BaseView {
    void onUpDataSuccess(UpdataPwdBean updataPwdBean);
    void onUpDataFiled();

}
