package com.haolyy.haolyy.ui.record;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.CouponInvestExpandableListAdapter;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.userinfo.InvestCouponBean;
import com.haolyy.haolyy.ui.record.presenter.CouponPresenter;
import com.haolyy.haolyy.ui.record.view.CouponView;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wyman
 * @date 2018/2/8
 * description : 卡券
 */

public class InvestCouponActivity extends BaseActivity<CouponPresenter, CouponView> implements CouponView {

    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.expandable_invest)
    ExpandableListView expandableInvest;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    // 声明对象
    private CouponInvestExpandableListAdapter adapter;
    private int pageIndex = 1;
    List<InvestCouponBean.ModelBean.DataListBean> mList = new ArrayList<>();
    private String investAmount, periodLength, periodUnit, type, productNo;
    private List<InvestCouponBean.ModelBean.DataListBean> dataList;
    private View viewById;

    @Override
    protected CouponPresenter createPresenter() {
        return new CouponPresenter(mContext);
    }

    public static Intent getReturnIntent(Context context, String investAmount, String periodLength, String periodUnit, String type, String productNo) {
        Intent intent = new Intent(context, InvestCouponActivity.class);
        intent.putExtra("investAmount", investAmount);
        intent.putExtra("periodLength", periodLength);
        intent.putExtra("periodUnit", periodUnit);
        intent.putExtra("type", type);
        intent.putExtra("productNo", productNo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_coupon);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.queryInvestCoupon2(true, pageIndex + "", 100 + "",
                investAmount, periodLength, periodUnit, type, productNo);
    }

    private void init() {
        viewById = findViewById(R.id.layout_empty);
        investAmount = getIntent().getStringExtra("investAmount");
        periodLength = getIntent().getStringExtra("periodLength");
        periodUnit = getIntent().getStringExtra("periodUnit");
        type = getIntent().getStringExtra("type");
        productNo = getIntent().getStringExtra("productNo");
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                finish();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });

        expandableInvest.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });
        expandableInvest.setOnGroupClickListener((expandableListView, view, i, l) -> {
            /*Intent intent1 = new Intent();
            if (TextUtils.isEmpty(dataList.get(i).getCanUse()) || "0".equals(dataList.get(i).getCanUse())) {
                //UIUtils.showToastCommon(mContext, "此优惠券不可用");
            } else {
                intent1.putExtra("bean", dataList.get(i));
                setResult(102, intent1);
                finish();
            }*/
            adapter.setOnChangeImgCallBack(imageViewMap -> {
                if (expandableListView.isGroupExpanded(i)) {
                    imageViewMap.get(i).setImageResource(R.drawable.coupon_up);
                } else {
                    imageViewMap.get(i).setImageResource(R.drawable.coupon_down);
                }
            });

            return false;
        });

    }

    @Override
    public void getData(InvestCouponBean investCouponBean) {
        dataList = investCouponBean.getModel().getDataList();
        if (null == dataList || dataList.size() == 0) {
            noNetwork();
        }
        adapter = new CouponInvestExpandableListAdapter(mContext, investCouponBean.getModel().getDataList());
        expandableInvest.setAdapter(adapter);
        adapter.setUseCallBack(pos -> {
            Intent intent = new Intent();
            intent.putExtra("bean", dataList.get(pos));
            setResult(102, intent);
            finish();
        });

    }

    @Override
    public void getDataMore(InvestCouponBean list) {

    }

    @Override
    public void noNetwork() {
       viewById.setVisibility(View.VISIBLE);
       tvEmpty.setText("没有可使用的优惠券");
    }
}
