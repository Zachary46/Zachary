package com.zlf.demo.init;

import android.app.Application;

import com.zlf.demo.App;
import com.zlf.demo.callback.CustomCallback;
import com.zlf.demo.callback.EmptyCallback;
import com.zlf.demo.callback.ErrorCallback;
import com.zlf.demo.callback.LoadingCallback;
import com.zlf.demo.callback.TimeoutCallback;

public class InitLoadSir implements App.IAppInit
{
    @Override
    public void init(Application application) {
        com.kingja.loadsir.core.LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
