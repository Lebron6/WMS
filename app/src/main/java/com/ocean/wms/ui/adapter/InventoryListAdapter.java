package com.ocean.wms.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.wms.R;
import com.ocean.wms.entity.InventoryListResult;
import com.ocean.wms.ui.activity.StorageAreaActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InventoryListAdapter extends RecyclerView.Adapter {



    private Context context;
    private InventoryListResult infoBean;

    public InventoryListAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(InventoryListResult infoBean) {
        this.infoBean = infoBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_inventory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        ViewHolder viewHolder = (ViewHolder) holder;
        if (mOnClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.onClickListener(v, position);
                }
            });
        }
        viewHolder.tvHwbm.setText(infoBean.getData().get(position).getGoodscoding());
        viewHolder.tvHwmc.setText(infoBean.getData().get(position).getCname());
        viewHolder.tvCcq.setText(infoBean.getData().get(position).getReserve());
        viewHolder.tvZkc.setText(infoBean.getData().get(position).getAllnum()+"");
        viewHolder.tvDmjhq.setText(infoBean.getData().get(position).getSupnum());
        viewHolder.tvHzmc.setText(infoBean.getData().get(position).getSupplier());
        viewHolder.tvCcq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageAreaActivity.actionStart(context,infoBean.getData().get(position).getCgID(),infoBean.getData().get(position).getSupplierID(),"0");
            }
        });
        viewHolder.tvDmjhq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StorageAreaActivity.actionStart(context,infoBean.getData().get(position).getCgID(),infoBean.getData().get(position).getSupplierID(),"1");
            }
        });
    }

    public interface OnClickListener {
        void onClickListener(View view, int position);
    }

    public void setOnClickListener(OnClickListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }

    private OnClickListener mOnClickListener;


    @Override
    public int getItemCount() {
        return infoBean.getData().size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_hwbm)
        TextView tvHwbm;
        @BindView(R.id.tv_hwmc)
        TextView tvHwmc;
        @BindView(R.id.tv_hzmc)
        TextView tvHzmc;
        @BindView(R.id.tv_zkc)
        TextView tvZkc;
        @BindView(R.id.tv_ccq)
        TextView tvCcq;
        @BindView(R.id.tv_dmjhq)
        TextView tvDmjhq;
        @BindView(R.id.ll_title_texts)
        LinearLayout llTitleTexts;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}