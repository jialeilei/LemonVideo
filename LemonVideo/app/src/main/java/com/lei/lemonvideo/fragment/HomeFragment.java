package com.lei.lemonvideo.fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.lei.lemonvideo.R;
import com.lei.lemonvideo.activity.DetailListActivity;
import com.lei.lemonvideo.model.Channel;

public class HomeFragment extends BaseFragment {

    private static final String TAG = "HomeFragment";
    private GridView mGridView;
    private GridViewAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        mGridView = bindViewId(R.id.home_grid_view);
    }

    @Override
    protected void initData() {

        mGridView.setAdapter(new GridViewAdapter());
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "position: " + position);
                switch (position){
                    case 6://直播

                        break;
                    case 7://搜藏

                        break;
                    case 8://历史

                        break;
                    default://跳转对应的频道
                        DetailListActivity.launchDetailList(getActivity(), position + 1);
                        break;

                }
            }
        });
    }

    class GridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Channel.CHANNEL_COUNT;
        }

        @Override
        public Channel getItem(int position) {
            return new Channel(position + 1,getActivity());
        }

        @Override
        public long getItemId(int position) {
            return position ;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Channel mChannel = getItem(position);
            ViewHolder holder;
            if (convertView == null){
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.home_grid_item,null);
                holder = new ViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.home_img_grid);
                holder.tv = (TextView) convertView.findViewById(R.id.home_tv_grid);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv.setText(mChannel.getChannelName());
            int id = mChannel.getChannelId();
            switch (id){
                case Channel.SHOW:
                    break;
                case Channel.MOVE:
                    break;
                case Channel.COMIC:
                    break;
                case Channel.DOCUMENTARY:
                    break;
                case Channel.MUSIC:
                    break;
                case Channel.VARIETY:
                    break;
                case Channel.LIVE:
                    break;
                case Channel.FAVORITE:
                    break;
                case Channel.HISTORY:
                    break;
                default:
                    break;
            }

            holder.img.setImageResource(R.mipmap.ic_launcher);
            return convertView;
        }
    }

    class ViewHolder{
        ImageView img;
        TextView tv;
    }

}
