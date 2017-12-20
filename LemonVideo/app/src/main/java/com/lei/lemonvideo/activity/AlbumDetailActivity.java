package com.lei.lemonvideo.activity;


import android.app.Activity;
import android.content.Intent;
import com.lei.lemonvideo.R;
import com.lei.lemonvideo.model.Album;


/**
 * Created by lei on 2017/12/20.
 */
public class AlbumDetailActivity extends BaseActivity {

    private Album mAlbum;
    private int mVideoNo;
    private boolean mIsShowDesc;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_album_detail;
    }

    @Override
    protected void initView() {
        mAlbum = getIntent().getParcelableExtra("album");
        mVideoNo = getIntent().getIntExtra("videoNo",0);
        mIsShowDesc = getIntent().getBooleanExtra("isShowDesc",true);
    }

    @Override
    protected void initData() {

    }

    /**
     * @param activity
     * @param album
     * @param videoNo
     * @param isShowDesc
     * 电视剧： 有剧集
     * 电影： 有详情
     * 纪录片、综艺： 按期显示，scrollView 滑动显示
     */
    public static void launch(Activity activity,Album album,int videoNo,boolean isShowDesc){
        Intent intent = new Intent(activity,AlbumDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("album",album);
        intent.putExtra("videoNo",videoNo);
        intent.putExtra("isShowDesc",isShowDesc);
        activity.startActivity(intent);
    }

    /**
     * @param activity
     * @param album
     */
    public static void launch(Activity activity, Album album) {
        Intent intent = new Intent(activity,AlbumDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("album",album);
        activity.startActivity(intent);
    }
}
