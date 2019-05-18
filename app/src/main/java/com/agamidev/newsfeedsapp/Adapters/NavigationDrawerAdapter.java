package com.agamidev.newsfeedsapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agamidev.newsfeedsapp.Models.DrawerItemModel;
import com.agamidev.newsfeedsapp.Interfaces.NavigationDrawerListener;
import com.agamidev.newsfeedsapp.R;
import com.agamidev.newsfeedsapp.Activities.MainActivity;

import java.util.ArrayList;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.ViewHolder> {

    private ArrayList<DrawerItemModel> drawerArrayList;
    private NavigationDrawerListener navigationDrawerListener;
    private Context context;

    public NavigationDrawerAdapter(Context context, ArrayList<DrawerItemModel> drawerArrayList, NavigationDrawerListener navigationDrawerListener){
        this.context = context;
        this.drawerArrayList = drawerArrayList;
        this.navigationDrawerListener = navigationDrawerListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.navigation_drawer_card, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        DrawerItemModel model =  drawerArrayList.get(i);
        holder.tv_title.setText(model.getTitle());
        holder.iv_icon.setImageResource(model.getImage_id());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.drawerLayout.closeDrawer(Gravity.START);
                switch(holder.getAdapterPosition())
                {
                    case 0:
                        navigationDrawerListener.explore();
                        holder.itemView.setBackground(context.getResources().getDrawable(R.drawable.drawer_card_background));
                        break;
                    case 1:
                        navigationDrawerListener.liveChat();
                        break;
                    case 2:
                        navigationDrawerListener.gallery();
                        break;
                    case 3:
                        navigationDrawerListener.wishList();
                        break;
                    case 4:
                        navigationDrawerListener.eMagazine();
                        break;
                    default:
                        navigationDrawerListener.explore();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return drawerArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_icon;
        TextView tv_title;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            tv_title = itemView.findViewById(R.id.tv_title);

        }
    }
}
