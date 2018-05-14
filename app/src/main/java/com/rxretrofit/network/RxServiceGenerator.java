package com.rxretrofit.network;

import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import com.rxretrofit.network.HttpLoggingInterceptor;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxServiceGenerator {
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS);
    private static Retrofit.Builder mBuilder =
            new Retrofit.Builder().baseUrl(NetWorkCore.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());//加入 RxJava2
    private static HttpLoggingInterceptor mHttpLoggingInterceptor = new HttpLoggingInterceptor("RxRetrofit");

    /**生成api
     * @param sClass
     * @param <Clx>
     * @return
     */

    public static <Clx> Clx createService(Class<Clx> sClass) {
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                // Request customization: add request headers
                Headers headers = original.headers();
                Request.Builder requestBuilder = original.newBuilder();
                if (!TextUtils.isEmpty(headers.get("token"))) {
                    requestBuilder.removeHeader("token");
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
                return chain.proceed(original);
            }
        });
        return createService(sClass, "");
    }

    /**
     * @param sClass
     * @param token  程度Token
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> sClass, final String token) {
        //如果要支持 https:
        /*httpclient.socketFactory(getSSLSocketFactory(context, R.raw.xxx));
        httpclient.hostnameVerifier(getHostnameVerifier());*/

        if (!httpClient.interceptors().contains(mHttpLoggingInterceptor)) {
            httpClient.interceptors().clear();

            /******************修改打印方式***********************/
            mHttpLoggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
            mHttpLoggingInterceptor.setColorLevel(Level.INFO);

            httpClient.addInterceptor(mHttpLoggingInterceptor);
        }
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();
                if (!TextUtils.isEmpty(token)) {
                    requestBuilder.header("token", token);
                }
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });

        Retrofit retrofit = mBuilder.client(httpClient.build()).build();
        return retrofit.create(sClass);
    }
}
