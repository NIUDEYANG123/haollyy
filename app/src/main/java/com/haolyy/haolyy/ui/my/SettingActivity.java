package com.haolyy.haolyy.ui.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseBean;
import com.haolyy.haolyy.base.RxBus;
import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.custom.dialog.DialogInvestTips;
import com.haolyy.haolyy.custom.dialog.DialogModifyPhone;
import com.haolyy.haolyy.custom.dialog.DialogOpen;
import com.haolyy.haolyy.entity.bank.QueryBankBean;
import com.haolyy.haolyy.entity.bank.QueryCardInfoBean;
import com.haolyy.haolyy.model.WymanModel;
import com.haolyy.haolyy.ui.my.View.SettingView;
import com.haolyy.haolyy.ui.my.presenter.SettingPresenter;
import com.haolyy.haolyy.ui.third.OpenBankActivity;
import com.haolyy.haolyy.ui.third.ResetBankActivity;
import com.haolyy.haolyy.ui.third.ShWebActivity;
import com.haolyy.haolyy.utils.AppToast;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;
import com.wyman.wangyin.mylibrary.ProgressSubscriber;
import com.wyman.wangyin.mylibrary.SubscriberOnNextListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.haolyy.haolyy.base.BaseApplication.accessToken;
import static com.haolyy.haolyy.base.BaseApplication.step;

/**
 * Created by shanghai on 2018/2/8.
 */

