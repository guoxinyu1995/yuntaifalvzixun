package com.ytfu.yuntaifawu.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
    private static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    private static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private int orientation;
    private int color;
    private int space;
    private int padding;

    public RecyclerViewItemDecoration(int orientation, @ColorInt int color, int space, int padding) {
        this.orientation = orientation;
        this.color = color;
        this.space = space;
        this.padding = padding;
    }

    private Rect mRect = new Rect(0, 0, 0, 0);
    private Paint mPaint = new Paint();

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        switch (orientation) {
            case HORIZONTAL_LIST:
                drawHorizontal(c, parent);
                break;
            default:
                drawVertical(c, parent);
                break;

        }

    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft() + padding;
        int right = parent.getWidth() - parent.getPaddingRight() - padding;
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            RecyclerView.Adapter adapter = parent.getAdapter();
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + space;
            mRect.set(left, top, right, bottom);
            mPaint.setColor(color);
            c.drawRect(mRect, mPaint);
        }
    }

    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop() + padding;
        int bottom = parent.getHeight() - parent.getPaddingBottom() - padding;
        for (int i = 0; i < parent.getChildCount(); i++) {
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
        } else {
            outRect.set(0, 0, space, 0);
        }
    }


    //===Desc:=================================================================
    public static RecyclerViewItemDecoration createVertical(@ColorInt int color,
                                                            int height,
                                                            int padding) {
        return new RecyclerViewItemDecoration(VERTICAL_LIST, color, height, padding);

    }

    public static RecyclerViewItemDecoration createHorizontal(@ColorInt int color,
                                                              int height,
                                                              int padding) {
        return new RecyclerViewItemDecoration(HORIZONTAL_LIST, color, height, padding);

    }


}
