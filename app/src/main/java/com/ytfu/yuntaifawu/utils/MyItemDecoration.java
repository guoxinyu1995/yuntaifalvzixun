package com.ytfu.yuntaifawu.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author 007
 * @date 2018/6/24
 * @description 列表分割线 需要RecycleView设置背景色
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 默认分割线高度
     */
    private Float height = 0.5f;

    public MyItemDecoration() {

    }

    /**
     * @param height 分割线高度（dp）
     */
    public MyItemDecoration(float height) {
        this.height = height;
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        // 第一个ItemView不需要在上面绘制分割线
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = DensityUtil.dip2px(height);
        }
    }
}