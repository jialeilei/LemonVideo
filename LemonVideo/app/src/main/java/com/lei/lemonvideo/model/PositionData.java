package com.lei.lemonvideo.model;

/**
 * Created by lei on 2017/12/18.
 */
public class PositionData {

    private int mLeft;
    private int mTop;
    private int mRight;
    private int mBottom;

    private int mContentLeft;
    private int mContentTop;
    private int mContentRight;
    private int mContentBottom;

    public int width(){
        return mRight - mLeft;
    }

    public int Height(){
        return mBottom = mTop;
    }

    public int contentWidth(){
        return mContentRight - mContentLeft;
    }

    public int contentHeight(){
        return mContentBottom - mContentTop;
    }

    /*
    * 水平方向中点
    * */
    public int horizontalCenter(){
        return mLeft + width()/2;
    }

    public int verticalCenter(){
        return mTop + Height()/2;
    }
}
