package com.lei.lemonvideo.api;

import com.lei.lemonvideo.model.Channel;

/**
 * Created by lei on 2017/12/1.
 */
public class SohuApi extends BaseSiteApi {

    private static final String API_CHANNEL_ALBUM_FORMAT = "http://api.tv.sohu.com/v4/search/channel.json" +
            "?cid=%s&o=1&plat=6&poid=1&api_key= " +
            "sver=6.2.0&sysver=4.4.2&partner=47&page=%s&page_size=%s";

    public static final int SOHU_CHANNELID_MOVE = 1;//搜狐电影频道
    public static final int SOHU_CHANNELID_SERIES = 2;//搜狐电视剧
    public static final int SOHU_CHANNELID_VARIETY = 7;//搜狐综艺
    public static final int SOHU_CHANNELID_DOCUMENTRY = 8;//搜狐纪录片
    public static final int SOHU_CHANNELID_COMIC = 16;//搜狐动漫
    public static final int SOHU_CHANNELID_MUSIC = 24;//搜狐音乐

    @Override
    public void onGetChannelAlbums(Channel channel,int pageNum,int pageSize,
                                   OnGetChannelAlbumListener listener) {

        String url = getChannelAlbumUrl(channel,pageNum,pageSize);
        doGetChannelAlbumsByUrl(url,listener);
    }

    private void doGetChannelAlbumsByUrl(String url, OnGetChannelAlbumListener listener) {

    }

    private String getChannelAlbumUrl(Channel channel, int pageNum, int pageSize) {
        return String.format(API_CHANNEL_ALBUM_FORMAT,toConvertChannelId(channel),pageNum,pageSize);
    }

    private int toConvertChannelId(Channel channel) {
        int channelId = -1;
        switch (channel.getChannelId()){
            case Channel.MOVE:
                channelId = SOHU_CHANNELID_MOVE;
                break;
            case Channel.SHOW:
                channelId = SOHU_CHANNELID_SERIES;
                break;
            case Channel.VARIETY:
                channelId = SOHU_CHANNELID_VARIETY;
                break;
            case Channel.DOCUMENTARY:
                channelId = SOHU_CHANNELID_DOCUMENTRY;
                break;
            case Channel.COMIC:
                channelId = SOHU_CHANNELID_COMIC;
                break;
            case Channel.MUSIC:
                channelId = SOHU_CHANNELID_MUSIC;
                break;
        }
        //自定义id与真实id转化
        return channelId;
    }

}
