package com.lei.lemonvideo.api;

import android.content.Context;
import com.lei.lemonvideo.model.Album;
import com.lei.lemonvideo.model.Channel;
import com.lei.lemonvideo.model.Site;
import com.lei.lemonvideo.model.sohu.Video;

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

    public static void onGetAlbumDetail(int siteId,Album album,OnGetAlbumDetailListener listener){
        switch (siteId){
            case Site.LETV:
                new LetvApi().onGetAlbumDetail(album, listener);
                break;
            case Site.SOHU:
                new SohuApi().onGetAlbumDetail(album, listener);
                break;
            default:
                break;
        }
    }

    /**
     * 取video相关信息
     * @param siteId
     * @param album
     * @param listener
     */
    public static void onGetVideo(int siteId,int pageSize, int pageNo,Album album,OnGetVideoListener listener){
        switch (siteId){
            case Site.LETV:
                new LetvApi().onGetVideo(album, pageSize, pageNo, listener);
                break;
            case Site.SOHU:
                new SohuApi().onGetVideo(album, pageSize, pageNo, listener);
                break;
            default:
                break;
        }
    }

    /**
     *
     * @param siteId
     * @param video
     * @param listener
     */
    public static void onGetVideoPlayUrl(int siteId,Video video,OnGetVideoPlayUrlListener listener){
        switch (siteId){
            case Site.LETV:
                new LetvApi().onGetVideoPlayUrl(siteId, video, listener);
                break;
            case Site.SOHU:
                new SohuApi().onGetVideoPlayUrl(siteId, video, listener);
                break;
            default:
                break;
        }
    }



}
