package com.haolyy.haolyy.guesture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.ActivityCollector;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.config.ConstantKey;
import com.haolyy.haolyy.custom.CustomerDialog;
import com.haolyy.haolyy.custom.GestureLockView;
import com.haolyy.haolyy.model.WymanModel;
import com.haolyy.haolyy.utils.AppToast;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.LockPatternUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 2016/5/9. 设置手势密码
 */
public class GesturePwdSetActivity extends BaseActivity {

    @BindView(R.id.ll_tips)
    LinearLayout llTips;
    @BindView(R.id.iv_tip)
    ImageView ivTip;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    /**
     * 系统当前的日期、标题、提示
     */
    private TextView tv_day, tv_month, tv_alert;
    /**
     * 手势密码的类
     */
    private GestureLockView gesture_lock_view;
    /**
     * 手势小图案图像集合
     */
    private ArrayList<ImageView> views = new ArrayList<>();
    /**
     * 动画
     */
    private TranslateAnimation animation;
    /**
     * 输入的手势密码
     */
    private String skey = "";
    /**
     * 限制错误数次
     */
    private int limitErrorNum = 2;
    private int num = 0;
    private static final int RESULT_CODE = 2;
    private ImageView newImg;
    private CustomerDialog customerDialog;
    private CustomerDialog customerDialog2;
    private int type;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fl_activity_set_gesture);
        ButterKnife.bind(this);
        init();
        setGesturePwd();
    }

    public static Intent getIntentInstance(Context context, int type) {
        Intent intent = new Intent(context, GesturePwdSetActivity.class);
        intent.putExtra("type", type);
        return intent;
    }

    public void init() {

        type = getIntent().getIntExtra("type", -1);
        customerDialog = new CustomerDialog(mContext);
        tv_day = (TextView) findViewById(R.id.tv_day);
        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_alert = (TextView) findViewById(R.id.tv_alert);
        gesture_lock_view = (GestureLockView) findViewById(R.id.gesture_lock_view);
        // 从日历获取日期
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        tv_day.setText(day + "");
        // 月份数字转英文
        tv_month.setText(CommonUtils.englishMonth(month));

        views.clear();
        views.add((ImageView) findViewById(R.id.iv_1));
        views.add((ImageView) findViewById(R.id.iv_2));
        views.add((ImageView) findViewById(R.id.iv_3));
        views.add((ImageView) findViewById(R.id.iv_4));
        views.add((ImageView) findViewById(R.id.iv_5));
        views.add((ImageView) findViewById(R.id.iv_6));
        views.add((ImageView) findViewById(R.id.iv_7));
        views.add((ImageView) findViewById(R.id.iv_8));
        views.add((ImageView) findViewById(R.id.iv_9));
    }


    /**
     * 设置手势密码
     */
    public void setGesturePwd() {
        animation = new TranslateAnimation(-20, 20, 0, 0);
        animation.setDuration(50);
        animation.setRepeatCount(2);
        animation.setRepeatMode(Animation.REVERSE);

        gesture_lock_view.setOnGestureFinishListener(new GestureLockView.OnGestureFinishListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void OnGestureFinish(boolean success, String key) {
                if (key.length() < 4) {
                    tv_alert.setText("请至少连续绘制4个点！");
                    tv_alert.setTextColor(Color.parseColor("#F54D3C"));
                    ivTip.setVisibility(View.VISIBLE);
                    llTips.startAnimation(animation);
                    setImage(key);
                    return;
                }
                num++;
                if (skey.equals(key)) {
                    //设置成功

                    // TODO: 2018/3/2 设置手势密码成功
                    LockPatternUtils.saveLockPattern(GesturePwdSetActivity.this, ConstantKey.GESTURE_STATE_KEY, key);


                    WymanModel.getInstance().gesture(BaseApplication.juid, "1", new ProgressSubscriber<>(new SubscriberOnNextListener<BaseBean>() {
                        @Override
                        public void onNext(BaseBean baseBean) {
                            if (Config.success.equals(baseBean.code)) {
                                switch (type) {
                                    case 0: //登录成功后设置手势密码
                                        startActivity(new Intent(mContext, MainActivity.class));
                                        break;
                                    case 1: //账户中心设置手势密码
                                        finish();
                                        break;

                                    case 2://账户中心修改设置手势密码
                                        ActivityCollector.finishActivity(GesturePwdSetActivity.class);
                                        ActivityCollector.finishActivity(GesturePwdLoginActivity.class);
                                        break;
                                }
                            } else {
                                UIUtils.showToastCommon(mContext, baseBean.msg);
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            AppToast.showShortText(mContext, "网络异常，请稍后重试");
                        }
                    }, mContext));



//                    if (FingerprintUtil.isHaveFingerPrint()) {//可以开启指纹解锁
//
//                        customerDialog.touchId(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                if (view.getId() == R.id.touch_left) {
//                                    customerDialog.dismiss();
//                                    FingerprintUtil.cancel();
//                                    // TODO: 2018/2/7 不开启指纹解锁，跳转
//                                } else if (view.getId() == R.id.touch_right) {
//                                    customerDialog.dismiss();
//                                    FingerprintUtil.checkFingerPrint(new FingerprintUtil.OnCheckListener() {
//                                        @Override
//                                        public void onAuthenticationStart() {
//                                            LogUtils.e("onAuthenticationStart");
//                                            customerDialog2 = new CustomerDialog(mContext);
//                                            customerDialog2.checkTouchId(new View.OnClickListener() {
//                                                @Override
//                                                public void onClick(View view) {
//                                                    customerDialog2.dismiss();
//                                                    FingerprintUtil.cancel();
//                                                    // TODO: 2018/2/7 不开启指纹解锁，跳转
//                                                }
//                                            }).show();
//                                        }
//
//                                        @Override
//                                        public void onAuthenticationError(int errMsgId, CharSequence errString) {
//                                            AppToast.makeShortToast(mContext, errString.toString());
//                                            customerDialog2.dismiss();
//                                            FingerprintUtil.cancel();
//                                            // TODO: 2018/2/7 错误次数太多，跳转
//                                        }
//
//                                        @Override
//                                        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
//                                            AppToast.makeShortToast(mContext, "验证成功");
//                                            customerDialog2.dismiss();
//                                            FingerprintUtil.cancel();
//                                            // TODO: 2018/2/7 记录该应用开启指纹解锁
//                                            startActivity(new Intent(mContext,GesturePwdLoginActivity.class));
//                                        }
//                                    });
//
//
//                                }
//                            }
//                        }).show();
//
//                    } else {//不开启指纹解锁
//
//                    }


                } else {
                    if (num < limitErrorNum) {
                        gesture_lock_view.setKey(key);
                        skey = key;
                        tv_alert.setTextColor(Color.parseColor("#3A6EFF"));
                        tv_alert.setText("请再次绘制手势密码");
                        ivTip.setVisibility(View.GONE);
                        llTips.startAnimation(animation);
                    } else {
                        gesture_lock_view.setKey("");
                        skey = "";
                        tv_alert.setText("再次输入的密码不一致");
                        tv_alert.setTextColor(Color.parseColor("#F54D3C"));
                        ivTip.setVisibility(View.VISIBLE);

                        llTips.startAnimation(animation);
                        num = 0;
                    }
                    setImage(key);
                    llTips.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void addImg() {
        newImg = new ImageView(this);
        //设置想要的图片，相当于android:src="@drawable/image"
        newImg.setImageResource(R.drawable.icon_tip);
        //设置子控件在父容器中的位置布局，wrap_content,match_parent
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        // 也可以自己想要的宽度，参数（int width, int height）均表示px
        // 如dp单位，首先获取屏幕的分辨率在求出密度，根据屏幕ppi=160时，1px=1dp
        //则公式为 dp * ppi / 160 = px ——> dp * dendity = px
        //如设置为48dp：1、获取屏幕的分辨率 2、求出density 3、设置
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float density = displayMetrics.density;
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                (int) (48 * density),
                (int) (48 * density));

        //相当于android:layout_marginLeft="8dp"
        params1.leftMargin = 8;

        //addView(View child)，默认往已有的view后面添加，后插入，不设置布局params,默认wrap_content
//        llTips.addView(newImg);

        //addView(View child, LayoutParams params)，往已有的view后面添加，后插入,并设置布局
//        llTips.addView(newImg,params1);

        //addView(View view,int index, LayoutParams params),在某个index处插入
        llTips.addView(newImg, 0, params);
    }

    /**
     * 设置小图案的路径
     *
     * @param image
     */
    public void setImage(String image) {
        System.out.println(image);
        // 设置提示为空
        for (int j = 0; j < views.size(); j++) {
            views.get(j).setImageResource(R.drawable.node_normal);
        }
        // 设置提示信息
        if (!"".equals(image)) {
            int temp = Integer.valueOf(image);
            if (image.startsWith("0")) {
                views.get(0).setImageResource(R.drawable.node_small_normal);
            }
            while (temp > 0) {
                views.get(temp % 10).setImageResource(R.drawable.node_small_normal);
                temp = temp / 10;
            }
        }
    }

    @OnClick(R.id.iv_close)
    public void onViewClicked() {
        if (type == 0) {
            finish();
            startActivity(new Intent(mContext, MainActivity.class));
        }

    }
}
