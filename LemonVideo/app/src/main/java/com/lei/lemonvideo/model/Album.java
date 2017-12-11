package com.lei.lemonvideo.model;


import android.os.Parcel;
import android.os.Parcelable;
import com.lei.lemonvideo.application.AppManager;

/**
 * Created by lei on 2017/12/1.
 */
public class Album implements Parcelable {

    private String albumId;//
    private int videoTotal;
    private String title;//专辑名称
    private String subTitle;//专辑子标题
    private String director;//导演
    private String mainActor;
    private String varImgUrl;//竖向图片
    private String horImgUrl;//横向图片
    private String albumDesc;//专辑描述
    private Site site;//网站
    private String tip;//提示
    private boolean isCompleted;//专辑是否结束
    private String letvStyle;//乐视特殊字段

    public static final Parcelable.Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel source) {
            return new Album(source);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(albumId);
        parcel.writeInt(videoTotal);
        parcel.writeString(title);
        parcel.writeString(subTitle);
        parcel.writeString(director);
        parcel.writeString(mainActor);
        parcel.writeString(varImgUrl);
        parcel.writeString(horImgUrl);
        parcel.writeString(albumDesc);
        parcel.writeInt(site.getSiteId());
        parcel.writeString(tip);
        parcel.writeByte((byte) (isCompleted ? 1 : 0));//
        parcel.writeString(letvStyle);
    }

    private Album(Parcel parcel){
        this.albumId = parcel.readString();
        this.videoTotal = parcel.readInt();
        this.title = parcel.readString();
        this.subTitle = parcel.readString();
        this.director = parcel.readString();
        this.mainActor = parcel.readString();
        this.varImgUrl = parcel.readString();
        this.horImgUrl = parcel.readString();
        this.albumDesc = parcel.readString();
        this.tip = parcel.readString();
        this.site = new Site(parcel.readInt());
        this.isCompleted = parcel.readByte() != 0;
        this.letvStyle = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Album(int siteId){
        site = new Site(siteId);
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public int getVideoTotal() {
        return videoTotal;
    }

    public void setVideoTotal(int videoTotal) {
        this.videoTotal = videoTotal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public String getVarImgUrl() {
        return varImgUrl;
    }

    public void setVarImgUrl(String varImgUrl) {
        this.varImgUrl = varImgUrl;
    }

    public String getHorImgUrl() {
        return horImgUrl;
    }

    public void setHorImgUrl(String horImgUrl) {
        this.horImgUrl = horImgUrl;
    }

    public String getAlbumDesc() {
        return albumDesc;
    }

    public void setAlbumDesc(String albumDesc) {
        this.albumDesc = albumDesc;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getLetvStyle() {
        return letvStyle;
    }

    public void setLetvStyle(String letvStyle) {
        this.letvStyle = letvStyle;
    }


    @Override
    public String toString() {
        return "Album{" +
                "albumId='" + albumId + '\'' +
                ", videoTotal=" + videoTotal +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", director='" + director + '\'' +
                ", mainActor='" + mainActor + '\'' +
                ", varImgUrl='" + varImgUrl + '\'' +
                ", horImgUrl='" + horImgUrl + '\'' +
                ", albumDesc='" + albumDesc + '\'' +
                ", site=" + site +
                ", tip='" + tip + '\'' +
                ", isCompleted=" + isCompleted +
                ", letvStyle='" + letvStyle + '\'' +
                '}';
    }

    public String toJson(){
        return AppManager.getGson().toJson(this);
    }

    public Album fromJson(String json){
        return AppManager.getGson().fromJson(json,Album.class);
    }

}
