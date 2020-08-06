package com.ocean.wms.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ocean.wms.R;
import com.ocean.wms.callback.OnScanClickListener;
import com.ocean.wms.entity.GoodsDetails;
import com.ocean.wms.entity.SearchCarInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarMotionAdapter extends RecyclerView.Adapter {



    private Context context;
    private SearchCarInfo searchCarInfo;

    public CarMotionAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(SearchCarInfo searchCarInfo) {
        this.searchCarInfo = searchCarInfo;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car_motion, parent, false);
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
        viewHolder.tvCarNum.setText(searchCarInfo.getData().getList().get(position).getCarnumber());
        viewHolder.tvBackTime.setText(searchCarInfo.getData().getList().get(position).getEndtime());
        viewHolder.tvDriver.setText(searchCarInfo.getData().getList().get(position).getDriver());
        viewHolder.tvSendTime.setText(searchCarInfo.getData().getList().get(position).getInputtime());
        viewHolder.tvStutas.setText(searchCarInfo.getData().getList().get(position).getStatus_name());
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
        return searchCarInfo.getData().getList().size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_car_num)
        TextView tvCarNum;
        @BindView(R.id.tv_driver)
        TextView tvDriver;
        @BindView(R.id.tv_send_time)
        TextView tvSendTime;
        @BindView(R.id.tv_back_time)
        TextView tvBackTime;
        @BindView(R.id.tv_stutas)
        TextView tvStutas;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}