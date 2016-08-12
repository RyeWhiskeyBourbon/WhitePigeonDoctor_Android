package com.mydemo.zhongyujiaoyu;

import android.content.Context;

import com.mydemo.zhongyujiaoyu.http.ServerApi;
import com.mydemo.zhongyujiaoyu.until.SharedPrefHelper;

/**
 * Created by kiss on 2016/8/11.
 */
public class AppContext {
    private Context mContext;
    private ServerApi mServerApi;
    private SharedPrefHelper mPreferences;
    private static AppContext ourInstance = new AppContext();

    public static AppContext getInstance() {
        return ourInstance;
    }

    private AppContext() {

    }

    private AppContext(Context context) {
        mContext = context;
        ourInstance = this;
        mPreferences = SharedPrefHelper.getInstance();
        mPreferences.init(context);
        mServerApi = new ServerApi();
    }

    public static void init(Context context) {
        ourInstance = new AppContext(context);
    }

    public SharedPrefHelper getSharedPrefHelper() {
        return mPreferences;
    }

    public ServerApi getServerApi() {
        return mServerApi;
    }

}
