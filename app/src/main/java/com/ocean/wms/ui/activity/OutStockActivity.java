package com.ocean.wms.ui.activity;

import com.ocean.wms.tools.TitleBarManger;

/**
 * Created by James on 2020/1/7.
 * 出库
 */
public class OutStockActivity extends BaseActivity{
    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("出库");
        manger.setBack();
    }

    @Override
    protected int attachLayoutRes() {
        return 0;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }
}
