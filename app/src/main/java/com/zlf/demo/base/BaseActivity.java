package com.zlf.demo.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import com.zlf.demo.R;
import com.zlf.demo.ui.views.TitleBar;

import qiu.niorgai.StatusBarCompat;

/**
 * @Author: Zachary
 * @Date: 2018/5/11/011
 * @Description: 简单基类(子类含有Fragment)
 */
public abstract class BaseActivity extends AppCompatActivity{
    protected View rootView;
    private TitleBar mTitleBar;
    private android.support.v7.widget.Toolbar toolbar;
    private RelativeLayout rlContent;

    public TitleBar getToolBar() {
        if (null == mTitleBar) {
            mTitleBar = new TitleBar(this, toolbar);
        }
        return mTitleBar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_base_toolbar);
        toolbar = findViewById(R.id.toolBar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            StatusBarCompat.translucentStatusBar(this);
            toolbar.setPadding(toolbar.getPaddingLeft(), getStatusBarHeight(), toolbar.getPaddingRight(), toolbar.getPaddingBottom());
        }

        rlContent = (RelativeLayout) findViewById(R.id.rlContent);
        rootView = getLayoutInflater().inflate(getLayoutId(), rlContent, false);
        rlContent.addView(rootView);
    }

    public abstract int getLayoutId();

    private int getStatusBarHeight() {
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            return getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}


