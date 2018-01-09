package com.lei.lemonvideo.activity;


import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.lei.lemonvideo.R;
import com.lei.lemonvideo.api.OnGetAlbumDetailListener;
import com.lei.lemonvideo.api.SiteApi;
import com.lei.lemonvideo.fragment.AlbumPlayGridFragment;
import com.lei.lemonvideo.model.Album;
import com.lei.lemonvideo.model.ErrorInfo;
import com.lei.lemonvideo.model.sohu.Video;
import com.lei.lemonvideo.utils.ImageUtils;


/**
 * Created by lei on 2017/12/20.
 */
public class AlbumDetailActivity extends BaseActivity {

    private static final String TAG = AlbumDetailActivity.class.getSimpleName();
    private Album mAlbum;
    private int mVideoNo;
    private boolean mIsShowDesc;
    private AlbumPlayGridFragment mFragment;
    private ImageView mAlbumImg;
    private TextView mAlbumName;
    private TextView mAlbumDirector;
    private TextView mAlbumActor;
    private TextView mAlbumDesc;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_album_detail;
    }

    @Override
    protected void initView() {
        mAlbum = getIntent().getParcelableExtra("album");
        mVideoNo = getIntent().getIntExtra("videoNo", 0);
        mIsShowDesc = getIntent().getBooleanExtra("isShowDesc", false);

        mAlbumImg = bindViewId(R.id.iv_album_image);
        mAlbumName = bindViewId(R.id.tv_album_name);
        mAlbumDirector = bindViewId(R.id.tv_album_director);
        mAlbumActor = bindViewId(R.id.tv_album_main_actor);
        mAlbumDesc = bindViewId(R.id.tv_album_desc);
    }

    @Override
    protected void initData() {

        mAlbumName.setText(mAlbum.getTitle());
        if (TextUtils.isEmpty(mAlbum.getDirector())){
            mAlbumDirector.setVisibility(View.GONE);
        }else {
            mAlbumDirector.setText("导演: " + mAlbum.getDirector());
            mAlbumDirector.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(mAlbum.getMainActor())){
            mAlbumActor.setVisibility(View.GONE);
        }else {
            mAlbumActor.setText("主演: " + mAlbum.getMainActor());
            mAlbumActor.setVisibility(View.VISIBLE);
        }
        //描述
        if (TextUtils.isEmpty(mAlbum.getAlbumDesc())){
            mAlbumDesc.setVisibility(View.GONE);
        }else {
            mAlbumDesc.setText(mAlbum.getAlbumDesc());
            mAlbumDesc.setVisibility(View.VISIBLE);
        }
        //海报图
        if (!TextUtils.isEmpty(mAlbum.getVarImgUrl())){
            ImageUtils.displayImage(mAlbumImg,mAlbum.getVarImgUrl());
        }

        if (!TextUtils.isEmpty(mAlbum.getHorImgUrl())){
            ImageUtils.displayImage(mAlbumImg,mAlbum.getHorImgUrl());
        }

        SiteApi.onGetAlbumDetail(1, mAlbum, new OnGetAlbumDetailListener() {
            @Override
            public void OnGetAlbumDetailSuccess(final Album album) {

                Log.i(TAG, "onGetAlbumDetail  total: " + album.getVideoTotal());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mFragment = AlbumPlayGridFragment.newInstance(album, mIsShowDesc, 0);
                        mFragment.setOnPlayVideoSelectedListener(mPlayVideoSelectedListener);
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_container, mFragment);
                        ft.commit();
                        //getSupportFragmentManager().executePendingTransactions();
                        getFragmentManager().executePendingTransactions();
                    }
                });

            }

            @Override
            public void OnGetAlbumDetailFail(ErrorInfo errorInfo) {

            }
        });

    }

    @Nullable
    @Override
    public ActionBar getSupportActionBar() {
        return super.getSupportActionBar();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                finish();
                return true;
            case R.id.favor_item:
                if (mIsFavor){
                    mIsFavor = false;
                    invalidateOptionsMenu();
                    Toast.makeText(this,"取消收藏",Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.normal_item:
                if (!mIsFavor){
                    mIsFavor = true;
                    invalidateOptionsMenu();
                    Toast.makeText(this,"收藏成功",Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean mIsFavor = false;
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem favorItem = menu.findItem(R.id.favor_item);
        MenuItem normalItem = menu.findItem(R.id.normal_item);
        favorItem.setVisible(mIsFavor);
        normalItem.setVisible(!mIsFavor);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.album_detail_menu,menu);
        return true;
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

    private AlbumPlayGridFragment.OnPlayVideoSelectedListener mPlayVideoSelectedListener = new AlbumPlayGridFragment.OnPlayVideoSelectedListener() {
        @Override
        public void onPlayVideoSelected(Video video, int position) {

            SiteApi.onGetVideoPlayUrl();
        }
    };

}
