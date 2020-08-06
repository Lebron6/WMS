package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ocean.wms.R;
import com.ocean.wms.entity.SearchCarInfo;
import com.ocean.wms.tools.RecyclerViewHelper;
import com.ocean.wms.tools.TitleBarManger;
import com.ocean.wms.ui.adapter.CarMotionAdapter;
import com.ocean.wms.ui.adapter.LocationAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by James on 2020/4/28.
 * 车运信息
 */
public class CarMotionActivity extends BaseActivity {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.layout_title)
    RelativeLayout layoutTitle;
    @BindView(R.id.tv_car_num)
    TextView tvCarNum;
    @BindView(R.id.layout_car_num)
    LinearLayout layoutCarNum;
    @BindView(R.id.layout_tab)
    LinearLayout layoutTab;
    @BindView(R.id.rv_tab)
    RecyclerView rvTab;
    private CarMotionAdapter adapter;
    public static final String SEARCHCARINFO="searchCarInfo";
    private SearchCarInfo searchCarInfo;

    public static void actionStart(Context context, SearchCarInfo searchCarInfo) {
        Intent intent = new Intent();
        intent.setClass(context, CarMotionActivity.class);
        intent.putExtra(SEARCHCARINFO,searchCarInfo);
        context.startActivity(intent);
    }

    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setBack();
        manger.setTitle("车运信息");
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_car_motion;
    }

    @Override
    protected void initViews() {
        adapter = new CarMotionAdapter(this);
        RecyclerViewHelper.initRecyclerViewV(this, rvTab, false, adapter, true);
    }

    @Override
    protected void initDatas() {
        searchCarInfo= (SearchCarInfo) getIntent().getSerializableExtra(SEARCHCARINFO);
        tvCarNum.setText(searchCarInfo.getData().getSum());
        adapter = new CarMotionAdapter(this);
        adapter.setDatas(searchCarInfo);
        RecyclerViewHelper.initRecyclerViewV(this, rvTab, false, adapter, true);
    }
}
