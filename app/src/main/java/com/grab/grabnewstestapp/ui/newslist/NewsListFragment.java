package com.grab.grabnewstestapp.ui.newslist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.grab.grabnewstestapp.R;
import com.grab.grabnewstestapp.data.model.News;
import com.grab.grabnewstestapp.ui.MainActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class NewsListFragment extends DaggerFragment {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private NewsListViewModel viewModel;
    private MainActivity activityReference;
    private RecyclerView recyclerview;
    private ProgressBar progressBar;


    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            activityReference = (MainActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.news_list_fragment, container, false);
        activityReference.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        activityReference.getSupportActionBar().setTitle(getString(R.string.newslist));
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel.class);
        recyclerview = rootView.findViewById(R.id.recyclerview);
        progressBar = rootView.findViewById(R.id.progressBar);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        NewsListAdapter adapter = new NewsListAdapter(activityReference);
        recyclerview.setLayoutManager(new LinearLayoutManager(activityReference));
        recyclerview.setAdapter(adapter);
        progressBar.setVisibility(View.VISIBLE);
        viewModel.itemPagedList.observe(this, new Observer<PagedList<News>>() {
            @Override
            public void onChanged(@Nullable PagedList<News> news) {
                adapter.submitList(news);
                recyclerview.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });

        GridLayoutManager manager = new GridLayoutManager(getActivity(), viewModel.getSpanSize(getActivity().getResources().getConfiguration().orientation));
        manager.setSpanSizeLookup(viewModel.spanSizeLookup);

        recyclerview.setLayoutManager(manager);
    }
}
