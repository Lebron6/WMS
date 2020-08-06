package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ocean.wms.R;
import com.ocean.wms.api.HttpUtil;
import com.ocean.wms.entity.StorageAreaResult;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.TitleBarManger;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/1/17.
 * 库存汇总
 */
public class InventorySummaryActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.et_hwbh)
    EditText etHwbh;
    @BindView(R.id.et_goods)
    EditText etGoods;
    @BindView(R.id.et_hz)
    EditText etHz;
    @BindView(R.id.tv_search)
    TextView tvSearch;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, InventorySummaryActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger=TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setBack();
        manger.setTitle("库存汇总");
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_inventory_summary;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.tv_search)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etHwbh.getText())&&TextUtils.isEmpty(etGoods.getText())&&TextUtils.isEmpty(etHz.getText())){
            Toast.makeText(this,"请至少输入一种搜索条件",Toast.LENGTH_SHORT).show();
            return;
        }
InventorySummaryListActivity.actionStart(this,etHwbh.getText().toString()+"",etGoods.getText().toString()+"",etHz.getText().toString()+"");
    }
}
