package com.ytfu.yuntaifawu.base;

import com.github.lee.mvp.base.BasicView;

/**
 * @作者 gxy
 * @创建时间 2019/11/9
 * @描述
 */
public interface BaseView extends BasicView {

    void runOnUi(Runnable runnable);

}
