package com.mydemo.zhongyujiaoyu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mydemo.zhongyujiaoyu.R;
import com.mydemo.zhongyujiaoyu.until.MyUtil;

/**
 * Created by Administrator on 2016/7/29.
 */
public class MeFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blind,container,false);
        MyUtil.setToolbar(view, getString(R.string.me));
        return view;
    }
}
