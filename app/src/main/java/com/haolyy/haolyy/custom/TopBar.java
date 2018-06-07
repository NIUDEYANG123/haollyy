package com.haolyy.haolyy.custom;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haolyy.haolyy.R;


/**
 * Created by wy on 2017/1/13.
 * 导航栏
 */
public class TopBar extends RelativeLayout {
    private Button mLeftButton, mRightButton;
    private TextView mTitle;
    private boolean mDrawableLeftVisibility;
    private Drawable mDrawableLeft;
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;
    private float mLeftTextSize;
    private float mPaddingLeft;

    private boolean mDrawableRightVisibility;
    private Drawable mDrawableRight;
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;
    private float mRightTextSize;
    private float mPaddingRight;

    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitleText;

    private Drawable mBackgroundDrawable;

    private LayoutParams leftParams, rightParams, titleParams;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void OnLeftButtonClicked();

        void OnRightButtonClicked();
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    @SuppressWarnings("deprecation")
    @SuppressLint({"NewApi", "RtlHardcoded"})
    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mDrawableLeftVisibility = typedArray.getBoolean(R.styleable.TopBar_drawableLeftVisibility, false);
        mDrawableLeft = typedArray.getDrawable(R.styleable.TopBar_drawableLeft);
        mLeftText = typedArray.getString(R.styleable.TopBar_leftText);
        mLeftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, Color.RED);
        mLeftTextSize = typedArray.getDimension(R.styleable.TopBar_leftTextSize, 12);
        mLeftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);
        mPaddingLeft = typedArray.getDimension(R.styleable.TopBar_paddingLeft, 0);

        mDrawableRightVisibility = typedArray.getBoolean(R.styleable.TopBar_drawableRightVisibility, false);
        mDrawableRight = typedArray.getDrawable(R.styleable.TopBar_drawableRight);
        mRightText = typedArray.getString(R.styleable.TopBar_rightText);
        mRightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, Color.RED);
        mRightTextSize = typedArray.getDimension(R.styleable.TopBar_rightTextSize, 12);
        mRightBackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);
        mPaddingRight = typedArray.getDimension(R.styleable.TopBar_paddingRight, 10);

        mTitleTextSize = typedArray.getDimension(R.styleable.TopBar_titleTextSize, 12);
        mTitleTextColor = typedArray.getColor(R.styleable.TopBar_titleTextColor, Color.RED);
        mTitleText = typedArray.getString(R.styleable.TopBar_title);

        mBackgroundDrawable = typedArray.getDrawable(R.styleable.TopBar_top_background);

        typedArray.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitle = new TextView(context);

        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setText(mLeftText);
        mLeftButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLeftTextSize);
        mLeftButton.setBackgroundDrawable(mLeftBackground);
        mLeftButton.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        mLeftButton.setPadding((int) mPaddingLeft, 0, 0, 0);
        mLeftButton.setMinWidth(0);
        if (mDrawableLeftVisibility) {
            mDrawableLeft.setBounds(0, 0, mDrawableLeft.getMinimumWidth(), mDrawableLeft.getMinimumHeight());
            mLeftButton.setCompoundDrawables(mDrawableLeft, null, null, null);//左上又下
        }

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setText(mRightText);
        mRightButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRightTextSize);
        mRightButton.setBackgroundDrawable(mRightBackground);
        mRightButton.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        mRightButton.setPadding(0, 0, (int) mPaddingRight, 0);
        mRightButton.setMinWidth(0);
        if (mDrawableRightVisibility) {
            mDrawableRight.setBounds(0, 0, mDrawableRight.getMinimumWidth(), mDrawableRight.getMinimumHeight());
            mRightButton.setCompoundDrawables(null, null, mDrawableRight, null);
        }

        mTitle.setTextColor(mTitleTextColor);
        mTitle.setText(mTitleText);
        mTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTitleTextSize);
        mTitle.setGravity(Gravity.CENTER);
        TextPaint paint = mTitle.getPaint();
        //paint.setFakeBoldText(true);//字体加粗
        setBackgroundDrawable(mBackgroundDrawable);
        setGravity(Gravity.CENTER);

        leftParams = new LayoutParams(80, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        addView(mLeftButton, leftParams);

        rightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, rightParams);

        titleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(mTitle, titleParams);

        mLeftButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.OnLeftButtonClicked();
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.OnRightButtonClicked();
            }
        });
    }


    public void setTitle(CharSequence title) {
        mTitle.setText(title);
    }

    public void setmTitleTextColor(int mTitleTextColor) {
        mTitle.setTextColor(mTitleTextColor);
    }

    public void setmDrawableLeft(Drawable mDrawableLeft) {
        if (mDrawableLeftVisibility) {
            mDrawableLeft.setBounds(0, 0, mDrawableLeft.getMinimumWidth(), mDrawableLeft.getMinimumHeight());
            mLeftButton.setCompoundDrawables(mDrawableLeft, null, null, null);//左上又下
        }
    }

    public void showRightButton(boolean b, CharSequence text) {
        if (b) {
            mDrawableRight.setBounds(0, 0, mDrawableRight.getMinimumWidth(), mDrawableRight.getMinimumHeight());
            mRightButton.setCompoundDrawables(null, null, mDrawableRight, null);
            mRightButton.setText(text);
        } else {
            mRightButton.setCompoundDrawables(null, null, null, null);
            mRightButton.setText(text);
        }

    }

    public void setmPaddingRight(float mPaddingRight) {

        mRightButton.setPadding(0, 0, (int) mPaddingRight, 0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }

    public void setmRightText(String mRightText) {
        mRightButton.setText(mRightText);
    }

    public void setRightButtonVisible(int visibility){
        mRightButton.setVisibility(visibility);
    }
}
