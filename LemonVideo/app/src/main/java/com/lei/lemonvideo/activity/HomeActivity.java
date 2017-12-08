package com.lei.lemonvideo.activity;


import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.lei.lemonvideo.R;
import com.lei.lemonvideo.model.Channel;


public class HomeActivity extends BaseActivity {

    private static final String TAG = "HomeActivity";
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private GridView mGridView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

        mDrawerLayout = bindViewId(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        mActionBarDrawerToggle.syncState();

        mNavigationView = bindViewId(R.id.nav_view);
        mGridView = bindViewId(R.id.home_grid_view);
        mGridView.setAdapter(new GridViewAdapter());

    }

    @Override
    protected void initData() {
        setSupportActionBar();
        setToolbarIcon(R.mipmap.toolbar_menu);
        setTitle("首页");

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "position: " + position);
                switch (position) {
                    case 6://直播

                        break;
                    case 7://搜藏

                        break;
                    case 8://历史

                        break;
                    default://跳转对应的频道
                        DetailListActivity.launchDetailList(HomeActivity.this, position + 1);
                        break;

                }
            }
        });

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_item_video:

                        break;
                    case R.id.navigation_item_setting:

                        break;
                    case R.id.navigation_item_about:

                        break;
                    default:
                        break;
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        mDrawerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    //onBackPressed();
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }

            }
        });
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //不需要右侧菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class GridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Channel.CHANNEL_COUNT;
        }

        @Override
        public Channel getItem(int position) {
            return new Channel(position + 1,HomeActivity.this);
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
                convertView = LayoutInflater.from(HomeActivity.this).inflate(R.layout.home_grid_item,null);
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
