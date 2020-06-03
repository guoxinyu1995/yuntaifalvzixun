package com.ytfu.yuntaifawu.ui.lvshiwode.view;


import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.MineBean;

/**
 * 律师我的页面的View层
 */
public interface LawyerPersonalView extends BaseView {

    /**
     * 获取成功
     */
    void onGetPersonalSuccess(MineBean loginBean);

    /**
     * 获取失败
     */
    void onGetPersonalFail(String errorMsg);


}
