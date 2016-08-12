package com.mydemo.zhongyujiaoyu.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.mydemo.zhongyujiaoyu.R;
import com.mydemo.zhongyujiaoyu.Zhongyu;
import com.mydemo.zhongyujiaoyu.listener.OnDialogClickListener;

import java.util.Map;

/**
 * Created by kiss on 2016/6/27.
 * 默认有透明背景
 */
public class LoadingView extends LinearLayout {
    private TextView textView;
    private Activity activity;
    //private static LoadingView loadingView;
    private LinearLayout body;
    private Boolean cancel;
    private Context context;
    private OnDialogClickListener onDialogClickListener;
    private Map<String, String> mapTag;
    private String stringTag;
    private LinearLayout ll_main;

    public LoadingView(Context context) {
        super(context);
        this.setOrientation(VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.loadingview, this);
        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        textView = (TextView) findViewById(R.id.message);
        body = (LinearLayout) findViewById(R.id.body);
        body.setClickable(true);
        //占据焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        activity = (Activity) context;
    }


    /**
     * 动态创建view
     *
     * @param relativeLayout 父控件
     * @param context        context
     * @param msg            文本显示
     * @param cancel         点击是否消失,不消失时，监听无效
     */
    public LoadingView(RelativeLayout relativeLayout, Context context, String msg, Boolean cancel) {
        super(context);
        this.setOrientation(VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.loadingview, this);
        RelativeLayout.LayoutParams ind_params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
        );

        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        textView = (TextView) findViewById(R.id.message);
        body = (LinearLayout) findViewById(R.id.body);
        body.setClickable(true);
        //占据焦点
        activity = (Activity) context;
        setLayoutParams(ind_params);
        relativeLayout.addView(this);
        if (msg == null || msg.equals("")) {
            textView.setVisibility(GONE);
        } else {
            textView.setText(msg);
        }
        setCancel(cancel);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setClickable(true);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.loadingview, this);
        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        textView = (TextView) findViewById(R.id.message);
        body = (LinearLayout) findViewById(R.id.body);
        body.setClickable(true);
        //占据焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        activity = (Activity) context;
        setClickable(true);
    }

