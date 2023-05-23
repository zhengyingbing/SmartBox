package com.bing.box.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bing.box.utils.Logger;

public class CommonDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public CommonDecoration(Context context){
        final TypedArray a = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int spanCount = getSpanCount(parent);
        drawHorzontal(c, parent, spanCount);
        drawVertical(c, parent, spanCount);
    }

    /**
     * 画横线
     * @param c
     * @param parent
     */
    private void drawHorzontal(Canvas c, RecyclerView parent, int spanCount){
        int childCount = parent.getChildCount();
        int rawCount = childCount / spanCount;
        if (childCount % spanCount != 0){
            rawCount = rawCount + 1;
        }

        for (int i = 0; i < childCount; i++){
            if (!isLastRaw(parent, i, spanCount, childCount)){
                View chidView = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) chidView.getLayoutParams();
                int left = chidView.getLeft() - params.leftMargin;
                int right = chidView.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
                if (i < spanCount){
                    right = right - 3;
                }
                int top = chidView.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight() - 2;
                Logger.i("画横线：" + left + ", " + top + ", " + right + ", " + bottom);
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }

    /**
     * 画竖线
     * @param c
     * @param parent
     */
    private void drawVertical(Canvas c, RecyclerView parent, int spanCount){
        int childCount = parent.getChildCount();
        Logger.i("spanCount = " + spanCount);
        for (int i = 0; i < childCount; i++){
            if (i % spanCount != (spanCount - 1)){
                View chidView = parent.getChildAt(i);
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) chidView.getLayoutParams();
                int top = chidView.getTop() - params.topMargin;
                int bottom = chidView.getBottom() + params.bottomMargin;
                int left = chidView.getRight() + params.rightMargin;
                int right = left + mDivider.getIntrinsicWidth() - 2;
                if (i < spanCount){
                    left = left - 3;
                    right = right - 3;
                }
                Logger.i("画竖线：" + left + ", " + top + ", " + right + ", " + bottom);
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }

        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.indexOfChild(view);
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastColum(parent, position, spanCount, childCount)){
            if (position == (childCount - 1)){
                outRect.set(0, 0, 0, 0);
            }else{
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            }
        }else if (isLastRaw(parent, position, spanCount, childCount)){
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }else{
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
        }

    }

    /**
     * 获取行数
     * @return
     */
    private int getSpanCount(RecyclerView parent){
        int spanCount = 1;
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            spanCount = ((GridLayoutManager) manager).getSpanCount();
        }else if(manager instanceof StaggeredGridLayoutManager){
            spanCount = ((StaggeredGridLayoutManager) manager).getSpanCount();
        }
        return spanCount;
    }

    /**
     * 是否最后一列
     * @param parent
     * @param itemPosition
     * @param spanCount
     * @param childCount
     * @return
     */
    private boolean isLastColum(RecyclerView parent, int itemPosition, int spanCount, int childCount){
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            if ((itemPosition + 1) / spanCount == 0){
                return true;
            }
        }else if (manager instanceof StaggeredGridLayoutManager){
            int orientation = ((StaggeredGridLayoutManager) manager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL){
                if ((itemPosition + 1) / spanCount == 0){
                    return true;
                }
            }else{
                childCount = childCount - childCount & spanCount;
                if (itemPosition >= childCount){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isLastRaw(RecyclerView parent, int itemPosition, int spanCount, int childCount){
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            int last = childCount % spanCount;
            if (last == 0){
                last = spanCount;
            }
            childCount = childCount - last;
            if (itemPosition >= childCount){
                return true;
            }
        }else if (manager instanceof StaggeredGridLayoutManager){
            int orientation = ((StaggeredGridLayoutManager) manager).getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL){
                int last = childCount % spanCount;
                if (last == 0){
                    last = spanCount;
                }
                childCount = childCount - last;
                if (itemPosition >= childCount){
                    return true;
                }
            }else {
                if ((itemPosition + 1) % spanCount == 0){
                    return true;
                }
            }
        }
        return false;
    }
}
