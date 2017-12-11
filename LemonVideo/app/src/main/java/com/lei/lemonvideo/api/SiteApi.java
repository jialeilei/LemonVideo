package com.lei.lemonvideo.api;

import android.content.Context;
import com.lei.lemonvideo.model.Channel;
import com.lei.lemonvideo.model.Site;

/**
 * Created by lei on 2017/12/1.
 */
public class SiteApi {

    public static void onGetChannelAlbums(Context context,int siteId,int channelId,int pageNum,int pageSize,
                                     OnGetChannelAlbumListener listener){
        switch (siteId){
            case Site.LETV:
                new LetvApi().onGetChannelAlbums(new Channel(channelId,context),pageNum,pageSize,listener);
                break;
            case Site.SOHU:
                new SohuApi().onGetChannelAlbums(new Channel(channelId,context),pageNum,pageSize,listener);
                break;
            default:
                break;
        }

    }



}
