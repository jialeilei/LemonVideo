package com.lei.lemonvideo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import com.lei.lemonvideo.R;
import com.lei.lemonvideo.adapter.OnVideoSelectedListener;
import com.lei.lemonvideo.adapter.VideoItemAdapter;
import com.lei.lemonvideo.api.OnGetVideoListener;
import com.lei.lemonvideo.api.SiteApi;
import com.lei.lemonvideo.model.Album;
import com.lei.lemonvideo.model.ErrorInfo;
import com.lei.lemonvideo.model.Site;
import com.lei.lemonvideo.model.VideoList;
import com.lei.lemonvideo.model.sohu.Video;


public class AlbumPlayGridFragment extends BaseFragment {

    private static final String TAG = AlbumPlayGridFragment.class.getSimpleName();
    private static final String ARGS_ALBUM = "album";
    private static final String ARGS_IS_SHOWDESC = "isShowDesc";
    private static final String ARGS_INIT_POSITION = "initPosition";
    private Album mAlbum;
    private boolean isShowDesc;
    private int initPosition;
    private int mPageNo;
    private int mPageSize;
    private VideoItemAdapter mVideoItemAdapter;

    public AlbumPlayGridFragment(){}

    public static Fragment newInstance(Album album,boolean isShowDesc, int initPosition){
        AlbumPlayGridFragment fragment = new AlbumPlayGridFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_ALBUM,album);
        bundle.putBoolean(ARGS_IS_SHOWDESC, isShowDesc);
        bundle.putInt(ARGS_INIT_POSITION, initPosition);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mAlbum = getArguments().getParcelable(ARGS_ALBUM);
            isShowDesc = getArguments().getBoolean(ARGS_IS_SHOWDESC);
            initPosition = getArguments().getInt(ARGS_INIT_POSITION);
            mPageNo = 0;
            mPageSize = 50;
            mVideoItemAdapter = new VideoItemAdapter(getActivity(),mAlbum.getVideoTotal(),videoSelectedListener);
            loadData();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_album_play_grid;
    }

    @Override
    protected void initView() {

    }

    private void loadData() {

        //
        SiteApi.onGetVideo(Site.SOHU, mPageSize, mPageNo, mAlbum, new OnGetVideoListener() {
            @Override
            public void OnGetVideoSuccess(VideoList videoList) {

                for (Video video : videoList){
                    Log.i(TAG," " + video.toString());
                    mVideoItemAdapter.addVideo(video);
                }
            }

            @Override
            public void OnGetVideoFail(ErrorInfo errorInfo) {

            }
        });


    }


    @Override
    protected void initData() {

    }

    private OnVideoSelectedListener videoSelectedListener = new OnVideoSelectedListener() {
        @Override
        public void onVideoSelected() {

        }
    };



}
