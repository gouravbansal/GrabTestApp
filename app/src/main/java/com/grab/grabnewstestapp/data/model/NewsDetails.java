package com.grab.grabnewstestapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class NewsDetails  implements Parcelable {
    public ArrayList<News> articles;
    public int totalResults;
    public String status;

    protected NewsDetails(Parcel in) {
        articles = in.createTypedArrayList(News.CREATOR);
        totalResults = in.readInt();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(articles);
        dest.writeInt(totalResults);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewsDetails> CREATOR = new Creator<NewsDetails>() {
        @Override
        public NewsDetails createFromParcel(Parcel in) {
            return new NewsDetails(in);
        }

        @Override
        public NewsDetails[] newArray(int size) {
            return new NewsDetails[size];
        }
    };

    public ArrayList<News> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<News> articles) {
        this.articles = articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
