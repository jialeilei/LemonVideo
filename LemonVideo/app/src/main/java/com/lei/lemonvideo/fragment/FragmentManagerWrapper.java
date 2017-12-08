package com.lei.lemonvideo.fragment;

import android.support.v4.app.Fragment;
import java.util.HashMap;

/**
 * Created by lei on 2017/11/22.
 */
public class FragmentManagerWrapper {
    private static volatile FragmentManagerWrapper mInstance = null;

    public static FragmentManagerWrapper getInstance(){
        if (mInstance == null){
            synchronized (FragmentManagerWrapper.class){
                if (mInstance == null){
                    mInstance = new FragmentManagerWrapper();
                }
            }
        }
        return mInstance;
    }

    private HashMap<String,BaseFragment> mHashMap = new HashMap<>();
    public Fragment createFragment(Class<?> clazz,boolean isObtain) {

        BaseFragment result = null;
        if (mHashMap.containsKey(clazz.getName())){
            try {
                result = (BaseFragment)Class.forName(clazz.getName()).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (isObtain){
            mHashMap.put(clazz.getName(),result);
        }

        return  result;
    }

}
