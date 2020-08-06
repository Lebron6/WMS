package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ocean.wms.R;
import com.ocean.wms.api.HttpUtil;
import com.ocean.wms.entity.CarScanResult;
import com.ocean.wms.entity.QueryCarResult;
import com.ocean.wms.entity.SendCarResult;
import com.ocean.wms.entity.WareScanResult;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.RecyclerViewHelper;
import com.ocean.wms.tools.TitleBarManger;
import com.ocean.wms.ui.adapter.LocationAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ocean.wms.ui.activity.CustomerLocationActivity.CALLBACK;
import static com.ocean.wms.ui.activity.CustomerLocationActivity.PARMS;

/**
 * Created by James on 2020/4/28.
 */
public class CarInfoActivity extends BaseActivity {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_car_num)
    EditText etCarNum;
    @BindView(R.id.layout_car_num)
    LinearLayout layoutCarNum;
    @BindView(R.id.txt_khmd)
    TextView txtKhmd;
    @BindView(R.id.iv_drop)
    ImageView ivDrop;
    @BindView(R.id.layout_location)
    RelativeLayout layoutLocation;
    @BindView(R.id.rv_location)
    RecyclerView rvLocation;
    @BindView(R.id.tv_seng_car)
    TextView tvSengCar;
    private LocationAdapter adapter;
    public static String RESULT = "result";
    QueryCarResult result;
    private List<String> clinet;
    public static String CARID = "carId";

    public static void actionStart(Context context, QueryCarResult result, String carId) {
        Intent intent = new Intent();
        intent.putExtra(RESULT, result);
        intent.putExtra(CARID, carId + "");
        intent.setClass(context, CarInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setBack();
        manger.setTitle("车辆信息");
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_carinfo;
    }

    @Override
    protected void initViews() {
        adapter = new LocationAdapter(this);

    }

    @Override
    protected void initDatas() {
        result = (QueryCarResult) getIntent().getSerializableExtra(RESULT);
        etCarNum.setText(result.getData().getCar_num() + "");
//        adapter.setDatas(result);

        clinet = new ArrayList<>();
    }

    @OnClick({R.id.layout_location, R.id.tv_seng_car})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_location:
                String json = new Gson().toJson(result);
                CustomerLocationActivity.actionStartForResult(this, json);
                break;
            case R.id.tv_seng_car:
                ;
                HttpUtil.createRequest(this).faCar(PreferenceUtils.getInstance().getUserToken(), getIntent().getStringExtra(CARID), clinet.toString().substring(1, clinet.toString().length() - 1)).enqueue(new Callback<SendCarResult>() {
                    @Override
                    public void onResponse(Call<SendCarResult> call, Response<SendCarResult> response) {
                        if (response != null) {
                            if (response.body().getStatus() == 200) {
                                Toast.makeText(CarInfoActivity.this, "发车成功", Toast.LENGTH_SHORT).show();
                                VehicleTransportActivity.actionStart(CarInfoActivity.this);
                            } else {
                                Toast.makeText(CarInfoActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(CarInfoActivity.this, "请求返回结果为空", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SendCarResult> call, Throwable t) {
                        Toast.makeText(CarInfoActivity.this, "网络异常:出车失败", Toast.LENGTH_SHORT).show();
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
            if (requestCode == PARMS) {
                final String result = bundle.getString(CALLBACK);
                QueryCarResult queryCarResult = new Gson().fromJson(result, QueryCarResult.class);
                List<String> location = new ArrayList<>();
                for (int i = 0; i < queryCarResult.getData().getClient_list().size(); i++) {
                    if (queryCarResult.getData().getClient_list().get(i).getTag() == 1) {
                        clinet.add(queryCarResult.getData().getClient_list().get(i).getClientID());
                        location.add(queryCarResult.getData().getClient_list().get(i).getCorp());
                    }
                }
                adapter.setDatas(location);
                RecyclerViewHelper.initRecyclerViewV(this, rvLocation, false, adapter, true);
            }
        }
    }
}
