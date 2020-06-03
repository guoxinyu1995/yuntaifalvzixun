package com.ytfu.yuntaifawu.ui.mseeage.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mseeage.bean.UserEvaluateBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.UserEvaluateBean1;
import com.ytfu.yuntaifawu.ui.mseeage.bean.UserEvaluateClassBean;

public interface IUserEvaluateView extends BaseView {
    void onUserEvaluateSuccess(UserEvaluateBean userEvaluateBean);
    void onUserEvaluateClassSuccess(UserEvaluateClassBean userEvaluateClassBean);
    void onUserEvaluateFiled();
}
