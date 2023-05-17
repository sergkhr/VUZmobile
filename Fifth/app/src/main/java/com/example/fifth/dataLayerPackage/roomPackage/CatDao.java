package com.example.fifth.dataLayerPackage.roomPackage;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CatDao {
    @Query("SELECT * FROM cat")
    List<Cat> getAll();
    @Query("SELECT * FROM cat WHERE catId IN (:catIds)")
    List<Cat> loadAllByIds(int[] catIds);
    @Query("SELECT * FROM cat WHERE cat_name LIKE :catName LIMIT 1")
    Cat findByName(String catName);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Cat... cats);
    @Delete
    void delete(Cat cat);
}
