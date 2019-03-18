package com.grab.grabnewstestapp.di;

import com.grab.grabnewstestapp.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity contributeHomeInjector();

}
