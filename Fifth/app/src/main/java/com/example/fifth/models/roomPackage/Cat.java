package com.example.fifth.models.roomPackage;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cat {
    @PrimaryKey(autoGenerate = true)
    public int catId;
    @ColumnInfo (name = "cat_name")
    public String catName;

    public Cat(String catName) {
        this.catName = catName;
    }

    @Override
    public String toString() {
        return catName + " " + catId;
    }
}
