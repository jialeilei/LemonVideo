package com.lei.lemonvideo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;
import com.lei.lemonvideo.R;
import com.lei.lemonvideo.model.Site;
import com.lei.lemonvideo.widget.PullLoadRecycleView;

public class DetailListFragment extends BaseFragment {

    private static int mSiteId;
    private static int mChannelId;
    private static final String SITE_ID = "site_id";
    private static final String CHANNEL_ID = "site_name";
    private PullLoadRecycleView mRecycleView;
    private TextView mTvEmpty;
    private DetailListAdapter mDetailAdapter;
    private int mColums ;
    private static final int REFRESH_DURATION = 1500;
    private static final int LOAD_MORE_DURATION = 3000;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public DetailListFragment(){}


    public static Fragment newInstance(int siteId,int channelId){
        DetailListFragment fragment = new DetailListFragment();
        mSiteId = siteId;
        mChannelId = channelId;
        Bundle bundle = new Bundle();
        bundle.putInt(SITE_ID,siteId);
        bundle.putInt(CHANNEL_ID, channelId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDetailAdapter = new DetailListAdapter();
        if (mSiteId == Site.LETV){
            mColums = 2;
            mDetailAdapter.setColumns(mColums);
        }else {
            mColums = 3;
            mDetailAdapter.setColumns(mColums);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail_list;
    }

    @Override
    protected void initView() {

        mTvEmpty = bindViewId(R.id.tv_show_empty);
        mTvEmpty.setText(getActivity().getResources().getString(R.string.data_is_loading));
        mRecycleView = bindViewId(R.id.pullLoadRecycleView);
        mRecycleView.setGridLayout(3);
        mRecycleView.setAdapter(mDetailAdapter);
        mRecycleView.setOnPullLoadMoreListener(new OnPullLoadMoreListener());

    }

    @Override
    protected void initData() {

    }


    class OnPullLoadMoreListener implements PullLoadRecycleView.OnPullLoadMoreListener{

        @Override
        public void refresh() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    reFreshData();
                    mRecycleView.setRefreshComplate();
                }
            },REFRESH_DURATION);

        }

        @Override
        public void loadMore() {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadMoreData();
                    mRecycleView.setPullLoadComplate();
                }
            },LOAD_MORE_DURATION);
        }
    }

    private void loadMoreData() {

    }

    private void reFreshData() {

    }


    class DetailListAdapter extends RecyclerView.Adapter{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public void setColumns(int columns){

        }

    }


}
