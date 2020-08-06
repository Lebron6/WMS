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
import com.ocean.wms.entity.OutGoodsDetails;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OutGoodDetailsAdapter extends RecyclerView.Adapter {


    private Context context;
    private OutGoodsDetails infoBean;

    public OutGoodDetailsAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(OutGoodsDetails infoBean) {
        this.infoBean = infoBean;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_out_goods_details, parent, false);
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
        viewHolder.tvHwbm.setText(infoBean.getData().getList().get(position).getGoodscoding());
        viewHolder.tvHwmc.setText(infoBean.getData().getList().get(position).getGoods());
        viewHolder.tvSl.setText(infoBean.getData().getList().get(position).getOutnum());
        switch (infoBean.getData().getList().get(position).getTag()) {
            case "1":
                viewHolder.tvNot.setVisibility(View.GONE);
                viewHolder.ivStatus.setVisibility(View.VISIBLE);
                break;
            default:
                viewHolder.tvNot.setVisibility(View.VISIBLE);
                viewHolder.ivStatus.setVisibility(View.GONE);
                break;
        }
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
        return infoBean.getData().getList().size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_hwbm)
        TextView tvHwbm;
        @BindView(R.id.tv_hwmc)
        TextView tvHwmc;
        @BindView(R.id.tv_sl)
        TextView tvSl;
        @BindView(R.id.iv_status)
        ImageView ivStatus;
        @BindView(R.id.tv_p)
        LinearLayout tvP;
        @BindView(R.id.ll_title_texts)
        LinearLayout llTitleTexts;
        @BindView(R.id.tv_not)
        TextView tvNot;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}