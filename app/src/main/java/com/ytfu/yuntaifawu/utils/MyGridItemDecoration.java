package com.ytfu.yuntaifawu.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 007
 * @date 2018/6/24
 * @description Grid列表分割线 - 根据屏幕宽度自动分配间距
 */
public class MyGridItemDecoration extends RecyclerView.ItemDecoration {

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
    public MyGridItemDecoration(int spanCount, float spacing) {
        this.spanCount = spanCount;
        this.spacing = DensityUtil.dip2px(spacing);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);
        // item所在列
        int column = position % spanCount;

        /*if(column != 0) {// 每行第1个不设置左边距
            outRect.left = spacing - column * spacing / spanCount;
        }
        if(column != (spanCount - 1)) {// 每行最后一个不设置右边距
            outRect.right = (column + 1) * spacing / spanCount;
        }*/
        outRect.left = (spanCount - column) * spacing / (spanCount - 1);
        outRect.right = (column + 1) * spacing / (spanCount - 1);
        // top edge
        if (position < spanCount) {
            outRect.top = spacing;
        }
        // item_recycle_qsz bottom
        outRect.bottom = spacing;
    }
}