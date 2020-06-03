package com.ytfu.yuntaifawu.ui.mine.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.CollectionListBean;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/16
*
*  @Des  我的收藏音频view
*
*/
public interface ICollectionView extends BaseView {
    void onCollectionSuccess(CollectionListBean audioListBean);
    void onFiled();
}
