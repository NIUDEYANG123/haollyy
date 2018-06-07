package com.haolyy.haolyy.ui.home;

import android.os.Bundle;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.custom.TopBar;
import com.haolyy.haolyy.entity.Notice;
import com.haolyy.haolyy.entity.my.MessageBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangyin on 2017/10/12.
 */

public class NoticeDetail extends BaseActivity {
    @BindView(R.id.topBar)
    TopBar topBar;
    @BindView(R.id.message_title)
    TextView messageTitle;
    @BindView(R.id.message_date)
    TextView messageDate;
    @BindView(R.id.message_content)
    TextView messageContent;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        ButterKnife.bind(this);
        init();
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

        Notice.ModelBean.ListBean resultListBean = (Notice.ModelBean.ListBean) getIntent().getSerializableExtra("message");
        messageTitle.setText(resultListBean.getTitle());
        messageDate.setText(resultListBean.getCreateTime());
        messageContent.setText(resultListBean.getNoticeContext());
    }
}
