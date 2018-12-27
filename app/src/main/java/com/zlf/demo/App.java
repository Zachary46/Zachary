package com.zlf.demo;

import android.app.Application;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.zlf.demo.init.InitLoadSir;
import com.zlf.demo.init.InitOkGo;
import com.zlf.demo.init.InitSmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import io.reactivex.annotations.NonNull;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        List<IAppInit> initList = new ArrayList<>();
        initList.add(new InitLoadSir());
        initList.add(new InitSmartRefreshLayout());
        initList.add(new InitOkGo());
        for (IAppInit iAppInit : initList){
            iAppInit.init(this);
        }
    }

    public interface IAppInit{
        void init(@NonNull Application application);
    }
}
