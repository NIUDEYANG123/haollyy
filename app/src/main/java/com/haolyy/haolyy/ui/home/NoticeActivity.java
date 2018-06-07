package com.haolyy.haolyy.ui.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.ajguan.library.EasyRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.adapter.NoticeAdapter;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.WebActivity;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.Notice;
import com.haolyy.haolyy.ui.home.View.NoticeView;
import com.haolyy.haolyy.ui.home.presenter.NoticePresenter;
import com.haolyy.haolyy.utils.AppToast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wyman
 * @date 2018/3/3
 * description :
 */

public class NoticeActivity extends BaseActivity<NoticePresenter, NoticeView> implements NoticeView, EasyRefreshLayout.EasyEvent, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.rv_main)
    RecyclerView rvMain;
    @BindView(R.id.easy_layout)
    EasyRefreshLayout easyLayout;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    private int pageNum = 1;
    private int pageSize = 10;
    private NoticeAdapter noticeAdapter;
    private List<Notice.ModelBean.ListBean> messageBeanList;

    @Override
    protected NoticePresenter createPresenter() {
        return new NoticePresenter(mContext);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        ButterKnife.bind(this);
        init();


    }

    @Override
    protected void onResume() {
        super.onResume();
        pageNum = 1;
        mPresenter.getNotice(pageNum + "", pageSize + "", false);
    }

    private void init() {
        topBar.setOnItemClickListener(new TopBar.OnItemClickListener() {
            @Override
            public void OnLeftButtonClicked() {
                finish();
            }

            @Override
            public void OnRightButtonClicked() {

            }
        });
        easyLayout.addEasyEvent(this);


    }


    @Override
    public void onRefreshing() {
        pageNum = 1;
        mPresenter.getNotice(pageNum + "", pageSize + "", false);
    }

    @Override
    public void onLoadMore() {
        easyLayout.loadMoreComplete();
        pageNum += 1;
        mPresenter.getNotice(pageNum + "", pageSize + "", true);
    }

    @Override
    public void getMessage(Notice notice) {
        easyLayout.refreshComplete();
        if (notice.getModel().getList().size() == 0) {
            easyLayout.setVisibility(View.GONE);
            llEmpty.setVisibility(View.VISIBLE);
        } else {
            easyLayout.setVisibility(View.VISIBLE);
            llEmpty.setVisibility(View.GONE);
            messageBeanList = notice.getModel().getList();
            noticeAdapter = new NoticeAdapter(R.layout.item_notice, messageBeanList);
            rvMain.setAdapter(noticeAdapter);
            rvMain.setLayoutManager(new LinearLayoutManager(this));
            noticeAdapter.setOnItemClickListener(this);
        }
    }

    @Override
    public void getMessageMore(Notice notice) {
        easyLayout.loadMoreComplete();

        if (notice.getModel().getList().size() == 0) {
            AppToast.makeShortToast(mContext, "没有数据了");

        } else {
            messageBeanList.addAll(notice.getModel().getList());
            noticeAdapter = new NoticeAdapter(R.layout.item_notice, messageBeanList);
            rvMain.setLayoutManager(new LinearLayoutManager(this));
            rvMain.setAdapter(noticeAdapter);
            noticeAdapter.setOnItemClickListener(this);
            //定位
            moveToPosition(new LinearLayoutManager(this), rvMain, messageBeanList.size() - notice.getModel().getList().size());
        }
    }


    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    private void moveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        startActivity(WebActivity.getWebIntentContent(mContext,messageBeanList.get(position).getTitle(),messageBeanList.get(position).getNoticeContext()));
    }
}
