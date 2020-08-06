package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ocean.wms.R;
import com.ocean.wms.api.HttpUtil;
import com.ocean.wms.callback.OnMoveSelectedListener;
import com.ocean.wms.entity.FindScanResult;
import com.ocean.wms.entity.MoveResult;
import com.ocean.wms.entity.MoveScanResult;
import com.ocean.wms.entity.WareScanResult;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.TitleBarManger;
import com.ocean.wms.view.MovePop;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.xudaojie.qrcodelib.CaptureActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/1/6.
 * 移库
 */
public class MoveLibraryActivity extends BaseActivity {
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
    @BindView(R.id.et_mbcw)
    EditText etMbcw;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.et_sl)
    EditText etSl;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.tv_ykyy)
    TextView tv_ykyy;
    private List<String> types;
    private ArrayAdapter adapter;

    public static String MOVEDATA = "MOVEDATA";
    private MoveScanResult result;
    private int type=1;//1:储位转移 2:拣货区补货
    private String warearea="";
    private String waId="";//储位编号
    private String wId="";//储位ID

    public static void actionStart(Context context,String datas){
        Intent intent=new Intent();
        intent.putExtra(MOVEDATA, datas);
        intent.setClass(context,MoveLibraryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("移库");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_move_library;
    }

    @Override
    protected void initViews() {
        types=new ArrayList<>();
        types.add("储位转移");
        types.add("拣货区补货");
        adapter = new ArrayAdapter(this, R.layout.item_move, R.id.tv_popqusetion, types);
    }

    @Override
    protected void initDatas() {
        String extra = getIntent().getStringExtra(MOVEDATA);
        result = new Gson().fromJson(extra, MoveScanResult.class);
        tvHzbh.setText("货主编号：" + result.getCorpcode());
        tvHzmc.setVisibility(View.GONE);
        tvHwbm.setText("货物编码：" + result.getGoodscoding());
        tvHwmc.setVisibility(View.GONE);
        tvSl.setText("数量：" + result.getKk());
        tvPc.setText("批次：" + result.getCbatch());
        HttpUtil.createRequest(this).findScan(PreferenceUtils.getInstance().getUserToken(),result.getKk(),result.getHU()).enqueue(new Callback<FindScanResult>() {
            @Override
            public void onResponse(Call<FindScanResult> call, Response<FindScanResult> response) {
                if (response.body().getStatus() == 200) {
                    warearea=response.body().getData().getWarearea();
                    waId=response.body().getData().getWaID()+"";
                    wId=response.body().getData().getId()+"";
                    tvCw.setText("储位："+warearea);
                } else {
                    Toast.makeText(MoveLibraryActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<FindScanResult> call, Throwable t) {
                Toast.makeText(MoveLibraryActivity.this,"网络异常:获得储位信息失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static final int TOWEARAREA = 1;//目标储位
    @OnClick({R.id.iv_scan, R.id.tv_commit,R.id.tv_ykyy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                Intent veriIntent = new Intent(this, CaptureActivity.class);
                startActivityForResult(veriIntent, TOWEARAREA);
                break;
            case R.id.tv_commit:
                if (TextUtils.isEmpty(etMbcw.getText().toString())){
                    Toast.makeText(MoveLibraryActivity.this,"请扫描目标储位码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(etSl.getText().toString())){
                    Toast.makeText(MoveLibraryActivity.this,"请输入数量",Toast.LENGTH_SHORT).show();
                    return;
                }
                HttpUtil.createRequest(this).moveLib(PreferenceUtils.getInstance().getUserToken(),
                        type+"",warearea,etMbcw.getText().toString(),result.getKk(),etSl.getText().toString(),
                        result.getCgID(),result.getSupplierID(),result.getCorpcode(),waId,result.getGoodscoding(),
                        result.getCbatch(),result.getHU(),wId).enqueue(new Callback<MoveResult>() {
                    @Override
                    public void onResponse(Call<MoveResult> call, Response<MoveResult> response) {
                        if (response.body().getStatus() == 200) {
                            CommitSucActivity.actionStart(MoveLibraryActivity.this,"移库");
                            finish();
                        } else {
                            Toast.makeText(MoveLibraryActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MoveResult> call, Throwable t) {
                        Toast.makeText(MoveLibraryActivity.this,"网络异常:移库失败",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.tv_ykyy:
                MovePop pop = new MovePop(this);
                pop.showView(tv_ykyy, adapter, listener);
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
            if (requestCode == TOWEARAREA) {
                Log.e("扫码结果",bundle.getString("result"));
                String result = bundle.getString("result");
                etMbcw.setText(result);
            }
        }
    }
    OnMoveSelectedListener listener = new OnMoveSelectedListener() {
        @Override
        public void select(int postion) {
            type=postion+1;
            tv_ykyy.setText(types.get(postion));
        }
    };
}
