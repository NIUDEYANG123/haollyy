package com.haolyy.haolyy.ui.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.ProductManageSanAdapter;
import com.haolyy.haolyy.base.BaseFragment;
import com.haolyy.haolyy.entity.my.AssetHoldListBean;
import com.haolyy.haolyy.ui.my.View.AssetHoldView;
import com.haolyy.haolyy.ui.my.presenter.AssetHoldPresenter;
import com.haolyy.haolyy.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shanghai on 2018/2/7.
 */

public class AssetHoldFragment extends BaseFragment<AssetHoldPresenter, AssetHoldView> implements AssetHoldView {

    private static AssetHoldFragment assetHoldFragment;
    @BindView(R.id.recycle_asset)
    LRecyclerView recycleAsset;
    Unbinder unbinder;
    @BindView(R.id.iv_empty)
    ImageView ivEmpty;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    private View view;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private ProductManageSanAdapter productManageSanAdapter;//赢计划与三表共用一个
    private int pageIndex = 1;
    List<AssetHoldListBean.ModelBean.ListInfoBean> mList = new ArrayList<>();
    /**
     * 每一页展示多少条数据
     */
    private static final int REQUEST_COUNT = 10;
    private String type;
    private String borrowType;
    private View emptyView;

    @Override
    protected AssetHoldPresenter createPresenter() {
        return new AssetHoldPresenter(mContext);
    }

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

    public static AssetHoldFragment newInstance(String type, String borrowType) {
        assetHoldFragment = new AssetHoldFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("borrowType", borrowType);
        assetHoldFragment.setArguments(bundle);
        return assetHoldFragment;
    }

    private void initListener() {
        emptyView = view.findViewById(R.id.empty_view);
        tvEmpty.setText("暂无数据");
        productManageSanAdapter = new ProductManageSanAdapter(mContext, borrowType);
        lRecyclerViewAdapter = CommonUtils.initLrecycleView(productManageSanAdapter, mContext, recycleAsset);
        recycleAsset.setOnRefreshListener(() -> {
            if (null == productManageSanAdapter) {
                //productManageAdapter.clear();
            } else {
                productManageSanAdapter.clear();
            }
            lRecyclerViewAdapter.notifyDataSetChanged();
            pageIndex = 1;
            mPresenter.queryListbyStatus(true, borrowType, type, pageIndex);

        });

        recycleAsset.setOnLoadMoreListener(() -> {
            pageIndex++;
            mPresenter.queryListbyStatus(false, borrowType, type, pageIndex);
        });

        lRecyclerViewAdapter.setOnItemClickListener((view, position) -> {
            if (borrowType.equals("2")) {
                startActivity(AssetDetailActivity.getReturnIntent(mContext, mList.get(position).getCashNo(), mList.get(position).getIsContinue(), mList.get(position).getOrderNo(), type, mList.get(position).getBorrowName(), mList.get(position).getBorrowNo(),mList.get(position).getApplyNo()));
            }
        });

        //初始刷新
        recycleAsset.refresh();

    }

    @Override
    public void getData(AssetHoldListBean list) {
        if (recycleAsset != null) {
            mList = list.getModel().getListInfo();
            if (null == productManageSanAdapter) {
                //productManageAdapter.addAll(bean.getModel().getListInfo());
            } else {
                productManageSanAdapter.addAll(list.getModel().getListInfo());
            }
            recycleAsset.setEmptyView(emptyView);
            recycleAsset.refreshComplete(REQUEST_COUNT);
            lRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getDataMore(AssetHoldListBean list) {
        mList.addAll(list.getModel().getListInfo());
        if (null == productManageSanAdapter) {
            //productManageAdapter.addAll(bean.getModel().getListInfo());
        } else {
            productManageSanAdapter.addAll(list.getModel().getListInfo());
        }
        recycleAsset.refreshComplete(REQUEST_COUNT);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
