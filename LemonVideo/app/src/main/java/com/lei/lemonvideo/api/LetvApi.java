package com.lei.lemonvideo.api;

import android.util.Log;

import com.lei.lemonvideo.application.AppManager;
import com.lei.lemonvideo.model.Album;
import com.lei.lemonvideo.model.AlbumList;
import com.lei.lemonvideo.model.Channel;
import com.lei.lemonvideo.model.ErrorInfo;
import com.lei.lemonvideo.model.Site;
import com.lei.lemonvideo.model.sohu.Result;
import com.lei.lemonvideo.utils.OkHttpUtils;

import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lei on 2017/12/1.
 */
public class LetvApi extends BaseSiteApi{

    //http://static.meizi.app.m.letv.com/android/mod/mob/ctl/listalbum/act/index/src/1/cg/2/or/20/vt/180001/ph/420003,420004/pt/-141003/pn/1/ps/30/pcode/010110263/version/5.6.2.mindex.html
    private final static String ALBUM_LIST_URL_FORMAT = "http://static.meizi.app.m.letv.com/android/" +
            "mod/mob/ctl/listalbum/act/index/src/1/cg/%s/ph/420003,420004/pn/%s/ps/%s/pcode/010110263/version/5.6.2.mindex.html";

    private final static String ALBUM_LIST_URL_DOCUMENTARY_FORMAT = "http://static.meizi.app.m.letv.com/android/" +
            "mod/mob/ctl/listalbum/act/index/src/1/cg/%s/or/3/ph/420003,420004/pn/%s/ps/%s/pcode/010110263/version/5.6.2.mindex.html";

    private final static String ALBUM_LIST_URL_SHOW_FORMAT = "http://static.meizi.app.m.letv.com/android/" +
            "mod/mob/ctl/listalbum/act/index/src/1/cg/%s/or/20/vt/180001/ph/420003,420004/pt/-141003/pn/%s/ps/%s/pcode/010110263/version/5.6.2.mindex.html";

    //http://static.meizi.app.m.letv.com/android/mod/mob/ctl/album/act/detail/id/10026309/pcode/010410000/version/2.1.mindex.html
    private final static String ALBUM_DESC_URL_FORMAT = "http://static.meizi.app.m.letv.com/" +
            "android/mod/mob/ctl/album/act/detail/id/%s/pcode/010410000/version/2.1.mindex.html";
    //key : bh65OzqYYYmHRQ
    private final static String SEVER_TIME_URL = "http://dynamic.meizi.app.m.letv.com/android/dynamic.php?mod=mob&ctl=timestamp&act=timestamp&pcode=010410000&version=5.4";

    //http://static.app.m.letv.com/android/mod/mob/ctl/videolist/act/detail/id/10026309/vid/0/b/1/s/30/o/-1/m/1/pcode/010410000/version/2.1.mindex.html
    private final static String ALBUM_VIDEOS_URL_FORMAT = "http://static.app.m.letv.com/" +
            "android/mod/mob/ctl/videolist/act/detail/id/%s/vid/0/b/%s/s/%s/o/%s/m/%s/pcode/010410000/version/2.1.mindex.html";

    //arg: mmsid currentServerTime key vid
    private final static String VIDEO_FILE_URL_FORMAT = "http://dynamic.meizi.app.m.letv.com/android/dynamic.php?mmsid=" +
            "%s&playid=0&tss=ios&pcode=010410000&version=2.1&tm=%s&key=%s&vid=" +
            "%s&ctl=videofile&mod=minfo&act=index";

    private final static String VIDEO_REAL_LINK_APPENDIX = "&format=1&expect=1&termid=2&pay=0&ostype=android&hwtype=iphone";

    private String test ="http://static.meizi.app.m.letv.com/android/" +
            "mod/mob/ctl/listalbum/atc/index/src/1/cg/2/or/20/vt/180001/ph/420003,420004/pt/-141003/pn/l/ps/30/pcode/010110263/version/5.6.2.mindex.html";

