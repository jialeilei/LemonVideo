package com.lei.lemonvideo.indicater;

import com.lei.lemonvideo.model.PositionData;

import java.util.List;

/**
 * Created by lei on 2017/12/18.
 */
public interface IViewPagerIndicaterView extends IPagerChangeListener{

    void setPositionDataList(List<PositionData> list);


}
