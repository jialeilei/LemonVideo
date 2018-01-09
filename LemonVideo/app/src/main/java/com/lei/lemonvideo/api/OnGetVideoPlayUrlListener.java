package com.lei.lemonvideo.api;


import com.lei.lemonvideo.model.ErrorInfo;
import com.lei.lemonvideo.model.sohu.Video;

/**
 * Created by lei on 2017/12/27.
 */

public interface OnGetVideoPlayUrlListener {

    void OnGetSuperUrl(Video video,String url);

    void OnGetNormalUrl(Video video,String url);

    void OnGetHeighUrl(Video video,String url);

    void onGetFailed(ErrorInfo errorInfo);

}
