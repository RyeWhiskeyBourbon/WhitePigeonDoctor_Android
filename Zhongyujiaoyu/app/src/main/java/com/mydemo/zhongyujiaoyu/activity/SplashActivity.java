package com.mydemo.zhongyujiaoyu.activity;

import android.support.v4.app.Fragment;

import com.mydemo.zhongyujiaoyu.fragment.SplashFragment;

/**
 * Created by kiss on 2016/8/12.
 */
public class SplashActivity extends BaseFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new SplashFragment();
    }
}
