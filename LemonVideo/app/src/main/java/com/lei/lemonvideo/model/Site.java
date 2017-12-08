package com.lei.lemonvideo.model;

import android.content.Context;
import com.lei.lemonvideo.R;

/**
 * Created by lei on 2017/11/20.
 */
public class Site {
    public static final int LETV = 1;
    public static final int SOHU = 2;
    public static final int SITE_COUNT = 2;

    private int siteId;
    private String siteName;
    private Context mContext;

    public Site(int id,Context context){
        siteId = id;
        mContext = context;
        switch (id){
            case LETV:
                siteName = mContext.getResources().getString(R.string.site_letv);
                break;
            case SOHU:
                siteName = mContext.getResources().getString(R.string.site_sohu);
                break;
            default:
                break;
        }
    }

    public int getSiteId() {
        return siteId;
    }


    public String getSiteName() {
        return siteName;
    }


}
