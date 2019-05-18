package com.agamidev.newsfeedsapp.Widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;


public class CustomProgressDialog extends ProgressDialog {
    private static CustomProgressDialog mProgress;

    public CustomProgressDialog(Context context, String msg) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setMessage(msg);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public static void onStart(Context context, String msg){
        mProgress = new CustomProgressDialog(context,msg);
        mProgress.show();

    }
    public static void onFinish(){
        if (mProgress!=null){
            mProgress.dismiss();
        }

    }


}
