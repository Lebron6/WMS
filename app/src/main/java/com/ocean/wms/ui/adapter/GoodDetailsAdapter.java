package com.ocean.wms.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ocean.wms.R;
import com.ocean.wms.callback.OnScanClickListener;
import com.ocean.wms.entity.GoodsDetails;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodDetailsAdapter extends RecyclerView.Adapter {

    private Context context;
    private GoodsDetails infoBean;

    public GoodDetailsAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(GoodsDetails infoBean) {
        this.infoBean = infoBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_goods_details, parent, false);
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
        viewHolder.tvHwmc.setText(infoBean.getData().get(position).getGoods());
        viewHolder.tvSl.setText(infoBean.getData().get(position).getOutnum());
        viewHolder.tvCw.setText(infoBean.getData().get(position).getWarearea());
        viewHolder.tvPch.setText(infoBean.getData().get(position).getCbatch());
        switch (infoBean.getData().get(position).getIsok()) {
            case "0":
                viewHolder.ivStatus.setVisibility(View.VISIBLE);
                viewHolder.ivStatus.setBackgroundResource(R.mipmap.icon_cuo);
                break;
            case "1":
                viewHolder.ivStatus.setVisibility(View.VISIBLE);
                viewHolder.ivStatus.setBackgroundResource(R.mipmap.icon_dui);
                break;
        }
        viewHolder.ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onScanClickListener.click(position);
            }
        });
    }

    OnScanClickListener onScanClickListener;
    public void setOnScanClickListener(OnScanClickListener onScanClickListener){
        this.onScanClickListener=onScanClickListener;
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

        @BindView(R.id.tv_hwmc)
        TextView tvHwmc;
        @BindView(R.id.tv_sl)
        TextView tvSl;
        @BindView(R.id.tv_cw)
        TextView tvCw;
        @BindView(R.id.tv_pch)
        TextView tvPch;
        @BindView(R.id.tv_not)
        TextView tvNot;
        @BindView(R.id.iv_status)
        ImageView ivStatus;
        @BindView(R.id.tv_p)
        LinearLayout tvP;
        @BindView(R.id.iv_scan)
        ImageView ivScan;
        @BindView(R.id.ll_title_texts)
        LinearLayout llTitleTexts;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}