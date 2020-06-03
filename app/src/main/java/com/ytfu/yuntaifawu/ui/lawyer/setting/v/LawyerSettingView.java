package com.ytfu.yuntaifawu.ui.lawyer.setting.v;

import com.ytfu.yuntaifawu.ui.mine.bean.InformationBean;

public interface LawyerSettingView {

    void onGetInformationSuccess(InformationBean info);

    void onGetInformationFail(String msg);
}
