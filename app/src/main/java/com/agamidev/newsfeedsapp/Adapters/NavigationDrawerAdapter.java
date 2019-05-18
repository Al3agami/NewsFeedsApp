package com.agamidev.newsfeedsapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agamidev.newsfeedsapp.Interfaces.DrawerListener;
import com.agamidev.newsfeedsapp.Models.DrawerItemModel;
import com.agamidev.newsfeedsapp.R;

import java.util.ArrayList;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.ViewHolder> {

    private ArrayList<DrawerItemModel> drawerArrayList;
    private Context context;
    private int row_index;
    DrawerListener drawerListener;

    public NavigationDrawerAdapter(Context context, ArrayList<DrawerItemModel> drawerArrayList, DrawerListener drawerListener){
        this.context = context;
        this.drawerArrayList = drawerArrayList;
        this.drawerListener = drawerListener;
        row_index = -1;
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
                row_index = drawerListener.drawerItemClickListener(holder.getAdapterPosition());

            }
        });
        if(row_index == i){
            holder.itemView.setBackground(context.getResources().getDrawable(R.drawable.drawer_card_selected_background));
        }
        else
        {
            holder.itemView.setBackground(context.getResources().getDrawable(R.drawable.drawer_card_default_background));
        }
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
