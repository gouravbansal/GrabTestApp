package com.grab.grabnewstestapp.ui.newslist;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.grab.grabnewstestapp.data.model.News;

import javax.inject.Inject;

public class ItemDataSourceFactory extends DataSource.Factory<Integer, News> {
    private MutableLiveData itemLiveDataSource = new MutableLiveData<PageKeyedDataSource<Integer, News>>();


    private ItemDataSource itemDataSource;

    @Inject
    public ItemDataSourceFactory(ItemDataSource itemDataSource) {
        this.itemDataSource = itemDataSource;
    }

    @Override
    public DataSource<Integer, News> create() {
        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, News>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }

    public MutableLiveData<Throwable> getErrorDataSource() {
        return itemDataSource.errorData;
    }
}
