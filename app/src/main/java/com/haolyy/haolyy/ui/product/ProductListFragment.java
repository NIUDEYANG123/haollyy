package com.haolyy.haolyy.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.FileUtils;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.ProductListAdapter;
import com.haolyy.haolyy.adapter.ProductManageSanAdapter;
import com.haolyy.haolyy.base.BaseFragment;
import com.haolyy.haolyy.entity.my.AssetHoldListBean;
import com.haolyy.haolyy.entity.product.BorrowPlanListBean;
import com.haolyy.haolyy.entity.product.PlanBorrowForAppBean;
import com.haolyy.haolyy.ui.my.View.AssetHoldView;
import com.haolyy.haolyy.ui.my.presenter.AssetHoldPresenter;
import com.haolyy.haolyy.ui.product.View.ProductListView;
import com.haolyy.haolyy.ui.product.presenter.ProductListPresenter;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shanghai on 2018/2/7.
 */

public class ProductListFragment extends BaseFragment<ProductListPresenter, ProductListView> implements ProductListView {
    private static ProductListFragment assetHoldFragment;
    @BindView(R.id.recycle_asset)
    LRecyclerView recycleAsset;
    Unbinder unbinder;
    private View view;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private ProductListAdapter productListAdapter;//赢计划与三表共用一个
    private int pageIndex = 1;
    List<BorrowPlanListBean.ModelBean.ListBean> mList = new ArrayList<>();
    /**
     * 每一页展示多少条数据
     */
    private static final int REQUEST_COUNT = 10;
    private String type;
    private String borrowType;
    private int allCount;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_asset, container, false);
        unbinder = ButterKnife.bind(this, view);
        type = getArguments().getString("type");
        borrowType = getArguments().getString("borrowType");
        initListener();
        return view;
    }

    public static ProductListFragment newInstance(String type, String borrowType) {
        assetHoldFragment = new ProductListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("borrowType", borrowType);
        assetHoldFragment.setArguments(bundle);
        return assetHoldFragment;
    }

    /**
     * 可见加载
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && null != view) {
            recycleAsset.refresh();
        }
    }

    private void initListener() {
        productListAdapter = new ProductListAdapter(mContext);
        lRecyclerViewAdapter = CommonUtils.initLrecycleView(productListAdapter, mContext, recycleAsset);

        recycleAsset.setOnRefreshListener(() -> {
                    productListAdapter.clear();
                    lRecyclerViewAdapter.notifyDataSetChanged();
                    pageIndex = 1;
                    mPresenter.getSanList(true, borrowType, "1", "3", pageIndex);
                }
        );

        recycleAsset.setOnLoadMoreListener(() -> {
            if (allCount > productListAdapter.getItemCount()) {
                pageIndex++;
                mPresenter.getSanList(false, borrowType, "1", "3", pageIndex);
            } else {
                recycleAsset.setNoMore(true);
            }
        });


        lRecyclerViewAdapter.setOnItemClickListener((view, position) -> {
            LogUtils.e(tag, mList.get(position).getBorrowName() + mList.get(position).getBorrowNo());
            startActivity(ProductSanDetailActivity.getDetailIntent(mContext, mList.get(position).getBorrowName(), mList.get(position).getBorrowNo()));
        });

        //初始刷新
        //new Handler().postDelayed(() -> recycleAsset.refresh(),200);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getData(BorrowPlanListBean planListBean) {
        allCount = planListBean.getModel().getAllCount();
        mList.addAll(planListBean.getModel().getList());
        productListAdapter.addAll(planListBean.getModel().getList());
        recycleAsset.refreshComplete(REQUEST_COUNT);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDataMore(BorrowPlanListBean planListBean) {
        LogUtils.e(tag,"getDataMore");
        mList.addAll(planListBean.getModel().getList());
        recycleAsset.refreshComplete(REQUEST_COUNT);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void noNetwork() {
        recycleAsset.refreshComplete(REQUEST_COUNT);
    }

    @Override
    public void getPlanData(BorrowPlanListBean baseBean) {

    }

    @Override
    protected ProductListPresenter createPresenter() {
        return new ProductListPresenter(mContext);
    }
}
