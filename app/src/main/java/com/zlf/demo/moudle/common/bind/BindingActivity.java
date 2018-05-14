package com.zlf.demo.moudle.common.bind;

import android.os.Bundle;

import com.zlf.demo.R;
import com.zlf.demo.base.BaseActivity;

public class BindingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolBar().setTitle("Binding test");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_binding;
    }
}
