package com.lei.lemonvideo.model;

import android.content.Context;

import com.lei.lemonvideo.R;

import java.io.Serializable;

/**
 * Created by lei on 2017/11/8.
 */
public class Channel implements Serializable {

    public static final int SHOW = 1; //电视剧
    public static final int MOVE = 2; //电影
    public static final int COMIC = 3; //动漫
    public static final int DOCUMENTARY = 4; //纪录片
    public static final int MUSIC = 5; //音乐
    public static final int VARIETY = 6; //综艺
    public static final int LIVE = 7; //直播
    public static final int FAVORITE = 8; //搜藏
    public static final int HISTORY = 9; //历史记录
    public static final int CHANNEL_COUNT = 9;

    private int channelId;
    private String channelName;
    private Context mContext;

    public Channel(int id,Context context){
        channelId = id;
        mContext = context;
        switch (id){
            case SHOW:
                channelName = mContext.getResources().getString(R.string.channel_tv);
                break;
            case MOVE:
                channelName = mContext.getResources().getString(R.string.channel_move);
                break;
            case COMIC:
                channelName = mContext.getResources().getString(R.string.channel_comic);
                break;
            case DOCUMENTARY:
                channelName = mContext.getResources().getString(R.string.channel_documentary);
                break;
            case MUSIC:
                channelName = mContext.getResources().getString(R.string.channel_music);
                break;
            case VARIETY:
                channelName = mContext.getResources().getString(R.string.channel_variety);
                break;
            case LIVE:
                channelName = mContext.getResources().getString(R.string.channel_live);
                break;
            case FAVORITE:
                channelName = mContext.getResources().getString(R.string.channel_favorite);
                break;
            case HISTORY:
                channelName = mContext.getResources().getString(R.string.channel_history);
                break;
            default:
                break;
        }
    }

    public int getChannelId() {
        return channelId;
    }


    public String getChannelName() {
        return channelName;
    }

}
