package com.haolyy.haolyy.custom;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.utils.AccountUtil;

/**
 * Created by shanghai on 2018/2/23.
 */

public class CustomizedProgressBar extends View {
    private float maxCount = 100; //进度条最大值
    private float currentCount; //进度条当前值
    // private Paint mPaint ;
    private int mWidth, mHeight;//整体布局的高度宽度（小黄点的高度加指示器的高度，宽度）
    private int mHeight2, mWidth2;//进度条的高度,宽度
    private int top;//进度条顶部偏移量
    private int left;//进度条左边偏移量
    private Context mContext;
    private String text;
    private boolean isGray;

    public CustomizedProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    public CustomizedProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }
    public CustomizedProgressBar(Context context) {
        super(context);
        initView(context);
    }
    private void initView(Context context) {
        mContext = context;
        mHeight2 = dipToPx(3);
        top = dipToPx(23);
        left = dipToPx(13);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth2 = mWidth - dipToPx(28);
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        int round = mHeight2 / 2; //半径

        mPaint.setColor(getResources().getColor(R.color.white_alpha)); //设置边框背景颜色
        RectF rectBg = new RectF(left, top, mWidth2 + left, mHeight2 + top);
        canvas.drawRoundRect(rectBg, round, round, mPaint);//绘制 最外面的大 圆角矩形，背景为白色

        float section = currentCount / maxCount; //进度条的比例
        RectF rectProgressBg = new RectF(left, top, mWidth2 * section + left, mHeight2 + top);

        //Paint设置setColor(白色无透明)和setShader，只让setShader生效；不然前面setColor设置了透明度，透明度会生效，和setShader效果叠加
        mPaint.setColor(getResources().getColor(R.color.white));
        mPaint.setShader(getLinearGradient());
        canvas.drawRoundRect(rectProgressBg, round, round, mPaint); //最左边的圆角矩形
        Resources res = mContext.getResources();
        if (isGray&&currentCount<100) {
            Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.dot_gray_progress);
            canvas.drawBitmap(bmp, mWidth2 * section - 6 + left, dipToPx(19), mPaint);
            Bitmap bmp2 = BitmapFactory.decodeResource(res, R.drawable.pao_gray_progres);
            canvas.drawBitmap(bmp2, mWidth2 * section, 0, mPaint);
        } else if(currentCount==100){
            Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.dot_gray_progress);
            canvas.drawBitmap(bmp, mWidth2 * section - 6 + left, dipToPx(19), mPaint);
            Bitmap bmp2 = BitmapFactory.decodeResource(res, R.drawable.pao_gray_progres);
            canvas.drawBitmap(bmp2, mWidth2 * section - 6, 0, mPaint);
        }else {
            mPaint.setShader(getLinearGradient());
            Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.dot_progress);
            canvas.drawBitmap(bmp, mWidth2 * section - 6 + left, dipToPx(19), mPaint);
            Bitmap bmp2 = BitmapFactory.decodeResource(res, R.drawable.pao_progress);
            canvas.drawBitmap(bmp2, mWidth2 * section, 0, mPaint);
        }
        Paint mPaintT = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintT.setColor(Color.parseColor("#FFFFFF"));
        mPaintT.setTextSize(dipToPx(8));
        Rect rect = new Rect();
        mPaintT.getTextBounds(text, 0, text.length(), rect);
        int width = rect.width();//文本的宽度
        int height = rect.height();//文本的高度
         if(currentCount==100&&!isGray){
            canvas.drawText(text, mWidth2 * section-4, height * 3 / 2, mPaintT);
        }else {
            canvas.drawText(text, mWidth2 * section + 4, height * 3 / 2, mPaintT);
         }

       /* if (maxCount != currentCount){ //如果不是100%，绘制第三段矩形
            RectF rectProgressBg2 = new RectF(mWidth2*section-round, top, mWidth2*section, mHeight2+top);
            mPaint.setShader(getLinearGradient());
            canvas.drawRect(rectProgressBg2, mPaint);
        }*/
    }

    private LinearGradient linearGradient;

    private LinearGradient getLinearGradient() {
        //getWidth()
        if (!isGray&&currentCount<100) {
            linearGradient = new LinearGradient(left, top, left + mWidth2, mHeight2 + top, new int[]{mContext.getResources().getColor(R.color.progress_color_1),
                    mContext.getResources().getColor(R.color.progress_color_2)}, null, Shader.TileMode.CLAMP); //根据R文件中的id获取到color
        }else {
            linearGradient = new LinearGradient(left, top, left + mWidth2, mHeight2 + top, new int[]{mContext.getResources().getColor(R.color.ebebeb),
                    mContext.getResources().getColor(R.color.ebebeb)}, null, Shader.TileMode.CLAMP); //根据R文件中的id获取到color
        }
        return linearGradient;
    }

    private int dipToPx(int dip) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f * (dip >= 0 ? 1 : -1));
    }

    /***
     * 设置最大的进度值
     * @param maxCount 最大的进度值
     */
    public void setMaxCount(float maxCount) {
        this.maxCount = maxCount;
    }

    /***
     * 设置当前的进度值
     * @param currentCount 当前进度值
     */

    public void setCurrentCount(float currentCount) {
        if (currentCount < 0.1f&&currentCount>0f) {
            this.currentCount = 0.1f;
        } else if(currentCount<100f&&currentCount>99.9f){
            this.currentCount=99.9f;
        }else {
            this.currentCount = currentCount > maxCount ? maxCount : currentCount;
        }
        text = AccountUtil.singleDouble(this.currentCount) + "%";
        invalidate();
    }

    public void setGray(boolean isGray){
        this.isGray=isGray;
    }
    public float getMaxCount() {
        return maxCount;
    }

    public float getCurrentCount() {
        return currentCount;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.EXACTLY || widthSpecMode == MeasureSpec.AT_MOST) {
            mWidth = widthSpecSize;
        } else {
            mWidth = 0;
        }
        if (heightSpecMode == MeasureSpec.AT_MOST || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            mHeight = dipToPx(28);
        } else {
            mHeight = heightSpecSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }
}
