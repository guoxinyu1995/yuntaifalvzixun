package com.ytfu.yuntaifawu.helper;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.appcompat.widget.AppCompatTextView;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;

/**
 * @author fans
 * @date 2019/5/8 15:48
 * @description MagicIndicator自定义title左右边距样式
 */
public class CustomPagerTitleView extends AppCompatTextView implements IMeasurablePagerTitleView {

    private int mSelectedColor;
    private int mNormalColor;
    private int mTextSize;

    private final int PADDING = 13;

    public CustomPagerTitleView(Context context) {
        super(context);
        init(context, -1, -1);
    }

    public CustomPagerTitleView(Context context, int index, int size) {
        super(context);
        init(context, index, size);
    }

    private void init(Context context, int index, int size) {
        /*int paddingRight, paddingLeft;
        if (0 == index) {
            // 首个tab设置左边距20dp
            paddingLeft = UIUtil.dip2px(context, 20);
            paddingRight = UIUtil.dip2px(context, PADDING);
        } else if ((size - 1) == index) {
            // 最后一个tab设置右边距20dp
            paddingLeft = UIUtil.dip2px(context, PADDING);
            paddingRight = UIUtil.dip2px(context, 20);
        } else {
            // 正常左右边距
            paddingLeft = UIUtil.dip2px(context, PADDING);
            paddingRight = UIUtil.dip2px(context, PADDING);
        }
        setPadding(paddingLeft, 0, paddingRight, 0);*/

        int padding = UIUtil.dip2px(context, PADDING);
        setPadding(padding, 0, padding, 0);
        setTextSize(UIUtil.dip2px(context, mTextSize));
        setGravity(Gravity.CENTER);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.END);
    }

    @Override
    public int getContentLeft() {
        Rect bound = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), bound);
        int contentWidth = bound.width();
        return getLeft() + getWidth() / 2 - contentWidth / 2;
    }

    @Override
    public int getContentTop() {
        Paint.FontMetrics metrics = getPaint().getFontMetrics();
        float contentHeight = metrics.bottom - metrics.top;
        return (int) (getHeight() / 2 - contentHeight / 2);
    }

    @Override
    public int getContentRight() {
        Rect bound = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), bound);
        int contentWidth = bound.width();
        return getLeft() + getWidth() / 2 + contentWidth / 2;
    }

    @Override
    public int getContentBottom() {
        Paint.FontMetrics metrics = getPaint().getFontMetrics();
        float contentHeight = metrics.bottom - metrics.top;
        return (int) (getHeight() / 2 + contentHeight / 2);
    }

    @Override
    public void onSelected(int i, int i1) {
        setTextColor(mSelectedColor);
    }

    @Override
    public void onDeselected(int i, int i1) {
        setTextColor(mNormalColor);
    }

    @Override
    public void onLeave(int i, int i1, float v, boolean b) {

    }

    @Override
    public void onEnter(int i, int i1, float v, boolean b) {

    }

    public void setSelectedColor(int mSelectedColor) {
        this.mSelectedColor = mSelectedColor;
    }

    public void setNormalColor(int mNormalColor) {
        this.mNormalColor = mNormalColor;
    }

    public void setTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
    }

    public void setContentView(View contentView) {
        setContentView(contentView);
    }
    public void setContentView(int layoutId) {
        View child = LayoutInflater.from(getContext()).inflate(layoutId, null);
        setContentView(child);
    }
}
