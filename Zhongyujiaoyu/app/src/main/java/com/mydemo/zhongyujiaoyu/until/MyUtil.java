package com.mydemo.zhongyujiaoyu.until;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mydemo.zhongyujiaoyu.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by Administrator on 2016/8/1.
 */
public class MyUtil {
    public static int getWindowsWidth(Context context){
        Activity activity= (Activity) context;
        WindowManager wm = activity.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        Log.e("width", String.valueOf(width));
        return width;
    }

    public static void setToolbar(View view,String string){
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);
        TextView tv_toolbar = (TextView) view.findViewById(R.id.tv_toolbar);
        tv_toolbar.setVisibility(View.VISIBLE);
        tv_toolbar.setText(string);
    }

    public static void setScaleType(Context context,ImageView imageView,int id){
        int width= getWindowsWidth(context);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),id);
        int bwidth = bitmap.getWidth();
        int bHeight = bitmap.getHeight();

        Log.e("====", bwidth + " " + bHeight + " " + width);
        int height = width * bHeight / bwidth;
        ViewGroup.LayoutParams para = imageView.getLayoutParams();
        para.height = height;
        imageView.setLayoutParams(para);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static void setLayoutHight(Context context,LinearLayout layout,int id){
        int width= getWindowsWidth(context);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        int bwidth = bitmap.getWidth();
        int bHeight = bitmap.getHeight();

        int height=(width-dip2px(context,50))/3*bHeight/bwidth;
        ViewGroup.LayoutParams para = layout.getLayoutParams();
        para.height = height;
        layout.setLayoutParams(para);
    }


}
