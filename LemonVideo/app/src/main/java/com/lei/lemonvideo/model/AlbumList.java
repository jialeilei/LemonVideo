package com.lei.lemonvideo.model;

import android.util.Log;
import java.util.ArrayList;

/**
 * Created by lei on 2017/12/1.
 */
public class AlbumList extends ArrayList<Album> {

    private static final String TAG = AlbumList.class.getSimpleName();

    public void debug(){
        for (Album a : this){
            Log.i(TAG,">> albumList " + a.toString());
        }
    }
    
}
