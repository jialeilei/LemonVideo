package com.lei.lemonvideo.api;


import android.util.Log;

import com.lei.lemonvideo.application.AppManager;
import com.lei.lemonvideo.model.Album;
import com.lei.lemonvideo.model.AlbumList;
import com.lei.lemonvideo.model.Channel;
import com.lei.lemonvideo.model.ErrorInfo;
import com.lei.lemonvideo.model.Site;
import com.lei.lemonvideo.model.VideoList;
import com.lei.lemonvideo.model.sohu.DetailResult;
import com.lei.lemonvideo.model.sohu.Result;
import com.lei.lemonvideo.model.sohu.ResultAlbum;
import com.lei.lemonvideo.model.sohu.Video;
import com.lei.lemonvideo.model.sohu.VideoResult;
import com.lei.lemonvideo.utils.OkHttpUtils;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by lei on 2017/12/1.
 */
public class SohuApi extends BaseSiteApi {

    private String test = "http://api.tv.sohu.com/v4/search/channel.json" +
            "?cid=2&o=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&" +
            "sver=6.2.0&sysver=4.4.2&partner=47&page=0&page_size=30";


    private final static String API_KEY = "plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=6.2.0&sysver=4.4.2&partner=47";
    private final static String API_ALBUM_INFO = "http://api.tv.sohu.com/v4/album/info/";

    private static final String API_CHANNEL_ALBUM_FORMAT = "http://api.tv.sohu.com/v4/search/channel.json" +
            "?cid=%s&o=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&" +
            "sver=6.2.0&sysver=4.4.2&partner=47&page=%s&page_size=%s";

    //
    private final static String API_ALBUM_VIDOES_FORMAT = "http://api.tv.sohu.com/v4/album/videos/%s.json?page=%s&page_size=%s&order=0&site=1&with_trailer=1&plat=6&poid=1&api_key=9854b2afa779e1a6bff1962447a09dbd&sver=6.2.0&sysver=4.4.2&partner=47";


    private static final String TAG = SohuApi.class.getSimpleName();
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
        doGetChannelAlbumsByUrl(url, listener);
    }

    private void doGetChannelAlbumsByUrl(final String url, final OnGetChannelAlbumListener listener) {

        OkHttpUtils.excute(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (listener != null) {
                    ErrorInfo info = buildErrorInfo(url, "doGetChannelAlbumsByUrl", e, ErrorInfo.ERROR_TYPE_URL);
                    listener.OnGetChannelAlbumFail(info);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    ErrorInfo info = buildErrorInfo(url, "doGetChannelAlbumsByUrl", null, ErrorInfo.ERROR_TYPE_URL);
                    listener.OnGetChannelAlbumFail(info);
                    return;
                }
                //取到数据映射到ResultAlbum
                //转化resultAlbum 到 Album
                //将Album存到AlbumList 中
                Result result = AppManager.getGson().
                        fromJson(response.body().string(), Result.class);
                AlbumList list = toConvertAlbumList(result);
                if (list != null) {
                    if (list.size() > 0 && listener != null) {
                        listener.OnGetChannelAlbumSuccess(list);
                    }
                } else {
                    ErrorInfo info = buildErrorInfo(url, "doGetChannelAlbumsByUrl", null, ErrorInfo.ERROR_TYPE_DATA);
                    listener.OnGetChannelAlbumFail(info);
                }
            }
        });
    }

    private AlbumList toConvertAlbumList(Result result) {
        if (result.getData().getList().size() > 0){
            AlbumList list = new AlbumList();
            for (ResultAlbum resultAlbum : result.getData().getList()){
                Album album = new Album(Site.SOHU);
                album.setAlbumDesc(resultAlbum.getTvDesc());
                album.setDirector(resultAlbum.getDirector());
                album.setHorImgUrl(resultAlbum.getHorHighPic());
                album.setVarImgUrl(resultAlbum.getVerHighPic());
                album.setMainActor(resultAlbum.getMainActor());
                album.setTitle(resultAlbum.getAlbumName());
                album.setTip(resultAlbum.getTip());
                album.setAlbumId(resultAlbum.getAlbumId());
                list.add(album);
            }
            return list;
        }
        return null;
    }

    private ErrorInfo buildErrorInfo(String url,String functionName,IOException e, int errorType){

        ErrorInfo errorInfo = new ErrorInfo(Site.SOHU,errorType);
        if (e != null){
            errorInfo.setExceptionString(e.getMessage());
        }
        errorInfo.setFunctionName(functionName);
        errorInfo.setUrl(url);
        errorInfo.setTag(TAG);
        errorInfo.setClassName(TAG);
        return errorInfo;
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

    public void onGetAlbumDetail(final Album album, final OnGetAlbumDetailListener listener) {

        final String url = API_ALBUM_INFO + album.getAlbumId() + ".json?" + API_KEY;
        OkHttpUtils.excute(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (listener != null) {
                    ErrorInfo info = buildErrorInfo(url, "onGetAlbumDetail", e, ErrorInfo.ERROR_TYPE_URL);
                    listener.OnGetAlbumDetailFail(info);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (!response.isSuccessful()) {
                    ErrorInfo info = buildErrorInfo(url, "onGetAlbumDetail", null, ErrorInfo.ERROR_TYPE_URL);
                    listener.OnGetAlbumDetailFail(info);
                    return;
                }
                //data
                DetailResult result = AppManager.getGson().fromJson(response.body().string(),DetailResult.class);
                if (result.getData() != null){
                    if (result.getData().getTotalVideoCount() > 0){
                        album.setVideoTotal(result.getData().getTotalVideoCount());
                    }else {
                        album.setVideoTotal(result.getData().getLatestVideoCount());
                    }
                }

                if (listener != null){
                    listener.OnGetAlbumDetailSuccess(album);
                }

            }
        });
    }

    public void onGetVideo(Album album, int pageSize, int pageNo,final OnGetVideoListener listener) {

        final String url = String.format(API_ALBUM_VIDOES_FORMAT,album.getAlbumId(),pageNo,pageSize);
        OkHttpUtils.excute(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (listener != null) {
                    ErrorInfo info = buildErrorInfo(url, "onGetVideo", e, ErrorInfo.ERROR_TYPE_URL);
                    listener.OnGetVideoFail(info);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    ErrorInfo info = buildErrorInfo(url, "onGetVideo", null, ErrorInfo.ERROR_TYPE_URL);
                    listener.OnGetVideoFail(info);
                    return;
                }
                VideoResult result = AppManager.getGson().fromJson(response.body().string(),VideoResult.class);
                if (result != null){
                    Log.i(TAG, "onGetVideo " + result.toString());
                        if (result.getData() != null){
                            VideoList videoList = new VideoList();
                            for (Video video : result.getData().getVideoList()){
                                Video v = new Video();
                                v.setHorHighPic(video.getHorHighPic());
                                v.setVerHighPic(video.getVerHighPic());
                                v.setTitle(video.getTitle());
                                v.setVid(video.getVid());
                                v.setVideoName(video.getVideoName());
                                videoList.add(v);
                            }
                            if (listener != null){
                                listener.OnGetVideoSuccess(videoList);
                            }
                        }
                }

            }
        });

    }

    /**
     *
     * @param siteId
     * @param video
     * @param listener
     */
    public void onGetVideoPlayUrl(int siteId, Video video, OnGetVideoPlayUrlListener listener) {

    }


}
