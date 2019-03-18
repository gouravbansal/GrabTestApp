package com.grab.grabnewstestapp.data;

import com.grab.grabnewstestapp.data.model.NewsDetails;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsServices {
    @GET("v2/top-headlines")
    Single<NewsDetails> getNewsList(@Query("country")  String country, @Query("category")  String cat, @Query("pageSize")  String var3, @Query("page")  String page, @Query("apiKey")  String key);

}