    private static final String TAG = LetvApi.class.getSimpleName();
    public static final int LETV_CHANNELID_MOVE = 1;//电影频道
    public static final int LETV_CHANNELID_SERIES = 2;//电视剧
    public static final int LETV_CHANNELID_VARIETY = 11;//综艺
    public static final int LETV_CHANNELID_DOCUMENTRY = 16;//纪录片
    public static final int LETV_CHANNELID_COMIC = 5;//动漫
    public static final int LETV_CHANNELID_MUSIC = 9;//音乐

    @Override
    public void onGetChannelAlbums(Channel channel,int pageNum,int pageSize,
                                   OnGetChannelAlbumListener listener) {
        String url = getChannelAlbumUrl(channel,pageNum,pageSize);
        doGetChannelAlbumsByUrl(url,listener);
    }


    private String getChannelAlbumUrl(Channel channel, int pageNum, int pageSize) {
        if (channel.getChannelId() == Channel.DOCUMENTARY){
            return String.format(ALBUM_LIST_URL_DOCUMENTARY_FORMAT,convertChannelId(channel),pageNum,pageSize);
        }else if (channel.getChannelId() == Channel.SHOW){
            return String.format(ALBUM_LIST_URL_SHOW_FORMAT,convertChannelId(channel),pageNum,pageSize);
        }
        return String.format(ALBUM_LIST_URL_FORMAT,convertChannelId(channel),pageNum,pageSize);
    }

    private int convertChannelId(Channel channel) {
        int channelId = -1;
        switch (channel.getChannelId()){
            case Channel.MOVE:
                channelId = LETV_CHANNELID_MOVE;
                break;
            case Channel.SHOW:
                channelId = LETV_CHANNELID_SERIES;
                break;
            case Channel.VARIETY:
                channelId = LETV_CHANNELID_VARIETY;
                break;
            case Channel.DOCUMENTARY:
                channelId = LETV_CHANNELID_DOCUMENTRY;
                break;
            case Channel.COMIC:
                channelId = LETV_CHANNELID_COMIC;
                break;
            case Channel.MUSIC:
                channelId = LETV_CHANNELID_MUSIC;
                break;
        }
        //自定义id与真实id转化
        return channelId;
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

                String json = response.body().string();
                try {
                    JSONObject jsonObject = null;
                    jsonObject = new JSONObject(json);

                    JSONObject bodyJson = jsonObject.optJSONObject("body");
                    if (bodyJson.optInt("album_count") > 0){
                        AlbumList list = new AlbumList();
                        JSONArray albumJsonList = bodyJson.optJSONArray("album_list");
                        for (int i = 0; i < albumJsonList.length(); i++){
                            Album album = new Album(Site.LETV);
                            JSONObject albumJson = albumJsonList.getJSONObject(i);
                            album.setAlbumId(albumJson.getString("aid"));
                            album.setAlbumDesc(albumJson.getString("subname"));
                            album.setTitle(albumJson.getString("name"));
                            album.setTip(albumJson.getString("subname"));
                            JSONObject jsonImage = albumJson.getJSONObject("images");
                            String imgUrl = StringEscapeUtils.unescapeJava(jsonImage.getString("400*300"));
                            album.setHorImgUrl(imgUrl);
                            list.add(album);
                        }

                        if (list != null) {
                            if (list.size() > 0 && listener != null) {
                                listener.OnGetChannelAlbumSuccess(list);
                                //log
                                list.debug();
                            }
                        } else {
                            ErrorInfo info = buildErrorInfo(url, "doGetChannelAlbumsByUrl", null, ErrorInfo.ERROR_TYPE_DATA);
                            listener.OnGetChannelAlbumFail(info);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    ErrorInfo info = buildErrorInfo(url, "doGetChannelAlbumsByUrl", null, ErrorInfo.ERROR_TYPE_PARSE_JSON);
                    listener.OnGetChannelAlbumFail(info);
                }


            }
        });
    }

    private ErrorInfo buildErrorInfo(String url,String functionName,IOException e, int errorType){

        ErrorInfo errorInfo = new ErrorInfo(Site.LETV,errorType);
        if (e != null){
            errorInfo.setExceptionString(e.getMessage());
        }
        errorInfo.setFunctionName(functionName);
        errorInfo.setUrl(url);
        errorInfo.setTag(TAG);
        errorInfo.setClassName(TAG);
        return errorInfo;

    }


}
