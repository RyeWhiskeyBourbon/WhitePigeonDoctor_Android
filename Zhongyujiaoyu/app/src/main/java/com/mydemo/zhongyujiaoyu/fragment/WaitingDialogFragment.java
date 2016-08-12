package com.mydemo.zhongyujiaoyu.fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;

/**
 * 用于耗时操作的提示
 */
public class WaitingDialogFragment extends DialogFragment {
    private final static String TAG = "WaitingDialogFragment";

    public static final String EXTRA_TT = "WaitingDialogFragment.TITLE";
    public static final String EXTRA_MSG = "WaitingDialogFragment.MESSAGE";
    public static final String EXTRA_CANCEL = "WaitingDialogFragment.CANCEL";

    private String mTitle, mMsg;
    private int a = 0;
    private static boolean cancel;

    public static WaitingDialogFragment newInstance(String title, String msg, boolean cancelable) {
        Bundle args = new Bundle();
        args.putString(EXTRA_TT, title);
        args.putString(EXTRA_MSG, msg);

        WaitingDialogFragment fragment = new WaitingDialogFragment();
        fragment.setArguments(args);
        fragment.setCancelable(cancelable);
        cancel=cancelable;
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mTitle = getArguments().getString(EXTRA_TT);
        mMsg = getArguments().getString(EXTRA_MSG);

        ProgressDialog dialog = new ProgressDialog(getActivity());
        if (!TextUtils.isEmpty(mTitle))
            dialog.setTitle(mTitle);
        dialog.setMessage(mMsg);

//        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                a++;
//                if (cancel==false) {
//                    if (a == 3) {
//                        getActivity().finish();
//                    }
//                    ToastUtil.showToast(getActivity(), getString(R.string.exit_tips));
//                }
//                return false;
//            }
//        });
        return dialog;
    }
}
