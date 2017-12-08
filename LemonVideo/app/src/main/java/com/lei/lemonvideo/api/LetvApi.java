package com.lei.lemonvideo.api;

import com.lei.lemonvideo.model.Channel;

/**
 * Created by lei on 2017/12/1.
 */
public class LetvApi extends BaseSiteApi{

    private static final String api = "http://api.tv.sohu.com/v4/search/channel.json?" +
            "cid=%s&o=1&plat=6&poid=1&api_key= " +
            "sver=6.2.0&sysver=4.4.2&partner=47&page=%s&pagesize=%s";
    @Override
    public void onGetChannelAlbums(Channel channel,int pageNum,int pageSize,
                                   OnGetChannelAlbumListener listener) {
        String url = getChannelAlbumUrl(channel,pageNum,pageSize);

    }

    private String getChannelAlbumUrl(Channel channel, int pageNum, int pageSize) {

        return null;
    }

}
