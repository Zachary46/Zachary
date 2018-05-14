package com.zlf.demo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.zlf.demo.R;
import com.zlf.demo.callback.EmptyCallback;
import com.zlf.demo.callback.ErrorCallback;
import com.zlf.demo.callback.LoadingCallback;
import com.zlf.demo.ui.views.dialog.DialogLayout;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ListCompositeDisposable;

/**
 * @Author: Zachary
 * @Date: 2018/5/11/011
 * @Description:
 */
public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends Fragment implements BaseView{
    private P presneter;
    private V view;
    protected View mRoot;
    protected LayoutInflater mInflater;
    protected LoadService mLoadService;
    private DialogLayout mDialogLayout;
    private ListCompositeDisposable mListCompositeDisposable = new ListCompositeDisposable();

    public P getPresneter() {
        return presneter;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot != null) {
            ViewGroup parent = (ViewGroup) container.getParent();
            if (parent != null) {
                parent.removeView(mRoot);
            }
        } else {
            mInflater = inflater;
            mRoot = inflater.inflate(getLayoutId(), container, false);
            initMVP();
            initView(mRoot);

            /**LoadSir注册必须放在initialize()后面，不然initialize()会报空指针，View会被LoadSir拦截的样子*/
            mLoadService = LoadSir.getDefault().register(mRoot, new Callback.OnReloadListener() {
                @Override
                public void onReload(View v) {
                    reloadData(v);
                }
            });

            /**LoadSir注册必须放在loadData()前面，不然mLoadService会报空指针*/
            loadData();
        }
        return mLoadService.getLoadLayout();
    }

    private void initMVP() {
        Log.i("zhou","============1============");
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.presneter.detachView();
    }

    protected void loadData(){

    }

    public abstract void reloadData(View v);

    protected void initView(View view){

    }

    @Override
    public void showProgress() {
        if (mDialogLayout == null) {
            mDialogLayout = new DialogLayout(getActivity());
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
        Log.i("zhou","============showLoading============");
        if (mDialogLayout == null) {
            mDialogLayout = new DialogLayout(getActivity());
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
        mListCompositeDisposable.add(disposable);
    }

    @Override
    public void removeDisposable(Disposable disposable) {
        mListCompositeDisposable.remove(disposable);
    }

    @Override
    public void clear() {
        mListCompositeDisposable.clear();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        getActivity().overridePendingTransition(R.anim.tl_in_left_to_right, R.anim.tl_out_right_to_left);

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(R.anim.tl_in_left_to_right, R.anim.tl_out_right_to_left);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clear();
        mRoot =null;
    }
    protected final int getColors(int color){
        return ContextCompat.getColor(getContext(),color);
    }
}
