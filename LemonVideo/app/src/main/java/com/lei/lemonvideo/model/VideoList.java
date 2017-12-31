package com.lei.lemonvideo.model;

import android.util.Log;

import com.lei.lemonvideo.model.sohu.Video;

import java.util.ArrayList;

/**
 * Created by lei on 2017/12/1.
 */
public class VideoList extends ArrayList<Video> {

    private static final String TAG = VideoList.class.getSimpleName();

    public void debug(){
        for (Video a : this){
            Log.i(TAG,">> videoList " + a.toString());
        }
    }
    
}
