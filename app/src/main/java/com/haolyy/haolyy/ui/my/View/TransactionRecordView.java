package com.haolyy.haolyy.ui.my.View;


import com.haolyy.haolyy.entity.my.TransactionRecordBean;

import java.util.List;

/**
 * Created by liliang on 2017/9/21.
 */

public interface TransactionRecordView {
    void getData(List<TransactionRecordBean.ModelBean.DataListBean> list);
    void getDataMore(List<TransactionRecordBean.ModelBean.DataListBean> list);

    void noNetwork();
}
