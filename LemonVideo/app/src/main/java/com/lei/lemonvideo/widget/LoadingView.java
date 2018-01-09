package com.lei.lemonvideo.widget;

import android.content.Context;
import android.widget.LinearLayout;

import com.lei.lemonvideo.R;

/**
 * Created by lei on 2018/1/4.
 */
public class LoadingView extends LinearLayout {

    private Context mContext;

    public LoadingView(Context context) {
        super(context);
        init();
    }

    private void init() {

        inflate(getContext(), R.layout.loading_view_layout,this);
    }


}
