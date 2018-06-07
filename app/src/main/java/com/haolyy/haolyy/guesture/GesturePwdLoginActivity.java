package com.haolyy.haolyy.guesture;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.config.ConstantKey;
import com.haolyy.haolyy.custom.CustomerDialog;
import com.haolyy.haolyy.custom.GestureLockView;
import com.haolyy.haolyy.model.WymanModel;
import com.haolyy.haolyy.ui.account.CheckUsernameActivity;
import com.haolyy.haolyy.utils.AppToast;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.LockPatternUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

/**
 * Created by User on 2016/5/9. 手势密码登录
 */
public class GesturePwdLoginActivity extends BaseActivity {
    @BindView(R.id.iv_tip)
    ImageView ivTip;
    @BindView(R.id.ll_tips)
    LinearLayout llTips;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    /**
     * 系统当前的日期、手机号、错误提示、忘记手势密码
     */
    private TextView tv_day, tv_month, tv_alert, tv_forget_pwd;
    /**
     * 手势密码的类
     */
    private GestureLockView gesture_lock_view;
    /**
     * 动画
     */
    private TranslateAnimation animation;
    private int error_count = 4;
    private CustomerDialog customerDialog;
    private String type;
    private Intent intent;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    public static Intent getIntentGestureLogin(Context context, String type) {
        Intent intent = new Intent(context, GesturePwdLoginActivity.class);
        intent.putExtra("type", type);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fl_activity_gesture_login);
        ButterKnife.bind(this);
        init();
        setGesturePwd();
    }

    public void init() {

        type = getIntent().getStringExtra("type");

        customerDialog = new CustomerDialog(mContext);
        tv_day = (TextView) findViewById(R.id.tv_day);
        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_alert = (TextView) findViewById(R.id.tv_alert);
        gesture_lock_view = (GestureLockView) findViewById(R.id.gesture_lock_view);
        tv_forget_pwd = (TextView) findViewById(R.id.tv_forget_pwd);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        tv_day.setText(day + "");
        //月份数字转英文
        tv_month.setText(CommonUtils.englishMonth(month));

//        String formatTel = CommonUtils.FormatTel(BaseApplication.mUser.getMobile());
//        tv_phone.setText(formatTel);


        animation = new TranslateAnimation(-20, 20, 0, 0);
        animation.setDuration(50);
        animation.setRepeatCount(2);
        animation.setRepeatMode(Animation.REVERSE);


    }

    @Override
    protected void onResume() {
        super.onResume();
        if ("setting_modify".equals(type)) {//密码管理，修改手势密码
            tv_alert.setTextColor(Color.parseColor("#2B6FF9"));
            tv_alert.setVisibility(View.VISIBLE);
            tv_alert.setText("请输入原始手势密码");
            llTips.startAnimation(animation);
            ivTip.setVisibility(View.GONE);

        } else {
            tv_alert.setTextColor(Color.parseColor("#2B6FF9"));
            tv_alert.setVisibility(View.VISIBLE);
            tv_alert.setText("请输入手势密码");
            llTips.startAnimation(animation);
            ivTip.setVisibility(View.GONE);
        }
    }

    /**
     * 设置手势密码
     */
    public void setGesturePwd() {

        // 获取手势密码

        gesture_lock_view.setKey(LockPatternUtils.getLockPattern(this, ConstantKey.GESTURE_STATE_KEY));

        // 手势完成后回
        gesture_lock_view.setOnGestureFinishListener(new GestureLockView.OnGestureFinishListener() {
            @Override
            public void OnGestureFinish(boolean success, String key) {
                if (success) {

                    // TODO: 2018/3/2 手势密码登录成功


                    if ("setting_close".equals(type)) {//密码管理，关闭手势密码

                        WymanModel.getInstance().gesture(BaseApplication.juid, "0", new ProgressSubscriber<>(new SubscriberOnNextListener<BaseBean>() {
                            @Override
                            public void onNext(BaseBean baseBean) {
                                if (Config.success.equals(baseBean.code)) {
                                    //  切换账户  ，去登陆
                                    LockPatternUtils.saveLockPattern(mContext, ConstantKey.GESTURE_STATE_KEY, null);
                                    finish();
                                } else {
                                    UIUtils.showToastCommon(mContext, baseBean.msg);
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                AppToast.showShortText(mContext, "网络异常，请稍后重试");
                            }
                        }, mContext));

                    } else if ("setting_modify".equals(type)) {//密码管理，修改手势密码
                        startActivity(GesturePwdSetActivity.getIntentInstance(mContext, 2));
                    } else {
                        tv_alert.setTextColor(Color.parseColor("#2B6FF9"));
                        tv_alert.setVisibility(View.VISIBLE);
                        tv_alert.setText("登录成功");
                        llTips.startAnimation(animation);
                        ivTip.setVisibility(View.GONE);
                        startActivity(new Intent(mContext, MainActivity.class));
                    }

                } else {
                    if (error_count <= 0) {
                        customerDialog.gesture(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                customerDialog.dismiss();
                                // TODO: 2018/3/2 手势密码登录错误5次，重新登录
                                startActivity(new Intent(mContext, CheckUsernameActivity.class));
                            }
                        }).show();
                        return;
                    }
                    tv_alert.setTextColor(Color.parseColor("#F54D3C"));
                    tv_alert.setVisibility(View.VISIBLE);
                    tv_alert.setText("密码错误，你还可以尝试" + error_count + "次");
                    ivTip.setVisibility(View.VISIBLE);
                    llTips.startAnimation(animation);
                    error_count--;
                }
            }
        });
    }


    @OnClick({R.id.tv_forget_pwd, R.id.tv_switch_login, R.id.iv_close})
    public void onViewClicked(View view) {


        switch (view.getId()) {
            case R.id.tv_forget_pwd:
                //  忘记手势密码  ，去登陆
            case R.id.tv_switch_login:

                WymanModel.getInstance().gesture(BaseApplication.juid, "0", new ProgressSubscriber<>(new SubscriberOnNextListener<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (Config.success.equals(baseBean.code)) {
                            //  切换账户  ，去登陆
                            intent = new Intent(mContext, CheckUsernameActivity.class);
                            intent.setAction("logout");
                            WYUtils.clearData();
                            startActivity(intent);
                        } else {
                            UIUtils.showToastCommon(mContext, baseBean.msg);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        AppToast.showShortText(mContext, "网络异常，请稍后重试");
                    }
                }, mContext));


                break;
            case R.id.iv_close:
                finish();
                break;
        }
    }


}
