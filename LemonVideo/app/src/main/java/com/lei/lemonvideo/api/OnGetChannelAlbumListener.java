package com.lei.lemonvideo.api;

import com.lei.lemonvideo.model.AlbumList;
import com.lei.lemonvideo.model.ErrorInfo;

/**
 * Created by lei on 2017/12/1.
 */
public interface OnGetChannelAlbumListener {

    void OnGetChannelAlbumSuccess(AlbumList albums);

    void OnGetChannelAlbumFail(ErrorInfo errorInfo);

}
