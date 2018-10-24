package com.zlf.demo.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @Author: Zachary
 * @Date: 2018/5/11/011
 * @Description:
 */
public abstract class BaseFragment extends Fragment {
    protected View mRoot;
    protected LayoutInflater mInflater;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        /*if (mRoot != null) {
            ViewGroup parent = (ViewGroup) container.getParent();
            if (parent != null) {
                parent.removeView(mRoot);
            }
        } else {
            mInflater = inflater;*/
            mRoot = inflater.inflate(getLayoutId(), container, false);
            unbinder = ButterKnife.bind(this, mRoot);
            initView(mRoot);
            loadData();
        //}

        return mRoot;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected void loadData(){

    }

    public abstract int getLayoutId();

    protected void initView(View view){

    }


}
