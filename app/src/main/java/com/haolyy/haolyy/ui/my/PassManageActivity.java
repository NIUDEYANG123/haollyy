package com.haolyy.haolyy.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.config.ConstantKey;
import com.haolyy.haolyy.custom.SwitchButton;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.custom.dialog.DialogOpen;
import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.entity.bank.QueryCardInfoBean;
import com.haolyy.haolyy.guesture.GesturePwdLoginActivity;
import com.haolyy.haolyy.guesture.GesturePwdSetActivity;
import com.haolyy.haolyy.ui.my.View.SettingView;
import com.haolyy.haolyy.ui.my.presenter.SettingPresenter;
import com.haolyy.haolyy.ui.third.OpenBankActivity;
import com.haolyy.haolyy.ui.third.ShWebActivity;
import com.haolyy.haolyy.utils.AppToast;
import com.haolyy.haolyy.utils.LockPatternUtils;
import com.haolyy.haolyy.utils.ToastAlone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.haolyy.haolyy.base.BaseApplication.step;

/**
 * Created by shanghai on 2018/3/5.
 */

public class PassManageActivity extends BaseActivity<SettingPresenter, SettingView> implements SettingView  {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.tv_reset_pass)
    TextView tvResetPass;
    @BindView(R.id.tv_reset_pay)
    TextView tvResetPay;
    @BindView(R.id.tv_reset_gesture)
    TextView tvResetGesture;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;
    @BindView(R.id.tv_transaction_pass)
    TextView tvTransactionPass;
    private boolean isLock;

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_manage);
        ButterKnife.bind(this);
        initListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        isLock = !TextUtils.isEmpty(LockPatternUtils.getLockPattern(mContext, ConstantKey.GESTURE_STATE_KEY));


        if (isLock) {
            switchButton.setCheckedNoEvent(true);

        } else {
            switchButton.setCheckedNoEvent(false);
        }
    }

    private void initListener() {
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                closeActivity();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //去设置手势密码
                    startActivity(GesturePwdSetActivity.getIntentInstance(mContext, 1));

                } else {
                    //验证手势密码，正确后清除
                    startActivity(GesturePwdLoginActivity.getIntentGestureLogin(mContext, "setting_close"));
                }
            }
        });
    }

    @OnClick({R.id.tv_reset_pass, R.id.tv_reset_pay, R.id.tv_reset_gesture,R.id.tv_transaction_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_reset_pass:
                openActivity(new Intent(mContext, ChangePassActivity.class));
                break;
            case R.id.tv_reset_pay:
                break;
            case R.id.tv_reset_gesture:

                if (isLock) {

                    startActivity(GesturePwdLoginActivity.getIntentGestureLogin(mContext, "setting_modify"));
                } else {
                    ToastAlone.showShortToast(mContext, "请开启手势密码");
                }

                break;
            case R.id.tv_transaction_pass:
                if (step < 3) {
                    showD();
                } else{
                    // TODO: 2018/5/17 调修改交易密码的接口 跳汇付

                    AppToast.makeShortToast(mContext,"修改交易密码");
                }
                break;
        }
    }
    public void showD() {
        DialogOpen dialogOpen = new DialogOpen(this, step);
        dialogOpen.setOnDoubleClickListener(() -> {
            if (step == 1) {
                openActivity(new Intent(mContext, OpenBankActivity.class));
            } else if (step == 2) {
                //去激活(先去信息查询)
                mPresenter.queryBank();
            }
        }).show();
    }

    @Override
    public void pushActivity(String s) {
        Intent intent = new Intent(mContext, ShWebActivity.class);
        intent.setAction(s);
        startActivityForResult(intent, 101);
    }

    @Override
    public void showName(QueryCardInfoBean queryCardInfoBean) {

    }

    @Override
    public void showRiskLevel(String s) {

    }

    @Override
    public void showBank(QueryBankBean queryBankBean) {

    }

    @Override
    public void selectModifyBankPhone(BaseBean baseBean) {

    }
}
