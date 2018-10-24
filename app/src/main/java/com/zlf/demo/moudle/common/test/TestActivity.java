package com.zlf.demo.moudle.common.test;

import android.os.Bundle;

import com.zlf.demo.R;
import com.zlf.demo.base.BaseActivity;

import butterknife.BindView;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new TestFragment()).commit();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }
}
