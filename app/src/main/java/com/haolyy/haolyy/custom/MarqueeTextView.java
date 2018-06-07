package com.haolyy.haolyy.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * Created by wy on 2017/3/6.
 */

public class MarqueeTextView extends android.support.v7.widget.AppCompatTextView {

    /**
     * 是否停止滚动
     */
    private boolean mStopMarquee;
    private String mText;
    private float mCoordinateX;
    private float mTextWidth;
    private Rect mTextBound;
    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mTextBound = new Rect();
    }

    public void setText(String text) {
        this.mText = text;
        mTextWidth = getPaint().measureText(mText);
        getPaint().getTextBounds(mText,0,mText.length(),mTextBound);
        if (mHandler.hasMessages(0))
            mHandler.removeMessages(0);
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    @Override
    protected void onAttachedToWindow() {
        mStopMarquee = false;
        if (!TextUtils.isEmpty(mText))
            mHandler.sendEmptyMessageDelayed(0, 2000);
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        mStopMarquee = true;
        if (mHandler.hasMessages(0))
            mHandler.removeMessages(0);
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!TextUtils.isEmpty(mText))
            canvas.drawText(mText, mCoordinateX+100, mTextBound.height(), getPaint());
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (Math.abs(mCoordinateX) > (mTextWidth + 100)) {
                        mCoordinateX = 0;
                        invalidate();
                        if (!mStopMarquee) {
                            sendEmptyMessageDelayed(0, 2000);
                        }
                    } else {
                        mCoordinateX -= 1;
                        invalidate();
                        if (!mStopMarquee) {
                            sendEmptyMessageDelayed(0, 30);
                        }
                    }

                    break;
            }
            super.handleMessage(msg);
        }
    };

}
