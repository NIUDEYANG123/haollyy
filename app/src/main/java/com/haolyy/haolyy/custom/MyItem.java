package com.haolyy.haolyy.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.utils.UIUtils;

/**
 * Created by ndy on 2016/6/17.
 */
public class MyItem extends LinearLayout {
    private TextView mTv2;
    private TextView mTv1;
    private MyItem instance;
    private String textLeft;
    private String textRight;
    private Drawable ivLeft;
    private Drawable ivRight;

    private LinearLayout llroot;
    private int tstyle;
    private boolean arrow;
    private int lColor;
    private int rColor;

    public MyItem(Context context) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public MyItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        instance = this;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyItem);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MyItem_textLeft:
                    textLeft = a.getString(attr);
                    break;
                case R.styleable.MyItem_textRight:
                    textRight = a.getString(attr);
                    break;
                case R.styleable.MyItem_ivLeft:
                    ivLeft = a.getDrawable(attr);
                    break;
                case R.styleable.MyItem_isArrow:
                    arrow = a.getBoolean(attr, false);
                    break;
                case R.styleable.MyItem_leftColor:
                    lColor = a.getColor(attr, UIUtils.getColor(R.color.text_9b9b9b));
                    break;
                case R.styleable.MyItem_rightColor:
                    rColor = a.getColor(attr, UIUtils.getColor(R.color.text_4a4a4a));
                    break;
                default:
                    break;
            }
        }
        a.recycle();

        mTv1 = new TextView(context);
        mTv2 = new TextView(context);
        mTv1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        mTv2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        mTv2.setTextColor(rColor);
        mTv1.setTextColor(lColor);
        mTv1.setCompoundDrawablePadding(UIUtils.dip2px(12));
        mTv2.setCompoundDrawablePadding(UIUtils.dip2px(12));
        mTv2.setGravity(Gravity.CENTER);
        mTv1.setGravity(Gravity.CENTER);
         if (arrow) {
            ivRight = UIUtils.getDrawable(R.drawable.arrow_right);
            ivRight.setBounds(0, 0, ivRight.getMinimumWidth(), ivRight.getMinimumHeight());
            mTv2.setCompoundDrawables(null, null, ivRight, null);
            //ivLeft.setBounds(0, 0, ivLeft.getMinimumWidth(), ivLeft.getMinimumHeight());
            //mTv1.setCompoundDrawables(ivLeft, null, null, null);
            }
            mTv2.setText(textRight);
            mTv1.setText(textLeft);
            View view = new View(context);
            this.setOrientation(HORIZONTAL);
            this.setGravity(Gravity.CENTER_VERTICAL);
            LayoutParams layouttop = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layouttop.leftMargin = UIUtils.dip2px(12);
            LayoutParams layoutbottom = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutbottom.rightMargin = UIUtils.dip2px(12);
            LayoutParams layoutCenter = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutCenter.weight = 1;
            addView(mTv1, layouttop);
            addView(view, layoutCenter);
            addView(mTv2, layoutbottom);

        }

    public MyItem(Context context, AttributeSet attrs, int defStyleAttr){
            super(context, attrs, defStyleAttr);
        }

    public void setTextLeft(String textTop) {
        mTv1.setText(textTop);
    }

    public void setTextRight(String textBottom) {
        mTv2.setText(textBottom);
    }

}
