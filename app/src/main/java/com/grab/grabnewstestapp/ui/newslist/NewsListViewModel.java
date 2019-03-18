package com.grab.grabnewstestapp.ui.newslist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;
import android.content.res.Configuration;
import android.support.v7.widget.GridLayoutManager;

import com.grab.grabnewstestapp.data.model.News;
import com.grab.grabnewstestapp.db.NewsDao;

import javax.inject.Inject;

public class NewsListViewModel extends ViewModel {

    private int maxCountPerRow = 3;
    public LiveData<PagedList<News>> itemPagedList;

    @Inject
    public NewsListViewModel(ItemDataSourceFactory itemDataSourceFactory) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(1000)
                .build();

        itemPagedList = new LivePagedListBuilder(itemDataSourceFactory, config).build();
    }

    GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {

        @Override
        public int getSpanSize(int position) {
            if (position % 7 == 0) {
                return maxCountPerRow;
            } else {
                return 1;
            }
        }
    };

    public int getSpanSize(int orientation) {
        maxCountPerRow = 3;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            maxCountPerRow = 2;
        }
        return maxCountPerRow;
    }

}
