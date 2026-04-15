package com.example.myapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Product.class}, version = 1)
public abstract class ProductDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

    private static ProductDatabase instance;

    public static ProductDatabase getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    ProductDatabase.class,
                    "product_database"
            ).allowMainThreadQueries().build();
        }
        return instance;
    }
}