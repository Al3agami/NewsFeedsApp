package com.agamidev.newsfeedsapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.agamidev.newsfeedsapp.Adapters.NewsAdapter;
import com.agamidev.newsfeedsapp.Activities.Main_Activity.MainContract;
import com.agamidev.newsfeedsapp.Models.NewsModel;
import com.agamidev.newsfeedsapp.R;
import com.agamidev.newsfeedsapp.Interfaces.RecyclerItemClickListener;
import com.agamidev.newsfeedsapp.Widget.Toaster;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {


    ArrayList<NewsModel> newsArrayList;
    NewsAdapter mNewsAdapter;

    public Toaster toaster;

    @BindView(R.id.rv_news_list)
    RecyclerView rv_news_list;

    @BindView(R.id.et_search)
    EditText et_search;

    Boolean isFiltered = false;


    private MainContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        toaster = new Toaster(this);


        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("SearchString",s.toString());
                mNewsAdapter.getFilter().filter(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("afterTextChanged",s.toString());
                if(s.toString().length() == 0){

                }
            }
        });

        newsArrayList = new ArrayList<>();

        if (getIntent().getExtras() != null){
            newsArrayList = getIntent().getExtras().getParcelableArrayList("newsArray");
            Log.e("newwwwwws",newsArrayList.toString());

            fillRecycler(newsArrayList);
        }else {
            toaster.makeToast("Cannot Load News!");
        }



    }
    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(NewsModel news) {
            toaster.makeToast("List title:  " + news.getTitle());
            Intent i = new Intent(SearchActivity.this, NewsDetailsActivity.class);
            i.putExtra("NewsModel",news);
            startActivity(i);

        }
    };

    public void fillRecycler(ArrayList<NewsModel> arrayList){
        mNewsAdapter = new NewsAdapter(SearchActivity.this,arrayList,recyclerItemClickListener);
        LinearLayoutManager newsLayoutManager = new LinearLayoutManager(SearchActivity.this);
        rv_news_list.setLayoutManager(newsLayoutManager);
        rv_news_list.setItemAnimator(new DefaultItemAnimator());
        rv_news_list.setAdapter(mNewsAdapter);
    }

    public void btnCancelSearchListener(View view) {
        et_search.setText("");
        mNewsAdapter.getFilter().filter("");
    }

    public void goBack(View view) {
        onBackPressed();
    }
}
