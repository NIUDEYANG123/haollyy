package com.haolyy.haolyy.ui.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.ProductNewListAdapter;
import com.haolyy.haolyy.adapter.ProductShortListAdapter;
import com.haolyy.haolyy.adapter.ProductWinPlanListAdapter;
import com.haolyy.haolyy.base.BaseFragment;
import com.haolyy.haolyy.custom.CustomizedProgressBar;
import com.haolyy.haolyy.entity.product.BorrowPlanListBean;
import com.haolyy.haolyy.ui.product.View.ProductListView;
import com.haolyy.haolyy.ui.product.presenter.ProductListPresenter;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.CommonUtils;
import com.haolyy.haolyy.utils.UIUtils;
import com.haolyy.haolyy.utils.WYUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shanghai on 2018/3/1.
 */

public class ProductPlanListFragment extends BaseFragment<ProductListPresenter, ProductListView> implements ProductListView {
    @BindView(R.id.lv_short_win)
    RecyclerView lvShortWin;
    @BindView(R.id.lv_win_plan)
    RecyclerView lvWinPlan;
    Unbinder unbinder;
    @BindView(R.id.easy_layout)
    EasyRefreshLayout easyLayout;
    @BindView(R.id.lv_new)
    RecyclerView lvNew;
    private List<BorrowPlanListBean.ModelBean.NoviciateBorrowBean> noviciateBorrow = new ArrayList<>();
    private List<BorrowPlanListBean.ModelBean.DqyBorrowListBean> dqyBorrowList = new ArrayList<>();
    private List<BorrowPlanListBean.ModelBean.ListBean> list = new ArrayList<>();
    private ProductWinPlanListAdapter productWinPlanListAdapter;
    private ProductShortListAdapter productShortListAdapter;
    private ProductNewListAdapter productNewListAdapter;
    private int pageIndex = 1;
    private View view;
    private View viewById;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plan_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initListener();
        return view;
    }

    private void initListener() {
        easyLayout.setLoadMoreModel(LoadModel.NONE);//禁止加载更多
        easyLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                       /* easyRefreshLayout.loadMoreComplete(new EasyRefreshLayout.Event() {
                            @Override
                            public void complete() {
                                adapter.getData().addAll(list);
                                adapter.notifyDataSetChanged();
                            }
                        }, 500);
*/
            }

            @Override
            public void onRefreshing() {
                productShortListAdapter.clear();
                productWinPlanListAdapter.clear();
                productNewListAdapter.clear();
                mPresenter.getPlanList(true, "2", "1", "3", 1);
            }
        });


    }

    private void initView() {
        lvWinPlan.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        lvShortWin.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        productWinPlanListAdapter = new ProductWinPlanListAdapter(mContext);
        productShortListAdapter = new ProductShortListAdapter(mContext);
        lvNew.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        productNewListAdapter = new ProductNewListAdapter(mContext);
    }

    @Override
    public void onResume() {
        super.onResume();
        productShortListAdapter.clear();
        productWinPlanListAdapter.clear();
        productNewListAdapter.clear();
        mPresenter.getPlanList(true, "2", "1", "3", 1);
    }

    @Override
    public void getData(BorrowPlanListBean planListBean) {
    }

    @Override
    public void getDataMore(BorrowPlanListBean planListBean) {
    }

    @Override
    public void noNetwork() {
        if (easyLayout.isRefreshing() || easyLayout.isLoading()) {
            easyLayout.refreshComplete();
        }
    }

    @Override
    public void getPlanData(BorrowPlanListBean planListBean) {
        easyLayout.refreshComplete();
        //新手列表
        if (null != planListBean.getModel().getNoviciateBorrow() && planListBean.getModel().getNoviciateBorrow().size() > 0) {
            noviciateBorrow = planListBean.getModel().getNoviciateBorrow();
            productNewListAdapter.addAll(noviciateBorrow);
            lvNew.setAdapter(productNewListAdapter);
        }
        //填充两个子列表
        if (null != planListBean.getModel().getList() && planListBean.getModel().getList().size() > 0) {
            list = planListBean.getModel().getList();
            productWinPlanListAdapter.addAll(list);
            lvWinPlan.setAdapter(productWinPlanListAdapter);
        }

        if (null != planListBean.getModel().getDqyBorrowList() && planListBean.getModel().getDqyBorrowList().size() > 0) {
            dqyBorrowList = planListBean.getModel().getDqyBorrowList();
            productShortListAdapter.addAll(dqyBorrowList);
            lvShortWin.setAdapter(productShortListAdapter);
        }


    }

    @Override
    protected ProductListPresenter createPresenter() {
        return new ProductListPresenter(mContext);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
