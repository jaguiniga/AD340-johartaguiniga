package com.example.ad340imperialregistryweek2;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;



public class UserViewModel extends ViewModel {

    public LiveData<List<User>> loadAllByIds(Context context, String[] emailIds) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        return db.userDao().loadAllByIds(emailIds);
    }

    public void updateUsers(Context context, User... users) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.userDao().updateUsers(users);
        });
    }

    public void insertAll(Context context, User... users) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.userDao().insertAll(users);
        });
    }
}