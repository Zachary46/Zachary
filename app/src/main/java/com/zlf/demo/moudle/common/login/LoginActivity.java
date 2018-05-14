package com.zlf.demo.moudle.common.login;

import android.os.Bundle;
import com.zlf.demo.R;
import com.zlf.demo.base.BaseActivity;

public class LoginActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolBar().setTitle("Zachary Demo");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

}
