package com.lei.lemonvideo.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lei.lemonvideo.R;

/**
 * Created by lei on 2017/11/30.
 */
public class PullLoadRecycleView extends LinearLayout {

    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private View mFooterView;
    private boolean mIsRefresh = false;
    private boolean mIsLoadMore = false;

    public PullLoadRecycleView(Context context) {
        super(context);
        initView(context);
    }

    public PullLoadRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public PullLoadRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.pull_load_recycle_view,null);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(android.R.color.holo_green_light,
                android.R.color.holo_blue_light, android.R.color.holo_orange_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayoutOnListener());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mIsRefresh || mIsLoadMore;
            }
        });
        mRecyclerView.setOnScrollListener(new RecyclerViewOnScroll());
        mRecyclerView.setVerticalScrollBarEnabled(false);
        mFooterView = view.findViewById(R.id.footer_view);
        TextView tv = (TextView) mFooterView.findViewById(R.id.tv_load_more);
        mFooterView.setVisibility(GONE);
        this.addView(view);

    }

    public void setGridLayout(int spanCount){
        GridLayoutManager manager = new GridLayoutManager(mContext,spanCount);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        if (adapter != null){
            mRecyclerView.setAdapter(adapter);
        }
    }

    class SwipeRefreshLayoutOnListener implements SwipeRefreshLayout.OnRefreshListener{
        @Override
        public void onRefresh() {

            if (!mIsRefresh){
                mIsRefresh = true;
                refreshData();
            }
        }
    }

    class RecyclerViewOnScroll extends RecyclerView.OnScrollListener{

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int firstItem = 0;
            int lastItem = 0;
            RecyclerView.LayoutManager manager = mRecyclerView.getLayoutManager();
            int totalCount = manager.getItemCount();
            if (manager instanceof GridLayoutManager){
                GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
                firstItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
                lastItem = gridLayoutManager.findLastCompletelyVisibleItemPosition();
                if (firstItem == 0 || firstItem == RecyclerView.NO_POSITION){
                    lastItem = gridLayoutManager.findLastVisibleItemPosition();
                }
            }

            //?
            if (mSwipeRefreshLayout.isEnabled()){
                mSwipeRefreshLayout.setEnabled(true);
            }else {
                mSwipeRefreshLayout.setEnabled(false);
            }

            if (!mIsLoadMore
                    && lastItem == totalCount
                    && mSwipeRefreshLayout.isEnabled()
                    && !mIsRefresh
                    && (dx > 0 || dy > 0) ){
                mIsLoadMore = true;
                loadMoreData();
            }
        }
    }

    public void setRefreshComplate(){
        mIsRefresh = false;
        setRefreshing(false);
    }

    public void setPullLoadComplate(){
        mIsRefresh = false;
        mIsLoadMore = false;
        setRefreshing(false);
        mFooterView.animate().translationY(mFooterView.getHeight()).setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(300).start();
    }

    private void setRefreshing(final boolean b) {
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(b);
            }
        });
    }

    private void refreshData() {
        if (mOnPullLoadMoreListener != null){
            mOnPullLoadMoreListener.refresh();
        }
    }

    private void loadMoreData() {
        if (mOnPullLoadMoreListener != null){
            mFooterView.animate().translationY(0).setInterpolator(new AccelerateDecelerateInterpolator())
                    .setDuration(300).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    mFooterView.setVisibility(VISIBLE);
                }
            }).start();
            invalidate();
            mOnPullLoadMoreListener.loadMore();
        }
    }

    public interface OnPullLoadMoreListener{
        void refresh();
        void loadMore();
    }

    private OnPullLoadMoreListener mOnPullLoadMoreListener;
    public void setOnPullLoadMoreListener(OnPullLoadMoreListener listener){
        mOnPullLoadMoreListener = listener;
    }

}
