package com.agamidev.newsfeedsapp.Utils;

import android.content.Context;

import com.agamidev.newsfeedsapp.Widget.Toaster;

public class DrawerActions {

    private Context context;
    private Toaster toaster;

    public DrawerActions(Context context){
        this.context = context;
        toaster = new Toaster(context);
    }

    public void goExplore(){
        toaster.makeToast("Explore Selected!");
    }
    public void goLiveChat(){
        toaster.makeToast("Live Chat Selected!");
    }
    public void goGallery(){
        toaster.makeToast("Gallery Selected!");
    }
    public void goWishList(){
        toaster.makeToast("Wish List Selected!");
    }
    public void goE_Magazine(){
        toaster.makeToast("E-Magazine Selected!");
    }

}
