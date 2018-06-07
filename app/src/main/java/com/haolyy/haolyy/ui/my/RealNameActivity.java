package com.haolyy.haolyy.ui.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.WYUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shanghai on 2018/3/16.
 */

public class RealNameActivity extends BaseActivity {

    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_id)
    TextView tvId;
    private String name;
    private String idCard;

    public static Intent getReturnIntent(Context context, String name, String idCard) {
        Intent intent = new Intent(context, RealNameActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("idCard", idCard);
        return intent;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        name = getIntent().getStringExtra("name");
        idCard = getIntent().getStringExtra("idCard");
        tvName.setText(WYUtils.nameSecret(name));
        tvId.setText(CommonUtils.secretId(idCard));
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
}
