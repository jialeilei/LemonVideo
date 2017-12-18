package com.lei.lemonvideo.indicater;

/**
 * Created by lei on 2017/12/18.
 */
public interface IPagerChangeListener {

    /*
    * 当选中时回调
    * */
    void onPagerSelected(int position);

    /*
    * 当滚动时回调
    * */
    void onPagerScrolled(int position,float positionOffsetPercent,int positionOffsetPixel);


    void onPagerScrollStateChanged(int position);

}
