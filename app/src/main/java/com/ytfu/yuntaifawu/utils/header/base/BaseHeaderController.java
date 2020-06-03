package com.ytfu.yuntaifawu.utils.header.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.StringRes;

public abstract class BaseHeaderController<T> {
    protected Context mContext;
    protected LayoutInflater mInflater;
    private View headerView;

    public BaseHeaderController(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.headerView = onCreateHeaderView();
        onInitView();
        onInitSetListener();
        onInitSetData();
    }

    /**
     * 子类实现创建一个显示在界面的header
     */
    protected abstract View onCreateHeaderView();

    protected void onInitView() {

    }

    protected void onInitSetListener() {
    }

    protected void onInitSetData() {

    }


    //===Desc:=================================================================

    /**
     * 外界调用渲染header
     */
    public void render(T data) {
    }

    public View getHeaderView() {
        return headerView;
    }
    //===Desc:=================================================================

    protected View inflateView(@LayoutRes int layoutId) {
        return mInflater.inflate(layoutId, null, false);
    }

    protected <V extends View> V findHeaderViewById(@IdRes int id) {
        return headerView.findViewById(id);
    }

    protected void setViewVisible(@IdRes int id) {
        findHeaderViewById(id).setVisibility(View.VISIBLE);
    }

    protected void setViewInvisible(@IdRes int id) {
        findHeaderViewById(id).setVisibility(View.INVISIBLE);
    }

    protected void setViewGone(@IdRes int id) {
        findHeaderViewById(id).setVisibility(View.GONE);
    }

    protected void setVIewText(@IdRes int viewId, String text) {
        TextView tv = findHeaderViewById(viewId);
        tv.setText(text);
    }

    protected void setVIewText(@IdRes int viewId, @StringRes int textId) {
        TextView tv = findHeaderViewById(viewId);
        tv.setText(textId);
    }


}
