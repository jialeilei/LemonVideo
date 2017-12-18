package com.lei.lemonvideo.utils;

import com.lei.lemonvideo.application.AppManager;
import okhttp3.Callback;
import okhttp3.Request;

/**
 * Created by lei on 2017/12/8.
 */
public class OkHttpUtils {

    private static final String REQUEST_TAG = "okhttp";

    private static Request buildRequest(String url){
        if (AppManager.isNetworkAvailable()){
            Request request = new Request.Builder()
                    .tag(REQUEST_TAG)
                    .url(url)
                    .build();
            return request;
        }
        return null;
    }


    public static void excute(String url, Callback callback){
        Request request = buildRequest(url);
        excute(request, callback);
    }


    private static void excute(Request request, Callback callback) {
        AppManager.getHttpClient().newCall(request).enqueue(callback);
    }


}
