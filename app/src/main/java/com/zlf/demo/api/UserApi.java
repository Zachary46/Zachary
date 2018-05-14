package com.zlf.demo.api;

import com.rxretrofit.network.RxHttpResponse;
import com.zlf.demo.model.UserModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface UserApi {
    @GET
    Observable<RxHttpResponse<UserModel>> login(@Url String url);
}