public class SettingActivity extends BaseActivity<SettingPresenter, SettingView> implements SettingView {
    @BindView(R.id.iv_setting_head)
    ImageView ivSettingHead;
    @BindView(R.id.tv_set_phone)
    TextView tvSetPhone;
    @BindView(R.id.tv_set_name)
    TextView tvSetName;
    @BindView(R.id.iv_bank_name)
    TextView ivBankName;
    @BindView(R.id.tv_bank_no)
    TextView tvBankNo;
    @BindView(R.id.tv_set_risk)
    TextView tvSetRisk;
    @BindView(R.id.tv_set_vip)
    TextView tvSetVip;
    @BindView(R.id.tv_set_invite)
    TextView tvSetInvite;
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.iv_horse)
    ImageView ivHorse;
    @BindView(R.id.ll_bind_phone)
    LinearLayout llBindPhone;
    @BindView(R.id.ll_real_name)
    LinearLayout llRealName;
    @BindView(R.id.out)
    TextView out;
    @BindView(R.id.tv_manage)
    TextView tvManage;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.ll_about)
    LinearLayout llAbout;
    @BindView(R.id.tv_bank_phone)
    TextView tvBankPhone;
    @BindView(R.id.ll_bank_phone)
    LinearLayout llBankPhone;
    private DialogInvestTips dialogInvestTips;
    private QueryCardInfoBean.ModelBean.UsrCardInfolistBean usrCardInfolistBean;
    private String oldMobile;
    private String bankCardNo;
    private String flag;
    private Subscription s;

    @Override
    protected SettingPresenter createPresenter() {
        return new SettingPresenter(mContext);
    }


    public static Intent getSettingIntent(Context context, String flag) {
        Intent intent = new Intent(context, SettingActivity.class);
        intent.putExtra("flag", flag);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        s = RxBus.getInstance().toObserverable(String.class).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
                    if ("guideSkip".equals(s)) {
                        //查询银行手机号是否修改成功
                        mPresenter.queryModifyTheBankPhone(BaseApplication.juid, BaseApplication.orderNo);

                    }
                });


        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                closeActivity();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });
        initView();


    }

    private void initView() {
        flag = getIntent().getStringExtra("flag");
        dialogInvestTips = new DialogInvestTips(this).setText("敬请期待", "确认");
        tvUserName.setText(WYUtils.phoneSecret(BaseApplication.mUserName));
        tvSetPhone.setText(WYUtils.phoneSecret(BaseApplication.mUserName));

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (step > 2) {
            //开过户在查姓名和银行卡信息
            mPresenter.getCardInfo();
        }
//        mPresenter.getRiskLevel();
        mPresenter.queryBankInfo();


    }

    @OnClick({R.id.iv_horse, R.id.ll_bind_phone, R.id.ll_real_name, R.id.out, R.id.tv_manage, R.id.tv_bank_no, R.id.tv_set_invite, R.id.tv_set_vip, R.id.tv_set_risk, R.id.ll_about, R.id.ll_bank_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_horse:
                dialogInvestTips.show();
                break;
            case R.id.ll_bind_phone:
                //openActivity(new Intent(mContext, PhoneAuthActivity.class));
                break;
            case R.id.ll_real_name:
                if (step < 3) {
                    showD();
                } else if (null != usrCardInfolistBean) {
                    openActivity(RealNameActivity.getReturnIntent(mContext, usrCardInfolistBean.UsrName, usrCardInfolistBean.CertId));
                }
                break;
            case R.id.out:
                WymanModel.getInstance().gesture(BaseApplication.juid, "0", new ProgressSubscriber<>(new SubscriberOnNextListener<BaseBean>() {
                    @Override
                    public void onNext(BaseBean baseBean) {
                        if (Config.success.equals(baseBean.code)) {
                            WYUtils.clearData();
                            openActivity(MainActivity.getMainIntent(mContext, 3));
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
            case R.id.tv_manage:
                openActivity(new Intent(mContext, PassManageActivity.class));
                break;
            case R.id.tv_bank_no:
                if (step < 3) {
                    showD();
                } else {
                    startActivity(new Intent(mContext, ResetBankActivity.class));
                }
                break;
            case R.id.tv_set_invite:
                //startActivity(WebActivity.getWebIntent(mContext,"邀请好友",NetConstantValues.invite));
                dialogInvestTips.show();
                break;
            case R.id.tv_set_vip:
                dialogInvestTips.show();
                break;
            case R.id.tv_set_risk:
                if (BaseApplication.riskCheck == 0) {
                    openActivity(WebActivity.getWebIntent(mContext, "风险测评", NetConstantValues.risk_testgl + "?userCode=" + BaseApplication.juid + "&token=" + accessToken + "&client=4"));
                    //openActivity(WebActivity.getWebIntent(mContext, "风险测评", NetConstantValues.CONTRACT_URL + "about/evaluating.html?userCode=" + BaseApplication.juid + "&client=4"));
                } else {
                    openActivity(new Intent(mContext, RiskResultActivity.class));
                    //openActivity(WebActivity.getWebIntent(mContext, "风险测评", NetConstantValues.CONTRACT_URL + "about/evaluating.html?userCode=" + BaseApplication.juid + "&client=4"));
                }
                break;
            case R.id.ll_about:
                openActivity(new Intent(mContext, AboutUsActivity.class));
                break;
            case R.id.ll_bank_phone:
                if (step < 3) {
                    showD();
                } else {
                    Intent intent = new Intent(mContext, ModifyBankPhoneActivity.class);
                    intent.putExtra("oldMobile", oldMobile);
                    intent.putExtra("bankCardNo", bankCardNo);
                    startActivity(intent);
                }
                break;
        }
    }

    /**
     * 激活
     *
     * @param s
     */
    @Override
    public void pushActivity(String s) {
        Intent intent = new Intent(mContext, ShWebActivity.class);
        intent.setAction(s);
        startActivityForResult(intent, 101);
    }

    @Override
    public void showName(QueryCardInfoBean queryCardInfoBean) {
        usrCardInfolistBean = queryCardInfoBean.model.UsrCardInfolist.get(0);
        tvSetName.setText(TextUtils.isEmpty(usrCardInfolistBean.UsrName) ? "未认证" : WYUtils.nameSecret(usrCardInfolistBean.UsrName));
        String cardId = usrCardInfolistBean.CardId;
        CommonUtils.showBankLogo(usrCardInfolistBean.BankId, ivBankName, cardId, tvBankNo);
        tvBankNo.setText("(" + usrCardInfolistBean.CardId.substring(cardId.length() - 4, cardId.length()) + ")");

    }

    @Override
    public void showRiskLevel(String s) {
        tvSetRisk.setText(s);

    }

    @Override
    public void showBank(QueryBankBean queryBankBean) {
        tvBankPhone.setText(WYUtils.phoneProcess(queryBankBean.model.Mobile));
        bankCardNo = queryBankBean.model.BankCardNo;
        oldMobile = queryBankBean.model.Mobile;
    }

    @Override
    public void selectModifyBankPhone(BaseBean baseBean) {
        if (Config.success.equals(baseBean.code)) {
            //成功  弹窗
            new DialogModifyPhone(mContext).setContent("修改手机号成功！", R.drawable.icon_modify_phone_success).show();
        } else {
            //失败弹窗
            new DialogModifyPhone(mContext).setContent("修改手机号失败！", R.drawable.icon_modify_phone_error).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            //激活成功查询银行卡信息
            mPresenter.getCardInfo();
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
    protected void onDestroy() {
        super.onDestroy();
        if (s != null) {
            s.unsubscribe();
        }
    }
}
