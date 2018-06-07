package com.haolyy.haolyy.ui.third;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.custom.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wyman
 * @date 2018/2/7
 * description :
 */

public class WithdrawError extends BaseActivity {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.btn_next)
    Button btnNext;
    private String msg;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_error);
        ButterKnife.bind(this);
        init();
    }
    public static Intent getFailOpenIntent(Context context, String msg) {
        Intent intent = new Intent(context, WithdrawError.class);
        intent.putExtra("msg", msg);
        return intent;
    }
    private void init() {

        msg = getIntent().getStringExtra("msg");

        tvInfo.setText(msg);

        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {

                finish();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        startActivity(new Intent(mContext, WithdrawActivity.class));
        finish();
    }
}
