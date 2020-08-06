package com.ocean.wms.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.ocean.wms.tools.AppManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    /**
     * activity堆栈管理
     */
    protected AppManager appManager = AppManager.getAppManager();
    private InputMethodManager imm;
    private Unbinder unBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (attachLayoutRes() != 0) {
            setContentView(attachLayoutRes());
            unBinder = ButterKnife.bind(this);
        }
        initViews();
        initDatas();
        loggerSimpleName();
        appManager.addActivity(this);
    }


    public void loggerSimpleName() {
        Log.d("当前界面 ：", "-------------"+getClass().getSimpleName()+"------------");
    }




    @Override
    protected void onResume() {
        super.onResume();
        initTitle();
    }

    /**
     * 初始化视图控件
     */
    protected abstract void initTitle();


    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 获取数据
     */
    protected abstract void initDatas();

    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (attachLayoutRes() != 0) {
            unBinder.unbind();
        }
        this.imm = null;
        // 从栈中移除activity
        appManager.finishActivity(this);
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }
}
