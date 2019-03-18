package com.grab.grabnewstestapp.ui.newslist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.grab.grabnewstestapp.data.model.News;
import com.grab.grabnewstestapp.data.model.NewsDetails;
import com.grab.grabnewstestapp.data.repository.NewsRepository;
import com.grab.grabnewstestapp.db.NewsDao;
import com.grab.grabnewstestapp.db.NewsEntity;
import com.grab.grabnewstestapp.util.CommonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class ItemDataSource extends PageKeyedDataSource<Integer, News> {
    private NewsRepository repo;
    private Context context;
    private NewsDao newsDao;
    private static final int FIRST_PAGE = 1;
    private static final int INTIAL_COUNT = 21;
    private static final int NEXT_COUNT = 20;
    private static final String COUNTRY = "in";
    private static final String CATEORY = "business";
    private static final String API_KEY = "fc367f85268247f38bd19d7c64cf69ed";
    private int count = 0;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public MutableLiveData<Throwable> errorData = new MutableLiveData<Throwable>();


    @Inject
    public ItemDataSource(NewsRepository repo, Context context, NewsDao newsDao) {
        this.repo = repo;
        this.context = context;
        this.newsDao = newsDao;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, News> callback) {
        try {
            if (CommonUtil.isNetworkStatusAvailable(context)) {
                compositeDisposable.add(repo.getNewsList(COUNTRY, CATEORY, String.valueOf(INTIAL_COUNT), String.valueOf(FIRST_PAGE), API_KEY).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                        subscribeWith(new DisposableSingleObserver<NewsDetails>() {

                            @Override
                            public void onSuccess(NewsDetails newsDetails) {
                                if (newsDetails != null && newsDetails.articles != null && newsDetails.articles.size() > 0) {
                                    Integer next = FIRST_PAGE + 1;
                                    callback.onResult(newsDetails.articles, null, next);
                                    for (int i = 0; i < newsDetails.articles.size(); i++) {
                                        News item = newsDetails.articles.get(i);
                                        newsDao.insert(new NewsEntity(count + i, item.title, item.content, item.publishedAt, item.urlToImage, item.author));
                                    }
                                    count = newsDetails.articles.size() + count;
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                errorData.postValue(e);

                            }
                        }));
            } else {
                List<NewsEntity> newsEntityList = newsDao.getAllNews();
                if (newsEntityList != null && newsEntityList.size() > 0) {
                    ArrayList<News> list = new ArrayList<News>();
                    for (NewsEntity newsEntity : newsEntityList) {
                        list.add(new News(newsEntity.title, newsEntity.content, newsEntity.publishedAt, newsEntity.urlToImage, newsEntity.author));
                    }
                    Integer next = null;
                    next = 1;
                    callback.onResult(list, null, next);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            compositeDisposable.clear();
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, News> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, News> callback) {
        try {
            if (CommonUtil.isNetworkStatusAvailable(context)) {
                compositeDisposable.add(repo.getNewsList(COUNTRY, CATEORY, String.valueOf(NEXT_COUNT), String.valueOf(params.key), API_KEY).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
                        subscribeWith(new DisposableSingleObserver<NewsDetails>() {

                            @Override
                            public void onSuccess(NewsDetails newsDetails) {
                                if (newsDetails != null) {
                                    Integer next = params.key + 1;
                                    callback.onResult(newsDetails.articles, next);
                                    for (int i = 0; i < newsDetails.articles.size(); i++) {
                                        News item = newsDetails.articles.get(i);
                                        newsDao.insert(new NewsEntity(count + i, item.title, item.content, item.publishedAt, item.urlToImage, item.author));
                                    }
                                    count = newsDetails.articles.size() + count;
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                errorData.postValue(e);
                            }
                        }));
            } else {
                List<NewsEntity> newsEntityList = newsDao.getAllNews();
                if (newsEntityList != null && newsEntityList.size() > 0) {
                    ArrayList<News> list = new ArrayList<News>();
                    for (NewsEntity newsEntity : newsEntityList) {
                        list.add(new News(newsEntity.title, newsEntity.content, newsEntity.publishedAt, newsEntity.urlToImage, newsEntity.author));
                    }
                    Integer next = null;
                    next = 1;
                    callback.onResult(list, next);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            compositeDisposable.clear();
        }
    }


}
