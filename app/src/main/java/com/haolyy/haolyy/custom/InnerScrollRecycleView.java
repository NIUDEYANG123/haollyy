package com.haolyy.haolyy.custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by haolyy on 2017/5/19.
 * 解决滑动嵌套，scrollerView嵌套listView
 */

public class InnerScrollRecycleView extends RecyclerView {
    public InnerScrollRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InnerScrollRecycleView(Context context) {
        super(context);
    }

    public InnerScrollRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
