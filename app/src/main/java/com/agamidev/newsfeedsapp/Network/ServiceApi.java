package com.agamidev.newsfeedsapp.Network;

import com.agamidev.newsfeedsapp.Models.MainModel;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {

    @GET("articles")
    Call<MainModel> getNews(
            @Query("source") String source,
            @Query("apiKey") String apiKey
    );

}
