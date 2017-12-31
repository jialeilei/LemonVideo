package com.lei.lemonvideo.model.sohu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lei on 2017/12/30.
 */
public class Video {

    @Expose
    private Long vid;

    @Expose
    @SerializedName("video_name")
    private String videoName;

    @Expose
    @SerializedName("hor_high_pic")
    private String horHighPic;

    @Expose
    @SerializedName("ver_high_pic")
    private String verHighPic;

    @Expose
    private String title;

    private int site;

    public Long getVid() {
        return vid;
    }

    public void setVid(Long vid) {
        this.vid = vid;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSite() {
        return site;
    }

    public void setSite(int site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "Video{" +
                "vid=" + vid +
                ", videoName='" + videoName + '\'' +
                ", horHighPic='" + horHighPic + '\'' +
                ", verHighPic='" + verHighPic + '\'' +
                ", title='" + title + '\'' +
                ", site=" + site +
                '}';
    }

}
