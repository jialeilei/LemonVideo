package com.lei.lemonvideo.api;

import com.lei.lemonvideo.model.ErrorInfo;
import com.lei.lemonvideo.model.VideoList;

/**
 * Created by lei on 2017/12/27.
 */
public interface OnGetVideoListener {

    void OnGetVideoSuccess(VideoList videoList);

    void OnGetVideoFail(ErrorInfo errorInfo);

}
