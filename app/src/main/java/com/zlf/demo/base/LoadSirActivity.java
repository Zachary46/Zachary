package com.zlf.demo.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.zlf.demo.R;
import com.zlf.demo.callback.EmptyCallback;
import com.zlf.demo.callback.ErrorCallback;
import com.zlf.demo.callback.LoadingCallback;
import com.zlf.demo.ui.views.TitleBar;
import com.zlf.demo.ui.views.dialog.DialogLayout;
import com.zlf.demo.ui.views.navigationview.NavigationView;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;

/**
 * @Author: Zachary
 * @Date: 2018/5/11/011
 * @Description: 基类(不能含有BaseFragment的子类)
 */
public abstract class LoadSirActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity implements BaseView{
    private P presneter;
    private V view;
    protected View rootView;
    private TitleBar mTitleBar;
    private RelativeLayout rlContent;
    private NavigationView mNavigationView;
    protected LoadService mLoadService;
    protected DialogLayout mDialogLayout;
    private ListCompositeDisposable mListCompositeDisposable = new ListCompositeDisposable();

    public P getPresneter() {
        return presneter;
    }

    public TitleBar getToolBar() {
        if (null == mTitleBar) {
            mTitleBar = new TitleBar(this, mNavigationView);
        }
        return mTitleBar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_base_toolbar);

        initMVP();

        rlContent = (RelativeLayout) findViewById(R.id.rlContent);
        mNavigationView = (NavigationView) findViewById(R.id.na);
        rootView = getLayoutInflater().inflate(getLayoutId(), rlContent, false);
        rlContent.addView(rootView);

        initView();

        /**LoadSir注册必须放在initialize()后面，不然initialize()会报空指针，View会被LoadSir拦截的样子*/
        mLoadService = LoadSir.getDefault().register(rootView, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                reloadData(v);
            }
        });

        /**LoadSir注册必须放在loadData()前面，不然mLoadService会报空指针*/
        loadData();
    }


    private void initMVP() {
        if (this.presneter == null){
            this.presneter = createPresneter();
        }
        if (this.view == null){
            this.view = createView();
        }
        if (this.presneter == null){
            throw new NullPointerException("presneter不能够为空");
        }
        if (this.view == null){
            throw new NullPointerException("view不能够为空");
        }
        this.presneter.attachView(this.view);
    }

    public abstract P createPresneter();
    public abstract V createView();
    public abstract void initView();
    public abstract void loadData();
    public abstract void reloadData(View v);

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presneter.detachView();
    }

    @Override
    public void showProgress() {
        if (mDialogLayout == null) {
            mDialogLayout = new DialogLayout(this);
        }
        mDialogLayout.show();
    }

    @Override
    public void showEmpty() {
        if (null != mLoadService) {
            mLoadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void showLoading(){
        if (mDialogLayout == null) {
            mDialogLayout = new DialogLayout(this);
        }
        mDialogLayout.show();
        if (null != mLoadService) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void hideLoading() {
        if (null != mLoadService) {
            mLoadService.showSuccess();
        }
        if (null != mDialogLayout) {
            mDialogLayout.dismiss();
        }
    }

    @Override
    public void showError(String message, int code) {
        switch (code) {
            case -1:
                mLoadService.showCallback(ErrorCallback.class);
                break;
        }
    }

    @Override
    public void addDisposable(Disposable disposable) {
        if (null != disposable && !disposable.isDisposed()) {
            mListCompositeDisposable.add(disposable);
        }
    }

    @Override
    public void removeDisposable(Disposable disposable) {
        if (null != disposable) {
            mListCompositeDisposable.remove(disposable);
        }
    }

    @Override
    public void clear() {
        hideLoading();
        mListCompositeDisposable.clear();
    }

}
