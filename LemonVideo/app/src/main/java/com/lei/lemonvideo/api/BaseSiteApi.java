package com.lei.lemonvideo.api;


import com.lei.lemonvideo.model.Channel;

/**
 * Created by lei on 2017/12/1.
 */
public abstract class BaseSiteApi {

    public abstract void onGetChannelAlbums(Channel channel,int pageNum,int pageSize,
                                            OnGetChannelAlbumListener listener);

}
