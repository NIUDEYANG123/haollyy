package com.haolyy.haolyy.ui.my;

import android.os.Bundle;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseActivity;
import com.haolyy.haolyy.base.BasePresenter;

/**
 * Created by shanghai on 2018/2/8.
 */

public class ShareActivity extends BaseActivity{
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
    }
}
