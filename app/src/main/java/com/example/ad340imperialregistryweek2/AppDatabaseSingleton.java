package com.example.ad340imperialregistryweek2;

import android.content.Context;

import androidx.room.Room;

public class AppDatabaseSingleton {

    private static AppDatabase db;

    public static AppDatabase getDatabase(Context context) {
        if(db == null) {
            db = Room.databaseBuilder(context,
                    AppDatabase.class, "sample-database")
                    //.addMigrations(MIGRATION_2_3)
                    .build();
        }

        return db;
    }

}