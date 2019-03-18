package com.grab.grabnewstestapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {
    public Source source;
    public String title;
    public String content;
    public String publishedAt;
    public String urlToImage;
    public String author;
    public String url;
    public String description;

    public News(String title, String content, String publishedAt, String urlToImage, String author) {
        this.title = title;
        this.content = content;
        this.publishedAt = publishedAt;
        this.urlToImage = urlToImage;
        this.author = author;
    }

    protected News(Parcel in) {
        title = in.readString();
        content = in.readString();
        publishedAt = in.readString();
        urlToImage = in.readString();
        author = in.readString();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(publishedAt);
        dest.writeString(urlToImage);
        dest.writeString(author);
    }
    public static class Source {
        public String id;
        public String name;

    }
}
