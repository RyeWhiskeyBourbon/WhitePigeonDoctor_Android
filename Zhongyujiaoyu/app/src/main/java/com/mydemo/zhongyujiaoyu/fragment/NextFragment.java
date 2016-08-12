package com.mydemo.zhongyujiaoyu.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.mydemo.zhongyujiaoyu.AppContext;
import com.mydemo.zhongyujiaoyu.model.Result;
import com.mydemo.zhongyujiaoyu.R;

/**
 * Created by kiss on 2016/8/11.
 */
public class NextFragment extends Fragment{
    private WaitingDialogFragment mDialog = null;
    String szImei;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blind,container,false);
       // mDialog = WaitingDialogFragment.newInstance(null, getString(R.string.dialog_wait), true);
        TelephonyManager TelephonyMgr = (TelephonyManager)getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        szImei = TelephonyMgr.getDeviceId();
        Login();
        return view;
    }

    private void Login() {
        Response.Listener<Result> listener=new Response.Listener<Result>() {
            @Override
            public void onResponse(Result response) {
                Log.e("suc", String.valueOf(response.getResultscode()));
                Log.e("suc", String.valueOf(response.getResultscode()));
                Log.e("suc", String.valueOf(response.getResultscode()));
            }
        };
        AppContext.getInstance().getServerApi().loginToken("18562599337","qwertyu", szImei,listener,errorListener);
    }

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Activity activity = getActivity();
            if (activity != null && isAdded()) {
                Toast.makeText(getActivity(), getActivity().getString(R.string.http_error), Toast.LENGTH_LONG).show();
                Log.e("volley", volleyError.toString());
            }
        }
    };

    /**
     * 显示载入框
     */
    private void showDialog() {
        if (mDialog != null) {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            mDialog.show(fm, null);
        }
    }

    /**
     * 关闭载入框
     */
    private void dismissDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
