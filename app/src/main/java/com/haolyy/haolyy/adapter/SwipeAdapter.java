package com.haolyy.haolyy.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.entity.my.MessageBean;

import java.util.List;

/**
 * Created by wangyin on 2017/10/11.
 */

public class SwipeAdapter extends BaseQuickAdapter<MessageBean.ModelBeanX.ModelBean.AccountMessagesBean, BaseViewHolder> {
    /**
     * 和Activity通信的接口
     */
    public interface onSwipeListener {
        void onDel(int pos);
    }

    private onSwipeListener mOnSwipeListener;

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

    public SwipeAdapter(@LayoutRes int layoutResId, @Nullable List<MessageBean.ModelBeanX.ModelBean.AccountMessagesBean> data) {
        super(layoutResId, data);
        notifyDataSetChanged();
    }

    @Override
    protected void convert(final BaseViewHolder viewHolder, MessageBean.ModelBeanX.ModelBean.AccountMessagesBean item) {
//        viewHolder.setText(R.id.title, item.getNews_title()).setText(R.id.date,item.getCreate_date()).setText(R.id.content,item.getNews_detail())
//                .addOnClickListener(R.id.ll_content);
//        if (item.getStatus()==1) {
//            viewHolder.getView(R.id.iv_mark).setVisibility(View.VISIBLE);
//        } else {
//            viewHolder.getView(R.id.iv_mark).setVisibility(View.GONE);
//        }
//        ((SwipeMenuView) viewHolder.getView(R.id.swipeMenuView)).setIos(true).setLeftSwipe(true);
//
//        viewHolder.getView(R.id.btnDelete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (null != mOnSwipeListener) {
//                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
//                    //且如果想让侧滑菜单同时关闭，需要同时调用
//                    //((CstSwipeDelMenu) holder.itemView).quickClose();
//                    mOnSwipeListener.onDel(viewHolder.getLayoutPosition());
//                }
//            }
//        });
    }
}
