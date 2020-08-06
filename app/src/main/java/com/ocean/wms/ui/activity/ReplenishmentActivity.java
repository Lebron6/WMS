package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ocean.wms.R;
import com.ocean.wms.api.HttpUtil;
import com.ocean.wms.entity.BuDanResult;
import com.ocean.wms.entity.Buone;
import com.ocean.wms.entity.GetChuResult;
import com.ocean.wms.entity.WareScanResult;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.TitleBarManger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.xudaojie.qrcodelib.CaptureActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/1/6.
 * 补货
 */
public class ReplenishmentActivity extends BaseActivity {
    private static final String ID = "ID";
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.iv_tips_top)
    ImageView ivTipsTop;
    @BindView(R.id.iv_scan_top)
    ImageView ivScanTop;
    @BindView(R.id.tv_bkdh)
    TextView tvBkdh;
    @BindView(R.id.tv_hzbh)
    TextView tvHzbh;
    @BindView(R.id.tv_hzmc)
    TextView tvHzmc;
    @BindView(R.id.tv_hwbm)
    TextView tvHwbm;
    @BindView(R.id.tv_hwmc)
    TextView tvHwmc;
    @BindView(R.id.tv_sl)
    TextView tvSl;
    @BindView(R.id.tv_pc)
    TextView tvPc;
    @BindView(R.id.tv_cw)
    TextView tvCw;
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.txt_jhq)
    TextView txtJhq;
    @BindView(R.id.et_dmjhq)
    EditText etDmjhq;
    @BindView(R.id.iv_tips)
    ImageView ivTips;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    private WareScanResult result;
    private String wId;
    private int isVeri = 0;//是否扫码验证  0：未验证  1： 已验证
    public static void actionStart(Context context, String id) {
        Intent intent = new Intent();
        intent.putExtra(ID, id);
        intent.setClass(context, ReplenishmentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("补货");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_replenishment;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        String id = getIntent().getStringExtra(ID);
        HttpUtil.createRequest(this).getBuone(PreferenceUtils.getInstance().getUserToken(), id).enqueue(new Callback<Buone>() {
            @Override
            public void onResponse(Call<Buone> call, Response<Buone> response) {
                if (response.body().getStatus() == 200) {
                    tvBkdh.setText("补货单号：" + response.body().getData().get(0).getBucode());
                    tvHzbh.setText("货主编号：" + response.body().getData().get(0).getSuppliercode());
                    tvHzmc.setText("货主编号：" + response.body().getData().get(0).getSupplier());
                    tvHwbm.setText("货物编码：" + response.body().getData().get(0).getGoodscoding());
                    tvHwmc.setVisibility(View.GONE);
                    tvSl.setText("数量：" + response.body().getData().get(0).getMovenum());
                    tvPc.setText("批次：" + response.body().getData().get(0).getCbatch());
                    tvCw.setText("储位：" + response.body().getData().get(0).getWarearea());
                    etDmjhq.setText(response.body().getData().get(0).getTowarearea());
                    wId=response.body().getData().get(0).getWid();
                } else {
                    Toast.makeText(ReplenishmentActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Buone> call, Throwable t) {
                Toast.makeText(ReplenishmentActivity.this, "网络异常:获取补货详情失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private static final int VERI = 1;//验证
    private static final int VERI2 = 2;//判断储位是否和扫描储位相同
    @OnClick({R.id.iv_scan, R.id.tv_commit, R.id.iv_scan_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                Intent veri2Intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(veri2Intent, VERI2);
                break;
            case R.id.iv_scan_top:
                Intent veriIntent = new Intent(this, CaptureActivity.class);
                startActivityForResult(veriIntent, VERI);
                break;
            case R.id.tv_commit:
                if (TextUtils.isEmpty(etDmjhq.getText().toString())){
                    Toast.makeText(ReplenishmentActivity.this,"请先扫码验证",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isVeri==0){
                    Toast.makeText(ReplenishmentActivity.this,"验证储位失败，请重新扫描储位码",Toast.LENGTH_SHORT).show();
                    return;
                }
                HttpUtil.createRequest(this).buDone(PreferenceUtils.getInstance().getUserToken(),getIntent().getStringExtra(ID),result.getHU()).enqueue(new Callback<BuDanResult>() {
                    @Override
                    public void onResponse(Call<BuDanResult> call, Response<BuDanResult> response) {
                        if (response.body().getStatus() == 200) {
                            CommitSucActivity.actionStart(ReplenishmentActivity.this,"补货");
                            finish();
                        } else {
                            Toast.makeText(ReplenishmentActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BuDanResult> call, Throwable t) {
                        Toast.makeText(ReplenishmentActivity.this, "补货失败:网络异常", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
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
            if (requestCode == VERI) {
               String bundleString = bundle.getString("result");
                Log.e("验证补货原储位扫码结果",bundleString);
                result = new Gson().fromJson(bundleString, WareScanResult.class);
                    HttpUtil.createRequest(this).getBuDan(PreferenceUtils.getInstance().getUserToken(),wId, result.getHU()).enqueue(new Callback<BuDanResult>() {
                        @Override
                        public void onResponse(Call<BuDanResult> call, Response<BuDanResult> response) {
                            if (response.body().getStatus() == 200) {
                                ivTipsTop.setBackgroundResource(R.mipmap.icon_ture);
                            } else {
                                ivTipsTop.setBackgroundResource(R.mipmap.icon_error);
                                Toast.makeText(ReplenishmentActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<BuDanResult> call, Throwable t) {
                            Toast.makeText(ReplenishmentActivity.this, "验证补货单原储位:网络异常", Toast.LENGTH_SHORT).show();
                        }
                    });
            }
            if (requestCode==VERI2){
                String result = bundle.getString("result");
                if (result.equals(etDmjhq.getText().toString())) {
                    HttpUtil.createRequest(this).getChu(PreferenceUtils.getInstance().getUserToken(), result, result).enqueue(new Callback<GetChuResult>() {
                        @Override
                        public void onResponse(Call<GetChuResult> call, Response<GetChuResult> response) {
                            if (response.body().getStatus() == 200) {
                                isVeri = 1;
                                ivTips.setBackgroundResource(R.mipmap.icon_ture);
                            } else {
                                isVeri = 0;
                                Toast.makeText(ReplenishmentActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<GetChuResult> call, Throwable t) {
                            isVeri = 0;
                            Toast.makeText(ReplenishmentActivity.this, "验证分配储位:网络异常", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    ivTips.setBackgroundResource(R.mipmap.icon_error);
                    Toast.makeText(ReplenishmentActivity.this, "自动分配储位与扫码储位不一致", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
