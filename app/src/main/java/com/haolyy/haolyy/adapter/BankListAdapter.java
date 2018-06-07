package com.haolyy.haolyy.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haolyy.haolyy.R;
import com.haolyy.haolyy.entity.bank.BankListBean;
import com.haolyy.haolyy.utils.WYUtils;

import java.util.List;

/**
 * Created by wangyin on 2017/10/16.
 */

public class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.MyHolder>{

    private Context mContext;
    private List<BankListBean.ModelBean> list;
    private OnItemOnClickListener onItemOnClickListener;

    public void setOnItemOnClickListener(OnItemOnClickListener onItemOnClickListener) {
        this.onItemOnClickListener = onItemOnClickListener;
    }

    public interface OnItemOnClickListener {
        void itemClickListener(View view, BankListBean.ModelBean modelBean);
    }
    public BankListAdapter(Context mContext, List<BankListBean.ModelBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_bank_list, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        WYUtils.showBankLogo(list.get(position).getBankCode(),holder.bank_logo);
        holder.bank_name.setText(list.get(position).getBankName());
        holder.bank_limit.setText("单日限额"+Double.parseDouble(list.get(position).getSamedayMaxAmount())/10000+"万 ，"
                +"单笔限额"+Double.parseDouble(list.get(position).getSingleMaxAmount())/10000+"万");
        if (onItemOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemOnClickListener.itemClickListener(view,list.get(position));
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView bank_logo;
        private TextView bank_name;
        private TextView bank_limit;
        public MyHolder(View itemView) {
            super(itemView);
            bank_logo = (ImageView) itemView.findViewById(R.id.iv_bank_logo);
            bank_name = (TextView) itemView.findViewById(R.id.tv_bank_name);
            bank_limit = (TextView) itemView.findViewById(R.id.tv_bank_limit);
        }
    }

}
