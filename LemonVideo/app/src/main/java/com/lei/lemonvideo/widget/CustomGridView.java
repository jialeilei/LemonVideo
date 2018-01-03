package com.lei.lemonvideo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
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

    public CustomGridView(Context context) {
        super(context);
        mContext = context;
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
