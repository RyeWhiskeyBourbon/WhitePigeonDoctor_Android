package com.mydemo.zhongyujiaoyu.until;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;


public class SharedPrefHelper {

    /**
     * 服务器的Cookies
     */
    public static final String PREF_KEY_COOKIES = "app_COOKIE";

    /**
     * 用户账号
     */
    public static final String PREF_KEY_EMAIL = "email";

    /**
     * 本地保存的加密密码
     */
    public static final String PREF_KEY_PWD = "pwd";
    public static final String PREF_KEY_ID = "id";
    public static final String PREF_KEY_LOGINID = "login_id";//判断上次的用户状态
    public static final String PREF_KEEY_LOGINED = "0";//登出状态
    public static final String PREF_KEY_LOGINING = "1";//登陆状态
    public static final String PREF_KEY_SEARCHFRIEND = "sf";//搜索时输入的用户

    /**
     * 当前用户的ID
     */
    public static final String PREF_KEY_UUID = "userid";
    public static final String PREF_KEY_USERID = "id";

    /**
     * 最新的VersionCode
     */
    public static final String PREF_KEY_APKCODE = "apkCode";

    /**
     * 被忽略的VersionCode
     */
    public static final String PREF_KEY_APKCODEIGN = "apkCodeIgnore";

    /**
     * 新的好友请求
     */
    public static final String PREF_KEY_NEWFRIEND = "friend";
    public static final String PREF_KEY_TIME = "PREF_KEY_TIME";
    public static final String PREF_KEY_DISTANCE = "PREF_KEY_DISTANCE";
    public static final String PREF_KEY_SPEED = "PREF_KEY_SPEED";
    public static final String PREF_KEY_CALORIES = "PREF_KEY_CALORIES";
    public static final String PREF_KEY_GOODSLISTID = "PREF_KEY_GOODSLISTID";
    public static final String PREF_KEY_GOODSLISTNAME = "PREF_KEY_GOODSLISTNAME";
    public static final String PREF_KEY_GOODSSTATUS = "PREF_KEY_GOODSSTATUS";

//    public static final String PREF_KEY_NAME = "name";
//    public static final String PREF_KEY_SEX = "sex";
//    public static final String PREF_KEY_TOKEN = "token";
//    public static final String PREF_KEY_BIRTHDAY = "birthday";
//    public static final String PREF_KEY_PORTRAIT= "portrait";
//    public static final String PREF_KEY_HEIGHT = "height";
//    public static final String PREF_KEY_WEIGHT= "weight";
//    public static final String PREF_KEY_REMARK = "remark";
//    public static final String PREF_KEY_SECRET = "secret";

    private static SharedPrefHelper ourInstance = new SharedPrefHelper();
    private static SharedPreferences sharedPre;

    public static SharedPrefHelper getInstance() {
        return ourInstance;
    }

    private SharedPrefHelper() {
    }

