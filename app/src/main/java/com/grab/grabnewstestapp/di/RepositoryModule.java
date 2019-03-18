package com.grab.grabnewstestapp.di;

import com.grab.grabnewstestapp.data.NewsServices;
import com.grab.grabnewstestapp.data.repository.NewsRepository;
import com.grab.grabnewstestapp.data.repository.NewsRespsitoryImplementation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class RepositoryModule {

    @Provides
    NewsRepository provideNewsRepo(NewsServices newsServices) {
        return new NewsRespsitoryImplementation(newsServices);
    }
}
