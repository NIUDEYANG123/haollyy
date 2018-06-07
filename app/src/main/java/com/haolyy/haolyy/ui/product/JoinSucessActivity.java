package com.haolyy.haolyy.ui.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haolyy.haolyy.MainActivity;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.utils.DateUtil;
import com.haolyy.haolyy.utils.LogUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by shanghai on 2018/2/24.
 */

public class JoinSucessActivity extends BaseActivity {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.iv_success)
    ImageView ivSuccess;
    @BindView(R.id.tv_green)
    TextView tvGreen;
    @BindView(R.id.cs_top)
    ConstraintLayout csTop;
    @BindView(R.id.dot1)
    ImageView dot1;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.iv_agree)
    ImageView ivAgree;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.dot2)
    ImageView dot2;
    @BindView(R.id.cs_left)
    ConstraintLayout csLeft;
    @BindView(R.id.tv_start_date)
    TextView tvStartDate;
    @BindView(R.id.tv_rule)
    TextView tvRule;
    @BindView(R.id.tv_my)
    TextView tvMy;
    @BindView(R.id.tv_continue)
    TextView tvContinue;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    private String start;
    private String end;
    private String dateInt;
    private long t2;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    public static Intent getReturnIntent(Context context, String start, String end, String dateInt) {
        Intent intent = new Intent(context, JoinSucessActivity.class);
        intent.putExtra("start", start);
        intent.putExtra("end", end);
        intent.putExtra("dateInt", dateInt);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_success);
        ButterKnife.bind(this);
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                finish();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });

        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        dateInt = getIntent().getStringExtra("dateInt");
        if(!TextUtils.isEmpty(start)) {
            Date date = DateUtil.strToDateLong(start);
            long time = date.getTime();
            t2 = time + Integer.parseInt(dateInt) * 24 * 3600 * 1000;
            LogUtils.e(tag, time + "=time=t2" + t2 + "yymmdd=" + DateUtil.getTimeyyyymmdd(t2));
        }
        tvStartDate.setText(DateUtil.getTimeyyyymmdd(t2) + "开始计息");
        tvExit.setText("预计" + end + "到期退出");
    }

    @OnClick({R.id.tv_my, R.id.tv_continue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_my:
                startActivity(MainActivity.getMainIntent(mContext, 3));
                break;
            case R.id.tv_continue:
                startActivity(MainActivity.getMainIntent(mContext, 1));
                break;
        }
    }
}
