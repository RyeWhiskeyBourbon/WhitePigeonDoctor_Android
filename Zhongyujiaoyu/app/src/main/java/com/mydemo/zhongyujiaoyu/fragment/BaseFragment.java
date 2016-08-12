package com.mydemo.zhongyujiaoyu.fragment;

/**
 * Created by Administrator on 2016/8/1.
 */
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mydemo.zhongyujiaoyu.R;


public class BaseFragment extends Fragment {
    public Toolbar mToolbar;
    public TextView tv_title;
    public ImageView img_back;

    public void toolbar(View view, String title, int id) {
        tv_title = (TextView) view.findViewById(R.id.tv_toolbar);
        tv_title.setText(title);
        tv_title.setVisibility(View.VISIBLE);
        mToolbar = (Toolbar) view.findViewById(id);
        mToolbar.setTitle("");
        mToolbar.setTitleTextColor(Color.WHITE);
        img_back= (ImageView) view.findViewById(R.id.img_back);
        img_back.setVisibility(View.VISIBLE);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.setSupportActionBar(mToolbar);
//        ActionBar actionBar = activity.getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

    }
}