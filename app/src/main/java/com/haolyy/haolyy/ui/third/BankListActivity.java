package com.haolyy.haolyy.ui.third;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.BankListAdapter;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.bank.BankListBean;
import com.haolyy.haolyy.ui.third.presenter.BankListPresenter;
import com.haolyy.haolyy.ui.third.view.BankListView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wyman
 * @date 2018/2/12
 * description :
 */

public class BankListActivity extends BaseActivity<BankListPresenter, BankListView> implements BankListView {

    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private String busiType;
    private BankListAdapter bankListAdapter;

    private String flag;
    @Override
    protected BankListPresenter createPresenter() {
        return new BankListPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_list);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                finish();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });
        busiType = getIntent().getStringExtra("busiType");
        flag = getIntent().getStringExtra("flag");
        mPresenter.getBanks(busiType);

    }

    @Override
    public void showBank(BankListBean listBean) {
        bankListAdapter = new BankListAdapter(mContext, listBean.getModel());
        // 设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);
        // 设置适配器
        recycler.setAdapter(bankListAdapter);
        if (!TextUtils.isEmpty(flag)&&flag.equals("recharge")) {
            return;
        }
        bankListAdapter.setOnItemOnClickListener(new BankListAdapter.OnItemOnClickListener() {
            @Override
            public void itemClickListener(View view, BankListBean.ModelBean modelBean) {
                setResult(0x03, new Intent().putExtra("data", modelBean));
                finish();
            }
        });
    }
}
