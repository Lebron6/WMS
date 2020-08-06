package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ocean.wms.R;
import com.ocean.wms.api.HttpUtil;
import com.ocean.wms.entity.StorageAreaResult;
import com.ocean.wms.entity.OutGoodsList;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.RecyclerViewHelper;
import com.ocean.wms.tools.TitleBarManger;
import com.ocean.wms.ui.adapter.OutGoodsAdapter;
import com.ocean.wms.ui.adapter.StorageAreaAdapter;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/1/9.
 * 存储区/地面拣货区
 */
public class StorageAreaActivity extends BaseActivity {
    @BindView(R.id.rv_out_goods)
    RecyclerView rvReplenishment;
    StorageAreaAdapter adapter;
    private StorageAreaResult result;

    public static final String CGID = "CGID";
    public static final String SUPPLIERID = "SUPPLIERID";
    public static final String ISTYPE = "ISTYPE";

    public static void actionStart(Context context, String cgid, String supplierid, String istype) {
        Intent intent = new Intent();
        intent.setClass(context, StorageAreaActivity.class);
        intent.putExtra(CGID, cgid);
        intent.putExtra(ISTYPE, istype);
        intent.putExtra(SUPPLIERID, supplierid);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        if (getIntent().getStringExtra(ISTYPE).equals("1")){
            manger.setTitle("地面拣货区");
        }else{
            manger.setTitle("存储区");
        }

        manger.setBack();
        HttpUtil.createRequest(this).showDetails(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(CGID),
                getIntent().getStringExtra(SUPPLIERID), getIntent().getStringExtra(ISTYPE)).enqueue(new Callback<StorageAreaResult>() {
            @Override
            public void onResponse(Call<StorageAreaResult> call, Response<StorageAreaResult> response) {

                if (response.body() != null && response.body().getData() != null && response.body().getData().size() > 0) {
                    if (response.body().getStatus() == 200) {
                        result = response.body();
                        adapter.setDatas(result);
                        RecyclerViewHelper.initRecyclerViewV(StorageAreaActivity.this, rvReplenishment, false, adapter, true);
                    } else {
                        Toast.makeText(StorageAreaActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(StorageAreaActivity.this, "暂无清单", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<StorageAreaResult> call, Throwable t) {
                Toast.makeText(StorageAreaActivity.this, "网络异常:获取列表失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_storage_area;
    }

    @Override
    protected void initViews() {
        adapter = new StorageAreaAdapter(StorageAreaActivity.this);
        adapter.setOnClickListener(onItemClickListener);
    }

    StorageAreaAdapter.OnClickListener onItemClickListener = new StorageAreaAdapter.OnClickListener() {
        @Override
        public void onClickListener(View view, int position) {
        }
    };

    @Override
    protected void initDatas() {

    }
}
