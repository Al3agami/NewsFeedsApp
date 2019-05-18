package com.agamidev.newsfeedsapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.agamidev.newsfeedsapp.Models.NewsModel;
import com.agamidev.newsfeedsapp.Interfaces.RecyclerItemClickListener;
import com.agamidev.newsfeedsapp.Utils.NewsDate;
import com.agamidev.newsfeedsapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>  implements Filterable {

    private Context context;
    private ArrayList<NewsModel> newsArrayList;
    private ArrayList<NewsModel> newsListFiltered;
    private ArrayList<NewsModel> backupArray;
    private RecyclerItemClickListener recyclerItemClickListener;


    public NewsAdapter(Context context, ArrayList<NewsModel> newsArrayList, RecyclerItemClickListener recyclerItemClickListener){
        this.context = context;
        this.newsArrayList = newsArrayList;
        this.backupArray = new ArrayList<>();
        backupArray.addAll(newsArrayList);
        this.newsListFiltered = newsArrayList;
        this.recyclerItemClickListener = recyclerItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_card, parent, false);

        return new NewsAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final NewsModel n = newsListFiltered.get(position);

        Glide.with(context)
                .load(n.getUrlToImage())
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)
                        .fitCenter())
                .into(holder.iv_image);

        holder.tv_title.setText(n.getTitle());
        holder.tv_author.setText("By " + n.getAuthor());
        holder.tv_date.setText(NewsDate.getFinalDate(n));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerItemClickListener.onItemClick(newsListFiltered.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    newsListFiltered = newsArrayList;
                } else {
                    ArrayList<NewsModel> filteredList = new ArrayList<>();
                    for (NewsModel row : newsArrayList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())
                                || row.getAuthor().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    newsListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = newsListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                newsListFiltered = (ArrayList<NewsModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_image;
        TextView tv_title;
        TextView tv_author;
        TextView tv_date;


        public MyViewHolder(View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_author = itemView.findViewById(R.id.tv_author);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
    }
}
