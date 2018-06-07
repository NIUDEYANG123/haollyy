package com.haolyy.haolyy.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haolyy.haolyy.R;


/**
 * Created by wy on 2016/6/17.
 */
public class VeticalDoubleTextView extends LinearLayout {
    private TextView mTv2;//下部textview
    private TextView mTv1;//上部textview
    private VeticalDoubleTextView instance;
    private int textColorTop;//上部颜色
    private int textSizeTop;//上部大小
    private int textColorBottom;//下部颜色
    private int textSizeBottom;//下部大小
    private String textTop;//上部文本
    private String textBottom;//下部文本
    private int textGravity = 1;//textview的文本是否剧中
    private Drawable ivLeft;
    private Drawable ivRight;
    private int drablepadding;
    private int textmargin;

    private LinearLayout llroot;
    private int tstyle;

    public VeticalDoubleTextView(Context context) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public VeticalDoubleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        instance = this;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.VeticalDoubleTextView);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.VeticalDoubleTextView_textColorTop:
                    textColorTop = a.getColor(attr, Color.parseColor("#ffffff"));
                    break;
                case R.styleable.VeticalDoubleTextView_textSizeTop:
                    textSizeTop = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.VeticalDoubleTextView_textColorBottom:
                    textColorBottom =
                            a.getColor(attr, Color.parseColor("#ffffff"));
                    break;
                case R.styleable.VeticalDoubleTextView_textSizeBottom:
                    textSizeBottom = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.VeticalDoubleTextView_textTop:
                    textTop = a.getString(attr);
                    break;
                case R.styleable.VeticalDoubleTextView_textBottom:
                    textBottom = a.getString(attr);
                    break;
                case R.styleable.VeticalDoubleTextView_textGravity:
                    textGravity = a.getInt(attr, 1);
                    break;
                case R.styleable.VeticalDoubleTextView_tstyle:
                    tstyle = a.getInt(attr, -1);
                    break;
                case R.styleable.VeticalDoubleTextView_textmargin:
                    textmargin = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 6, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.VeticalDoubleTextView_ivleft:
                    ivLeft = a.getDrawable(attr);
                    break;
                case R.styleable.VeticalDoubleTextView_ivright:
                    ivRight = a.getDrawable(attr);
                    break;
                case R.styleable.VeticalDoubleTextView_textdrablepadding:
                    drablepadding = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    break;
                default:
                    break;
            }
        }
        a.recycle();
   /*     if (textGravity == 0) {
            llroot.setGravity(Gravity.CENTER);
            this.setGravity(Gravity.CENTER);
        }else if(textGravity==2){
            llroot.setGravity(Gravity.RIGHT);
            this.setGravity(Gravity.RIGHT);
        }*/
        mTv1 = new TextView(context);
        mTv2 = new TextView(context);
        mTv1.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeTop);
        mTv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeBottom);
        mTv2.setTextColor(textColorBottom);
        mTv1.setTextColor(textColorTop);
        mTv1.setCompoundDrawablePadding(drablepadding);
        mTv2.setCompoundDrawablePadding(drablepadding);
        mTv2.setGravity(Gravity.CENTER);
        mTv1.setGravity(Gravity.CENTER);
        // mTv1.setBackgroundColor(UIUtils.getColor(R.color.white));
        if (null != ivRight) {
            ivRight.setBounds(0, 0, ivRight.getMinimumWidth(), ivRight.getMinimumHeight());
            mTv1.setCompoundDrawables(null, null, ivRight, null);
        } else if (null != ivLeft) {
            ivLeft.setBounds(0, 0, ivLeft.getMinimumWidth(), ivLeft.getMinimumHeight());
            mTv2.setCompoundDrawables(ivLeft, null, null, null);
        }
        mTv2.setText(textBottom);
        mTv1.setText(textTop);
        if (tstyle == 0) {
            mTv1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
        } else if (tstyle == 1) {
            mTv2.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
        }
        this.setOrientation(VERTICAL);
        LayoutParams layouttop = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layouttop.bottomMargin = textmargin;
        LayoutParams layoutbottom = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(mTv1, layouttop);
        addView(mTv2, layoutbottom);

    }

    public VeticalDoubleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTextTop(String textTop) {
        mTv1.setText(textTop);
    }

    public void setTextBottom(String textBottom) {
        mTv2.setText(textBottom);
    }

}
