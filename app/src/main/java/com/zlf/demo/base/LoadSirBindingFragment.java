package com.zlf.demo.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * @Author: Zachary
 * @Date: 2018/5/11/011
 * @Description:
 */
public abstract class LoadSirBindingFragment<V extends BaseView,P extends BasePresenter<V>,Binding extends ViewDataBinding>
        extends LoadSirFragment<V,P> {
    protected Binding mBinding;
    @Override
    protected void initView(View view) {
        super.initView(view);
        mBinding= DataBindingUtil.bind(view);
    }
}
