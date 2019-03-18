package com.grab.grabnewstestapp.ui.newsdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.grab.grabnewstestapp.R;
import com.grab.grabnewstestapp.ui.MainActivity;

import dagger.android.support.DaggerFragment;

public class NewDetailsFragment extends DaggerFragment {

    public static final String URL_TO_LOAD = "urlLoad";

    public static NewDetailsFragment getInstance(String url) {
        NewDetailsFragment newDetailsFragment = new NewDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(URL_TO_LOAD, url);
        newDetailsFragment.setArguments(bundle);
        return newDetailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.newsdetails_fragment, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.news_details));

        ProgressBar progressBar = rootView.findViewById(R.id.progressBar_detail);
        WebView webView = rootView.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.loadUrl(getArguments().getString(URL_TO_LOAD));
        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                // do your stuff here
            }
        });

        return rootView;
    }
}
