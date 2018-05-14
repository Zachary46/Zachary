package com.zlf.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import com.zlf.demo.R;
import com.zlf.demo.ui.views.TitleBar;
import com.zlf.demo.ui.views.navigationview.NavigationView;

/**
 * @Author: Zachary
 * @Date: 2018/5/11/011
 * @Description: 简单基类(子类含有Fragment)
 */
public abstract class BaseActivity extends AppCompatActivity{
    protected View rootView;
    private TitleBar mTitleBar;
    private RelativeLayout rlContent;
    private NavigationView mNavigationView;

    public TitleBar getToolBar() {
        if (null == mTitleBar) {
            mTitleBar = new TitleBar(this, mNavigationView);
        }
        return mTitleBar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_base_toolbar);

        rlContent = (RelativeLayout) findViewById(R.id.rlContent);
        mNavigationView = (NavigationView) findViewById(R.id.na);
        rootView = getLayoutInflater().inflate(getLayoutId(), rlContent, false);
        rlContent.addView(rootView);
    }

    public abstract int getLayoutId();
}
