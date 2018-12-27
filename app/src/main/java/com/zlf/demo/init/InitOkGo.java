package com.zlf.demo.init;

import android.app.Application;

import com.kingja.loadsir.core.LoadSir;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.zlf.demo.App;
import com.zlf.demo.R;
import com.zlf.demo.callback.CustomCallback;
import com.zlf.demo.callback.EmptyCallback;
import com.zlf.demo.callback.ErrorCallback;
import com.zlf.demo.callback.LoadingCallback;
import com.zlf.demo.callback.TimeoutCallback;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.logging.Level;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class InitOkGo implements App.IAppInit
{
    @Override
    public void init(Application application) {
        //测试拉卡拉
        HttpHeaders headers =new HttpHeaders();
        headers.put("Accept","application/json;charset=utf-8");
        headers.put("Content-Type","application/json;charset=utf-8,application/json;charset=utf-8");
        headers.put("User-Agent","Lakala IOS Client");
        headers.put("X-Client-Bus-Ver","v1.3.0");
        headers.put("X-Client-Id","6673720F-2E49-41D0-B4E7-6D5C50F85CE1");
        headers.put("X-Client-Platform","IOS");
        headers.put("X-Client-PV","qr");
        headers.put("X-Client-Series","76869FE52F50462B97959691CA478052");
        headers.put("X-Client-Ver","1003");
        headers.put("X-Device-Id","FE7635BD-8CFF-4F46-A5E0-D22347A82409");
        OkGo.getInstance()
                /**
                 * 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                 * 最后的true表示是否打印okgo的内部异常,一般打开方便调试错误
                 */
                .debug("OkGo", Level.INFO, true)

                /**如果使用默认的3秒,以下三行也不需要传*/
                .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)/**全局的连接超时时间*/
                .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)/**全局的读取超时时间*/
                .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)/**全局的写入超时时间*/

                .setCertificates(application.getResources().openRawResource(R.raw.b),"lakala@2018#0306")

                /**可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看github介绍 https://github.com/jeasonlzy*/
                .setCacheMode(CacheMode.NO_CACHE)

                /**可以全局统一设置缓存时间,默认永不过期*/
                // .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                /**可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0*/
                .setRetryCount(0)
                .addCommonHeaders(headers)
        /**这两行同上，不需要就不要加入
         .addCommonHeaders(headers)//设置全局公共头
         .addCommonParams(params)//设置全局公共参数*/
        ;

    }

}
