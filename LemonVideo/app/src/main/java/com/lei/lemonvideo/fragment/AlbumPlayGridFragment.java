package com.lei.lemonvideo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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
import com.lei.lemonvideo.widget.CustomGridView;


public class AlbumPlayGridFragment extends BaseFragment {

    private static final String TAG = AlbumPlayGridFragment.class.getSimpleName();
    private static final String ARGS_ALBUM = "album";
    private static final String ARGS_IS_SHOWDESC = "isShowDesc";
    private static final String ARGS_INIT_POSITION = "initPosition";
    private Album mAlbum;
    private boolean isShowDesc;
    private int mInitPosition;
    private int mPageNo;
    private int mPageSize;
    private VideoItemAdapter mVideoItemAdapter;
    private CustomGridView mCustomGridView;
    private TextView tvEmpty;
    private int mPageTotal;
    private boolean mIsFirstSelection = true;
    private int mCurrentPosition;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public AlbumPlayGridFragment(){}

    public static AlbumPlayGridFragment newInstance(Album album,boolean isShowDesc, int initPosition){
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
            mInitPosition = getArguments().getInt(ARGS_INIT_POSITION);
            mCurrentPosition = mInitPosition;
            mPageNo = 0;
            mPageSize = 50;
            mVideoItemAdapter = new VideoItemAdapter(getActivity(),mAlbum.getVideoTotal(),videoSelectedListener);
            mPageTotal = ( mAlbum.getVideoTotal() + mPageSize - 1 ) / mPageSize;
            mVideoItemAdapter.setIsShowTitleContent(isShowDesc);
            loadData();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_album_play_grid;
    }

    @Override
    protected void initView() {

        tvEmpty = bindViewId(R.id.tv_empty);
        tvEmpty.setVisibility(View.VISIBLE);
        mCustomGridView = bindViewId(R.id.custom_grid_view);
        //同样是剧集，电视剧是多集，综艺是多少期
        mCustomGridView.setNumColumns(isShowDesc ? 1 : 6);
        mCustomGridView.setAdapter(mVideoItemAdapter);
        if (mAlbum.getVideoTotal() > 0 && mAlbum.getVideoTotal() > mPageSize){
            mCustomGridView.setHasMoreItem(true);
        }else {
            mCustomGridView.setHasMoreItem(false);
        }
        mCustomGridView.setOnLoadMoreListener(new CustomGridView.OnLoadMoreListener() {
            @Override
            public void onLoadMoreItems() {
                loadData();
            }
        });

    }

    private void loadData() {

        //
        SiteApi.onGetVideo(Site.SOHU, mPageSize, mPageNo, mAlbum, new OnGetVideoListener() {
            @Override
            public void OnGetVideoSuccess(final VideoList videoList) {

                for (Video video : videoList){
                    Log.i(TAG, " " + video.toString());
                    mVideoItemAdapter.addVideo(video);
                }

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mVideoItemAdapter != null){
                            mVideoItemAdapter.notifyDataSetChanged();
                        }
                        tvEmpty.setVisibility(View.INVISIBLE);
                        if (mVideoItemAdapter.getCount() > mInitPosition && mIsFirstSelection){
                            mCustomGridView.setSelection(mInitPosition);
                            mCustomGridView.setItemChecked(mInitPosition, true);
                            mIsFirstSelection = false;
                            SystemClock.sleep(100);
                            mCustomGridView.smoothScrollToPosition(mInitPosition);
                        }
                    }
                });

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
        public void onVideoSelected(Video v, int position) {

            if (mCustomGridView != null){
                mCustomGridView.setSelection(position);
                mCustomGridView.setItemChecked(position, true);
                mCurrentPosition = position;

                if (mListener != null){
                    mListener.onPlayVideoSelected(v,position);
                }
            }

        }

    };

    private OnPlayVideoSelectedListener mListener;

    public void setOnPlayVideoSelectedListener(OnPlayVideoSelectedListener listener){
        mListener = listener;
    }

    public interface OnPlayVideoSelectedListener{

        void onPlayVideoSelected(Video video,int position);

    }



}
