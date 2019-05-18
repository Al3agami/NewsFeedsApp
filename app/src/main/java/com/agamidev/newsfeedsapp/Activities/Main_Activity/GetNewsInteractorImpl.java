package com.agamidev.newsfeedsapp.Activities.Main_Activity;

import android.util.Log;

import com.agamidev.newsfeedsapp.Models.MainModel;
import com.agamidev.newsfeedsapp.Network.RetrofitWeb;
import com.agamidev.newsfeedsapp.Network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNewsInteractorImpl implements MainContract.GetNewsInteractor {
    @Override
    public void getNewsArrayList(final OnFinishedListener onFinishedListener) {
        RetrofitWeb.getClient().create(ServiceApi.class).getNews("the-next-web","533af958594143758318137469b41ba9").enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("ok")){
                        Log.e("getNewsData",response.body().getArticles().toString());
                        if (response.body().getArticles().size()>0){
                            onFinishedListener.onFinished(response.body().getArticles());
                        }else {
                            Log.e("getNewsStatus","No News");
                        }
                    }else {
                        Log.e("getNewsStatus","Result is not ok");
                    }
                } else {
                    Log.e("getNewsStatus","Body is Null");
                }
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                Log.e("getNewsStatus", t.toString());
                onFinishedListener.onFailure(t);
            }
        });

    }
}
