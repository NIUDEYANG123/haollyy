package com.haolyy.haolyy.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.ViewGroup;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.databinding.HomeBinding;
import com.haolyy.haolyy.entity.product.HomeListBean;
import com.haolyy.haolyy.utils.WYUtils;

import java.util.List;

/**
 * Created by wangyin on 2017/10/11.
 */

public class HomeProjectAdapter extends WyBaseAdapter {


    private HomeBinding binding;

    public HomeProjectAdapter(List list, Context context) {
        super(list, context);
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeListBean.ModelBean.PlanBorrowBean planBorrowBean = (HomeListBean.ModelBean.PlanBorrowBean) list.get(position);
        binding = DataBindingUtil.inflate(inflater, R.layout.item_home_product_list, parent, false);
        binding.setProduct(planBorrowBean);
        binding.tvType.setText(WYUtils.getProfitPlan(planBorrowBean.getProfitPlan()+""));
        binding.tvInvestMinAmount.setText(WYUtils.getTwoNumStr(planBorrowBean.getInvestMinAmount()) + "元");
        binding.tvName.setText(planBorrowBean.getBorrowName());
        binding.tvRate.setText(WYUtils.getSingleNumStr(planBorrowBean.getAnnualizedRate()));
        binding.tvAppend.setText("+"+WYUtils.getSingleNumStr(planBorrowBean.getAppendRate()));
        binding.tvPreriodUnit.setText(planBorrowBean.getPeriodLength() + WYUtils.periodUnit(planBorrowBean.getPeriodUnit() + ""));


        //期限10周，1周锁定期后可免费转让，存在无法转出可能
        /*if (planBorrowBean.getBorrowType() != 1 && planBorrowBean.getPeriodLength() < 10) {
            binding.tvHomeAnnotation.setVisibility(View.VISIBLE);
            binding.tvHomeAnnotation.setText("债券期限10周，" + planBorrowBean.getPeriodLength() + "周锁定期后可免费转让，存在无法转出可能");
        } else {
            binding.tvHomeAnnotation.setVisibility(View.GONE);
        }*/
        return binding.getRoot();
    }


}
