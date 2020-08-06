package com.ocean.wms.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.wms.R;
import com.ocean.wms.entity.StorageAreaResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StorageAreaAdapter extends RecyclerView.Adapter {


    private Context context;
    private StorageAreaResult infoBean;

    public StorageAreaAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(StorageAreaResult infoBean) {
        this.infoBean = infoBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_storage_area, parent, false);
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
        viewHolder.tvHwbm.setText(infoBean.getData().get(position).getGoods());
        viewHolder.tvCwbm.setText(infoBean.getData().get(position).getWarearea());
        viewHolder.tvPc.setText(infoBean.getData().get(position).getCbatch());
        viewHolder.tvSl.setText(infoBean.getData().get(position).getGnum());
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
        @BindView(R.id.tv_cwbm)
        TextView tvCwbm;
        @BindView(R.id.tv_pc)
        TextView tvPc;
        @BindView(R.id.tv_sl)
        TextView tvSl;
        @BindView(R.id.ll_title_texts)
        LinearLayout llTitleTexts;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}