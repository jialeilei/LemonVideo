package com.lei.lemonvideo.model.sohu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lei on 2017/12/8.
 * 真正的数据结构
 */
public class ResultAlbum {

    @SerializedName("album_desc")
    @Expose
    private String tvDesc;//专辑描述

    @Expose
    private String director;//导演

    @SerializedName("hor_high_pic")
    @Expose
    private String horHighPic;

    @SerializedName("ver_high_pic")
    @Expose
    private String verHighPic;

    @SerializedName("main_actor")
    @Expose
    private String mainActor;

    @SerializedName("album_name")
    @Expose
    private String albumName;

    @Expose
    private String tip;//更新的集数

    @SerializedName("aid")
    @Expose
    private String albumId;

    //详情
    @SerializedName("latest_video_count")
    @Expose
    private int latestVideoCount;

    @SerializedName("total_video_count")
    @Expose
    private int totalVideoCount;

    public String getTvDesc() {
        return tvDesc;
    }

    public void setTvDesc(String tvDesc) {
        this.tvDesc = tvDesc;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getHorHighPic() {
        return horHighPic;
    }

    public void setHorHighPic(String horHighPic) {
        this.horHighPic = horHighPic;
    }

    public String getVerHighPic() {
        return verHighPic;
    }

    public void setVerHighPic(String verHighPic) {
        this.verHighPic = verHighPic;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public int getLatestVideoCount() {
        return latestVideoCount;
    }

    public void setLatestVideoCount(int latestVideoCount) {
        this.latestVideoCount = latestVideoCount;
    }

    public int getTotalVideoCount() {
        return totalVideoCount;
    }

    public void setTotalVideoCount(int totalVideoCount) {
        this.totalVideoCount = totalVideoCount;
    }
}
