package com.ytfu.yuntaifawu.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 007
 * @date 2018/6/24
 * @description Grid列表分割线 - 指定相同间距
 */
public class MyGridItemDecoration2 extends RecyclerView.ItemDecoration {

    /**
     * 行item个数
     */
    private int spanCount;
    /**
     * 间距px
     */
    private int spacing;

    /**
     * @param spanCount spanCount
     * @param spacing   间距dp
     */
    public MyGridItemDecoration2(int spanCount, float spacing) {
        this.spanCount = spanCount;
        this.spacing = DensityUtil.dip2px(spacing);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);
        // item所在列
        int column = position % spanCount;

        // top edge
        outRect.top = 0;

        // left edge
        if (column == 0) {
            // 每行第一个不设置左边距
            outRect.left = 0;
        } else {
            outRect.left = spacing / 2;
        }

        // right edge
        if (column == spanCount - 1) {
            // 每行最后一个个不设置右边距
            outRect.right = 0;
        } else {
            outRect.right = spacing / 2;
        }

        // item_recycle_qsz bottom
        outRect.bottom = spacing;
    }
}