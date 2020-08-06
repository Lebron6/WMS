package com.ocean.wms.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ocean.wms.R;
import com.ocean.wms.callback.OnScanClickListener;
import com.ocean.wms.entity.QueryCarResult;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> queryCarResult;

    public LocationAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<String> queryCarResult) {
        this.queryCarResult = queryCarResult;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_location, parent, false);
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
            viewHolder.tvLocation.setText(queryCarResult.get(position));

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
        return queryCarResult.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_location)
        TextView tvLocation;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}