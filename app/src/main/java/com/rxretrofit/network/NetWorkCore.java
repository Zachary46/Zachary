package com.rxretrofit.network;


import com.zlf.demo.BuildConfig;

public interface NetWorkCore {
    String BASE_URL = BuildConfig.REQUEST_URL;
    String BASE_URL_IMG = BASE_URL + "img/";
    String BASE_URL_FILE = BASE_URL + "download/";
    String BASE_URL_AGGREMENT = BASE_URL + "guard/agreement/protocol.htm";
}
