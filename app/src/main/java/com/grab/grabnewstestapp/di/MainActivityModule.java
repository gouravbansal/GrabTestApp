package com.grab.grabnewstestapp.di;

import com.grab.grabnewstestapp.ui.newsdetails.NewDetailsFragment;
import com.grab.grabnewstestapp.ui.newslist.NewsListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract NewsListFragment contributeNewsListFragmentInjector();

    @ContributesAndroidInjector
    abstract NewDetailsFragment contributeNewsDetailFragmentInjector();


}
