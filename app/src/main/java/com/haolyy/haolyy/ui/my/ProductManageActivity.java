package com.haolyy.haolyy.ui.my;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.TabAdapter;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.entity.my.HomePageBean;
import com.haolyy.haolyy.entity.my.ProductManageBean;
import com.haolyy.haolyy.entity.my.ProductManageDetailBean;
import com.haolyy.haolyy.ui.my.View.ProductManageView;
import com.haolyy.haolyy.ui.my.presenter.ProductManagePresenter;
import com.haolyy.haolyy.utils.AccountUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shanghai on 2018/2/7.
 */

public class ProductManageActivity extends BaseActivity<ProductManagePresenter, ProductManageView> implements ProductManageView {
    @BindView(R.id.iv_finish)
    TextView ivFinish;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.titleBar)
    RelativeLayout titleBar;
    @BindView(R.id.tv_total)
    TextView tvTotal;
    @BindView(R.id.tab_asset)
    XTabLayout tabAsset;
    @BindView(R.id.vp_asset)
    ViewPager vpAsset;
    @BindView(R.id.tv_profit_total)
    TextView tvProfitTotal;
    private String borrowType;
    private List<ProductManageBean.ModelBean.DataListBean> listData;
    List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private String amount;

    @Override
    protected ProductManagePresenter createPresenter() {
        return new ProductManagePresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_manage);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        borrowType = getIntent().getStringExtra("borrowType");
        mPresenter.queryUserInfo();
        if ("1".equals(borrowType)) {
            tvTitle.setText("我的散标");
            titles.add("募集中");
            titles.add("收益中");
            titles.add("已退出");
        } else if ("2".equals(borrowType)) {
            tvTitle.setText("我的赢计划");
            titles.add("持有中");
            titles.add("退出中");
            titles.add("已退出");
        }

        ivFinish.setOnClickListener(view -> closeActivity());
        AssetHoldFragment assetHolderFragment = AssetHoldFragment.newInstance("1", borrowType);
        AssetHoldFragment assetHolderFragment1 = AssetHoldFragment.newInstance("2", borrowType);
        AssetHoldFragment assetHolderFragment2 = AssetHoldFragment.newInstance("3", borrowType);

        fragments.add(assetHolderFragment);
        fragments.add(assetHolderFragment1);
        fragments.add(assetHolderFragment2);
        vpAsset.setAdapter(new TabAdapter(getSupportFragmentManager(), fragments, titles));
        tabAsset.setupWithViewPager(vpAsset);

    }


    @Override
    public void getDetail(ProductManageDetailBean detailBean) {

    }

    @Override
    public void showAsset(HomePageBean bean) {
        if ("1".equals(borrowType)) {
            tvTotal.setText(AccountUtil.StringToMString(bean.model.borrowAmount));
            tvProfitTotal.setText("累计收益" + bean.model.borrowProfit + "元");
        } else if ("2".equals(borrowType)) {
            tvProfitTotal.setText("累计收益" + bean.model.profitAll + "元");
            tvTotal.setText(AccountUtil.StringToMString(bean.model.appointmentBorrowAmount));
        }
    }

    @Override
    public void changeOpen(String model) {

    }

    @Override
    public void contractUrl(String model) {

    }
}