    public static void init(Context context) {
        sharedPre = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 获取服务器Cookies
     * @return
     */
    public String getCookies() {
        return sharedPre.getString(PREF_KEY_COOKIES, null);
    }

    /**
     * 保存Cookies
     * @param value
     */
    public static void setCookies(String value) {
        SharedPreferences.Editor localEditor = sharedPre.edit();
        localEditor.putString(PREF_KEY_COOKIES, value);
        localEditor.commit();
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor localEditor = sharedPre.edit();
        localEditor.putInt(key, value);
        localEditor.apply();
    }

    public int getInt(String paramString) {
        return sharedPre.getInt(paramString, -1);
    }

    public int getInt(String paramString, int defVal) {
        return sharedPre.getInt(paramString, defVal);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor localEditor = sharedPre.edit();
        localEditor.putString(key, value);
        localEditor.apply();
    }

    public boolean commitString(String key, String value) {
        SharedPreferences.Editor localEditor = sharedPre.edit();
        localEditor.putString(key, value);
        return localEditor.commit();
    }

    public String getString(String key) {
        return sharedPre.getString(key, "");
    }

    public void remove(String key) {
        SharedPreferences.Editor localEditor = sharedPre.edit();
        localEditor.remove(key);
        localEditor.apply();
    }

    /**
     * desc:保存对象
     *
     * @param context
     * @param key
     * @param obj     要保存的对象，只能保存实现了serializable的对象
     *                modified:
     */
    public void saveObject(Context context, String key, Object obj) {
        try {
            // 保存对象
            SharedPreferences.Editor sharedata = context.getSharedPreferences(SharedPrefHelper.PREF_KEY_ID, 0).edit();
            //先将序列化结果写到byte缓存中，其实就分配一个内存空间
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bos);
            //将对象序列化写入byte缓存
            os.writeObject(obj);
            //将序列化的数据转为16进制保存
            String bytesToHexString = bytesToHexString(bos.toByteArray());
            //保存该16进制数组
            sharedata.putString(key, bytesToHexString);
            sharedata.apply();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("", "保存obj失败,没有序列化对象");
        }
    }

    /**
     * desc:将数组转为16进制
     *
     * @param bArray
     * @return modified:
     */
    public static String bytesToHexString(byte[] bArray) {
        if (bArray == null) {
            return null;
        }
        if (bArray.length == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * desc:获取保存的Object对象
     *
     * @param context
     * @param key
     * @return modified:
     */
    public Object readObject(Context context, String key) {
        try {
            SharedPreferences sharedata = context.getSharedPreferences(SharedPrefHelper.PREF_KEY_ID, 0);
            if (sharedata.contains(key)) {
                String string = sharedata.getString(key, "");
                if (TextUtils.isEmpty(string)) {
                    return null;
                } else {
                    //将16进制的数据转为数组，准备反序列化
                    byte[] stringToBytes = StringToBytes(string);
                    ByteArrayInputStream bis = new ByteArrayInputStream(stringToBytes);
                    ObjectInputStream is = new ObjectInputStream(bis);
                    //返回反序列化得到的对象
                    Object readObject = is.readObject();
                    return readObject;
                }
            }
        } catch (StreamCorruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //所有异常返回null
        return null;

    }

    /**
     * desc:将16进制的数据转为数组
     * <p>创建人：聂旭阳 , 2014-5-25 上午11:08:33</p>
     *
     * @param data
     * @return modified:
     */
    public static byte[] StringToBytes(String data) {
        String hexString = data.toUpperCase().trim();
        if (hexString.length() % 2 != 0) {
            return null;
        }
        byte[] retData = new byte[hexString.length() / 2];
        for (int i = 0; i < hexString.length(); i++) {
            int int_ch;  // 两位16进制数转化后的10进制数
            char hex_char1 = hexString.charAt(i); ////两位16进制数中的第一位(高位*16)
            int int_ch1;
            if (hex_char1 >= '0' && hex_char1 <= '9')
                int_ch1 = (hex_char1 - 48) * 16;   //// 0 的Ascll - 48
            else if (hex_char1 >= 'A' && hex_char1 <= 'F')
                int_ch1 = (hex_char1 - 55) * 16; //// A 的Ascll - 65
            else
                return null;
            i++;
            char hex_char2 = hexString.charAt(i); ///两位16进制数中的第二位(低位)
            int int_ch2;
            if (hex_char2 >= '0' && hex_char2 <= '9')
                int_ch2 = (hex_char2 - 48); //// 0 的Ascll - 48
            else if (hex_char2 >= 'A' && hex_char2 <= 'F')
                int_ch2 = hex_char2 - 55; //// A 的Ascll - 65
            else
                return null;
            int_ch = int_ch1 + int_ch2;
            retData[i / 2] = (byte) int_ch;//将转化后的数放入Byte里
        }
        return retData;
    }
}
