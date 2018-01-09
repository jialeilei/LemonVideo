package com.lei.lemonvideo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lei on 2018/1/1.
 */
public class CustomGridView extends GridView {


    private Context mContext;
    private List<ViewHolder> mFooterViewList = new ArrayList<>();
    private boolean isLoading;
    private boolean hasMoreItem;
    private OnLoadMoreListener mOnLoadMoreListener = null;
    private OnScrolledListener  mOnScrolledListener = null;


    public interface OnLoadMoreListener{
        void onLoadMoreItems();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener){
        this.mOnLoadMoreListener = listener;
    }

    public interface OnScrolledListener{
        void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
    }

    public void setOnScrollListener(OnScrolledListener listener){
        this.mOnScrolledListener = listener;
    }



    public CustomGridView(Context context) {
        super(context);
        initView(context);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        isLoading = false;
        //添加loadingView
        LoadingView loadingView = new LoadingView(mContext);
        addFooterView(loadingView);

        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (mOnScrolledListener != null){
                    mOnScrolledListener.onScroll(view,firstVisibleItem,visibleItemCount,totalItemCount);
                }

                if (totalItemCount > 0){
                    int lastViewVisible = firstVisibleItem + visibleItemCount;
                    if (!isLoading && hasMoreItem && lastViewVisible == totalItemCount){
                        if (mOnLoadMoreListener != null){
                            mOnLoadMoreListener.onLoadMoreItems();
                        }
                    }
                }

            }
        });


    }

    public boolean isHasMoreItem() {
        return hasMoreItem;
    }

    public void setHasMoreItem(boolean hasMoreItem) {
        this.hasMoreItem = hasMoreItem;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void isLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    /**
     * 添加 footerView
     * @param view
     * @param data
     * @param isSelected
     */
    public void addFooterView(View view, Object data, boolean isSelected){
        ViewHolder holder = new ViewHolder();
        FrameLayout fl = new FullWidthViewLayout(mContext);
        fl.addView(view);
        holder.view = view;
        holder.data = data;
        holder.viewContainer = fl;
        holder.isSelected = isSelected;
        mFooterViewList.add(holder);
    }

    public void addFooterView(View view){
        addFooterView(view, null, true);
    }

    public void removeFooterView(View view){
        if (mFooterViewList.size() > 0){
            removeView(view,mFooterViewList);
        }
    }

    private void removeView(View view, List<ViewHolder> mFooterViewList) {

        for (int i = 0; i < mFooterViewList.size(); i++) {
            ViewHolder holder = mFooterViewList.get(i);
            if (holder.view == view){
                mFooterViewList.remove(i);
                break;
            }
        }

    }

    public void notifyChanged(){
        this.requestLayout();
        this.invalidate();
    }

    class ViewHolder{
        View view;
        ViewGroup viewContainer;
        Object data;
        boolean isSelected;
    }

    class FullWidthViewLayout extends FrameLayout{


        public FullWidthViewLayout(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int targetWidth = this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
            MeasureSpec.makeMeasureSpec(targetWidth,MeasureSpec.getMode(widthMeasureSpec));
        }


    }

}
