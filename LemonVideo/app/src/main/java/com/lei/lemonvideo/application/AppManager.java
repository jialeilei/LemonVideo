package com.lei.lemonvideo.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;

/**
 * Created by lei on 2017/12/4.
 */
public class AppManager extends Application {

    private static Gson mGson;
    private static OkHttpClient mClient;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mGson = new Gson();
        mClient = new OkHttpClient();
        mContext = this;
    }

    public static Gson getGson(){
        return mGson;
    }

    public static OkHttpClient getHttpClient(){
        return mClient;
    }

    public static Context getContext(){
        return mContext;
    }

    public static Resources getResource(){
        return mContext.getResources();
    }



}
