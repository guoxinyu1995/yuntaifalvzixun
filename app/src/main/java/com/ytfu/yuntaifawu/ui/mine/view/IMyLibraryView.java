package com.ytfu.yuntaifawu.ui.mine.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.MyLibraryBean;

public interface IMyLibraryView extends BaseView {
    void onMyLibrarySuccess(MyLibraryBean libraryBean);
    void onFiled();
    void onSendEmail(SendEmailBean emailBean);
    void onLibraryBdEmailSuccess(BdEmailBean bdEmailBean);
}
