package com.zlf.demo.callback;

import com.kingja.loadsir.callback.Callback;
import com.zlf.demo.R;

public class LoadingCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.layout_loading;
    }
}
