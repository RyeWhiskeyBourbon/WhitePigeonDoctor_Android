package com.mydemo.zhongyujiaoyu.activity;

import android.support.v4.app.Fragment;

import com.mydemo.zhongyujiaoyu.fragment.NextFragment;

/**
 * Created by kiss on 2016/8/11.
 */
public class NextActivity extends BaseFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new NextFragment();
    }
}
