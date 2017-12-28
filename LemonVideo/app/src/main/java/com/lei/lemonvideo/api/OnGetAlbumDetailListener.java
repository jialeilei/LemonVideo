package com.lei.lemonvideo.api;

import com.lei.lemonvideo.model.Album;
import com.lei.lemonvideo.model.ErrorInfo;

/**
 * Created by lei on 2017/12/27.
 */
public interface OnGetAlbumDetailListener {

    void OnGetAlbumDetailSuccess(Album album);

    void OnGetAlbumDetailFail(ErrorInfo errorInfo);

}
