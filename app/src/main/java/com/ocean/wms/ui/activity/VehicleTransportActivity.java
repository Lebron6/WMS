package com.ocean.wms.ui.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ocean.wms.R;
import com.ocean.wms.api.HttpUtil;
import com.ocean.wms.entity.CarScanResult;
import com.ocean.wms.entity.QueryCarResult;
import com.ocean.wms.entity.SearchCarInfo;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.TitleBarManger;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.xudaojie.qrcodelib.CaptureActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/4/28.
 * 车辆运输管理看板
 */
public class VehicleTransportActivity extends BaseActivity {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.iv_scan)
    ImageView ivScan;
    @BindView(R.id.et_cph)
    EditText etCph;
    @BindView(R.id.et_sj)
    EditText etSj;
    @BindView(R.id.txt_sj)
    TextView txtSj;
    @BindView(R.id.et_start_time)
    TextView etStartTime;
    @BindView(R.id.et_end_time)
    TextView etEndTime;
    @BindView(R.id.layout_o)
    LinearLayout layoutO;
    @BindView(R.id.tv_search)
    TextView tvSearch;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, VehicleTransportActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setBack();
        manger.setTitle("车辆运输管理看板");
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_vehicle_transport;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    private static final int BOARD = 1;//入库
    private int startYear, startMonth, startDay;
    private int endYear, endMonth, endDay;
    private String startTime = "";
    private String endTime = "";

    @OnClick({R.id.iv_scan, R.id.tv_search, R.id.et_start_time, R.id.et_end_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_scan:
                Intent rkIntent = new Intent(this, CaptureActivity.class);
                startActivityForResult(rkIntent, BOARD);
                break;
            case R.id.tv_search:
                HttpUtil.createRequest(this).searchCar(PreferenceUtils.getInstance().getUserToken(), etCph.getText().toString(), etSj.getText().toString(), startTime, endTime).enqueue(new Callback<SearchCarInfo>() {
                    @Override
                    public void onResponse(Call<SearchCarInfo> call, Response<SearchCarInfo> response) {
                        if (response.body().getStatus() == 200) {
                            CarMotionActivity.actionStart(VehicleTransportActivity.this, response.body());
                        } else {
                            Toast.makeText(VehicleTransportActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchCarInfo> call, Throwable throwable) {
                        Toast.makeText(VehicleTransportActivity.this, "暂无搜索结果", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.et_start_time:
                final Calendar ca = Calendar.getInstance();
                startYear = ca.get(Calendar.YEAR);
                startMonth = ca.get(Calendar.MONTH);
                startDay = ca.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(VehicleTransportActivity.this,R.style.MyDatePickerDialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                startTime = (year + "-" + (monthOfYear + 1)
                                        + "-" + dayOfMonth);
                                etStartTime.setText(startTime);
                            }
                        }, startYear, startMonth, startDay).show();
                break;
            case R.id.et_end_time:
                final Calendar cae = Calendar.getInstance();
                endYear = cae.get(Calendar.YEAR);
                endMonth = cae.get(Calendar.MONTH);
                endDay = cae.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(VehicleTransportActivity.this,R.style.MyDatePickerDialogTheme,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                endTime = (year + "-" + (monthOfYear + 1)
                                        + "-" + dayOfMonth);
                                etEndTime.setText(endTime);
                            }
                        }, endYear, endMonth, endDay).show();
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
            if (requestCode == BOARD) {
                final String result = bundle.getString("result");
                Log.e("扫描结果", result);
                try {
                    final CarScanResult carScanResult = new Gson().fromJson(result, CarScanResult.class);
                    HttpUtil.createRequest(this).carNum(PreferenceUtils.getInstance().getUserToken(), carScanResult.getCarID()).enqueue(new Callback<QueryCarResult>() {
                        @Override
                        public void onResponse(Call<QueryCarResult> call, Response<QueryCarResult> response) {
                            if (response.body().getData().getCar_type() == 0) {//未发车
                                CarInfoActivity.actionStart(VehicleTransportActivity.this, response.body(), carScanResult.getCarID() + "");
                            } else {//已发车
                                CarDetilsActivity.actionStart(VehicleTransportActivity.this, response.body(), carScanResult.getCarID() + "");
                            }
                        }

                        @Override
                        public void onFailure(Call<QueryCarResult> call, Throwable throwable) {
                            Log.e("onFailyre", throwable.getMessage() + "");
                            Toast.makeText(VehicleTransportActivity.this, "网络异常:扫码显示状态失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(this, "扫码异常:请扫描正确二维码或条形码", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
