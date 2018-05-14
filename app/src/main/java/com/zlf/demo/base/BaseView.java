package com.zlf.demo.base;

import android.app.Activity;

import com.rxretrofit.view.HttpView;

/**
 * 作者: Dream on 2017/9/4 21:40
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//高度抽象UI层接口
public interface BaseView extends HttpView{
    Activity getActivity();
    int getLayoutId();
}
