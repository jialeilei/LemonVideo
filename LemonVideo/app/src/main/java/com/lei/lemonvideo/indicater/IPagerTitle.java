package com.lei.lemonvideo.indicater;

/**
 * Created by lei on 2017/12/18.
 */
public interface IPagerTitle {

    /*
    * @param index 第几个
    * @param totalCount 总数
    * */
    void onSelect(int index,int totalCount);

    void onDisSelect(int index,int totalCount);

    /*
    * @param leavePercent 0.0f~1.0f    1:完全离开
    * */
    void onLeave(int index,int totalCount,float leavePercent, boolean isLeftToRight);

    void onEnter(int index,int totalCount,float enterPercent, boolean isLeftToRight);

}
