package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ocean.wms.R;
import com.ocean.wms.api.HttpUtil;
import com.ocean.wms.entity.BuListResult;
import com.ocean.wms.entity.OutGoodsList;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.RecyclerViewHelper;
import com.ocean.wms.tools.TitleBarManger;
import com.ocean.wms.ui.adapter.OutGoodsAdapter;
import com.ocean.wms.ui.adapter.ReplenishmentAdapter;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/1/9.
 * 补货列表
 */
public class OutGoodsListActivity extends BaseActivity {
    @BindView(R.id.rv_out_goods)
    RecyclerView rvReplenishment;
    OutGoodsAdapter adapter;
    private OutGoodsList result;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, OutGoodsListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("出库列表");
        manger.setBack();
        HttpUtil.createRequest(this).outGoodList(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<OutGoodsList>() {
            @Override
            public void onResponse(Call<OutGoodsList> call, Response<OutGoodsList> response) {

                if (response.body() != null && response.body().getData() != null && response.body().getData().size() > 0) {
                    if (response.body().getStatus() == 200) {
                        result = response.body();
                        adapter.setDatas(result);
                        RecyclerViewHelper.initRecyclerViewV(OutGoodsListActivity.this, rvReplenishment, false, adapter, true);
                    } else {
                        Toast.makeText(OutGoodsListActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OutGoodsListActivity.this, "暂无出库清单", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<OutGoodsList> call, Throwable t) {
                Toast.makeText(OutGoodsListActivity.this, "网络异常:获取出库列表", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_out_goods_list;
    }

    @Override
    protected void initViews() {
        adapter = new OutGoodsAdapter(OutGoodsListActivity.this);
        adapter.setOnClickListener(onItemClickListener);
    }

    OutGoodsAdapter.OnClickListener onItemClickListener = new OutGoodsAdapter.OnClickListener() {
        @Override
        public void onClickListener(View view, int position) {
            OutGoodsActivity.actionStart(OutGoodsListActivity.this, result.getData().get(position).getOutID());
        }
    };

    @Override
    protected void initDatas() {

    }
}
