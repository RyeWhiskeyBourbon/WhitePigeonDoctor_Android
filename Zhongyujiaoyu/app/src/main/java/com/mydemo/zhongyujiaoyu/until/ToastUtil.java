package com.mydemo.zhongyujiaoyu.until;

/**
 * Created by kiss on 2016/3/1.
 */
import android.content.Context;
import android.widget.Toast;


/**
 * Toast util class.
 *
 * @author <a href="http://www.linuxidc.com">http://www.linuxidc.com</a>
 * @version 2011/11/30
 *
 */
public class ToastUtil{

    private static String oldMsg;
    protected static Toast toast   = null;
    private static long oneTime=0;
    private static long twoTime=0;


    public static void showToast(Context context, String s){
        if(toast==null){
            toast =Toast.makeText(context, s, Toast.LENGTH_SHORT);
            toast.show();
            oneTime=System.currentTimeMillis();
        }else{
            twoTime=System.currentTimeMillis();
            if(s.equals(oldMsg)){
                if(twoTime-oneTime>Toast.LENGTH_SHORT){
                    toast.show();
                }
            }else{
                oldMsg = s;
                toast.setText(s);
                toast.show();
            }
        }
        oneTime=twoTime;
    }


    public static void showToast(Context context, int resId){
        showToast(context, context.getString(resId));
    }

}