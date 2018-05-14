package com.rxretrofit.network;

import com.rxretrofit.view.HttpView;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BaseObservableTransformer<T> implements ObservableTransformer<RxHttpResponse<T>, RxHttpResponse<T>> {
    private WeakReference<HttpView> mWeakReference;
    private final long RETRY_TIME = 3;

    public BaseObservableTransformer(HttpView baseView) {
        mWeakReference = new WeakReference<>(baseView);
    }

    @Override
    public ObservableSource apply(Observable upstream) {
        Observable ob = upstream
                .retry(RETRY_TIME)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (NetWorkUtils.isNetworkAvailable(mWeakReference.get().getActivity().getApplicationContext())) {
                            onAccept(true);
                        } else {
                            //Todo
                            onAccept(false);
                            //Toast.makeText(mView.getActivity(), "网络连接异常，请检查网络", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        return ob;
    }
    protected void onAccept(boolean valid){
        if (!valid){
            mWeakReference.get().showError("网络连接异常，请检查网络",-1);
        }
    }
}
