package com.grab.grabnewstestapp;

import android.content.Context;

import com.grab.grabnewstestapp.data.repository.NewsRepository;
import com.grab.grabnewstestapp.db.NewsDao;
import com.grab.grabnewstestapp.ui.newslist.ItemDataSource;
import com.grab.grabnewstestapp.ui.newslist.ItemDataSourceFactory;
import com.grab.grabnewstestapp.ui.newslist.NewsListViewModel;

import org.hamcrest.core.IsNull;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class NewsListViewModelTest {

    @Mock
    private NewsRepository repo;
    @Mock
    private Context context;
    @Mock
    private NewsDao newsDao;

    private NewsListViewModel newsListViewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ItemDataSource itemDataSource = new ItemDataSource(repo, context, newsDao);
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory(itemDataSource);
        newsListViewModel = new NewsListViewModel(itemDataSourceFactory);
    }

    @Test
    public void testNull() {
        Assert.assertThat(newsListViewModel.itemPagedList, IsNull.notNullValue());
    }


    @Test
    public void getSpanSize() {
        Assert.assertEquals(newsListViewModel.getSpanSize(1), 2);
        Assert.assertEquals(newsListViewModel.getSpanSize(0), 3);
    }
}