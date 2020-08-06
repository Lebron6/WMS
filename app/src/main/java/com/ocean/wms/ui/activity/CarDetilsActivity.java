package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ocean.wms.R;
import com.ocean.wms.api.HttpUtil;
import com.ocean.wms.entity.CarBackResult;
import com.ocean.wms.entity.QueryCarResult;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.TitleBarManger;
import com.ocean.wms.tools.Util;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/4/28.
 * 车辆信息
 */
public class CarDetilsActivity extends BaseActivity {


    public static String RESULT = "result";
    public static String CARID = "carId";
    QueryCarResult result;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.layout_title)
    RelativeLayout layoutTitle;
    @BindView(R.id.tv_car_num)
    TextView tvCarNum;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_al_time)
    TextView tvAlTime;
    @BindView(R.id.layout_car_num)
    LinearLayout layoutCarNum;
    @BindView(R.id.tv_back)
    TextView tvBack;
    private Timer timer;

    public static void actionStart(Context context, QueryCarResult result, String carId) {
        Intent intent = new Intent();
        intent.putExtra(RESULT, result);
        intent.putExtra(CARID, carId + "");
        intent.setClass(context, CarDetilsActivity.class);
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
        return R.layout.activity_car_details;
    }

    @Override
    protected void initViews() {

    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                int second = (int) (Util.getTimeStamp() - (result.getData().getCar_list().getDuration()));
               tvAlTime.setText(FormatMiss(second));
            }
        }
    };
    public static String FormatMiss(int time){
        String hh=time/3600>9?time/3600+"":"0"+time/3600;
        String mm=(time% 3600)/60>9?(time% 3600)/60+"":"0"+(time% 3600)/60;
        String ss=(time% 3600) % 60>9?(time% 3600) % 60+"":"0"+(time% 3600) % 60;
        return hh+":"+mm+":"+ss;
    }
String location="";
    @Override
    protected void initDatas() {

        result = (QueryCarResult) getIntent().getSerializableExtra(RESULT);
        tvCarNum.setText(result.getData().getCar_num());
        for (int i = 0; i <result.getData().getCar_list().getCarinfo().size() ; i++) {
            location=location+result.getData().getCar_list().getCarinfo().get(i)+",";
        }
        tvLocation.setText(location);
        tvTime.setText(result.getData().getCar_list().getInputtime());
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // (1) 使用handler发送消息
                Message message=new Message();
                message.what=0;
                mHandler.sendMessage(message);
            }
        },0,1000);//每隔一秒使用handler发送一下消息,也就是每隔一秒执行一次,一直重复执行

    }

    @Override
    public void finish() {
        super.finish();
        timer.cancel();
    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        HttpUtil.createRequest(this).back(PreferenceUtils.getInstance().getUserToken(),getIntent().getStringExtra(CARID)).enqueue(new Callback<CarBackResult>() {
            @Override
            public void onResponse(Call<CarBackResult> call, Response<CarBackResult> response) {
                if (response.body().getStatus()==200){
                    Toast.makeText(CarDetilsActivity.this,"车辆返回成功",Toast.LENGTH_SHORT).show();
                    VehicleTransportActivity.actionStart(CarDetilsActivity.this);
                }else{
                    Toast.makeText(CarDetilsActivity.this,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CarBackResult> call, Throwable throwable) {
                Toast.makeText(CarDetilsActivity.this,"车辆返回异常",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
