package com.lei.lemonvideo.model.sohu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;


/**
 * Created by lei on 2017/12/8.
 */
public class Data {

    @Expose
    private int count;

    @Expose
    @SerializedName("videos")
    private List<ResultAlbum> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ResultAlbum> getList() {
        return list;
    }

    public void setList(List<ResultAlbum> list) {
        this.list = list;
    }

}
