package com.ytfu.yuntaifawu.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView分割线
 */
public class ItemDecoration extends RecyclerView.ItemDecoration {
    private static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    private static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    public static ItemDecoration createVertical(@ColorInt int color,int height,int padding) {
        return new ItemDecoration(VERTICAL_LIST, color, height, padding);
    }
    public static ItemDecoration createHorizontal(@ColorInt int color,int height,int padding) {
        return new ItemDecoration(HORIZONTAL_LIST, color, height, padding);
    }


    private Rect mRect = new Rect(0, 0, 0, 0);
    private Paint mPaint = new Paint();
    private int orientation = VERTICAL_LIST;


    private int color = Color.TRANSPARENT;
    private int space = 0;
    private int padding = 0;

    public ItemDecoration(int orientation, int color, int space, int padding) {
        this.orientation = orientation;
        this.color = color;
        this.space = space;
        this.padding = padding;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (orientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////

    private void drawVertical(@NonNull Canvas c, @NonNull RecyclerView parent) {
        int left = parent.getPaddingLeft() + padding;
        int right = parent.getWidth() - parent.getPaddingRight() - padding;
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + space;
            mRect.set(left, top, right, bottom);
            mPaint.setColor(color);
            c.drawRect(mRect, mPaint);
        }
    }

    private void drawHorizontal(@NonNull Canvas c, @NonNull RecyclerView parent) {
        int top = parent.getPaddingTop() + padding;
        int bottom = parent.getHeight() - parent.getPaddingBottom() - padding;
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin;
            int right = left + space;
            mRect.set(left, top, right, bottom);
            mPaint.setColor(color);
            c.drawRect(mRect, mPaint);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (orientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, space);
        }else{
            outRect.set(0, 0, space, 0);
        }
    }
    //    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
//        super.getItemOffsets(outRect, view, parent, state)
//        if (orientation == VERTICAL_LIST) {
//            outRect.set(0, 0, 0, space)
//        } else {
//            outRect.set(0, 0, space, 0)
//        }
//    }
}
