package com.grab.grabnewstestapp.data.repository;

import com.grab.grabnewstestapp.data.NewsServices;
import com.grab.grabnewstestapp.data.model.NewsDetails;

import io.reactivex.Single;
import retrofit2.Call;

public class NewsRespsitoryImplementation implements NewsRepository {

    private NewsServices newsServices;
    public NewsRespsitoryImplementation(NewsServices newsServices){
        this.newsServices = newsServices;
    }

    @Override
    public Single<NewsDetails> getNewsList(String country, String category, String pageSize, String page, String apiKey) {
        return newsServices.getNewsList(country,category,pageSize,page,apiKey);
    }
}
