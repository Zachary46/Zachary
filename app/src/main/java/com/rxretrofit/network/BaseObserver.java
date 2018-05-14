package com.rxretrofit.network;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.rxretrofit.view.HttpView;

import java.lang.ref.WeakReference;
import java.net.ConnectException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class BaseObserver<T> implements Observer<RxHttpResponse<T>> {
    private static final String TAG = HttpConstant.HTTP_TAG;
    private WeakReference<HttpView> mWeakReference;

    /**构造函数
     * @param baseView
     */
    public BaseObserver(HttpView baseView) {
        mWeakReference = new WeakReference<>(baseView);
    }

    /**
     * @param d  网络请求时，可用于控制取消等操作
     */
    @Override
    public void onSubscribe(Disposable d) {
        if (mWeakReference.get() != null) {
            mWeakReference.get().showLoading();
            mWeakReference.get().addDisposable(d);
        }
    }

    /**网络请求结果回调
     * @param value
     */
    @Override
    public void onNext(RxHttpResponse<T> value) {
        if (mWeakReference.get() != null) {
            mWeakReference.get().hideLoading();
        }
        if (value.success) {
            onSuccess(value);
        } else {
            mWeakReference.get().showError(value.message, value.code);
        }
    }

    /** 网络请求异常
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError: "+e.getMessage());
        e.printStackTrace();
        if (mWeakReference.get() != null) {
            int errorCode = -1;
            if (e instanceof HttpException) {
                errorCode = ((HttpException) e).code();
                Log.d(TAG, "onError() called with: e = [" + ((HttpException) e).code() + "]");
            } else if (e instanceof ConnectException) {
                errorCode = 600;
            }
            mWeakReference.get().hideLoading();
            mWeakReference.get().showError(e.getMessage(), errorCode);
        }


    }

    /**
     * 网络请求结束
     */
    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
    }

    /**请求结果回调，供Presenter调用
     * @param response  返回结果
     */
    public abstract void onSuccess(RxHttpResponse<T> response);
}
