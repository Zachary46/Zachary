package com.rxretrofit.view;

import android.app.Activity;

import io.reactivex.disposables.Disposable;

/**
 * Project Name：RxRetrofit
 * Describe：
 * Created By：yul
 * Modify：yul
 * Modify Time：16:26
 * Remark：
 */
public interface HttpView {
    void showLoading();

    void hideLoading();

    Activity getActivity();

    void showError(String message, int code);

    void addDisposable(Disposable disposable);

    void removeDisposable(Disposable disposable);

    void clear();

    void showEmpty();

    void showProgress();
}
