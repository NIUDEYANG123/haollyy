package com.haolyy.haolyy.ui.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.RepayRecordAdapter;
import com.haolyy.haolyy.base.BaseFragment;
import com.haolyy.haolyy.entity.product.InvestRecordBean;
import com.haolyy.haolyy.entity.product.RepayPlanBean;
import com.haolyy.haolyy.entity.product.SanRepayPlanBean;
import com.haolyy.haolyy.ui.product.View.DetailRecordView;
import com.haolyy.haolyy.ui.product.presenter.DetailRecordPresenter;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shanghai on 2018/2/26.
 */

public class RepayPlanFragment extends BaseFragment<DetailRecordPresenter, DetailRecordView> implements DetailRecordView {
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.lv_repay_record)
    RecyclerView lvRepayRecord;
    Unbinder unbinder;
    private RepayRecordAdapter repayRecordAdapter;
    private RepayPlanBean.ModelBean model;

    @Override
    protected DetailRecordPresenter createPresenter() {
        return new DetailRecordPresenter(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repay_plan, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        String borrowNo = getArguments().getString("borrowNo");
        mPresenter.getRepayPlan(borrowNo,"1","20");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static RepayPlanFragment newInstance(String borrowNo) {
        Bundle args = new Bundle();
        args.putString("borrowNo", borrowNo);
        RepayPlanFragment fragment = new RepayPlanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private View getFooterView() {
        TextView tv1, tv2, tv3;
        View view = getActivity().getLayoutInflater().inflate(R.layout.head_view_repay_footer, (ViewGroup) lvRepayRecord.getParent(), false);
        tv1 = (TextView) view.findViewById(R.id.tv_show_pay);
        tv2 = (TextView) view.findViewById(R.id.tv_show_pay_interest);
        tv3 = (TextView) view.findViewById(R.id.tv_remain);
       // tv1.setText(AccountUtil.DoubleToString(model.getTotalCorpus()));
        //tv2.setText(AccountUtil.DoubleToString(model.getTotalInterest()));
        //tv3.setText(AccountUtil.DoubleToString(model.getTotalCorpusAndInterest()));
        return view;
    }

    @Override
    public void showRepayPlan(RepayPlanBean repayPlanBean) {
        model = repayPlanBean.getModel();
        lvRepayRecord.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        repayRecordAdapter = new RepayRecordAdapter(repayPlanBean.getModel().getResult());
        repayRecordAdapter.openLoadAnimation();
        lvRepayRecord.setAdapter(repayRecordAdapter);
        repayRecordAdapter.setEmptyView(CommonUtils.getEmptyView(getActivity(),lvRepayRecord,"暂无还款计划~",-1));
        //repayRecordAdapter.addFooterView(getFooterView());
        lvRepayRecord.setAdapter(repayRecordAdapter);

    }

    @Override
    public void getInvestRecord(InvestRecordBean investRecordBean) {

    }

    @Override
    public void getMoreInvestRecord(InvestRecordBean investRecordBean) {

    }
}
