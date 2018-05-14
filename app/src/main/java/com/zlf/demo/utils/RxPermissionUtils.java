package com.zlf.demo.utils;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zlf.demo.base.BaseView;

import java.lang.ref.WeakReference;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxPermissionUtils {
    private static WeakReference<BaseView> mWeakReference;

    public static void request(final BaseView baseView, final OnRequestResult requestResult, final String...
            permission) {
        mWeakReference = new WeakReference<>(baseView);
        final RxPermissions rxPermissions = new RxPermissions(mWeakReference.get().getActivity());

        Disposable disposable = rxPermissions.request(permission).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    requestResult.onGrant();
                } else {
                    Disposable d = rxPermissions.shouldShowRequestPermissionRationale(mWeakReference.get()
                            .getActivity(), permission).subscribe(new Consumer<Boolean>() {


                        @Override
                        public void accept(@NonNull Boolean aBoolean) throws Exception {
                            if (aBoolean) {
                                requestResult.shouldShow();
                            } else {
                                requestResult.onDenied();
                            }
                        }
                    });
                    mWeakReference.get().addDisposable(d);
                }
            }
        });
        mWeakReference.get().addDisposable(disposable);
    }

    public static void onDestroy() {
        if (null != mWeakReference && null != mWeakReference.get()) {
            mWeakReference.get().clear();
            mWeakReference.clear();
        }
    }

    public static abstract class OnRequestResult {
        public abstract void onGrant();

        public void onDenied() {

        }

        public void shouldShow() {

        }
    }
}
