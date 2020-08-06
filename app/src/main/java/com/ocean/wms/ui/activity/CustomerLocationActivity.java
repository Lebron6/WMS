package com.ocean.wms.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ocean.wms.R;
import com.ocean.wms.entity.QueryCarResult;
import com.ocean.wms.tools.RecyclerViewHelper;
import com.ocean.wms.tools.TitleBarManger;
import com.ocean.wms.ui.adapter.ChoseLocationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by James on 2020/4/28.
 */
public class CustomerLocationActivity extends BaseActivity {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.rv_location)
    RecyclerView rvLocation;

    public static final int PARMS = 001;
    public static final String QUERYCARRESULT = "QueryCarResult";
    public static final String CALLBACK = "callBack";
    private QueryCarResult result;

    public static void actionStartForResult(Activity context, String result) {
        Intent intent = new Intent();
        intent.putExtra(QUERYCARRESULT, result);
        intent.setClass(context, CustomerLocationActivity.class);
        context.startActivityForResult(intent, PARMS);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setBack();
        manger.setTitle("客户目的地");
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_customer_location;
    }

    @Override
    protected void initViews() {
        result = new Gson().fromJson(getIntent().getStringExtra(QUERYCARRESULT), QueryCarResult.class);
        ChoseLocationAdapter adapter = new ChoseLocationAdapter(this);
        adapter.setDatas(result);
        RecyclerViewHelper.initRecyclerViewV(this, rvLocation, false, adapter, true);
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.tv_over)
    public void onViewClicked() {
        Intent intent=new Intent();

        intent.putExtra(CALLBACK,new Gson().toJson(result));
        setResult(3,intent);
//        intent.putExtra(CALLBACK,"111");
        finish();
    }
}
