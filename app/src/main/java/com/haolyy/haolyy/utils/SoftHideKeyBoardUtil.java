package com.haolyy.haolyy.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * Created by haolyy on 2017/5/8.
 */

public class SoftHideKeyBoardUtil {
    public static void assistActivity(Activity activity) {
        new SoftHideKeyBoardUtil(activity);

    }


    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frLayoutParams;

    private SoftHideKeyBoardUtil(Activity activity) {
        //1.找到activity的最外层布局空间,它其实是一个decorView,它所用的空间就是framelayout
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        //2.获取到setContentView放进去的View
        mChildOfContent = content.getChildAt(0);
        //3.给activity的xml布局设置树监听,当布局有变化,如键盘弹出或收缩时,都会回调此监听
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //4.软键盘弹起会使onGlobalLayout发生变化
            @Override
            public void onGlobalLayout() {
        //5.当前布局发生变化时,对activity的xml布局进行重绘
                prossiblyResizeChildOfContent();
            }
        });
        //6.获取到activity的xml布局放置的参数
        frLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }

    /**
     * 获取界面可用高度,如果软键盘弹起后,activity的xml布局可用高度减去键盘高度
     */
    private void prossiblyResizeChildOfContent() {
        //1.获取当前界面可用高度,键盘弹起后,当前界面可用布局会减少键盘的高度
        int usableHeightNow=computeUsableHeight();
        //2.如果当期可用高度和原始值不一样
        if (usableHeightNow != usableHeightPrevious) {
            //3.获取activity中xml布局在当前界面显示的高度
            int usableHeightScansKeyBord = mChildOfContent.getRootView().getHeight();
            //4.activity中xml布局高度-当前可用高度
            int heightDifference = usableHeightScansKeyBord - usableHeightNow;
            //5.高度差大于屏幕1/4时,说明键盘弹出
            if (heightDifference > (usableHeightScansKeyBord / 4)) {
                //6.键盘弹出了,activity的xml布局高度应当减去键盘高度
                frLayoutParams.height = usableHeightScansKeyBord - heightDifference;
            } else {
                //6.键盘收起了,activity的xml布局高度应当和可用高度一样
                frLayoutParams.height = usableHeightScansKeyBord;
            }
            //7.重绘activity的xml布局
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        //全屏模式下:直接返回r.bottom,r.top其实是状态栏的高度
        return r.bottom - r.top;
    }
}
