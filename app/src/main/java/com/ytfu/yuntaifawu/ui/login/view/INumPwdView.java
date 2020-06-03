package com.ytfu.yuntaifawu.ui.login.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.login.bean.LoginPwdBean;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/15
*
*  @Des 账号密码登录view
*
*/
public interface INumPwdView extends BaseView {
    void onNumPwdSuccess(LoginPwdBean pwdBean);
    void onNumPwdFiled();
}
