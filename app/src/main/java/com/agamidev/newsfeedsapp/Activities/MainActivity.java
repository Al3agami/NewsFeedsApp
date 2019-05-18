package com.agamidev.newsfeedsapp.Activities;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.agamidev.newsfeedsapp.Activities.Main_Activity.GetNewsInteractorImpl;
import com.agamidev.newsfeedsapp.Activities.Main_Activity.MainContract;
import com.agamidev.newsfeedsapp.Activities.Main_Activity.MainPresenterImpl;
import com.agamidev.newsfeedsapp.Interfaces.RecyclerItemClickListener;
import com.agamidev.newsfeedsapp.Adapters.NewsAdapter;
import com.agamidev.newsfeedsapp.Models.NewsModel;
import com.agamidev.newsfeedsapp.Fragments.NavigationDrawerFragment;
import com.agamidev.newsfeedsapp.R;
import com.agamidev.newsfeedsapp.Widget.Toaster;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {



    //drawer
    public static DrawerLayout drawerLayout;
    public static FragmentManager fragmentManager;
    NavigationDrawerFragment navigationDrawerFragment;

    //recyler
    @BindView(R.id.rv_news_list)
    RecyclerView rv_news_list;
    NewsAdapter mNewsAdapter;
    static ArrayList<NewsModel> newsArrayList;
    @BindView(R.id.pullToRefresh)
    SwipeRefreshLayout pullToRefresh;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ProgressBar progressBar;

    private MainContract.presenter presenter;

    Toaster toaster;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        setSupportActionBar(toolbar);
        initializeToolbarAndRecyclerView();
        initProgressBar();

        presenter = new MainPresenterImpl(this, new GetNewsInteractorImpl());
        presenter.requestDataFromServer();

        fragmentManager.beginTransaction().replace(R.id.nav_view, navigationDrawerFragment).commit();

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onRefreshButtonClick();
                pullToRefresh.setRefreshing(false);
            }
        });

    }

    private void init(){
        toaster = new Toaster(this);
        drawerLayout = findViewById(R.id.drawerLayout);
        fragmentManager = getSupportFragmentManager();
        newsArrayList = new ArrayList<>();
        navigationDrawerFragment = new NavigationDrawerFragment();
    }

    private void initializeToolbarAndRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rv_news_list.setLayoutManager(layoutManager);


    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }







    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(NewsModel news) {

//            toaster.makeToast("List title:  " + news.getTitle());
            Intent i = new Intent(MainActivity.this, NewsDetailsActivity.class);
            i.putExtra("NewsModel",news);
            startActivity(i);

        }
    };



    public void openDrawer(View view) {
        drawerLayout.openDrawer(Gravity.START);
    }

    public void startSearch(View view) {
        if (newsArrayList.size()>0) {
            Intent i = new Intent(MainActivity.this, SearchActivity.class);
            i.putParcelableArrayListExtra("newsArray", newsArrayList);
            startActivity(i);
            Log.e("newsArraySize",newsArrayList.size()+"");
        }else {
            Log.e("newsArraySize","zero");
        }

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    //MainView Interface Methods
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<NewsModel> mNewsArrayList) {
        newsArrayList = mNewsArrayList;

        mNewsAdapter = new NewsAdapter(this, mNewsArrayList , recyclerItemClickListener);
        rv_news_list.setAdapter(mNewsAdapter);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        toaster.makeToast("Something went wrong...Error message: " + throwable.getMessage());
    }
}
