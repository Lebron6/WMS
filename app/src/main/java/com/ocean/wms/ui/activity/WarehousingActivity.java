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
import com.ocean.wms.entity.Distribution;
import com.ocean.wms.entity.GetChuResult;
import com.ocean.wms.entity.UpShelfaResult;
import com.ocean.wms.entity.WareScanResult;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.TitleBarManger;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.xudaojie.qrcodelib.CaptureActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/1/6.
 * 入库
 */
public class WarehousingActivity extends BaseActivity {
    @BindView(R.id.tv_ysdh)
    TextView tvYsdh;
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
    @BindView(R.id.layout_top)
    LinearLayout layoutTop;
    @BindView(R.id.layout_center)
    LinearLayout layoutCenter;
    @BindView(R.id.et_cwm)
    EditText etCwm;
    @BindView(R.id.iv_tips)
    ImageView ivTips;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.et_sl)
    EditText etSl;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    private int status = 0;//当前状态  0：待自动分配  1： 已分配
    private int isVeri = 0;//是否扫码验证  0：未验证  1： 已验证  2： 验证失败

    public static String WAREDATA = "WAREDATA";
    private WareScanResult result;
    private String warearea;

    public static void actionStart(Context context, String datas) {
        Intent intent = new Intent();
        intent.putExtra(WAREDATA, datas);
        intent.setClass(context, WarehousingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("入库");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_ware_housing;
    }

    @Override
    protected void initViews() {
        layoutCenter.setVisibility(View.GONE);
        ivTips.setBackgroundResource(R.mipmap.icon_error);
    }

    @Override
    protected void initDatas() {
        String extra = getIntent().getStringExtra(WAREDATA);
        try {
            result = new Gson().fromJson(extra, WareScanResult.class);
            tvYsdh.setText("原始单号：" + result.getInvoice());
            tvHzbh.setText("货主编号：" + result.getCorpcode());
            tvHzmc.setVisibility(View.GONE);
            tvHwbm.setText("货物编码：" + result.getGoodscoding());
            tvHwmc.setVisibility(View.GONE);
            tvSl.setText("数量：" + result.getKk());
            tvPc.setText("批次：" + result.getCbatch());
        }catch (Exception e){
            Toast.makeText(WarehousingActivity.this,"扫码异常:请扫描正确二维码或条形码",Toast.LENGTH_SHORT).show();
        }

    }

    private static final int VERI = 1;//验证

    @OnClick({R.id.iv_scan, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                if (TextUtils.isEmpty(etCwm.getText().toString())) {
                    Toast.makeText(WarehousingActivity.this, "请分配储位后扫码验证", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent veriIntent = new Intent(this, CaptureActivity.class);
                startActivityForResult(veriIntent, VERI);
                break;
            case R.id.tv_commit:
                if (status == 0) {//分配
                    HttpUtil.createRequest(this).distribution(PreferenceUtils.getInstance().getUserToken(), result.getCgID(), result.getKk(), result.getSupplierID()).enqueue(new Callback<Distribution>() {
                        @Override
                        public void onResponse(Call<Distribution> call, Response<Distribution> response) {
                            if (response.body().getStatus() == 200) {
                                status = 1;//状态改为待提交
                                layoutCenter.setVisibility(View.VISIBLE);
                                etCwm.setText(response.body().getData().getWarearea());
                                warearea = response.body().getData().getWarearea();
                                etSl.setText(response.body().getData().getWareareanum() + "");
                                tvCommit.setText("提交");
                            } else {
                                Toast.makeText(WarehousingActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Distribution> call, Throwable t) {
                            Toast.makeText(WarehousingActivity.this, "自动分配储位:网络异常", Toast.LENGTH_SHORT).show();
                        }
                });
                } else {//提交
                    if (isVeri == 0) {
                        Toast.makeText(WarehousingActivity.this, "请先扫码确认储位", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (isVeri == 2) {//验证失败
                        Toast.makeText(WarehousingActivity.this, "自动分配储位与扫码储位不一致", Toast.LENGTH_SHORT).show();
                        return;
                    } else {//上架
                        if (TextUtils.isEmpty(etSl.getText().toString())) {
                            Toast.makeText(WarehousingActivity.this, "请输入数量", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        HttpUtil.createRequest(this).upShelfa(PreferenceUtils.getInstance().getUserToken(), result.getGoodscoding(), result.getInvoice(), warearea, etSl.getText().toString(), result.getHU()).enqueue(new Callback<UpShelfaResult>() {
                            @Override
                            public void onResponse(Call<UpShelfaResult> call, Response<UpShelfaResult> response) {
                                if (response.body().getStatus() == 200) {
                                    CommitSucActivity.actionStart(WarehousingActivity.this, "入库");
                                    finish();
                                } else {
                                    Toast.makeText(WarehousingActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<UpShelfaResult> call, Throwable t) {
                                Toast.makeText(WarehousingActivity.this, "上架:网络异常", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
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
                String result = bundle.getString("result");
                if (result.equals(etCwm.getText().toString())) {
                    HttpUtil.createRequest(this).getChu(PreferenceUtils.getInstance().getUserToken(), warearea, result).enqueue(new Callback<GetChuResult>() {
                        @Override
                        public void onResponse(Call<GetChuResult> call, Response<GetChuResult> response) {
                            if (response.body().getStatus() == 200) {
                                isVeri = 1;
                                ivTips.setBackgroundResource(R.mipmap.icon_ture);
                            } else {
                                isVeri = 2;
                                Toast.makeText(WarehousingActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<GetChuResult> call, Throwable t) {
                            isVeri = 0;
                            Toast.makeText(WarehousingActivity.this, "验证上架与分配储位:网络异常", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    isVeri = 2;
                    ivTips.setBackgroundResource(R.mipmap.icon_error);
                    Toast.makeText(WarehousingActivity.this, "自动分配储位与扫码储位不一致", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
