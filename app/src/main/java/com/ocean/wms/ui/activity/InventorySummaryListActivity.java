package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ocean.wms.R;
import com.ocean.wms.api.HttpUtil;
import com.ocean.wms.entity.InventoryListResult;
import com.ocean.wms.entity.StorageAreaResult;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.RecyclerViewHelper;
import com.ocean.wms.tools.TitleBarManger;
import com.ocean.wms.ui.adapter.InventoryListAdapter;
import com.ocean.wms.ui.adapter.StorageAreaAdapter;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/1/9.
 * 库存列表
 */
public class InventorySummaryListActivity extends BaseActivity {
    @BindView(R.id.rv_out_goods)
    RecyclerView rvReplenishment;
    InventoryListAdapter adapter;
    private InventoryListResult result;

    public static final String GOODSCODING = "GOODSCODING";
    public static final String CNAME = "CNAME";
    public static final String SUPPLIER = "SUPPLIER";

    public static void actionStart(Context context, String goodcoding, String cname, String supplier) {
        Intent intent = new Intent();
        intent.setClass(context, InventorySummaryListActivity.class);
        intent.putExtra(SUPPLIER, supplier);
        intent.putExtra(CNAME, cname);
        intent.putExtra(GOODSCODING, goodcoding);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("库存汇总");
        manger.setBack();
        HttpUtil.createRequest(this).getReserve(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(GOODSCODING),
                getIntent().getStringExtra(CNAME), getIntent().getStringExtra(SUPPLIER)).enqueue(new Callback<InventoryListResult>() {
            @Override
            public void onResponse(Call<InventoryListResult> call, Response<InventoryListResult> response) {

                if (response.body() != null && response.body().getData() != null && response.body().getData().size() > 0) {
                    if (response.body().getStatus() == 200) {
                        result = response.body();
                        adapter.setDatas(result);
                        RecyclerViewHelper.initRecyclerViewV(InventorySummaryListActivity.this, rvReplenishment, false, adapter, true);
                    } else {
                        Toast.makeText(InventorySummaryListActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(InventorySummaryListActivity.this, "暂无清单", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<InventoryListResult> call, Throwable t) {
                Toast.makeText(InventorySummaryListActivity.this, "网络异常:获取列表失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_inventory_list;
    }

    @Override
    protected void initViews() {
        adapter = new InventoryListAdapter(InventorySummaryListActivity.this);
    }


    @Override
    protected void initDatas() {

    }
}
