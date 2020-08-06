package com.ocean.wms.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ocean.wms.R;
import com.ocean.wms.api.HttpUtil;
import com.ocean.wms.entity.UserInfo;
import com.ocean.wms.tools.PreferenceUtils;
import com.ocean.wms.tools.TitleBarManger;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by James on 2020/1/6.
 */
public class UserLoginActivity extends BaseActivity {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_log)
    TextView tvLog;

    public static void actionStart(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, UserLoginActivity.class);
        context.startActivity(intent);
    }
    
    @Override
    protected void initTitle() {
        TitleBarManger manger = TitleBarManger.getInsetance();
        manger.setContext(this);
        manger.setTitle("登录");
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_user_login;
    }

    @Override
    protected void initViews() {
        if (!TextUtils.isEmpty(PreferenceUtils.getInstance().getUserToken())) {
            MainActivity.actionStart(UserLoginActivity.this);
            finish();
        }
    }

    @Override
    protected void initDatas() {

    }

    @OnClick(R.id.tv_login)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etAccount.getText().toString())) {
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(etPwd.getText().toString())) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpUtil.createRequest(this).userLogin(etAccount.getText().toString(), etPwd.getText().toString()).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.body().getStatus() == 200) {
                    PreferenceUtils.getInstance().setUserToken(response.body().getUser());
                    PreferenceUtils.getInstance().setLoginStatus(true);
                    MainActivity.actionStart(UserLoginActivity.this);
                    finish();
                } else {
                    Toast.makeText(UserLoginActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Log.e("异常打印", t.toString());
                Toast.makeText(UserLoginActivity.this, "登录:网络异常" + t.toString(), Toast.LENGTH_SHORT).show();
                tvLog.setText(t.toString());
            }
        });
    }
}
