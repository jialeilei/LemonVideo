package com.lei.lemonvideo.model.sohu;

import com.google.gson.annotations.Expose;

/**
 * Created by lei on 2017/12/8.
 */
public class Result {

    @Expose
    private long status;

    @Expose
    private String statusText;

    @Expose//列表
    private Data data;

    @Expose//详情
    private ResultAlbum resultAlbum;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public ResultAlbum getResultAlbum() {
        return resultAlbum;
    }

    public void setResultAlbum(ResultAlbum resultAlbum) {
        this.resultAlbum = resultAlbum;
    }
}
