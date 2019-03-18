package com.grab.grabnewstestapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insertAll(List<NewsEntity> news);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NewsEntity news);

    @Update
    void update(NewsEntity news);


    @Query("Select * FROM news")
    List<NewsEntity> getAllNews();

    @Query("DELETE FROM news")
    void deleteAll();

}
