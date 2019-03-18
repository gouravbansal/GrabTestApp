package com.grab.grabnewstestapp.di;

import android.content.Context;
import android.net.NetworkInfo;
import android.util.Log;

import com.grab.grabnewstestapp.data.NewsServices;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class NetworkModule {

    private static long cacheSize = 5 * 1024 * 1024;
    private static final String BASE_URL = "https://newsapi.org/";
    private static final String HEADER_CACHE_CONTROL = "Cache-Control";
    private static final String HEADER_PRAGMA = "Pragma";

    @Singleton
    @Provides
    NewsServices provideNewsApi(Retrofit retrofit) {
        return retrofit.create(NewsServices.class);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Context context) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(logging)
                .addNetworkInterceptor(provideCacheInterceptor(context))
                .build();

        return new Retrofit.Builder().baseUrl(BASE_URL).client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Singleton
    @Provides
    Interceptor provideCacheInterceptor(Context context) {
        return chain -> {
            Response response = chain.proceed(chain.request());
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(2, TimeUnit.MINUTES)
                    .build();

            return response.newBuilder()
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .removeHeader(HEADER_PRAGMA)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build();

        };
    }


}


