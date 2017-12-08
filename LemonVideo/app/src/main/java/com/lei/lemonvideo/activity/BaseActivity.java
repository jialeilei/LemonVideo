package com.lei.lemonvideo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.lei.lemonvideo.R;

/**
 * Created by lei on 2017/11/4.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private View mView;
    protected Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected <T extends View> T bindViewId(int resId){
        return (T) findViewById(resId);
    }

    protected void setSupportActionBar(){
        mToolbar = bindViewId(R.id.toolbar);
        if (mToolbar != null){
            setSupportActionBar(mToolbar);
        }
    }

    protected void setToolbarIcon(int resId){
        if (mToolbar != null){
            mToolbar.setNavigationIcon(resId);
        }
    }

    protected void getSupportArrowActionBar(boolean isShow){
        getSupportActionBar().setDisplayHomeAsUpEnabled(isShow);
    }

}
