package com.lei.lemonvideo.model;

import com.lei.lemonvideo.R;
import com.lei.lemonvideo.application.AppManager;

/**
 * Created by lei on 2017/11/20.
 */
public class Site {

    public static final int SOHU = 1;
    public static final int LETV = 2;

    public static final int SITE_COUNT = 2;
    private int siteId;
    private String siteName;

    public Site(int id){
        siteId = id;
        switch (id){
            case LETV:
                siteName = AppManager.getContext().getResources().getString(R.string.site_letv);
                break;
            case SOHU:
                siteName = AppManager.getContext().getResources().getString(R.string.site_sohu);
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
