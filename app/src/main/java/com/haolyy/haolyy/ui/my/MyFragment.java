package com.haolyy.haolyy.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseApplication;
import com.haolyy.haolyy.base.BaseFragment;
import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.custom.dialog.DialogInvestTips;
import com.haolyy.haolyy.custom.dialog.DialogOpen;
import com.haolyy.haolyy.entity.my.HomePageBean;
import com.haolyy.haolyy.ui.account.CheckUsernameActivity;
import com.haolyy.haolyy.ui.my.View.MyView;
import com.haolyy.haolyy.ui.my.presenter.MyPresenter;
import com.haolyy.haolyy.ui.record.MyCouponActivity;
import com.haolyy.haolyy.ui.third.OpenBankActivity;
import com.haolyy.haolyy.ui.third.RechargeActivity;
import com.haolyy.haolyy.ui.third.ShWebActivity;
import com.haolyy.haolyy.ui.third.WithdrawActivity;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.SPUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.haolyy.haolyy.base.BaseApplication.accessToken;
import static com.haolyy.haolyy.base.BaseApplication.step;

/**
 * Created by haolyy on 2017/8/9.
 */

public class MyFragment extends BaseFragment<MyPresenter, MyView> implements MyView {
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.iv_message)
    ImageView ivMessage;
    @BindView(R.id.ck_eye)
    CheckBox ckEye;
    @BindView(R.id.tv_amount_total)
    TextView tvAmountTotal;
    @BindView(R.id.tv_yesterday_income)
    TextView tvYesterdayIncome;
    @BindView(R.id.tv_all_income)
    TextView tvAllIncome;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.tv_with_draw)
    TextView tvWithDraw;
    @BindView(R.id.ll_my_san)
    LinearLayout llMySan;
    @BindView(R.id.ll_my_plan)
    LinearLayout llMyPlan;
    @BindView(R.id.ll_my_bai)
    LinearLayout llMyBai;
    @BindView(R.id.tv_my_trans)
    TextView tvMyTrans;
    @BindView(R.id.tv_my_return)
    TextView tvMyReturn;
    @BindView(R.id.tv_my_post)
    TextView tvMyPost;
    @BindView(R.id.tv_my_quan)
    TextView tvMyQuan;
    @BindView(R.id.tv_my_friend)
    TextView tvMyFriend;
    @BindView(R.id.tv_my_vip)
    TextView tvMyVip;
    Unbinder unbinder;
    @BindView(R.id.btn_open)
    TextView btnOpen;
    @BindView(R.id.ll_have_open)
    LinearLayout llHaveOpen;
    @BindView(R.id.ll_my_top)
    LinearLayout llMyTop;
    @BindView(R.id.ll_not_open)
    LinearLayout llNotOpen;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_rw)
    LinearLayout llRw;
    @BindView(R.id.tv_my_balance)
    TextView tvMyBalance;
    @BindView(R.id.tv_my_san)
    TextView tvMySan;
    @BindView(R.id.tv_my_win)
    TextView tvMyWin;
    @BindView(R.id.iv_old)
    ImageView ivOld;
    private View view;
    private boolean issecret;
    private HomePageBean homePageBean;
    private DialogInvestTips dialogInvestTips;
    private boolean isVisibleToUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        dialogInvestTips = new DialogInvestTips(getActivity());
        dialogInvestTips.setText("敬请期待", "确认");
        issecret = SPUtils.getBoolean(mContext, "ISSECRET", false);
        ckEye.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (b) {
                SPUtils.saveBoolean(mContext, "ISSECRET", true);
                tvAmountTotal.setText("-- .--");
                tvYesterdayIncome.setText("-- .--");
                tvAllIncome.setText("-- .--");
                tvMyBalance.setText("-- .--");
                tvMyWin.setText("--.--");
                tvMySan.setText("--.--");
            } else {
                SPUtils.saveBoolean(mContext, "ISSECRET", false);
                tvAmountTotal.setText(AccountUtil.StringToMString(homePageBean.model.amountTotal));
                tvYesterdayIncome.setText(AccountUtil.StringToMString(homePageBean.model.yesterdayIncome));
                tvAllIncome.setText(AccountUtil.StringToMString(homePageBean.model.profitAll));
                tvMyBalance.setText(AccountUtil.StringToMString(homePageBean.model.availableAmount) + "元");
                tvMySan.setText(AccountUtil.StringToMString(homePageBean.model.borrowAmount) + "元");
                tvMyWin.setText(AccountUtil.StringToMString(homePageBean.model.appointmentBorrowAmount) + "元");
            }
        }));

    }


    @Override
    public void onResume() {
        super.onResume();
        if (BaseApplication.mLoginState) {
            mPresenter.selectUserState();
            if (BaseApplication.exist) {
                ivOld.setVisibility(View.VISIBLE);
            } else {
                ivOld.setVisibility(View.GONE);
            }
            tvPhone.setText(WYUtils.phoneSecret(BaseApplication.mUserName));
        } else {
            //显示未登录
            tvMyBalance.setText("--");
            tvMyWin.setText("--");
            tvMySan.setText("--");
            llMyTop.setBackgroundDrawable(UIUtils.getDrawable(R.drawable.bg_my));
            llNotOpen.setVisibility(View.VISIBLE);
            llHaveOpen.setVisibility(View.GONE);
            tvPhone.setTextColor(UIUtils.getColor(R.color.text_333333));
            tvPhone.setText("未登录");
            btnOpen.setText("注册/登录");
        }
    }


    @OnClick({R.id.iv_setting, R.id.iv_message, R.id.ll_my_san, R.id.ll_my_plan,
            R.id.ll_my_bai, R.id.tv_my_trans, R.id.tv_my_return, R.id.tv_my_post,
            R.id.tv_my_quan, R.id.tv_my_friend, R.id.tv_my_vip, R.id.tv_recharge, R.id.tv_with_draw, R.id.btn_open, R.id.iv_old})
    public void onViewClicked(View view) {
        //未登录的时候一切按钮跳转登陆
        if (!BaseApplication.mLoginState) {
            startActivity(new Intent(mContext, CheckUsernameActivity.class));
            return;
        }
        switch (view.getId()) {
            case R.id.iv_setting:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
            case R.id.iv_message:
                break;
            case R.id.ll_my_san:
                Intent intent = new Intent(mContext, ProductManageActivity.class);
                intent.putExtra("borrowType", "1");
                startActivity(intent);
                break;
            case R.id.ll_my_plan:
                Intent intent1 = new Intent(mContext, ProductManageActivity.class);
                intent1.putExtra("borrowType", "2");
                startActivity(intent1);
                break;
            case R.id.ll_my_bai:
                break;
            case R.id.tv_my_trans:
                startActivity(new Intent(mContext, TransactionRecordActivity.class));
                break;
            case R.id.tv_my_return:
                startActivity(new Intent(mContext, ReturnedAdvanceActivity.class));
                break;
            case R.id.tv_my_post:
                if (BaseApplication.riskCheck == 0) {
                    startActivity(WebActivity.getWebIntent(mContext, "风险测评", NetConstantValues.risk_testgl + "?userCode=" + BaseApplication.juid + "&token=" + accessToken + "&client=4"));
                    //startActivity(WebActivity.getWebIntent(mContext, "风险测评", NetConstantValues.CONTRACT_URL + "about/evaluating.html?userCode=" + BaseApplication.juid + "&client=4"));
                } else {
                    startActivity(new Intent(mContext, RiskResultActivity.class));
                }
                break;
            case R.id.tv_my_quan:
                startActivity(new Intent(mContext, MyCouponActivity.class));
                break;
            case R.id.tv_my_friend:
                //startActivity(WebActivity.getWebIntent(mContext,"邀请好友",NetConstantValues.invite));
                dialogInvestTips.show();
                break;
            case R.id.tv_my_vip:
                dialogInvestTips.show();
                break;
            case R.id.tv_recharge:
                if (step < 3) {
                    showD();
                } else {
                    startActivity(new Intent(mContext, RechargeActivity.class));
                }
                break;
            case R.id.tv_with_draw:
                if (step < 3) {
                    showD();
                } else {
                    startActivity(new Intent(mContext, WithdrawActivity.class));
                }
                break;
            case R.id.btn_open:
                if (step < 3) {
                    showD();
                } else {
                    startActivity(new Intent(mContext, OpenBankActivity.class));
                }
                break;
            case R.id.iv_old:
                startActivity(WebActivity.getWebIntent(mContext, "好利网", NetConstantValues.back_old + BaseApplication.juidMd5));
                break;
        }
    }

    @Override
    protected MyPresenter createPresenter() {
        return new MyPresenter(mContext);
    }

    public void showD() {
        DialogOpen dialogOpen = new DialogOpen(getActivity(), step);
        dialogOpen.setOnDoubleClickListener(new DialogOpen.OnDoubleClickListener() {
            @Override
            public void execute() {
                if (step == 1) {
                    startActivity(new Intent(mContext, OpenBankActivity.class));
                } else if (step == 2) {
                    //去激活(先去信息查询)
                    mPresenter.queryBank();
                }
            }
        }).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 显示未登录未开户的状态
     */
    @Override
    public void showMyFragmentStatus() {
        if (step == 1 || step == 2) {
            tvMyBalance.setText("--");
            tvMyWin.setText("--");
            tvMySan.setText("--");
            tvPhone.setTextColor(UIUtils.getColor(R.color.text_333333));
            btnOpen.setText("立即开户");
        } else {
            tvPhone.setTextColor(UIUtils.getColor(R.color.white));
            llMyTop.setBackgroundDrawable(UIUtils.getDrawable(R.drawable.gradient_fe7537));
            llNotOpen.setVisibility(View.GONE);
            llHaveOpen.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showAsset(HomePageBean homePageBean) {
        this.homePageBean = homePageBean;
        if (issecret) {
            tvAmountTotal.setText("-- .--");
            tvYesterdayIncome.setText("-- .--");
            tvAllIncome.setText("-- .--");
            tvMyBalance.setText("-- .--");
            tvMyWin.setText("--.--");
            tvMySan.setText("--.--");
        } else {
            tvAmountTotal.setText(AccountUtil.StringToMString(homePageBean.model.amountTotal));
            tvYesterdayIncome.setText(AccountUtil.StringToMString(homePageBean.model.yesterdayIncome));
            tvAllIncome.setText(AccountUtil.StringToMString(homePageBean.model.profitAll));
            tvMyBalance.setText(AccountUtil.StringToMString(homePageBean.model.availableAmount) + "元");
            tvMySan.setText(AccountUtil.StringToMString(homePageBean.model.borrowAmount) + "元");
            tvMyWin.setText(AccountUtil.StringToMString(homePageBean.model.appointmentBorrowAmount) + "元");
        }
    }

    @Override
    public void pushActivity(String s) {
        Intent intent = new Intent(mContext, ShWebActivity.class);
        intent.setAction(s);
        startActivityForResult(intent, 101);
    }
}
