package com.ytfu.yuntaifawu.ui.login.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.login.bean.ForgectDosePwdBean;
import com.ytfu.yuntaifawu.ui.login.bean.ForgetSendBean;

public interface IForgetPwdView extends BaseView {
    void onForgetPwdSuccess(ForgetSendBean forgetSendBean);
    void onForgetPweFilded();
    void onForgetDosetPwd(ForgectDosePwdBean dosePwdBean);
}
