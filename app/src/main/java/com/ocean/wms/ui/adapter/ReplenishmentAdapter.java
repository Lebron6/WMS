package com.ocean.wms.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.wms.R;
import com.ocean.wms.entity.BuListResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReplenishmentAdapter extends RecyclerView.Adapter {


    private Context context;
    private BuListResult infoBean;

    public ReplenishmentAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(BuListResult infoBean) {
        this.infoBean = infoBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_replenishment, parent, false);
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
        viewHolder.tvBhdh.setText(infoBean.getData().get(position).getBucode());
        viewHolder.tvHz.setText(infoBean.getData().get(position).getSupplier());
        viewHolder.tvHwmc.setText(infoBean.getData().get(position).getGoods());
        viewHolder.tvSl.setText(infoBean.getData().get(position).getMovenum());
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

        @BindView(R.id.tv_bhdh)
        TextView tvBhdh;
        @BindView(R.id.tv_hz)
        TextView tvHz;
        @BindView(R.id.tv_hwmc)
        TextView tvHwmc;
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