package com.haolyy.haolyy.ui.product;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;


import com.haolyy.haolyy.BuildConfig;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.base.BaseFragment;
import com.haolyy.haolyy.base.BasePresenter;
import com.haolyy.haolyy.config.NetConstantValues;
import com.haolyy.haolyy.utils.NetworkUtils;
import com.haolyy.haolyy.utils.WYUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author wangyin
 * @date 2017/12/12.
 * @description : 问题中心（预约标）
 */

public class QAFragment extends BaseFragment {
    @BindView(R.id.web_fp)
    WebView webFp;
    @BindView(R.id.web_p)
    ProgressBar webP;
    Unbinder unbinder;

    private String projectType;
    private String borrowNo;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qa, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        webFp.clearCache(true);//清除缓存
        unbinder.unbind();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser&&null!=webFp){
            if (TextUtils.isEmpty(borrowNo)) {
                //常见问题
                WYUtils.loadHtmlNew(getActivity(),NetConstantValues.CONTRACT_URL+"about/4.html", webFp, null);
                // WYUtils.loadHtmlNew(BuildConfig.INTERFACE_CONTART + "second/helpCenter.html?projectType=1&type=1", webFp, webP);}
            }
            else if(borrowNo.equals("name")){
                WYUtils.loadHtmlNew(getActivity(),NetConstantValues.CONTRACT_URL+"about/1.html", webFp, null);
            }
            else {
                //散标详情
                //H5链接中文编码
                WYUtils.loadHtmlNew(getActivity(),Uri.encode(NetConstantValues.CONTRACT_URL+"about/3.html?borrowNo=" + borrowNo,"-![.:/,%?&=]"),webFp,null);
            }
        }
    }

    private void init() {
        borrowNo = getArguments().getString("borrowNo");
        if (TextUtils.isEmpty(borrowNo)) {
            //常见问题
            WYUtils.loadHtmlNew(getActivity(),NetConstantValues.CONTRACT_URL+"about/4.html", webFp, null);
            // WYUtils.loadHtmlNew(BuildConfig.INTERFACE_CONTART + "second/helpCenter.html?projectType=1&type=1", webFp, webP);}
        }
        else if(borrowNo.equals("name")){
            WYUtils.loadHtmlNew(getActivity(),NetConstantValues.CONTRACT_URL+"about/1.html", webFp, null);
        }
        else {
            //散标详情
            //H5链接中文编码
            WYUtils.loadHtmlNew(getActivity(),Uri.encode(NetConstantValues.CONTRACT_URL+"about/3.html?borrowNo=" + borrowNo,"-![.:/,%?&=]"),webFp,null);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    public static QAFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString("borrowNo", name);
        QAFragment fragment = new QAFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
