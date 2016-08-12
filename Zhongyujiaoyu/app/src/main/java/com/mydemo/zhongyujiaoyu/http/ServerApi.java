package com.mydemo.zhongyujiaoyu.http;

import com.android.volley.Response;
import com.mydemo.zhongyujiaoyu.AppContext;
import com.mydemo.zhongyujiaoyu.model.Result;
import com.mydemo.zhongyujiaoyu.model.User;
import com.mydemo.zhongyujiaoyu.until.SharedPrefHelper;
import com.mydemo.zhongyujiaoyu.Zhongyu;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kiss on 2016/8/11.
 */
public class ServerApi {
    public static String HOST = "http://api.zhongyuedu.com/bg/";
    private final static String LOGIN = "login.php";//登录
    private final static String REGISTER = "register.php";//注册
    private final static String KS_LIST = "ks_list.php";//科室列表
    private SharedPrefHelper shared = AppContext.getInstance().getSharedPrefHelper();

    /**
     * 用户登录
     *
     * @param username 邮箱
     * @param password 密码
     * @param udid     token
     * @param res      请求成功的监听操作
     * @param reserr   请求失败的监听操作
     */
    public void loginToken(String username, String password,String udid, Response.Listener<Result> res, Response.ErrorListener reserr) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", username);
        map.put("password", password);
        map.put("udid", udid);

        JsonObjectPostRequest<Result> jsonObjectPostRequest = new JsonObjectPostRequest<Result>(HOST + LOGIN,
                Result.class, res, reserr, map);
        Zhongyu.getInstance().addToRequestQueue(jsonObjectPostRequest);
    }

}
