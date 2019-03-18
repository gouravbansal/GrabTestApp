package com.grab.grabnewstestapp.di;

import android.app.Application;

import com.grab.grabnewstestapp.NewsApp;
import com.grab.grabnewstestapp.db.AppDatabase;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class,
        NetworkModule.class,
        ActivityBuilder.class,
        RoomModule.class,
        RepositoryModule.class,
        ViewModelModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        @BindsInstance
        Builder appDatabase(AppDatabase appDatabase);

        ApplicationComponent build();
    }

    void inject(NewsApp app);

    @Override
    void inject(DaggerApplication instance);
}
