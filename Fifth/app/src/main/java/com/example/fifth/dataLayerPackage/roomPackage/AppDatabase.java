package com.example.fifth.dataLayerPackage.roomPackage;

import androidx.room.Database;

@Database(entities = {Cat.class}, version = 1)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    public abstract CatDao catDao();
}
