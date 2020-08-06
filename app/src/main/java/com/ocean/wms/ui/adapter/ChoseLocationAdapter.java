package com.ocean.wms.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ocean.wms.R;
import com.ocean.wms.callback.OnScanClickListener;
import com.ocean.wms.entity.GoodsDetails;
import com.ocean.wms.entity.QueryCarResult;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChoseLocationAdapter extends RecyclerView.Adapter {


    private Context context;
    private QueryCarResult queryCarResult;

    public ChoseLocationAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(QueryCarResult queryCarResult) {
        this.queryCarResult = queryCarResult;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chose_location, parent, false);
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
        if ( queryCarResult.getData().getClient_list().get(position).getTag()==0){
            viewHolder.cb.setChecked(false);
        }else{
            viewHolder.cb.setChecked(true);
        }
        viewHolder.tvLocation.setText(queryCarResult.getData().getClient_list().get(position).getCorp());
        viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b==true){
                    queryCarResult.getData().getClient_list().get(position).setTag(1);
                }else{
                    queryCarResult.getData().getClient_list().get(position).setTag(0);
                }
            }
        });
    }

    OnScanClickListener onScanClickListener;

    public void setOnScanClickListener(OnScanClickListener onScanClickListener) {
        this.onScanClickListener = onScanClickListener;
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
        return queryCarResult.getData().getClient_list().size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.cb)
        CheckBox cb;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}