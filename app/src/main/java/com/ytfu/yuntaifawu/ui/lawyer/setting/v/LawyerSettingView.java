package com.ytfu.yuntaifawu.ui.lawyer.setting.v;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.InformationBean;

public interface LawyerSettingView extends BaseView {

    void onGetInformationSuccess(InformationBean info);

    void onGetInformationFail(String msg);
}
