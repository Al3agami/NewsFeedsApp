package com.agamidev.newsfeedsapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.agamidev.newsfeedsapp.Models.NewsModel;
import com.agamidev.newsfeedsapp.Utils.NewsDate;
import com.agamidev.newsfeedsapp.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailsActivity extends AppCompatActivity {

    //Views
    @BindView(R.id.iv_image)
    ImageView iv_image;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_author)
    TextView tv_author;
    @BindView(R.id.tv_description)
    TextView tv_description;
    @BindView(R.id.btn_open_website)
    Button btn_open_website;

    //Models
    NewsModel myModel;

    ArrayList<NewsModel> newsArrayList;

    private static final String TAG = NewsDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        ButterKnife.bind(this);

        init();
    }

    public void init(){
        newsArrayList = new ArrayList<>();
        newsArrayList = MainActivity.newsArrayList;

        if (getIntent().getExtras() != null) {
            myModel = (NewsModel) getIntent().getExtras().get("NewsModel");
        }else {
            Log.e(TAG,"Null Intent Data");
        }
        if (myModel != null) {
            fillView(myModel);
        }else {
            Log.e(TAG+": myModel", null);
        }
    }

    private void fillView(NewsModel myModel){

        Glide.with(this)
                .load(myModel.getUrlToImage())
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)
                        .fitCenter())
                .into(iv_image);

        tv_date.setText(NewsDate.getFinalDate(myModel));
        tv_title.setText(myModel.getTitle());
        tv_author.setText("By " + myModel.getAuthor());
        tv_description.setText(myModel.getDescription());


    }

    public void goBack(View view) {
        onBackPressed();
    }

    public void startSearch(View view) {
        Intent i = new Intent(NewsDetailsActivity.this, SearchActivity.class);
        i.putParcelableArrayListExtra("newsArray",newsArrayList);
        startActivity(i);
    }

    public void openOnWebsite(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(myModel.getUrl()));
        startActivity(intent);
    }
}
