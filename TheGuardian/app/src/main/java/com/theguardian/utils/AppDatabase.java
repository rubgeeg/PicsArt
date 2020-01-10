package com.theguardian.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.theguardian.dao.ResponseDao;
import com.theguardian.models.Fields;
import com.theguardian.models.Response;
import com.theguardian.models.Result;

@Database(entities = {Result.class, Response.class}, version = 2, exportSchema = false)
@TypeConverters({DBConverters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract ResponseDao responseDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "response-database")
                            .allowMainThreadQueries() // Hayk M., is this good practise ?
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
