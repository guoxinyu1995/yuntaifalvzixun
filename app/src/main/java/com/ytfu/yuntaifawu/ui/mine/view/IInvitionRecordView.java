package com.ytfu.yuntaifawu.ui.mine.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.InvitationRecordListBean;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/17
*
*  @Des  邀请记录view
*
*/
public interface IInvitionRecordView extends BaseView {
    void onInvitionSuccess(InvitationRecordListBean listBean);
    void onFiled();
}
