package com.lei.lemonvideo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import com.lei.lemonvideo.R;
import com.lei.lemonvideo.model.VideoList;
import com.lei.lemonvideo.model.sohu.Video;

/**
 * Created by lei on 2017/12/31.
 */
public class VideoItemAdapter extends BaseAdapter {

    private Context mContext;
    private int mTotalCount;
    private OnVideoSelectedListener mListener;
    private VideoList mVideoList = new VideoList();

    public VideoItemAdapter(Context context,int totalCount,OnVideoSelectedListener listener){
        mContext = context;
        mTotalCount = totalCount;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mVideoList.size();
    }

    @Override
    public Video getItem(int position) {
        if (mVideoList.size() > 0){
            return mVideoList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.video_item,null);
            holder = new ViewHolder();
            holder.videoContainer = (LinearLayout) convertView.findViewById(R.id.video_container);
            holder.btnVideo = (Button) convertView.findViewById(R.id.btn_video_title);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    public void addVideo(Video video){
        mVideoList.add(video);
    }

    class ViewHolder{
        LinearLayout videoContainer;
        Button btnVideo;
    }

}
