package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ocean.wms.R;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.TitleBarManger;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import io.github.xudaojie.qrcodelib.CaptureActivity;

public class MainActivity extends BaseActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("首页");
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.layout_search, R.id.layout_rk, R.id.layout_yk, R.id.layout_bh, R.id.layout_ck, R.id.layout_kchz, R.id.layout_car_board, R.id.layout_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_rk:
                Intent rkIntent = new Intent(this, CaptureActivity.class);
                startActivityForResult(rkIntent, RK);
                break;
            case R.id.layout_yk:
                Intent ykIntent = new Intent(this, CaptureActivity.class);
                startActivityForResult(ykIntent, YK);
                break;
            case R.id.layout_search:
                Intent shIntent = new Intent(this, CaptureActivity.class);
                startActivityForResult(shIntent, SH);
                break;
            case R.id.layout_bh:
                ReplenishmentListActivity.actionStart(this);
                break;
            case R.id.layout_ck:
                OutGoodsListActivity.actionStart(this);
                break;
            case R.id.layout_kchz:
                InventorySummaryActivity.actionStart(this);
                break;
            case R.id.layout_car_board:
                VehicleTransportActivity.actionStart(this);
                break;
            case R.id.layout_exit:
                PreferenceUtils.getInstance().loginOut();
                UserLoginActivity.actionStart(this);
                finish();
                break;

        }
    }

    private static final int RK = 1;//入库
    private static final int YK = 2;//移库
    private static final int SH = 3;//查询

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
            if (requestCode == RK) {
                String result = bundle.getString("result");
                WarehousingActivity.actionStart(this, result);
            }
            if (requestCode == YK) {
                String result = bundle.getString("result");
                Log.e("移库扫描结果", result);
                MoveLibraryActivity.actionStart(this, result);
            }
            if (requestCode == SH) {
                String result = bundle.getString("result");
                Log.e("查询扫描结果", result);
                SearchResultActivity.actionStart(this, result);
            }
        }
    }
}
