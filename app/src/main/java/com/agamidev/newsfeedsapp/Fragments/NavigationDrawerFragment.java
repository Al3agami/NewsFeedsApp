package com.agamidev.newsfeedsapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agamidev.newsfeedsapp.Activities.MainActivity;
import com.agamidev.newsfeedsapp.Adapters.NavigationDrawerAdapter;
import com.agamidev.newsfeedsapp.Interfaces.DrawerListener;
import com.agamidev.newsfeedsapp.Models.DrawerItemModel;
import com.agamidev.newsfeedsapp.R;
import com.agamidev.newsfeedsapp.Utils.DrawerActions;
import com.agamidev.newsfeedsapp.Widget.Toaster;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationDrawerFragment extends Fragment implements DrawerListener {

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

    int row_index;
    DrawerActions drawerActions;

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
        drawerActions = new DrawerActions(getActivity());

    }

    public void setDrawerData() {
        drawerItemsArray = new ArrayList<>();
        drawerItemsArray.add(new DrawerItemModel(R.mipmap.explore, getString(R.string.explore), true));
        drawerItemsArray.add(new DrawerItemModel(R.mipmap.live_chat, getString(R.string.live_chat), false));
        drawerItemsArray.add(new DrawerItemModel(R.mipmap.gallery, getString(R.string.gallery), false));
        drawerItemsArray.add(new DrawerItemModel(R.mipmap.wish_list, getString(R.string.wish_list), false));
        drawerItemsArray.add(new DrawerItemModel(R.mipmap.magazine, getString(R.string.e_magazine), false));


        row_index = -1;
        rv_drawer.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        drawerAdapter = new NavigationDrawerAdapter(getActivity(), drawerItemsArray,this);
        rv_drawer.setAdapter(drawerAdapter);
    }


    @Override
    public int drawerItemClickListener(int itemPosition) {
        MainActivity.drawerLayout.closeDrawer(Gravity.START);
        row_index = itemPosition;
        drawerAdapter.notifyDataSetChanged();
        switch(itemPosition)
        {
            case 0:
                drawerActions.goExplore();
                break;
            case 1:
                drawerActions.goLiveChat();
                break;
            case 2:
                drawerActions.goGallery();
                break;
            case 3:
                drawerActions.goWishList();
                break;
            case 4:
                drawerActions.goE_Magazine();
                break;
            default:
                drawerActions.goExplore();
        }
        return row_index;
    }
}
