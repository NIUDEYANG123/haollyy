package com.haolyy.haolyy.ui.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.BorrowRecordAdapter;
import com.haolyy.haolyy.base.BaseFragment;
import com.haolyy.haolyy.entity.product.InvestRecordBean;
import com.haolyy.haolyy.entity.product.RepayPlanBean;
import com.haolyy.haolyy.ui.product.View.DetailRecordView;
import com.haolyy.haolyy.ui.product.presenter.DetailRecordPresenter;
import com.haolyy.haolyy.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shanghai on 2018/2/26.
 */

public class BorrowRecordFragment extends BaseFragment<DetailRecordPresenter, DetailRecordView> implements DetailRecordView {
    @BindView(R.id.lv_record)
    LRecyclerView lvRecord;
    Unbinder unbinder;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.nest_scroll_view)
    NestedScrollView nestScrollView;
    private List<InvestRecordBean.ModelBean.ListBean> mList = new ArrayList<>();
    private static final int REQUEST_COUNT = 10;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private View view;
    private View viewById;
    private BorrowRecordAdapter borrowRecordAdapter;
    private int pageIndex = 1;
    private int allCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_borrow_record, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        viewById = view.findViewById(R.id.empty_view);
        tvEmpty.setText("当前暂无出借记录");

        String borrowNo = getArguments().getString("borrowNo");
        nestScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                if (allCount > borrowRecordAdapter.getItemCount()) {
                    pageIndex++;
                    mPresenter.getInvestRecord(true, borrowNo, pageIndex+"");
                } else {

                }
            }
        });
        borrowRecordAdapter = new BorrowRecordAdapter(mContext);
        lRecyclerViewAdapter = CommonUtils.initLrecycleView(borrowRecordAdapter, mContext, lvRecord);
        mPresenter.getInvestRecord(true, borrowNo, "1");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showRepayPlan(RepayPlanBean repayPlanBean) {


    }

    @Override
    public void getInvestRecord(InvestRecordBean investRecordBean) {
        allCount = investRecordBean.getModel().getAllCount();
        mList = investRecordBean.getModel().getList();
        borrowRecordAdapter.addAll(mList);
        lvRecord.setEmptyView(viewById);
        lvRecord.refreshComplete(REQUEST_COUNT);
        lRecyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void getMoreInvestRecord(InvestRecordBean investRecordBean) {
        mList.addAll(investRecordBean.getModel().getList());
        lvRecord.refreshComplete(REQUEST_COUNT);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected DetailRecordPresenter createPresenter() {
        return new DetailRecordPresenter(mContext);
    }

    public static BorrowRecordFragment newInstance(String borrowNo) {
        Bundle args = new Bundle();
        args.putString("borrowNo", borrowNo);
        BorrowRecordFragment fragment = new BorrowRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
