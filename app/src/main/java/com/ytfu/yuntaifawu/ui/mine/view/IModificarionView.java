package com.ytfu.yuntaifawu.ui.mine.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.ModificationBean;
import com.ytfu.yuntaifawu.ui.mine.bean.PictureUploadBean;

public interface IModificarionView extends BaseView {
    void onPictureUpLoadSuccess(PictureUploadBean uploadBean);
    void onUploadFiled();
    void onModificartionSuccess(ModificationBean modificationBean);

}
