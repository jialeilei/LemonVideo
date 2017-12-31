package com.lei.lemonvideo.model.sohu;

import com.google.gson.annotations.Expose;

/**
 * Created by lei on 2017/12/8.
 */
public class DetailResult {

    @Expose
    private long status;

    @Expose
    private String statusText;

    @Expose//详情
    private ResultAlbum data;

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public ResultAlbum getData() {
        return data;
    }

    public void setData(ResultAlbum data) {
        this.data = data;
    }
}
