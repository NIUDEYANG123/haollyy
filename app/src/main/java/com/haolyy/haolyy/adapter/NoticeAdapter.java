package com.haolyy.haolyy.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.entity.Notice;
import com.haolyy.haolyy.utils.LogUtils;

import java.util.List;


/**
 * Created by niudeyang on 2017/12/27.
 */

public class NoticeAdapter extends BaseQuickAdapter<Notice.ModelBean.ListBean, BaseViewHolder> {


    public NoticeAdapter(int layoutResId, @Nullable List<Notice.ModelBean.ListBean> data) {
        super(layoutResId, data);
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, Notice.ModelBean.ListBean item) {
        helper.setText(R.id.tv_title, item.getTitle()).setText(R.id.tv_content,item.getCreateTime());
    }
}
