package com.rxretrofit.view;

import android.view.View;

/**
  * @Author: Zachary
  * @Date: 2018/5/10/010 18:03
  * @Description:
  *
  */
public abstract class LoadingView {

    public abstract void show();

    public abstract void hide() ;

    public abstract void forceHide();

    public abstract void setStatus(int strResId, int type);

    public abstract void setOnRetryListener(View.OnClickListener listener);
}
