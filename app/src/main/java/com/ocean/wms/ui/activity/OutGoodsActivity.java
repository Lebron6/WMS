package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ocean.wms.R;
import com.ocean.wms.api.HttpUtil;
import com.ocean.wms.entity.OutGoodsDetails;
import com.ocean.wms.entity.OutGoodsList;
import com.ocean.wms.entity.OutGoodsResult;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.RecyclerViewHelper;
import com.ocean.wms.tools.TitleBarManger;
import com.ocean.wms.ui.adapter.OutGoodDetailsAdapter;
import com.ocean.wms.ui.adapter.OutGoodsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/1/10.
 */
public class OutGoodsActivity extends BaseActivity {
    OutGoodDetailsAdapter adapter;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.iv_tips_top)
    ImageView ivTipsTop;
    @BindView(R.id.iv_scan_top)
    ImageView ivScanTop;
    @BindView(R.id.tv_ckdh)
    TextView tvCkdh;
    @BindView(R.id.tv_hzmc)
    TextView tvHzmc;
    @BindView(R.id.tv_khmc)
    TextView tvKhmc;
    @BindView(R.id.tv_xdsj)
    TextView tvXdsj;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.txt_hwxx)
    TextView txtHwxx;
    @BindView(R.id.ll_title_texts)
    LinearLayout llTitleTexts;
    @BindView(R.id.rv_out_goods_details)
    RecyclerView rvOutGoodsDetails;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    private static String OUTID = "OUTID";
    private OutGoodsDetails outGoodsDetails;

    public static void actionStart(Context context, String outId) {
        Intent intent = new Intent();
        intent.putExtra(OUTID, outId);
        intent.setClass(context, OutGoodsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("出库");
        manger.setBack();
        HttpUtil.createRequest(this).outGoodsOne(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(OUTID)).enqueue(new Callback<OutGoodsDetails>() {
            @Override
            public void onResponse(Call<OutGoodsDetails> call, Response<OutGoodsDetails> response) {
                if (response.body() != null) {
                    outGoodsDetails = response.body();
                    if (outGoodsDetails.getStatus() == 200) {
                        tvCkdh.setText("出库单号：" + outGoodsDetails.getData().getOutcode());
                        tvHzmc.setText("货主名称：" + outGoodsDetails.getData().getSupplier());
                        tvKhmc.setText("客户名称：" + outGoodsDetails.getData().getClient());
                        tvXdsj.setText("下单时间：" + outGoodsDetails.getData().getOuttime());
                        adapter = new OutGoodDetailsAdapter(OutGoodsActivity.this);
                        adapter.setDatas(outGoodsDetails);
                        adapter.setOnClickListener(onItemClickListener);
                        RecyclerViewHelper.initRecyclerViewV(OutGoodsActivity.this, rvOutGoodsDetails, false, adapter, true);
                    } else {
                        Toast.makeText(OutGoodsActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<OutGoodsDetails> call, Throwable t) {
                Toast.makeText(OutGoodsActivity.this, "网络异常:获取出库详情失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_out_goods;
    }

    @Override
    protected void initViews() {

    }

    OutGoodDetailsAdapter.OnClickListener onItemClickListener = new OutGoodDetailsAdapter.OnClickListener() {
        @Override
        public void onClickListener(View view, int position) {
            GoodsDetailsActivity.actionStart(OutGoodsActivity.this, outGoodsDetails.getData().getList().get(position).getOutID(), outGoodsDetails.getData().getList().get(position).getId(),outGoodsDetails.getData().getList().get(position).getCgID());
        }
    };

    @Override
    protected void initDatas() {

    }

    private boolean isCanClick() {
        if (outGoodsDetails != null && outGoodsDetails.getData() != null && outGoodsDetails.getData().getList() != null) {
            for (int i = 0; i < outGoodsDetails.getData().getList().size(); i++) {
                if (outGoodsDetails.getData().getList().get(i).getTag().equals("1")) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    @OnClick(R.id.tv_commit)
    public void onViewClicked() {

        if (isCanClick()) {
            HttpUtil.createRequest(this).chuDone(PreferenceUtils.getInstance().getUserToken(),
                    getIntent().getStringExtra(OUTID)).enqueue(new Callback<OutGoodsResult>() {
                @Override
                public void onResponse(Call<OutGoodsResult> call, Response<OutGoodsResult> response) {
                    if (response!=null){
                        if (response.body().getStatus() == 200) {
                            CommitSucActivity.actionStart(OutGoodsActivity.this,"出库");
                            finish();
                        } else {
                            Toast.makeText(OutGoodsActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(OutGoodsActivity.this, "请求返回结果为空", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<OutGoodsResult> call, Throwable t) {
                    Toast.makeText(OutGoodsActivity.this, "网络异常:出库失败", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "请扫描核对货物信息", Toast.LENGTH_SHORT).show();
        }
    }
}
