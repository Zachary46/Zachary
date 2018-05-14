package com.zlf.demo;

import android.app.Application;
import com.kingja.loadsir.core.LoadSir;
import com.zlf.demo.callback.CustomCallback;
import com.zlf.demo.callback.EmptyCallback;
import com.zlf.demo.callback.ErrorCallback;
import com.zlf.demo.callback.LoadingCallback;
import com.zlf.demo.callback.TimeoutCallback;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initLoadSir();
    }

    private void initLoadSir() {
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
