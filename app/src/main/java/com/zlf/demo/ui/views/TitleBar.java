package com.zlf.demo.ui.views;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zlf.demo.R;
import com.zlf.demo.ui.views.navigationview.NavigationView;


public class TitleBar {

    private AppCompatActivity mActivity;
    private Toolbar mRoot;
    private LinearLayout llCustom;
    private ActionBar mActionBar;
    private NavigationView rootView;

    public TitleBar(final AppCompatActivity activity, NavigationView v) {
        mActivity = activity;
        mRoot = (Toolbar) v.findViewById(R.id.toolBar);
        rootView = v;
        mActivity.setSupportActionBar(mRoot);
        mActionBar = mActivity.getSupportActionBar();
        llCustom = (LinearLayout) mRoot.findViewById(R.id.llCustom);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mRoot.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        mActionBar.setDisplayShowTitleEnabled(false);
    }

    public TitleBar hideTitleBar() {
        rootView.setVisibility(View.VISIBLE);
        mActionBar.hide();
        return this;
    }

    public TitleBar hideRoot() {
        rootView.setVisibility(View.GONE);
        mActionBar.hide();
        return this;
    }

    public TitleBar showRoot() {
        rootView.setVisibility(View.VISIBLE);
        mActionBar.show();
        return this;
    }

    /**
     * ToolBar 的左右边距
     * 单位：px
     *
     * @param start
     * @param end
     * @return
     */
    public TitleBar setContentInsetsRelative(int start, int end) {
        mRoot.setContentInsetsRelative(start, end);
        return this;
    }

    /**
     * 单位： px
     *
     * @param left
     * @param right
     * @return
     */
    public TitleBar setContentInsetsAbsolute(int left, int right) {
        mRoot.setContentInsetsAbsolute(left, right);
        return this;
    }

    public int getContentInsetLeft() {
        return mRoot.getContentInsetLeft();
    }

    public TitleBar setTitleMarginLeft(int margin) {
        mRoot.setTitleMarginStart(mRoot.getTitleMarginStart() + margin);
        return this;
    }

    public TitleBar showTitleBar() {
        mActionBar.show();
        rootView.setVisibility(View.VISIBLE);
        return this;
    }

    public TitleBar setBackgroundColor(int color) {
        mRoot.setBackgroundColor(color);
        return this;
    }


    /**
     * 设置标题
     *
     * @param resId 资源ID
     * @return
     */
    public TitleBar setTitle(int resId) {
        mRoot.setTitle(resId);
        return this;
    }

    public TitleBar setLogo(int resId) {
        if (resId == -1) {
            mActionBar.setDisplayUseLogoEnabled(false);

        } else {
            mActionBar.setDisplayUseLogoEnabled(true);
            mActionBar.setLogo(resId);
        }

        return this;
    }

    public TitleBar setHomeButtonEnabled(boolean enabled) {
        mActionBar.setHomeButtonEnabled(enabled);
        return this;
    }

    public TitleBar setNavigationIcon(int resId) {
        if (resId == -1) {
        } else {
            mRoot.setNavigationIcon(resId);

        }

        return this;
    }

    public TitleBar setOnClickListener(View.OnClickListener clickListener) {

        mRoot.setOnClickListener(clickListener);

        return this;
    }

    public TitleBar setNavigationOnClickListener(View.OnClickListener mClickListener) {
        mRoot.setNavigationOnClickListener(mClickListener);
        return this;
    }

    public TitleBar setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            TextView view = new TextView(mActivity);
            view.setText(title);
            view.setTextSize(15);
            view.setTextColor(ContextCompat.getColor(mActivity, R.color.md_white));
            return addCustom(view);
        }
        return this;
    }

    public TitleBar setLeftTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            hideCustom();
            mRoot.setTitle(title);
        } else {
            mRoot.setTitle("");
        }
        return this;
    }

    public TitleBar setSubTitle(int resId) {

        if (resId == -1) {
            mActionBar.setSubtitle("");
        } else {
            mActionBar.setSubtitle(resId);
        }
        return this;
    }

    public TitleBar addCustom(View v) {
        llCustom.removeAllViews();
        llCustom.addView(v);
        llCustom.setVisibility(View.VISIBLE);
        return this;
    }

    public TitleBar hideCustom() {
        llCustom.setVisibility(View.GONE);
        return this;
    }

    public TitleBar setDisplayHomeAsUpEnabled(boolean show) {
        mActionBar.setDisplayHomeAsUpEnabled(show);
        return this;
    }

    public TitleBar setMenuTitle(int menuID, int resID) {
        mRoot.getMenu().findItem(menuID).setTitle(resID);
        return this;
    }


}