//    /**
//     * 动态创建view
//     *
//     * @param relativeLayout 父控件
//     * @param context        context
//     * @param msg            文本显示
//     * @param cancel         点击是否消失,不消失时，监听无效
//     */
//    public static LoadingView createView(RelativeLayout relativeLayout, Context context, String msg, Boolean cancel) {
//        RelativeLayout.LayoutParams ind_params = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
//        );
//        loadingView = new LoadingView(context);
//        loadingView.setLayoutParams(ind_params);
//        relativeLayout.addView(loadingView);
//        if(msg==null||msg.equals("")){
//            textView.setVisibility(GONE);
//        }else {
//            textView.setText(msg);
//        }
//        loadingView.setCancel(cancel);
//        return loadingView;
//    }

    /**
     * 动态创建view
     *
     * @param relativeLayout 父控件
     * @param context        context
     * @param msg            文本显示
     * @param cancel         点击是否消失,不消失时，监听无效
     */
    public LoadingView(RelativeLayout relativeLayout, Context context, String msg, Boolean cancel, OnDialogClickListener listener) {
        super(context);
        this.setOrientation(VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.loadingview, this);
        RelativeLayout.LayoutParams ind_params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
        );
        setLayoutParams(ind_params);
        relativeLayout.addView(this);
        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        textView = (TextView) findViewById(R.id.message);
        body = (LinearLayout) findViewById(R.id.body);
        body.setClickable(true);
        activity = (Activity) context;
        onDialogClickListener = listener;
        if (msg == null || msg.equals("")) {
            textView.setVisibility(GONE);
        } else {
            textView.setText(msg);
        }
        setCancel(cancel);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setClickable(true);
    }


    /**
     * 动态创建view
     *
     * @param relativeLayout 父控件
     * @param context        context
     * @param msg            文本显示
     * @param cancel         点击是否消失,不消失时，监听无效
     * @param isBg           有无背景
     */
    public LoadingView(RelativeLayout relativeLayout, Context context, String msg, Boolean cancel, Boolean isBg) {
        super(context);
        this.setOrientation(VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.loadingview, this);
        RelativeLayout.LayoutParams ind_params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
        );
        setLayoutParams(ind_params);
        relativeLayout.addView(this);
        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        textView = (TextView) findViewById(R.id.message);
        body = (LinearLayout) findViewById(R.id.body);
        body.setClickable(true);
        if (msg == null || msg.equals("")) {
            textView.setVisibility(GONE);
        } else {
            textView.setText(msg);
        }
        setCancel(cancel);
        setFocusable(true);
        setFocusableInTouchMode(true);
        isBackground(isBg);
        setClickable(true);
    }

    /**
     * 动态创建view
     *
     * @param relativeLayout 父控件
     * @param context        context
     * @param msg            文本显示
     * @param cancel         点击是否消失,不消失时，监听无效
     * @param isBg           有无背景
     * @param listener       设置view消失的监听
     */
    public LoadingView(RelativeLayout relativeLayout, Context context, String msg, Boolean cancel, Boolean isBg, OnDialogClickListener listener) {
        super(context);
        this.setOrientation(VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.loadingview, this);
        RelativeLayout.LayoutParams ind_params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT
        );
        setLayoutParams(ind_params);
        ll_main = (LinearLayout) findViewById(R.id.ll_main);
        textView = (TextView) findViewById(R.id.message);
        body = (LinearLayout) findViewById(R.id.body);
        body.setClickable(true);
        relativeLayout.addView(this);
        onDialogClickListener = listener;
        if (msg == null || msg.equals("")) {
            textView.setVisibility(GONE);
        } else {
            textView.setText(msg);
        }
        setCancel(cancel);
        setFocusable(true);
        setFocusableInTouchMode(true);
        isBackground(isBg);
        setClickable(true);
    }

    //设置显示文字
    public void setText(String text) {
        textView.setText(text);
    }

    //隐藏LoadingView
    public void dismiss() {
        setVisibility(GONE);
        return;
    }

    //隐藏LoadingView,并取消网络请求
    public void dismiss(Map<String, String> tag) {
        setVisibility(GONE);
        for (String tags : tag.values()) {
            Zhongyu.getInstance().cancelPendingRequests(tags);
        }
        return;
    }

    //隐藏LoadingView,并取消网络请求
    public void dismiss(String tag) {
        setVisibility(GONE);
        Zhongyu.getInstance().cancelPendingRequests(tag);
        return;
    }

    //显示LoadingView
    public void show() {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        setVisibility(VISIBLE);
    }

    //设置有无透明背景，false时无背景
    public void isBackground(Boolean b) {
        if (b == false) {
            ll_main.setBackgroundColor(Color.argb(00, 00, 00, 00));
            body.setBackgroundColor(Color.argb(00, 00, 00, 00));
        }
    }

    //设置监听，ture时点击外部，LoadingView消失
    public void setCancel(Boolean b) {
        if (b == true) {
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (onDialogClickListener != null) {
                        onDialogClickListener.onclick();
                    }
                }
            });
        }
    }

    //设置view消失的监听
    public void setListener(OnDialogClickListener listener) {
        onDialogClickListener = listener;
    }

    public void setMapTag(Map<String, String> tag) {
        this.mapTag = tag;
    }

    public void setStringTag(String tag) {
        this.stringTag = tag;
    }

    //设置返回键是否可点击，true时可点击
    public void setMyOnKeyListener(final Boolean isOnTouch) {
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onclick();
                }
                if (isOnTouch == true) {
                    if (mapTag != null && !mapTag.isEmpty()) {
                        dismiss(mapTag);
                    } else if (stringTag != null && stringTag != "") {
                        dismiss(stringTag);
                    } else {
                        dismiss();
                    }
                    if (event.getAction() == KeyEvent.ACTION_DOWN) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }

            }
        });
    }

}
