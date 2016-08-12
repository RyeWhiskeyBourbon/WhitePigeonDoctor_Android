package com.mydemo.zhongyujiaoyu.http;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.mydemo.zhongyujiaoyu.AppContext;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonObjectPostRequest<T> extends Request<T> {
    private final static String TAG = "JsonObjectPostRequest";
    public final static int OPT_FRIENDS = 1;

    private Map<String, String> mMap;
    private Response.Listener<T> mListener;
    public String cookieFromResponse;
    private String mHeader;
    private Class<T> mClass;
    private Gson mGson;
    private Map<String, String> sendHeader = new HashMap<String, String>(1);

    public JsonObjectPostRequest(String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener, Map map) {
        super(Method.POST, url, errorListener);
        mGson = new Gson();
        mClass = clazz;
        mListener = listener;
        mMap = map;
    }

    //当http请求是post时，则需要该使用该函数设置往里面添加的键值对
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mMap;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        Map<String, String> params = getParams();
        if (params != null && params.size() > 0) {
            return encodeParameters(params, getParamsEncoding());
        }
        return null;
    }

    /**
     * Converts <code>params</code> into an application/x-www-form-urlencoded encoded string.
     */
    private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            int idx = -1;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                idx = entry.getKey().indexOf('[');
                if (idx == -1)
                    encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                else {
                    encodedParams.append(URLEncoder.encode(entry.getKey().substring(0, idx), paramsEncoding));
                }
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
//            Log.i(TAG, encodedParams.toString());
            return encodedParams.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            mHeader = response.headers.toString();

            //使用正则表达式从reponse的头中提取cookie内容的子串
            Pattern pattern = Pattern.compile("Set-Cookie.*?;");
            Matcher m = pattern.matcher(mHeader);
            if (m.find()) {
                cookieFromResponse = m.group();
                Log.w("LOG", "cookie from server " + cookieFromResponse);

                //去掉cookie末尾的分号
                cookieFromResponse = cookieFromResponse.substring(11, cookieFromResponse.length() - 1);

                //保存Cookies
                AppContext.getInstance().getSharedPrefHelper().setCookies(cookieFromResponse);
            }

            //jsonObject会被deliverResponse递交，调用请求时则能在onResponse中得到
            JSONObject jsonObject = new JSONObject(jsonString);
            Log.i(TAG, jsonString);
            return Response.success(mGson.fromJson(jsonString, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return sendHeader;
    }

    /**
     * 设置cookies到http请求
     *
     * @param cookie
     */
    public void setSendCookie(String cookie) {
        sendHeader.put("Cookie", cookie);
    }


}

