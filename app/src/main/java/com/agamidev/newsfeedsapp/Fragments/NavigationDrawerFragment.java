package com.agamidev.newsfeedsapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agamidev.newsfeedsapp.Adapters.NavigationDrawerAdapter;
import com.agamidev.newsfeedsapp.Interfaces.NavigationDrawerListener;
import com.agamidev.newsfeedsapp.Models.DrawerItemModel;
import com.agamidev.newsfeedsapp.R;
import com.agamidev.newsfeedsapp.Widget.Toaster;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationDrawerFragment extends Fragment {

    @BindView(R.id.civ_avatar)
    CircleImageView civ_avatar;
    @BindView(R.id.tv_welcome)
    TextView tv_welcome;
    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.iv_go_profile)
    ImageView iv_go_profile;
    @BindView(R.id.rv_drawer)
    RecyclerView rv_drawer;

    NavigationDrawerAdapter drawerAdapter;
    ArrayList<DrawerItemModel> drawerItemsArray;

    Toaster toaster;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.navigation_drawer, container, false);
        ButterKnife.bind(this, view);
        initViews(view);
        setDrawerData();
        return view;
    }

    private void initViews(View v){
        toaster = new Toaster(getActivity());
        drawerItemsArray = new ArrayList<>();

    }

    NavigationDrawerListener navigationDrawerListener = new NavigationDrawerListener() {

        @Override
        public void explore() {
            toaster.makeToast("Explore Selected!");
        }

        @Override
        public void liveChat() {
            toaster.makeToast("Live Chat Selected!");
        }

        @Override
        public void gallery() {
            toaster.makeToast("Gallery Selected!");
        }

        @Override
        public void wishList() {
            toaster.makeToast("Wish List Selected!");
        }

        @Override
        public void eMagazine() {
            toaster.makeToast("E-Magazine Selected!");
        }
    };

    public void setDrawerData() {
        drawerItemsArray = new ArrayList<>();
        drawerItemsArray.add(new DrawerItemModel(R.mipmap.explore, getString(R.string.explore), true));
        drawerItemsArray.add(new DrawerItemModel(R.mipmap.live_chat, getString(R.string.live_chat), false));
        drawerItemsArray.add(new DrawerItemModel(R.mipmap.gallery, getString(R.string.gallery), false));
        drawerItemsArray.add(new DrawerItemModel(R.mipmap.wish_list, getString(R.string.wish_list), false));
        drawerItemsArray.add(new DrawerItemModel(R.mipmap.magazine, getString(R.string.e_magazine), false));


        rv_drawer.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        drawerAdapter = new NavigationDrawerAdapter(getActivity(), drawerItemsArray, navigationDrawerListener);
        rv_drawer.setAdapter(drawerAdapter);
    }


}
