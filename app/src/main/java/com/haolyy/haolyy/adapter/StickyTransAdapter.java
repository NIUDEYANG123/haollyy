package com.haolyy.haolyy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haolyy.haolyy.R;
import com.haolyy.haolyy.entity.my.TransactionRecordBean;
import com.haolyy.haolyy.utils.AccountUtil;
import com.haolyy.haolyy.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class StickyTransAdapter extends RecyclerView.Adapter<StickyTransAdapter.ViewHolder> implements
        StickyHeaderAdapter<StickyTransAdapter.HeaderHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    protected List<TransactionRecordBean.ModelBean.DataListBean> mDataList = new ArrayList<>();

    public StickyTransAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = mInflater.inflate(R.layout.item_transaction_record, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (mDataList.size() > position) {
            TransactionRecordBean.ModelBean.DataListBean bean = mDataList.get(position);
            viewHolder.tv_name.setText(bean.typeName);
            viewHolder.tv_time.setText(bean.createTime);
            /**
             * 列表返回的type:
             1：充值  2：提现 3：加入计划  4：散标出借  5：预约标回款  6：散标回款
             7：预约标收益  8：散标收益  9：平台奖励  10：提现退款  11：出借退款
             12：其他
             */
            if (bean.optype==1) {
                viewHolder.tv_money.setText("+" + AccountUtil.StringToMString(bean.operationAmount));
            } else if(bean.optype==2){
                viewHolder.tv_money.setText("-" + AccountUtil.StringToMString(bean.operationAmount));
            }else {
                viewHolder.tv_money.setText(AccountUtil.StringToMString(bean.operationAmount));
            }
            viewHolder.tv_available.setText("余额： " + AccountUtil.StringToMString(bean.availableAmountAfter)+ "元");
        }
    }
    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List<TransactionRecordBean.ModelBean.DataListBean> getDataList() {
        return mDataList;
    }

    public void setDataList(Collection<TransactionRecordBean.ModelBean.DataListBean> list) {
        this.mDataList.clear();
        this.mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(Collection<TransactionRecordBean.ModelBean.DataListBean> list) {
        int lastIndex = this.mDataList.size();
        if (this.mDataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    public void remove(int position) {
        this.mDataList.remove(position);
        notifyItemRemoved(position);

        if (position != (getDataList().size())) { // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, this.mDataList.size() - position);
        }
    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    /**
     * @param position the item position
     * @return
     */
    long i = 0;

    @Override
    public long getHeaderId(int position) {
        if(mDataList.size()>position){
            return CommonUtils.dataOne( mDataList.get(position).createTime.substring(0,7)+"-01");
        }
        return CommonUtils.dataOne( mDataList.get(position-1).createTime.substring(0,7)+"-01");
        //return (long) position / 7;
    }

    /**
     * @param parent the header's view parent
     * @return
     */
    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.item_stick_header, parent, false);
        return new HeaderHolder(view);
    }

    /**
     * @param viewholder the header view holder
     * @param position   the header's item position
     */
    @Override
    public void onBindHeaderViewHolder(final HeaderHolder viewholder, final int position) {
        if(mDataList.size()>position){
            viewholder.header.setText( mDataList.get(position).createTime.substring(0,7));
        }else {
            viewholder.itemView.setVisibility(View.GONE);
        }

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_time, tv_available, tv_money;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tr_item_name);
            tv_time = (TextView) itemView.findViewById(R.id.tr_item_time);
            tv_available = (TextView) itemView.findViewById(R.id.tr_item_available);
            tv_money = (TextView) itemView.findViewById(R.id.tr_item_money);
        }
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView header;

        public HeaderHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.tr_item_date);
        }
    }
}
