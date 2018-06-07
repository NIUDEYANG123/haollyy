package com.haolyy.haolyy.ui.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.xtablayout.XTabLayout;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.TabAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by haolyy on 2017/8/9.
 */

public class ProductFragment extends Fragment {
    @BindView(R.id.tab_product)
    XTabLayout tabProduct;
    @BindView(R.id.vp_product)
    ViewPager vpProduct;
    Unbinder unbinder;
    private View view;
    List<Fragment> fragments = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        titles.clear();
        fragments.clear();
        titles.add("赢计划");
        titles.add("散标");

        //ProductListFragment productListFragment = ProductListFragment.newInstance("1", "2");
        ProductPlanListFragment productPlanListFragment = new ProductPlanListFragment();
        ProductListFragment productListFragment1 = ProductListFragment.newInstance("1", "1");

        fragments.add(productPlanListFragment);
        fragments.add(productListFragment1);

        vpProduct.setAdapter(new TabAdapter(getChildFragmentManager(), fragments, titles));
        tabProduct.setupWithViewPager(vpProduct);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
