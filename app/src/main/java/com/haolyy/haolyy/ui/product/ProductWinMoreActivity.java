package com.haolyy.haolyy.ui.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.ProductDqyMoreListAdapter;
import com.haolyy.haolyy.adapter.ProductListAdapter;
import com.haolyy.haolyy.adapter.ProductWinMoreListAdapter;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.product.BorrowPlanListBean;
import com.haolyy.haolyy.entity.product.DqyBean;
import com.haolyy.haolyy.entity.product.ProductSanListBean;
import com.haolyy.haolyy.ui.product.View.ProductWinMoreView;
import com.haolyy.haolyy.ui.product.presenter.ProductWinMorePresenter;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shanghai on 2018/3/1.
 * 新手专享 赢计划 短期赢 查看更多页面
 */

public class ProductWinMoreActivity extends BaseActivity<ProductWinMorePresenter, ProductWinMoreView> implements ProductWinMoreView {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.lv_win_more)
    LRecyclerView lvWinMore;
    private int pageIndex = 1;
    private String name;
    private String borrowActiveType;
    private ProductWinMoreListAdapter productWinMoreListAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<ProductSanListBean.ModelBean.ListBean> mList = new ArrayList<>();
    private List<DqyBean.ModelBean.ListBean> mList1 = new ArrayList<>();
    /**
     * 每一页展示多少条数据
     */
    private static final int REQUEST_COUNT = 10;
    private int allCount;
    private boolean isWin;
    private ProductDqyMoreListAdapter productDqyMoreListAdapter;

    /**
     *
     * @param context
     * @param borrowActiveType 0:正常 1:新手标 2:活动标
     * @param name
     * @return
     */
    public static Intent getReturnIntent(Context context, String borrowActiveType, String name) {
        Intent intent = new Intent(context, ProductWinMoreActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("borrowActiveType", borrowActiveType);
        return intent;
    }

    @Override
    protected ProductWinMorePresenter createPresenter() {
        return new ProductWinMorePresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producrt_win_more);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        name = getIntent().getStringExtra("name");
        borrowActiveType = getIntent().getStringExtra("borrowActiveType");
        if (borrowActiveType.equals("-1")) {
            isWin = false;
        } else {
            isWin = true;
        }
        topBar.setTitle(name);
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                closeActivity();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });

        if (isWin) {
            productWinMoreListAdapter = new ProductWinMoreListAdapter(mContext);
            lRecyclerViewAdapter = CommonUtils.initLrecycleView(productWinMoreListAdapter, mContext, lvWinMore);
        } else {
            productDqyMoreListAdapter = new ProductDqyMoreListAdapter(mContext);
            lRecyclerViewAdapter = CommonUtils.initLrecycleView(productDqyMoreListAdapter, mContext, lvWinMore);
        }
        lvWinMore.setOnRefreshListener(() -> {
                    if (isWin) {
                        productWinMoreListAdapter.clear();
                        lRecyclerViewAdapter.notifyDataSetChanged();
                        pageIndex = 1;
                        mPresenter.getWinMore(true, borrowActiveType, "1");
                    } else {
                        productDqyMoreListAdapter.clear();
                        lRecyclerViewAdapter.notifyDataSetChanged();
                        pageIndex = 1;
                        mPresenter.getDqyMore(true, "1");
                    }
                }
        );

        lvWinMore.setOnLoadMoreListener(() -> {
            if (isWin) {
                if (allCount > productWinMoreListAdapter.getItemCount()) {
                    pageIndex++;
                    mPresenter.getWinMore(false, borrowActiveType, pageIndex + "");
                } else {
                    lvWinMore.setNoMore(true);
                }
            } else {
                if (allCount > productDqyMoreListAdapter.getItemCount()) {
                    pageIndex++;
                    mPresenter.getDqyMore(false, pageIndex + "¬");
                } else {
                    lvWinMore.setNoMore(true);
                }
            }

        });


        lRecyclerViewAdapter.setOnItemClickListener((view, position) -> {
            if (isWin) {
                startActivity(ProductPlanDetailActivity.getDetailIntent(mContext, mList.get(position).borrowName, mList.get(position).borrowNo,false));
            } else {
                startActivity(ProductPlanDetailActivity.getDetailIntent(mContext, mList1.get(position).getBorrowName(), mList1.get(position).getBorrowNo(),true));
            }
        });

        //初始刷新
        lvWinMore.refresh();
    }

    @Override
    public void getWindata(ProductSanListBean productSanListBean) {
        this.allCount = productSanListBean.model.allCount;
        mList = productSanListBean.model.list;
        productWinMoreListAdapter.addAll(productSanListBean.model.list);
        lvWinMore.refreshComplete(REQUEST_COUNT);
        lRecyclerViewAdapter.notifyDataSetChanged();

    }

    @Override
    public void getMoreWindata(ProductSanListBean productSanListBean) {
        mList.addAll(productSanListBean.model.list);
        productWinMoreListAdapter.addAll(productSanListBean.model.list);
        lvWinMore.refreshComplete(REQUEST_COUNT);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDqy(DqyBean dqyBean) {
        allCount = dqyBean.getModel().getAllCount();
        mList1 = dqyBean.getModel().getList();
        productDqyMoreListAdapter.addAll(dqyBean.getModel().getList());
        lvWinMore.refreshComplete(REQUEST_COUNT);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void showDqyMore(DqyBean dqyBean) {
        mList1.addAll(dqyBean.getModel().getList());
        productDqyMoreListAdapter.addAll(dqyBean.getModel().getList());
        lvWinMore.refreshComplete(REQUEST_COUNT);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }
}
