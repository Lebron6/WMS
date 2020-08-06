package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.ocean.wms.R;
import com.ocean.wms.api.HttpUtil;
import com.ocean.wms.callback.OnScanClickListener;
import com.ocean.wms.entity.GoodsDetails;
import com.ocean.wms.entity.GoodsDetailsScanResult;
import com.ocean.wms.entity.OutGoodsResult;
import com.ocean.wms.entity.WareScanResult;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.RecyclerViewHelper;
import com.ocean.wms.tools.TitleBarManger;
import com.ocean.wms.ui.adapter.GoodDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.xudaojie.qrcodelib.CaptureActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/1/16.
 * 货物明细
 */
public class GoodsDetailsActivity extends BaseActivity implements OnScanClickListener {


    private static String ID = "ID";
    private static String OUTID = "OUTID";
    private static String CGID = "CGID";
    GoodDetailsAdapter adapter;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.ll_title_texts)
    LinearLayout llTitleTexts;
    @BindView(R.id.rv_out_goods)
    RecyclerView rvOutGoods;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    private GoodsDetails result;

    public static void actionStart(Context context, String outId, String id, String cgid) {
        Intent intent = new Intent();
        intent.putExtra(OUTID, outId);
        intent.putExtra(ID, id);
        intent.putExtra(CGID, cgid);
        intent.setClass(context, GoodsDetailsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("货物明细");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_goods_details;
    }

    @Override
    protected void initViews() {
        adapter = new GoodDetailsAdapter(this);
        adapter.setOnScanClickListener(this);
    }

    @Override
    protected void initDatas() {
        HttpUtil.createRequest(this).outHuo(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(OUTID), getIntent().getStringExtra(ID)).enqueue(new Callback<GoodsDetails>() {
            @Override
            public void onResponse(Call<GoodsDetails> call, Response<GoodsDetails> response) {

                if (response.body() != null && response.body().getData() != null && response.body().getData().size() > 0) {
                    if (response.body().getStatus() == 200) {
                        result = response.body();
                        adapter.setDatas(result);
                        RecyclerViewHelper.initRecyclerViewV(GoodsDetailsActivity.this, rvOutGoods, false, adapter, true);
                    } else {
                        Toast.makeText(GoodsDetailsActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GoodsDetailsActivity.this, "货物明细清单", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GoodsDetails> call, Throwable t) {
                Toast.makeText(GoodsDetailsActivity.this, "网络异常:获取货物明细失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private static final int CK = 2;//出库
    private int position;

    /**
     * 处理二维码扫描结果
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            if (requestCode == CK) {
                String s = bundle.getString("result");
                GoodsDetailsScanResult goodsDetailsScanResult = new Gson().fromJson(s, GoodsDetailsScanResult.class);
                HttpUtil.createRequest(this).getOutChu(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(OUTID), result.getData().get(position).getId(), goodsDetailsScanResult.getHU()).enqueue(new Callback<OutGoodsResult>() {
                    @Override
                    public void onResponse(Call<OutGoodsResult> call, Response<OutGoodsResult> response) {

                        if (response.body() != null && response.body().getData() != null) {
                            if (response.body().getStatus() == 200) {
                                result.getData().get(position).setIsok("1");
                                adapter.setDatas(result);
                            } else {
                                Toast.makeText(GoodsDetailsActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(GoodsDetailsActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<OutGoodsResult> call, Throwable t) {
                        Toast.makeText(GoodsDetailsActivity.this, "网络异常:验证货物明细失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    private boolean isCanClick() {
        if (result != null && result.getData() != null && result.getData().size() > 0) {
            for (int i = 0; i < result.getData().size(); i++) {
                if (result.getData().get(i).getIsok().equals("1")) {
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
            List<String> warearea = new ArrayList<>();
            for (int i = 0; i < result.getData().size(); i++) {
                warearea.add(result.getData().get(i).getWarearea());
            }
            List<String> outnum = new ArrayList<>();
            for (int i = 0; i < result.getData().size(); i++) {
                outnum.add(result.getData().get(i).getOutnum());
            }
            List<String> cabtch = new ArrayList<>();
            for (int i = 0; i < result.getData().size(); i++) {
                cabtch.add(result.getData().get(i).getCbatch());
            }
            List<String> hu = new ArrayList<>();
            for (int i = 0; i < result.getData().size(); i++) {
                hu.add(result.getData().get(i).getHu());
            }
            List<String> wid = new ArrayList<>();
            for (int i = 0; i < result.getData().size(); i++) {
                wid.add(result.getData().get(i).getId());
            }

            HttpUtil.createRequest(this).outSure(PreferenceUtils.getInstance().getUserToken(),
                    getIntent().getStringExtra(OUTID), getIntent().getStringExtra(CGID), getIntent().getStringExtra(ID),warearea,outnum,cabtch,hu,wid).enqueue(new Callback<OutGoodsResult>() {
                @Override
                public void onResponse(Call<OutGoodsResult> call, Response<OutGoodsResult> response) {
                    if (response.body().getStatus() == 200) {
                        Toast.makeText(GoodsDetailsActivity.this,"完成", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(GoodsDetailsActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<OutGoodsResult> call, Throwable t) {
                    Toast.makeText(GoodsDetailsActivity.this, "网络异常:完成明细失败", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            Toast.makeText(this, "请扫描核对货物信息", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void click(int postion) {
        Intent ykIntent = new Intent(this, CaptureActivity.class);
        startActivityForResult(ykIntent, CK);
        this.position = postion;
    }
}
