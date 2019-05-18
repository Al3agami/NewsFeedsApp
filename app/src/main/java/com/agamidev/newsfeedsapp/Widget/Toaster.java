package com.agamidev.newsfeedsapp.Widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.agamidev.newsfeedsapp.R;


public class Toaster extends Toast {
    Context context;

    public Toaster(Context context) {
        super(context);
        this.context = context;
    }


    public void makeToast(String message) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.toast_custom_layout, null);
        TextView tv_toast = (TextView) view.findViewById(R.id.msg);
        Toast toast = new Toast(context);
        tv_toast.setText(message);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
