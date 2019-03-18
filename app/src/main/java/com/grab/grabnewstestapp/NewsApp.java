package com.grab.grabnewstestapp;

import android.arch.persistence.room.Room;

import com.grab.grabnewstestapp.db.AppDatabase;
import com.grab.grabnewstestapp.di.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class NewsApp extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {

        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "news-db").allowMainThreadQueries().build();
        return DaggerApplicationComponent.builder().application(this).appDatabase(appDatabase).build();
    }
}
