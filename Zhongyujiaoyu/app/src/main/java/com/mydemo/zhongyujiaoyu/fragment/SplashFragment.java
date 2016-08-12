package com.mydemo.zhongyujiaoyu.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mydemo.zhongyujiaoyu.R;
import com.mydemo.zhongyujiaoyu.activity.MainActivity;
import com.mydemo.zhongyujiaoyu.constant.Constant;

/**
 * Created by kiss on 2016/8/12.
 */
public class SplashFragment extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_splash,container,false);
        if (Build.VERSION.SDK_INT >= 23) {
            checkPermission();
        } else {
            Intent intent=new Intent(getActivity(), MainActivity.class);
            getActivity().startActivity(intent);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.d("SplashFragment", "onRequestPermissioinResult");
        switch (requestCode) {
            //请求位置权限
            case Constant.MY_READ_PHONE_STATE:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    new AlertDialog.Builder(getActivity()).setMessage(getString(R.string.phone_state_permission_not_granted)).show();
                }
                getActivity().finish();
                break;
            default:
                break;
        }
    }

    /**
     * 当系统版本大于等于23时，需要申请权限
     */
    private void checkPermission() {
        Log.d("SplashFragment", "checkPermission");
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //当用户拒绝获取的权限申请时，要显示的说明信息
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(getActivity()).setMessage(getString(R.string.phone_state_permission_not_granted))
                        .setPositiveButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.READ_PHONE_STATE},
                                        Constant.MY_READ_PHONE_STATE);

                                Log.e("1","1");
                                Intent intent=new Intent(getActivity(), MainActivity.class);
                                getActivity().startActivity(intent);
                            }
                        })
                        .setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                mTabs.clear();
//                                mTabs.add(TabEntity.mTabMe);
//                                mTabs.add(TabEntity.mMarathon);
//                                mTabs.add(TabEntity.mTabConversation);
//                                mTabs.add(TabEntity.mTabMy);
//                                initView();
                                Log.e("2","2");
                                getActivity().finish();
                            }
                        }).show();
            } else {
                Log.d("SplashFragment", "requestPermissions");
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        Constant.MY_READ_PHONE_STATE);
            }
        } else {
//            initView();
            Log.e("3","3");
            Intent intent=new Intent(getActivity(), MainActivity.class);
            getActivity().startActivity(intent);
        }
    }
}
