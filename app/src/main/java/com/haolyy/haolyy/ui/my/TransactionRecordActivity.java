package com.haolyy.haolyy.ui.my;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LuRecyclerView;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.GridViewAdapter;
import com.haolyy.haolyy.adapter.StickyHeaderDecoration;
import com.haolyy.haolyy.adapter.StickyTransAdapter;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.entity.my.GridInfo;
import com.haolyy.haolyy.entity.my.TransactionRecordBean;
import com.haolyy.haolyy.ui.my.View.TransactionRecordView;
import com.haolyy.haolyy.ui.my.presenter.TransactionRecordPresenter;
import com.haolyy.haolyy.utils.LogUtils;
import com.haolyy.haolyy.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 交易记录
 * Created by liliang on 2017/9/21.
 */

public class TransactionRecordActivity extends BaseActivity<TransactionRecordPresenter, TransactionRecordView> implements TransactionRecordView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.iv_finish)
    ImageView ivFinish;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.view_line_tr)
    View viewLine;
    @BindView(R.id.tr_layout)
    RelativeLayout trLayout;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.trans_main)
    LinearLayout transMain;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    @BindView(R.id.recycle_record)
    LuRecyclerView recycleRecord;
    /**
     * 每一页展示多少条数据
     */
    private static final int REQUEST_COUNT = 10;
    private PopupWindow popupWindow;//采用 EasyPopup 优化一下啊
    private int page_num = 1;
    private int type = 0;
    private StickyHeaderDecoration decor;
    private List<TransactionRecordBean.ModelBean.DataListBean> listData = new ArrayList<>();
    private StickyTransAdapter stickyTransAdapter;
    private LuRecyclerViewAdapter lRecyclerViewAdapter;
    private boolean isChuSHiHua;

    private boolean noOne = true;

    @Override
    protected TransactionRecordPresenter createPresenter() {
        return new TransactionRecordPresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_record);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        recycleRecord.setOnLoadMoreListener(() -> {
            noOne = false;
            page_num++;
            mPresenter.getTransactionRecord(false, type, page_num);
        });
        //设置底部加载颜色
        //recycleRecord.setFooterViewColor(R.color.bg_2b6ff9, R.color.text_3788f8, android.R.color.white);
        //设置底部加载文字提示
        recycleRecord.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");
        //设置刷新时动画的颜色，可以设置4个
        if (swipe != null) {
            swipe.setProgressViewOffset(true, 0, UIUtils.dip2px(48));
            swipe.setColorSchemeResources(R.color.bg_FE7537, R.color.bg_FE7537);
            swipe.setOnRefreshListener(this);
        }

        onRefresh();

        tvTitle.setText("交易明细");
        String str[] = {"全部", "充值", "提现", "加入计划", "散标出借", "回款", "收益", "奖励", "退款", "其他"};
        for (int i = 0; i < str.length; i++) {
            GridInfo gridInfo = new GridInfo();
            gridInfo.setTitle(str[i]);
            if (i == 0) {
                gridInfo.setSelect(true);
            } else {
                gridInfo.setSelect(false);
            }
            list.add(gridInfo);
        }
        adapter = new GridViewAdapter(mContext, list);
    }

    @Override
    public void onRefresh() {
        noOne = true;
        page_num = 1;
        swipe.setRefreshing(true);
        mPresenter.getTransactionRecord(true, type, page_num);
    }

    @OnClick({R.id.iv_finish, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_finish:
                closeActivity();
                break;
            case R.id.tv_right:
                showPopWindow(getView());
                break;
        }
    }

    private void showPopWindow(View view) {
        try {
            popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            ColorDrawable cd = new ColorDrawable(0x00ffffff);// 背景颜色全透明
            popupWindow.setBackgroundDrawable(cd);
            //设置背景透明度
            WindowManager.LayoutParams ll = getWindow().getAttributes();
            ll.alpha = 0.6f;//
            getWindow().setAttributes(ll);
            int[] location = new int[2];
            trLayout.getLocationOnScreen(location);
            popupWindow.setAnimationStyle(R.style.style_pop_animation);// 动画效果必须放在showAsDropDown()方法上边，否则无效
            //backgroundAlpha(0.5f);// 设置activity背景半透明
            popupWindow.showAtLocation(transMain, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            popupWindow.showAsDropDown(trLayout);
            popupWindow.setOnDismissListener(() -> {
                popupWindow = null;
                ll.alpha = 1.0f;
                getWindow().setAttributes(ll);

            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private GridView pop_gridview;
    private ImageView iv_close;
    private List<GridInfo> list = new ArrayList<>();
    private GridViewAdapter adapter;

    /**
     * 得到popupwindow的View
     */
    private View getView() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_pop_item, null);
        iv_close = (ImageView) view.findViewById(R.id.iv_close);
        pop_gridview = (GridView) view.findViewById(R.id.pop_gridview);
        pop_gridview.setAdapter(adapter);
        pop_gridview.setOnItemClickListener((adapterView, view1, position, l) -> {
            type = position;
            page_num = 1;
            listData.clear();
            mPresenter.getTransactionRecord(true, type, page_num);
            for (int i = 0; i < list.size(); i++) {
                if (position == i) {//当前选中的Item改变背景颜色
                    list.get(i).setSelect(true);
                } else {
                    list.get(i).setSelect(false);
                }
            }
            popupWindow.dismiss();
        });
        iv_close.setOnClickListener(v -> popupWindow.dismiss());
        return view;
    }

    @Override
    public void getData(List<TransactionRecordBean.ModelBean.DataListBean> list) {
        if (recycleRecord != null) {
            recycleRecord.setVisibility(View.VISIBLE);
            if (swipe.isRefreshing()) {
                swipe.setRefreshing(false);
            }
            if (list.size() == 0) {
                tvNoData.setVisibility(View.VISIBLE);
                recycleRecord.setVisibility(View.INVISIBLE);
            } else {
                tvNoData.setVisibility(View.GONE);
                recycleRecord.setVisibility(View.VISIBLE);
                if (!isChuSHiHua) {
                    initAdapter(list);
                } else {
                    stickyTransAdapter.clear();
                }
                stickyTransAdapter.addAll(list);
                lRecyclerViewAdapter.notifyDataSetChanged();
            }
        }
    }

    private void initAdapter(List<TransactionRecordBean.ModelBean.DataListBean> list) {
        isChuSHiHua = true;
        listData = list;
        recycleRecord.setHasFixedSize(true);
        recycleRecord.setLayoutManager(new LinearLayoutManager(mContext));
        stickyTransAdapter = new StickyTransAdapter(this);
        decor = new StickyHeaderDecoration(stickyTransAdapter);
        lRecyclerViewAdapter = new LuRecyclerViewAdapter(stickyTransAdapter);
        recycleRecord.setAdapter(lRecyclerViewAdapter);
        recycleRecord.addItemDecoration(decor);


        lRecyclerViewAdapter.setOnItemClickListener((view, position) -> {
            /**
             * 列表返回的type:
             1：充值  2：提现 3：加入计划  4：散标出借  5：预约标回款  6：散标回款
             7：预约标收益  8：散标收益  9：平台奖励  10：提现退款  11：出借退款
             12：其他
             */

            /**
             *  1:充值；4:加入计划；5：平台奖励；7：提现；6：散标出借；2：回款；3：散标收益；8：退款；
             */
            int sta = stickyTransAdapter.getDataList().get(position).type;
            int tt = 0;
            String bt = "";
            if (sta == 5) {
                tt = 2;
                bt = "1";
            } else if (sta == 6) {
                tt = 2;
                bt = "2";
            } else if (sta == 7) {
                tt = 3;
                bt = "1";
            } else if (sta == 8) {
                tt = 3;
                bt = "2";
            } else if (sta == 3) {
                tt = 4;
                bt = "4";
            } else if (sta == 9) {
                tt = 5;
            } else if (sta == 4) {
                tt = 6;
            } else if (sta == 2) {
                tt = 7;
            } else if (sta == 10 || sta == 11) {
                tt = 8;
            } else if (sta == 1) {
                tt = 1;
            } else if (sta >= 13) {//邀请奖励
                tt = sta;
            }

            if (sta == 1 || sta == 2 || sta == 3 || sta == 4 || sta == 5 || sta == 6 || sta == 7 || sta == 9 || sta == 10 || sta == 11 || sta >= 13) {
                Intent intent = new Intent(mContext, TransactionRecordDetailsActivity.class);
                intent.putExtra("type", tt);
                intent.putExtra("orderNo", stickyTransAdapter.getDataList().get(position).orderNo);
                intent.putExtra("bidType", bt);
                intent.putExtra("billDate", stickyTransAdapter.getDataList().get(position).createTime);
                intent.putExtra("ids", stickyTransAdapter.getDataList().get(position).id);
                startActivity(intent);
            }
          /* if (sta == 12 || sta == 8) {
                LogUtils.e("返回类型=="+sta);
           }*/
        });
    }

    @Override
    public void getDataMore(List<TransactionRecordBean.ModelBean.DataListBean> list) {
      /*  if (list.size() < REQUEST_COUNT) {
            recycleRecord.setNoMore(true);
        }*/
        listData.addAll(list);
        recycleRecord.refreshComplete(REQUEST_COUNT);
        stickyTransAdapter.addAll(list);
        lRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void noNetwork() {
        if (swipe.isRefreshing()) {
            swipe.setRefreshing(false);
        }
        if (noOne) {
            recycleRecord.setVisibility(View.INVISIBLE);
        } else {
        }
    }


}
