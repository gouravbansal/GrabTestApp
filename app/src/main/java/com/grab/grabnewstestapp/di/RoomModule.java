package com.grab.grabnewstestapp.di;

import com.grab.grabnewstestapp.db.AppDatabase;
import com.grab.grabnewstestapp.db.NewsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @Singleton
    @Provides
    NewsDao providesArticleDao(AppDatabase appDb) {
        return appDb.newsDao();
    }
}
