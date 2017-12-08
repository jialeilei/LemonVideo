package com.lei.lemonvideo.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.lei.lemonvideo.R;
import com.lei.lemonvideo.fragment.FragmentManagerWrapper;
import com.lei.lemonvideo.fragment.HomeFragment;
import com.lei.lemonvideo.model.Channel;

public class HomeActivity2 extends BaseActivity {

    private static final String TAG = "HomeActivity" ;


    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private MenuItem mPreItem;
    private FragmentManager mFragmentManager;
    private Fragment mFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {

        mDrawerLayout = bindViewId(R.id.drawer_layout);
        mNavigationView = bindViewId(R.id.navigation_view);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.syncState();
        mPreItem = mNavigationView.getMenu().getItem(0);
        mPreItem.setChecked(true);
        //initFragment();
        handleNavigationView();

    }

    private void initFragment() {

        mFragment = FragmentManagerWrapper.getInstance().createFragment(HomeFragment.class,true);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.frame_layout_home,mFragment).show(mFragment);
        mFragmentManager.beginTransaction().commit();

    }



    private void handleNavigationView() {

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (mPreItem != null){
                    mPreItem.setChecked(false);
                }
                switch (item.getItemId()){
                    case R.id.navigation_item_video:

                        break;
                    case R.id.navigation_item_setting:

                        break;
                    case R.id.navigation_item_about:
                        
                        break;
                    default:
                        break;
                }
                //mDrawerLayout.closeDrawer(Gravity.LEFT);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                mPreItem = item;
                return false;
            }
        });
    }

    @Override
    protected void initData() {

        setSupportActionBar();
        setToolbarIcon(R.mipmap.toolbar_menu);
        setTitle("首页");


    }




}
