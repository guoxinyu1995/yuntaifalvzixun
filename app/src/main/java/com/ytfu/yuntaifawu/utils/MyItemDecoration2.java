package com.ytfu.yuntaifawu.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;


/**
 * @author 007
 * @date 2018/6/24
 * @description 列表分割线
 */
public class MyItemDecoration2 extends RecyclerView.ItemDecoration {

    /**
     * 分割线高度（dp）
     */
    private int height = DensityUtil.dip2px(0.5f);
    /**
     * 分割线左右边距（dp）
     */
    private int left, right;
    /**
     * 分割线颜色
     */
    private int colorRes = R.color.textcolor_f2;
    private Paint paint;

    public MyItemDecoration2(Float left, Float right) {
        this.left = DensityUtil.dip2px(left);
        this.right = DensityUtil.dip2px(right);
        init();
    }

    public MyItemDecoration2(int colorRes) {
        this.colorRes = colorRes;
        init();
    }

    public MyItemDecoration2(Float left, Float right, int colorRes) {
        this.left = DensityUtil.dip2px(left);
        this.right = DensityUtil.dip2px(right);
        this.colorRes = colorRes;
        init();
    }

    public void init() {
        paint = new Paint();
        paint.setColor(CommonUtil.getColor(colorRes));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        // 第一个ItemView不需要在上面绘制分割线
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = height;
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int index = parent.getChildAdapterPosition(view);
            // 第一个ItemView不需要绘制
            if (index == 0) {
                continue;
            }

            float dividerTop = view.getTop() - height;
            float dividerLeft = parent.getPaddingLeft() + left;
            float dividerBottom = view.getTop();
            float dividerRight = parent.getWidth() - parent.getPaddingLeft() - right;
            c.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, paint);
        }

    }
}