package com.grab.grabnewstestapp.data.repository;

import com.grab.grabnewstestapp.data.model.NewsDetails;

import io.reactivex.Single;
import retrofit2.Call;

 public interface NewsRepository {
    Single <NewsDetails> getNewsList(String country, String category, String pageSize, String page, String apiKey);
}
