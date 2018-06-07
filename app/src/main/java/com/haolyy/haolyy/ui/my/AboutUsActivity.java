package com.haolyy.haolyy.ui.my;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.config.Config;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.custom.dialog.DialogDoubleButtom;
import com.haolyy.haolyy.utils.WYUtils;
import com.tbruyelle.rxpermissions.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shanghai on 2018/3/8.
 */

public class AboutUsActivity extends BaseActivity {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_new_version)
    TextView tvNewVersion;
    @BindView(R.id.ll_feed_back)
    LinearLayout llFeedBack;
    @BindView(R.id.ll_answer)
    LinearLayout llAnswer;
    @BindView(R.id.tv_we_chat)
    TextView tvWeChat;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.ll_us)
    LinearLayout llUs;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_us);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvVersion.setText("版本号 " + WYUtils.getAppVersionName(mContext));
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                closeActivity();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });
    }

    @OnClick({R.id.tv_new_version, R.id.ll_feed_back, R.id.ll_answer, R.id.tv_we_chat, R.id.tv_phone, R.id.ll_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_new_version:
                break;
            case R.id.ll_feed_back:
                break;
            case R.id.ll_answer:
           openActivity(WebActivity.getWebIntent(mContext,"常见问题",NetConstantValues.CONTRACT_URL+"about/4.html"));
                break;
            case R.id.tv_we_chat:
                break;
            case R.id.tv_phone:
                DialogDoubleButtom dialogDoubleButtom = new DialogDoubleButtom(this);
                dialogDoubleButtom.setText(Config.service_phone, "").setOnDoubleClickListener(new DialogDoubleButtom.OnDoubleClickListener() {
                    @Override
                    public void executeLeft() {

                    }

                    @Override
                    public void executeRight() {
                        RxPermissions rxPermissions = new RxPermissions(AboutUsActivity.this);
                        rxPermissions.request(Manifest.permission.CALL_PHONE)
                                .subscribe(aBoolean -> {
                                    if (aBoolean) {
                                        //用户同意
                                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(Config.permisson_service_phone));
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    } else {

                                    }
                                });
                    }
                }).show();

                break;
            case R.id.ll_us:
                startActivity(WebActivity.getWebIntent(mContext, "关于我们", NetConstantValues.CONTRACT_URL + "about/aboutUs.html"));
                break;
        }
    }
}
