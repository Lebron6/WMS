package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ocean.wms.R;
import com.ocean.wms.api.HttpUtil;
import com.ocean.wms.entity.SearchResult;
import com.ocean.wms.entity.WareScanResult;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.TitleBarManger;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/3/17.
 * 查询结果
 */
public class SearchResultActivity extends BaseActivity {


    public static String WAREDATA = "WAREDATA";
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_hzbh)
    TextView tvHzbh;
    @BindView(R.id.tv_hwbm)
    TextView tvHwbm;
    @BindView(R.id.tv_sl)
    TextView tvSl;
    @BindView(R.id.tv_pc)
    TextView tvPc;
    @BindView(R.id.tv_cw)
    TextView tvCw;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    private WareScanResult result;

    public static void actionStart(Context context, String datas) {
        Intent intent = new Intent();
        intent.putExtra(WAREDATA, datas);
        intent.setClass(context, SearchResultActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("查询结果");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {
        String extra = getIntent().getStringExtra(WAREDATA);
        try {
            result = new Gson().fromJson(extra, WareScanResult.class);

            HttpUtil.createRequest(this).search(PreferenceUtils.getInstance().getUserToken(), result.getHU()).enqueue(new Callback<SearchResult>() {
                @Override
                public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {

                    if (response.body() != null && response.body().getStatus() == 200) {
tvHzbh.setText("货主编号："+response.body().getData().getCorpcode());
                        tvHwbm.setText("货物编码：" + response.body().getData().getGoods());
                        tvSl.setText("数量：" + response.body().getData().getNum());
                        tvPc.setText("批次：" + response.body().getData().getCbatch());
                        tvCw.setText("储位: "+response.body().getData().getWarearea());
                    } else {
                        Toast.makeText(SearchResultActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<SearchResult> call, Throwable t) {
                    Toast.makeText(SearchResultActivity.this, "网络异常:查询失败", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Toast.makeText(SearchResultActivity.this, "扫码异常:请扫描正确二维码或条形码", Toast.LENGTH_SHORT).show();
        }

    }

    private static final int VERI = 1;//验证

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
