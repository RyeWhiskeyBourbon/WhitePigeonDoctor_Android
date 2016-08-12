package com.mydemo.zhongyujiaoyu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mydemo.zhongyujiaoyu.activity.NextActivity;
import com.mydemo.zhongyujiaoyu.R;
import com.mydemo.zhongyujiaoyu.until.MyUtil;

/**
 * Created by kiss on 2016/8/11.
 */
public class InformationFragment extends Fragment {
    private Button btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgent_home, container, false);
        MyUtil.setToolbar(view, getString(R.string.information));
        btn = (Button) view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), NextActivity.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }


}
