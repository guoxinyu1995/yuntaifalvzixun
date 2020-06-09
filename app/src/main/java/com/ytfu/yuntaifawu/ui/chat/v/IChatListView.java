package com.ytfu.yuntaifawu.ui.chat.v;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.chat.bean.LawyerListBean;

import java.util.List;

public interface IChatListView extends BaseView {

    void onLawyerListSuccess(List<LawyerListBean.LawyerItemBean> list);

    void onLawyerListFail(String msg);

    void reLoadList();

}
