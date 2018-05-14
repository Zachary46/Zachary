package com.zlf.demo.moudle.common.login;


import com.rxretrofit.network.BaseObservableTransformer;
import com.rxretrofit.network.BaseObserver;
import com.rxretrofit.network.RxHttpResponse;
import com.rxretrofit.network.RxServiceGenerator;
import com.zlf.demo.api.UserApi;
import com.zlf.demo.base.BasePresenter;
import com.zlf.demo.model.UserModel;

public class LoginPresenter extends BasePresenter<LoginView> {
    private UserApi api;
    public void login(String username, String password) {
        api = RxServiceGenerator.createService(UserApi.class);
        api.login("http://www.wanandroid.com/tools/mockapi/5554/login")
                .compose(new BaseObservableTransformer<UserModel>(getView()))
                .subscribe(new BaseObserver<UserModel>(getView()) {

            @Override
            public void onSuccess(RxHttpResponse<UserModel> response) {
                if (response.success) {
                    getView().onLoginResult(response.info);
                }
            }
        });

    }

}
