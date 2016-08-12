package com.mydemo.zhongyujiaoyu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.mydemo.zhongyujiaoyu.model.TabEntity;
import com.mydemo.zhongyujiaoyu.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {

    private String TAG = "MainActivity";

    private Bundle mSavedBundle;

    private FragmentTabHost mTabHost;

    private LayoutInflater layoutInflater;

    private FragmentManager mFragmentManager;

    private List<TabEntity> mTabs;

    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        super.onCreate(savedInstanceState);
        mSavedBundle = savedInstanceState;
        setContentView(R.layout.activity_main);
        layoutInflater = LayoutInflater.from(this);
        mFragmentManager = getSupportFragmentManager();
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        mTabHost.setOnTabChangedListener(this);
        mTabHost.setup(this, mFragmentManager, R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);

        mTabs = new ArrayList<TabEntity>();
        mTabs.add(TabEntity.one);
        mTabs.add(TabEntity.two);
        mTabs.add(TabEntity.three);
        mTabs.add(TabEntity.four);

        initView();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, "onNewIntent");
        super.onNewIntent(intent);
        setIntent(intent);
    }

    private void initView() {

        int count = mTabs.size();
        for (int i = 0; i < count; i++) {
            TabEntity tab = mTabs.get(i);
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(String.valueOf(tab.getStringId())).
                    setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, tab.getClss(), null);
            // mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
        }
        mTabHost.setCurrentTab(0);


    }

    private View getTabItemView(int index) {
        TabEntity tab = mTabs.get(index);
        View view = layoutInflater.inflate(R.layout.activity_main_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(tab.getImageId());
//        TextView textView = (TextView) view.findViewById(R.id.textview);
//        textView.setText(tab.getStringId());
        return view;
    }



    public void displayNavBottom(boolean display) {
        if (display) {
            mTabHost.setVisibility(View.VISIBLE);
        } else {
            mTabHost.setVisibility(View.GONE);
        }
    }


    @Override
    public void onTabChanged(String tabId) {

    }
}
