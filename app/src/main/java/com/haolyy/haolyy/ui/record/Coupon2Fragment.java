package com.haolyy.haolyy.ui.record;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.Coupon2ExpandableListAdapter;
import com.haolyy.haolyy.base.BaseFragment;
import com.haolyy.haolyy.entity.userinfo.AccountCouponbean;
import com.haolyy.haolyy.ui.record.presenter.CardQuanPresenter;
import com.haolyy.haolyy.ui.record.view.CardQuanView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author wyman
 * @date 2018/2/8
 * description : 卡券
 */

public class Coupon2Fragment extends BaseFragment<CardQuanPresenter, CardQuanView> implements CardQuanView {
    @BindView(R.id.expandable_id)
    ExpandableListView expandableId;
    Unbinder unbinder;
    @BindView(R.id.tv_tips)
    TextView tvTips;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    private View view;
    // 声明对象
    private Coupon2ExpandableListAdapter adapter = null;
    private List<AccountCouponbean.ModelBean.CouponReceiveListBean> listall;
    private List<AccountCouponbean.ModelBean.CouponReceiveListBean> listuse;

    @Override
    protected CardQuanPresenter createPresenter() {
        return new CardQuanPresenter(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_coupon, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.queryQuan();
    }

    private void init() {
        tvTips.setText("暂无数据");
        // 实例化ExpandableListView对象
        expandableId = (ExpandableListView) view.findViewById(R.id.expandable_id);

        // 设置监听器
        expandableId.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Log.e("test", "GroupPosition is " + groupPosition);
                Log.e("test", "ChildPosition is" + childPosition);
                return false;
            }
        });
        expandableId.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {


                adapter.setOnChangeImgCallBack(new Coupon2ExpandableListAdapter.OnChangeImgCallBack() {
                    @Override
                    public void changeImg(Map<Integer, ImageView> imageViewMap) {
                        if (expandableListView.isGroupExpanded(i)) {

                            imageViewMap.get(i).setImageResource(R.drawable.coupon_up);

                        } else {
                            imageViewMap.get(i).setImageResource(R.drawable.coupon_down);
                        }
                    }
                });

                return false;
            }
        });
    }

    @Override
    public void showCoupons(AccountCouponbean accountCouponbean) {


        if (accountCouponbean.getModel().getCouponReceiveList().size() > 0) {

            listuse = new ArrayList<>();
            expandableId.setVisibility(View.VISIBLE);
            llNoData.setVisibility(View.GONE);
            listall = accountCouponbean.getModel().getCouponReceiveList();
            for (AccountCouponbean.ModelBean.CouponReceiveListBean couponListBean : listall) {
                if (couponListBean.getStatus() == 2) {
                    listuse.add(couponListBean);
                }
            }


            if (listuse.size() > 0) {
                // 实例化ExpandableListView的适配器
                adapter = new Coupon2ExpandableListAdapter(mContext, listuse);
                // 设置适配器
                expandableId.setAdapter(adapter);
                expandableId.setVisibility(View.VISIBLE);
                llNoData.setVisibility(View.GONE);
            } else {
                llNoData.setVisibility(View.VISIBLE);
                expandableId.setVisibility(View.GONE);
            }

        } else {
            llNoData.setVisibility(View.VISIBLE);
            expandableId.setVisibility(View.GONE);
        }
    }

    @Override
    public void noNetwork() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
