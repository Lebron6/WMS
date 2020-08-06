package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ocean.wms.R;
import com.ocean.wms.api.HttpUtil;
import com.ocean.wms.entity.BuListResult;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.RecyclerViewHelper;
import com.ocean.wms.tools.TitleBarManger;
import com.ocean.wms.ui.adapter.ReplenishmentAdapter;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/1/9.
 * 补货列表
 */
public class ReplenishmentListActivity extends BaseActivity {
    @BindView(R.id.rv_replenishment)
    RecyclerView rvReplenishment;
    ReplenishmentAdapter adapter;
    private BuListResult result;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ReplenishmentListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("补货列表");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_replenishment_list;
    }

    @Override
    protected void initViews() {
        adapter = new ReplenishmentAdapter(ReplenishmentListActivity.this);
        adapter.setOnClickListener(onItemClickListener);
    }

    ReplenishmentAdapter.OnClickListener onItemClickListener = new ReplenishmentAdapter.OnClickListener() {
        @Override
        public void onClickListener(View view, int position) {
            ReplenishmentActivity.actionStart(ReplenishmentListActivity.this, result.getData().get(position).getId());
        }
    };

    @Override
    protected void initDatas() {
        HttpUtil.createRequest(this).buList(PreferenceUtils.getInstance().getUserToken()).enqueue(new Callback<BuListResult>() {
            @Override
            public void onResponse(Call<BuListResult> call, Response<BuListResult> response) {

                if (response.body() != null && response.body().getData() != null && response.body().getData().size() > 0) {
                    if (response.body().getStatus() == 200) {
                        result = response.body();
                        adapter.setDatas(result);
                        RecyclerViewHelper.initRecyclerViewV(ReplenishmentListActivity.this, rvReplenishment, false, adapter, true);
                    } else {
                        Toast.makeText(ReplenishmentListActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ReplenishmentListActivity.this, "暂无补货清单", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<BuListResult> call, Throwable t) {
                Toast.makeText(ReplenishmentListActivity.this, "网络异常:获取补货列表", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
