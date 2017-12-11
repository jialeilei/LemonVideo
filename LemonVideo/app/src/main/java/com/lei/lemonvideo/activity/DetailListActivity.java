package com.lei.lemonvideo.activity;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.ViewGroup;
import com.lei.lemonvideo.R;
import com.lei.lemonvideo.fragment.DetailListFragment;
import com.lei.lemonvideo.model.Channel;
import com.lei.lemonvideo.model.Site;
import java.util.HashMap;


public class DetailListActivity extends BaseActivity {

    private final static String CHANNEL_ID = "channelid";
    private int mChannelId;
    private ViewPager mViewPager;
    private SitePagerAdapter mSitePagerAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_list;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        if (intent != null){
            mChannelId = intent.getIntExtra(CHANNEL_ID, 0);
        }
        Channel channel = new Channel(mChannelId,DetailListActivity.this);
        setSupportActionBar();//支持toolbar
        mToolbar.setTitle("title" + channel.getChannelName());
        mViewPager = bindViewId(R.id.viewpager_detail);
        mSitePagerAdapter = new SitePagerAdapter(getSupportFragmentManager(),DetailListActivity.this,mChannelId);
        mViewPager.setAdapter(mSitePagerAdapter);

    }

    //处理左上角返回箭头
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {

    }

    public static void launchDetailList(Context context,int channelId ){
        Intent intent = new Intent(context,DetailListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(CHANNEL_ID, channelId);
        context.startActivity(intent);
    }


     class SitePagerAdapter extends FragmentPagerAdapter{

         private HashMap<Integer,DetailListFragment> mPagerMap ;
         private int mChannel_id;
         private Context mContext;

         public SitePagerAdapter(FragmentManager fm,Context context,int channelId){
             super(fm);
             mContext = context;
             mChannel_id = channelId;
             mPagerMap = new HashMap<>();
         }

         @Override
         public Fragment getItem(int position) {
             Fragment fragment = DetailListFragment.newInstance(position + 1,mChannel_id);
             return fragment;
         }

         @Override
         public int getCount() {
             return Site.SITE_COUNT;
         }


         @Override
         public Object instantiateItem(ViewGroup container, int position) {
             Object object = super.instantiateItem(container, position);
             if (object instanceof DetailListFragment){
                 mPagerMap.put(position,(DetailListFragment)object);
             }
             return object;
         }

         @Override
         public void destroyItem(ViewGroup container, int position, Object object) {
             super.destroyItem(container, position, object);
             mPagerMap.remove(position);
         }
     }

}
