package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ocean.wms.R;
import com.ocean.wms.tools.TitleBarManger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.xudaojie.qrcodelib.CaptureActivity;

/**
 * Created by James on 2020/1/6.
 */
public class CommitSucActivity extends BaseActivity {

    public static String TYPE = "TYPE";
    @BindView(R.id.tv_rk)
    TextView tvRk;

    public static void actionStart(Context context, String type) {
        Intent intent = new Intent();
        intent.putExtra(TYPE, type);
        intent.setClass(context, CommitSucActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("提交成功");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_commit_suc;
    }

    @Override
    protected void initViews() {
        switch (getIntent().getStringExtra(TYPE)) {
            case "入库":
                tvRk.setText("再次入库");
                break;
            case "移库":
                tvRk.setText("再次移库");
                break;
            case "补货":
                tvRk.setText("再次补货");
            case "出库":
                tvRk.setText("再次出库");
                break;
        }
    }

    @Override
    protected void initDatas() {

    }


    private static final int RK = 1;//入库
    private static final int YK = 2;//移库
    @OnClick({R.id.tv_rk, R.id.tv_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_rk:
                switch (getIntent().getStringExtra(TYPE)) {
                    case "入库":
                        Intent rkIntent = new Intent(this, CaptureActivity.class);
                        startActivityForResult(rkIntent, RK);
                        break;
                    case "移库":
                        Intent ykIntent = new Intent(this, CaptureActivity.class);
                        startActivityForResult(ykIntent, YK);
                        break;
                    case "补货":
                       ReplenishmentListActivity.actionStart(this);
                        break;
                    case "出库":
                        OutGoodsListActivity.actionStart(this);
                        break;
                }
                break;
            case R.id.tv_home:
                MainActivity.actionStart(this);
                finish();
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
            String result = bundle.getString("result");
            Log.e("当前扫描数据类型:"+requestCode,result);
            if (requestCode == RK) {

                WarehousingActivity.actionStart(this,result);
                finish();
            }
            if (requestCode == YK) {
               MoveLibraryActivity.actionStart(this,result);
               finish();
            }
        }
    }
}
